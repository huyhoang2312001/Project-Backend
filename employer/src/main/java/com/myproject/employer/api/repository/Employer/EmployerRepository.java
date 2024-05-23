package com.myproject.employer.api.repository.Employer;

import com.myproject.employer.api.entity.jpa.Employers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface EmployerRepository extends JpaRepository <Employers, Long>{
    Optional<Employers> findByEmail(String email);
    Page<Employers> findAll(Pageable page);
    @Override
    @Cacheable(value = "EMPLOYER", key = "#id")
    Optional<Employers> findById(Long id);
}
