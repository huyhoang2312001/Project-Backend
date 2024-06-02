package com.myproject.employer.api.service.resume;

import com.myproject.employer.api.dto.in.PageDtoIn;
import com.myproject.employer.api.dto.in.resume.ResumeDtoIn;
import com.myproject.employer.api.dto.in.resume.UpdateResumeDtoIn;
import com.myproject.employer.api.dto.out.PageDtoOut;
import com.myproject.employer.api.dto.out.resume.ResumeDtoOut;
import com.myproject.employer.api.entity.jpa.Resume;
import com.myproject.employer.api.repository.resume.ResumeRepository;
import com.myproject.employer.common.Common;
import com.myproject.employer.common.errorcode.ErrorCode;
import com.myproject.employer.common.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl implements ResumeService{

    private final ResumeRepository resumeRepository;
    @Autowired

    public ResumeServiceImpl(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Override
    public PageDtoOut<ResumeDtoOut> list(PageDtoIn pageDtoIn) {
        Page<Resume> resume = this.resumeRepository.findAll(PageRequest.of(
                pageDtoIn.getPage() -1,
                pageDtoIn.getPageSize(),
                Sort.by("id").ascending()));
        return PageDtoOut.from(pageDtoIn.getPage(),
                pageDtoIn.getPageSize(),
                resume.getTotalElements(),
                resume.stream().map(ResumeDtoOut::from).toList());
    }

    @Override
    public ResumeDtoOut create(ResumeDtoIn resumeDtoIn) {
        resumeRepository.findByCareer_obj(resumeDtoIn.getCareer_obj()).ifPresent(resume -> {throw new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "resume already exist");});
        Resume resume = resumeRepository.save(Resume.builder()
                        .seeker_id(resumeDtoIn.getSeekerId())
                        .career_obj(resumeDtoIn.getCareer_obj())
                        .salary(resumeDtoIn.getSalary())
                        .build());
        return ResumeDtoOut.from(resume);
    }

    @Override
    public ResumeDtoOut update(Long id, UpdateResumeDtoIn updateResumeDtoIn) {
        Resume resume = resumeRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.BAD_REQUEST, "not found resume"));
        resume.setTitle(updateResumeDtoIn.getTitle());
        resume.setCareer_obj(updateResumeDtoIn.getCareerObj());
        resume.setSalary(updateResumeDtoIn.getSalary());
        resume.setUpdated_at(Common.currentTime());
        resume = resumeRepository.save(resume);
        return ResumeDtoOut.from(resume);
    }

    @Override
    public ResumeDtoOut get(Long id) {
        Resume resume = resumeRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.BAD_REQUEST, "not found resume"));


        return ResumeDtoOut.from(resume);
    }

    @Override
    public void delete(Long id) {
        Resume resume = resumeRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.BAD_REQUEST, "not found resume"));
        resumeRepository.delete(resume);


    }
}
