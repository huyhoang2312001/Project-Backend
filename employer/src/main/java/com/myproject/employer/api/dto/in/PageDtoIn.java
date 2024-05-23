package com.myproject.employer.api.dto.in;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageDtoIn {
    @NonNull
    @Min(value = 1)
    private Integer page = 1;
    @NonNull
    @Min(value = 1)
    @Max(value = 500)
    private Integer pageSize = 10;
}
