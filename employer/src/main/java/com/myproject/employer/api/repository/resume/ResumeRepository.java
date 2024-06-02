package com.myproject.employer.api.repository.resume;

import com.myproject.employer.api.entity.jpa.Resume;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Optional<Resume> findByCareer_obj(String career_obj);
    Page<Resume> findAll(Pageable page);
    @Override
    @Cacheable(value = "RESUME", key = "#id")
    Optional<Resume> findById(Long id);
}
