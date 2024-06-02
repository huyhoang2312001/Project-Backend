package com.myproject.employer.api.dto.in.job;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class JobDtoIn {
    @NotEmpty
    private Long employer_id;
    @NotEmpty
    private String title;
    @NotEmpty
    private Integer quantity;
    @NotEmpty
    private Integer description;
    @NotEmpty
    private List<Long> fieldIds;
    @NotEmpty
    private List<Long> provinceIds;
    @NotEmpty
    private Integer salary;
    @NotEmpty
    private Date expiredAt;
}
