package com.hbjs.hrscommon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "教育经历VO实体")
public class EducationalExperienceVO extends BaseInfoVO {

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "关联id")
    private Long pid;

    @Schema(title = "学校")
    private String schoolName;

    @Schema(title = "开始日期")
    private Date startDate;

    @Schema(title = "结束日期")
    private Date endDate;

    @Schema(title = "学历")
    private String education;

    @Schema(title = "专业")
    private String major;

    @Schema(title = "学制")
    private Integer studyYears;

    @Schema(title = "是否全日制")
    private Boolean fullTime;

    @Schema(title = "证明人姓名")
    private String witnessName;

    @Schema(title = "证明人电话")
    private String witnessPhone;

}
