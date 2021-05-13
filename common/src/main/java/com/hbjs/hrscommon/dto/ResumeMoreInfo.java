package com.hbjs.hrscommon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "简历更多信息实体")
public class ResumeMoreInfo {

    @Schema(title = "工作履历")
    private List<WorkExperienceDTO> workExperienceList;

    @Schema(title = "教育经历")
    private List<EducationalExperienceDTO> educationalExperienceList;

    @Schema(title = "证件信息")
    private List<CertificateDTO> certificateList;

    @Schema(title = "家庭成员")
    private List<FamilyDTO> familyList;

}
