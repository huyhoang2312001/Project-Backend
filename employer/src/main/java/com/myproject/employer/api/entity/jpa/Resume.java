package com.myproject.employer.api.entity.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "seeker")
public class Resume implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "seeker_id", unique = true)
    private Long seeker_id;
    @Column(name = "career_obj")
    private String career_obj;
    @Column(name = "title")
    private String title;
    @Column(name = "salary")
    private Integer salary;
    @Column(name = "fields")
    private String fields;
    @Column(name = "province")
    private Integer province;
    @Column(name = "created_at")
    private Date created_at;
    @Column(name = "updated_at")
    private Date updated_at;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seeker_id")
    private Seeker seeker;
    @ManyToMany
    @JoinTable(
            name = "resume_job_field",
            joinColumns = @JoinColumn(name = "fields"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<Job_Field> jobFields;
    @ManyToMany
    @JoinTable(
            name = "resume_job_province",
            joinColumns = @JoinColumn(name = "provinces"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<Job_Province> jobProvinces;

}
