package com.myproject.employer.api.service.employer;

import com.myproject.employer.api.dto.in.employer.EmployerDtoIn;
import com.myproject.employer.api.dto.in.PageDtoIn;
import com.myproject.employer.api.dto.in.employer.UpdateEmployerDtoIn;
import com.myproject.employer.api.dto.out.employer.EmployerDtoOut;
import com.myproject.employer.api.dto.out.PageDtoOut;
import com.myproject.employer.api.entity.jpa.Employers;
import com.myproject.employer.api.repository.Employer.EmployerRepository;
import com.myproject.employer.common.Common;
import com.myproject.employer.common.errorcode.ErrorCode;
import com.myproject.employer.common.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

@Service
public class EmployerServiceImpl implements EmployerService {
    private final EmployerRepository employerRepository;
    @Autowired
    public EmployerServiceImpl (EmployerRepository employerRepository){

        this.employerRepository = employerRepository;
    }
    @Override
    public PageDtoOut<EmployerDtoOut> list(PageDtoIn pageDtoIn) {
        Page<Employers> employers = this.employerRepository.findAll(PageRequest.of(
                pageDtoIn.getPage() -1 ,
                pageDtoIn.getPageSize(),
                Sort.by("id").ascending()));
        return PageDtoOut.from(pageDtoIn.getPage(),
                pageDtoIn.getPageSize(),
                employers.getTotalElements(),
                employers.stream().map(EmployerDtoOut::from).toList());
    }
    public EmployerDtoOut get(Long id) {
        Employers employers = employerRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "employer not found"));
        return EmployerDtoOut.from(employers);
    }

    @Override
    public EmployerDtoOut create(EmployerDtoIn employerDtoIn) {
        employerRepository.findByEmail(employerDtoIn.getEmail()).ifPresent( employers -> {throw new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "email already existed");
                }
        );
        Employers employers = employerRepository.save(Employers.builder()
                        .email(employerDtoIn.getEmail())
                        .name(employerDtoIn.getName())
                        .build());
        return EmployerDtoOut.from(employers);
    }

    @Override
    public EmployerDtoOut update(Long id, UpdateEmployerDtoIn updateEmployerDtoIn) {
            Employers employers = employerRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "employer not found"));
            employers.setName(updateEmployerDtoIn.getName());
            employers.setUpdatedDate(Common.currentTime());
            employers = employerRepository.save(employers);
        return EmployerDtoOut.from(employers);
    }

    @Override
    public void delete(Long id) {
        Employers employers = employerRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "employer not found"));
        employerRepository.delete(employers);

    }
}
