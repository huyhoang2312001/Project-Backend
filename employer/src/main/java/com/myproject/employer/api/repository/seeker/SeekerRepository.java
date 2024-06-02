package com.myproject.employer.api.repository.seeker;

import com.myproject.employer.api.entity.jpa.Seeker;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeekerRepository extends JpaRepository<Seeker, Long> {

    Page<Seeker> findAll(Pageable page);
    Optional<Seeker> findByName(String name);
    @Override
    @Cacheable(value = "SEEKER", key = "#id")
    Optional<Seeker> findById(Long id);
}
