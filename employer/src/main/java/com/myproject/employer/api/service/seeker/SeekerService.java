package com.myproject.employer.api.service.seeker;

import com.myproject.employer.api.dto.in.PageDtoIn;
import com.myproject.employer.api.dto.in.seeker.SeekerDtoIn;
import com.myproject.employer.api.dto.in.seeker.UpdateSeekerDtoIn;
import com.myproject.employer.api.dto.out.PageDtoOut;
import com.myproject.employer.api.dto.out.seeker.SeekerDtoOut;
import org.springframework.stereotype.Service;

@Service
public interface SeekerService {
    PageDtoOut<SeekerDtoOut> list(PageDtoIn pageDtoIn);
    SeekerDtoOut get(Long id);
    SeekerDtoOut create(SeekerDtoIn seekerDtoIn);
    SeekerDtoOut update(Long id, UpdateSeekerDtoIn updateSeekerDtoIn);
    void delete(Long id);
}
