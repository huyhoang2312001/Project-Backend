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
public class Seeker implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "address")
    private String address;
    @Column(name = "province")
    private Integer province;
    @Column(name = "created_at")
    private Date created_at;
    @Column(name = "updated_at")
    private Date updated_at;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province")
    private Job_Province jobProvince;
    @ManyToMany
    @JoinTable(
            name = "seeker_job_province",
            joinColumns = @JoinColumn(name = "province"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<Job_Province> jobProvinces;

}
