package com.myproject.employer.api.dto.out.job;

import com.myproject.employer.api.entity.jpa.Job;
import com.myproject.employer.api.entity.jpa.Employers;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDtoOut {
    private Long id;
    private String title;
    private int quantity;
    private String description;
    private Integer salary;
    private String fields;
    private String provinces;
    private Long employerId;
    private String employerName;
    private Date expiredAt;
    public static JobDtoOut from(Job j) {
        Employers employers = j.getEmployers();
        return JobDtoOut.builder()
                .id(j.getId())
                .title(j.getTitle())
                .quantity(j.getQuantity())
                .description(j.getDescription())
                .salary(j.getSalary())
                .fields(j.getFields())
                .provinces(j.getProvinces())
                .employerName(employers.getName())
                .employerId(employers.getId())
                .expiredAt((Date) j.getExpired_at())
                .build();
    }
}
