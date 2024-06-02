package com.myproject.employer.api.dto.in.job;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateJobDtoIn {
    @NotEmpty
    private Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private Integer quantity;
    @NotEmpty
    private String description;
    @NotEmpty
    private List<Long> fieldIds;
    @NotEmpty
    private List<Long> provinceIds;
    @NotEmpty
    private Integer salary;
    @NotEmpty
    private Date expiredAt;


}
