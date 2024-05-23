package com.myproject.employer.api.entity.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employer")
public class Employers implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "province")
    private int province;

    @Column(name = "description")
    private String description;

    @Builder.Default
    @Column(name = "created_at")
    private Date createdDate = new Date();

    @Builder.Default
    @Column(name = "updated_at")
    private Date updatedDate = new Date();


}
