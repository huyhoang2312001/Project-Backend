package com.myproject.employer.api.service;

import com.myproject.employer.api.dto.in.EmployerDtoIn;
import com.myproject.employer.api.dto.in.PageDtoIn;
import com.myproject.employer.api.dto.in.UpdateEmployerDtoIn;
import com.myproject.employer.api.dto.out.EmployerDtoOut;
import com.myproject.employer.api.dto.out.PageDtoOut;
import com.myproject.employer.api.entity.jpa.Employers;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface EmployerService {
PageDtoOut<EmployerDtoOut> list(PageDtoIn pageDtoIn);
    EmployerDtoOut get(Long id);
    EmployerDtoOut create(EmployerDtoIn employerDtoIn);
    EmployerDtoOut update(Long id, UpdateEmployerDtoIn updateEmployerDtoIn);
    void delete(Long id);

}
