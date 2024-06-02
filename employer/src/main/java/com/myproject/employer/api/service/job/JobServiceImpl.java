package com.myproject.employer.api.service.job;

import com.myproject.employer.api.dto.in.PageDtoIn;
import com.myproject.employer.api.dto.in.job.JobDtoIn;
import com.myproject.employer.api.dto.in.job.UpdateJobDtoIn;
import com.myproject.employer.api.dto.out.PageDtoOut;
import com.myproject.employer.api.dto.out.job.JobDtoOut;
import com.myproject.employer.api.entity.jpa.Job;
import com.myproject.employer.api.repository.job.JobRepository;
import com.myproject.employer.common.Common;
import com.myproject.employer.common.errorcode.ErrorCode;
import com.myproject.employer.common.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService{
    private final JobRepository jobRepository;
    @Autowired

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    @Override
    public PageDtoOut<JobDtoOut> list(PageDtoIn pageDtoIn){
        Page<Job> job = this.jobRepository.findAll(PageRequest.of(
                        pageDtoIn.getPage() -1 ,
                        pageDtoIn.getPageSize(),
                        Sort.by("id").ascending()));
        return PageDtoOut.from(pageDtoIn.getPage(),
                pageDtoIn.getPageSize(),
                job.getTotalElements(),
                job.stream().map(JobDtoOut::from).toList());
    }
    @Override
    public JobDtoOut get(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.BAD_REQUEST, "Job found id"));
        return JobDtoOut.from(job);
    }

    @Override
    public JobDtoOut create(JobDtoIn jobDtoIn) {
        jobRepository.findByEmployer_id(jobDtoIn.getEmployer_id()).ifPresent( job -> { new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Job already exist");});
        Job job = jobRepository.save(Job.builder()
                        .employer_id(jobDtoIn.getEmployer_id())
                        .title(jobDtoIn.getTitle())
                        .quantity(jobDtoIn.getQuantity())
                        .build());

        return JobDtoOut.from(job);
    }

    @Override
    public JobDtoOut update(Long id, UpdateJobDtoIn updateJobDtoIn) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.BAD_REQUEST, "Job not found"));
        job.setSalary(updateJobDtoIn.getSalary());
        job.setUpdated_at(Common.currentTime());
        job = jobRepository.save(job);
        return JobDtoOut.from(job);
    }

    @Override
    public void delete(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.BAD_REQUEST, "Job not found"));
        jobRepository.delete(job);

    }
}
