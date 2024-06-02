package com.myproject.employer.api.dto.out.resume;

import com.myproject.employer.api.entity.jpa.Job_Field;
import com.myproject.employer.api.entity.jpa.Job_Province;
import com.myproject.employer.api.entity.jpa.Resume;
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
public class ResumeDtoOut {
    private Long id;
    private Long seekerId;
    private String seekerName;
    private String careerObj;
    private String title;
    private Integer salary;
    private List<Job_Field> fields;
    private List<Job_Province> provinces;
    public static ResumeDtoOut from(Resume r) {
        List<Job_Field> jobField = r.getJobFields();
        List<Job_Province> jobProvinces = r.getJobProvinces();
        Seeker seeker = new Seeker();
        return ResumeDtoOut.builder()
                .id(r.getId())
                .seekerId(seeker.getId())
                .seekerName(seeker.getName())
                .careerObj(r.getCareer_obj())
                .title(r.getTitle())
                .salary(r.getSalary())
                .fields(jobField)
                .provinces(jobProvinces)
                .build();
    }

}
