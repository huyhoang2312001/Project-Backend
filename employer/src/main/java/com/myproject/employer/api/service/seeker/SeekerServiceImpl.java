package com.myproject.employer.api.service.seeker;

import com.myproject.employer.api.dto.in.PageDtoIn;
import com.myproject.employer.api.dto.in.seeker.SeekerDtoIn;
import com.myproject.employer.api.dto.in.seeker.UpdateSeekerDtoIn;
import com.myproject.employer.api.dto.out.PageDtoOut;
import com.myproject.employer.api.dto.out.seeker.SeekerDtoOut;
import com.myproject.employer.api.entity.jpa.Seeker;
import com.myproject.employer.api.repository.seeker.SeekerRepository;
import com.myproject.employer.common.Common;
import com.myproject.employer.common.errorcode.ErrorCode;
import com.myproject.employer.common.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class SeekerServiceImpl implements SeekerService{

    private final SeekerRepository seekerRepository;
    @Autowired
    public SeekerServiceImpl(SeekerRepository seekerRepository){
        this.seekerRepository = seekerRepository;
    }

    @Override
    public PageDtoOut<SeekerDtoOut> list(PageDtoIn pageDtoIn) {
        Page<Seeker> seekers = this.seekerRepository.findAll(PageRequest.of(
                pageDtoIn.getPage() - 1,
                pageDtoIn.getPageSize(),
                Sort.by("id").ascending()));
        return PageDtoOut.from(pageDtoIn.getPage(),
                pageDtoIn.getPageSize(),
                seekers.getTotalElements(),
                seekers.stream().map(SeekerDtoOut::from).toList());
    }

    @Override
    public SeekerDtoOut get(Long id) {
        Seeker seeker = seekerRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.BAD_REQUEST, "not found seeker"));


        return SeekerDtoOut.from(seeker);
    }

    @Override
    public SeekerDtoOut create(SeekerDtoIn seekerDtoIn) {
        seekerRepository.findByName(seekerDtoIn.getName()).ifPresent(seeker -> {throw new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "seeker already exist");
        });
        Seeker seeker = seekerRepository.save(Seeker.builder()
                        .name(seekerDtoIn.getName())
                        .address(seekerDtoIn.getAddress())
                        .birthday(seekerDtoIn.getBirthday())
                        .province(seekerDtoIn.getProvinceId())
                        .build());
        return SeekerDtoOut.from(seeker);
    }

    @Override
    public SeekerDtoOut update(Long id, UpdateSeekerDtoIn updateSeekerDtoIn) {
        Seeker seeker = seekerRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.BAD_REQUEST, "not found seeker"));
        seeker.setName(updateSeekerDtoIn.getName());
        seeker.setUpdated_at(Common.currentTime());
        seeker = seekerRepository.save(seeker);
        return SeekerDtoOut.from(seeker);
    }

    @Override
    public void delete(Long id) {
        Seeker seeker = seekerRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.BAD_REQUEST, "not found seeker"));
        seekerRepository.delete(seeker);

    }
}
