package com.myproject.employer.api.dto.out.employer;

import com.myproject.employer.api.entity.jpa.Employers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployerDtoOut {
    private Long id;
    private String email;
    private String name;
    private int province;
    private String description;
    public static EmployerDtoOut from(Employers e) {
        return EmployerDtoOut.builder()
                .id(e.getId())
                .email(e.getEmail())
                .name(e.getName())
                .province(e.getProvince())
                .description(e.getDescription())
                .build();
    }

}
