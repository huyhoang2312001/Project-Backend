package com.myproject.employer.api.dto.in.employer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployerDtoIn {
   @NotEmpty
   @Email
   @Size(max = 255)
    private String email;
    @NotEmpty
    @Size(max = 255)
    private String name;
    @NotEmpty
    private Integer province;
    private String description;

}
