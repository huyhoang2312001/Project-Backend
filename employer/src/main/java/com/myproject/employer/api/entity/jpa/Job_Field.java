package com.myproject.employer.api.entity.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "job_field")
public class Job_Field {
    @Id
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "slug")
    private String slug;

}
