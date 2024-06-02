package com.myproject.employer.api.repository.job;

import com.myproject.employer.api.entity.jpa.Job;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository <Job, Long>{
    Optional<Job> findByEmployer_id(Long employer_id);
    Page<Job> findAll(Pageable page);

    @Override
    @Cacheable(value = "JOB", key = "#id")
    Optional<Job>findById(Long id);

}
