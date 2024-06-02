package com.myproject.employer.api.dto.in.resume;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeDtoIn {
    @NotEmpty
    private Long seekerId;
    @NotEmpty
    private String career_obj;
    @NotEmpty
    private String title;
    @NotEmpty
    private Integer salary;
    @NotEmpty
    private List<Long> fieldIds;
    @NotEmpty
    private List<Long> provinceIds;
}
