package com.myproject.employer.api.dto.in.resume;

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
public class UpdateResumeDtoIn {
    @NotEmpty
    private Long id;
    @NotEmpty
    private String careerObj;
    @NotEmpty
    private String title;
    @NotEmpty
    private Integer salary;
    @NotEmpty
    private List<Long> fieldIds;
    @NotEmpty
    private List<Long> provinceIds;

}
