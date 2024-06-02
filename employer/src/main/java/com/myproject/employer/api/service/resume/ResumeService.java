package com.myproject.employer.api.service.resume;

import com.myproject.employer.api.dto.in.PageDtoIn;
import com.myproject.employer.api.dto.in.resume.ResumeDtoIn;
import com.myproject.employer.api.dto.in.resume.UpdateResumeDtoIn;
import com.myproject.employer.api.dto.out.PageDtoOut;
import com.myproject.employer.api.dto.out.resume.ResumeDtoOut;
import com.myproject.employer.api.entity.jpa.Resume;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ResumeService {
    PageDtoOut<ResumeDtoOut> list(PageDtoIn pageDtoIn);
    ResumeDtoOut create(ResumeDtoIn resumeDtoIn);
    ResumeDtoOut update(Long id, UpdateResumeDtoIn updateResumeDtoIn);
    ResumeDtoOut get(Long id);
    void delete(Long id);
}
