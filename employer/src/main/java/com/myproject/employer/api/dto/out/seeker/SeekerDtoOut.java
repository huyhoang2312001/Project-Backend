package com.myproject.employer.api.dto.out.seeker;

import com.myproject.employer.api.entity.jpa.Job_Province;
import com.myproject.employer.api.entity.jpa.Seeker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeekerDtoOut {
    private Long id;
    private String name;
    private String birthday;
    private String address;
    private Long provinceId;
    private String provinceName;
        public static SeekerDtoOut from(Seeker s) {
            Job_Province jobProvince = s.getJobProvince();
            return SeekerDtoOut.builder()
                    .id(s.getId())
                    .name(s.getName())
                    .birthday(s.getBirthday())
                    .address(s.getAddress())
                    .provinceId(jobProvince.getId())
                    .provinceName(jobProvince.getName())
                    .build();
        }
}
