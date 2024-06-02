package com.myproject.employer.api.service.employer;

import com.myproject.employer.api.dto.in.employer.EmployerDtoIn;
import com.myproject.employer.api.dto.in.PageDtoIn;
import com.myproject.employer.api.dto.in.employer.UpdateEmployerDtoIn;
import com.myproject.employer.api.dto.out.employer.EmployerDtoOut;
import com.myproject.employer.api.dto.out.PageDtoOut;
import org.springframework.stereotype.Service;

@Service
public interface EmployerService {
PageDtoOut<EmployerDtoOut> list(PageDtoIn pageDtoIn);
    EmployerDtoOut get(Long id);
    EmployerDtoOut create(EmployerDtoIn employerDtoIn);
    EmployerDtoOut update(Long id, UpdateEmployerDtoIn updateEmployerDtoIn);
    void delete(Long id);

}
