package com.myproject.employer.api.dto.in.seeker;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeekerDtoIn {
    @NotEmpty
    private String name;
    @NotEmpty
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format. Required format: yyyy-MM-dd")
    private String birthday;
    @NotEmpty
    private Integer provinceId;
    private String address;
}
