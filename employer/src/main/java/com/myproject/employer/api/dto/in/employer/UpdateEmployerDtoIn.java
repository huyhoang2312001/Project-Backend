package com.myproject.employer.api.dto.in.employer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEmployerDtoIn {
    @NotEmpty
    private Long id;
    @NotEmpty
    @Size(max = 255)
    private String name;
    @NotEmpty
    private Integer province;
    private String description;
}
