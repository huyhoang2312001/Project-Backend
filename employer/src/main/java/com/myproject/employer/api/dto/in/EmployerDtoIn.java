package com.myproject.employer.api.dto.in;
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
    private String email;
    @NotEmpty
    @Size(max = 100)
    private String name;





}
