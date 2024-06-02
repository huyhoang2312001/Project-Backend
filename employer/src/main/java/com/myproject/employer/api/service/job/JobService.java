package com.myproject.employer.api.service.job;

import com.myproject.employer.api.dto.in.PageDtoIn;
import com.myproject.employer.api.dto.in.job.JobDtoIn;
import com.myproject.employer.api.dto.in.job.UpdateJobDtoIn;
import com.myproject.employer.api.dto.out.PageDtoOut;
import com.myproject.employer.api.dto.out.job.JobDtoOut;
import org.springframework.stereotype.Service;

@Service
public interface JobService {
    PageDtoOut<JobDtoOut> list(PageDtoIn pageDtoIn);

    JobDtoOut get(Long id);
    JobDtoOut create(JobDtoIn jobDtoIn);
    JobDtoOut update(Long id, UpdateJobDtoIn updateJobDtoIn);
    void delete(Long id);

}
