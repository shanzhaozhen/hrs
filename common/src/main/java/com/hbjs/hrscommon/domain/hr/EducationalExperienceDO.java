package com.hbjs.hrscommon.domain.hr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hbjs.hrscommon.domain.BaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("hr_educational_experience")
@Schema(description = "教育经历DO实体")
public class EducationalExperienceDO extends BaseInfo {

    private static final long serialVersionUID = -5214125117506141882L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "关联id")
    private Long pid;

    @Schema(title = "学校")
    private String schoolName;

    @Schema(title = "开始日期")
    private Date startDate;

    @Schema(title = "结束日期")
    private Date endDate;

    @Schema(title = "专业")
    private String major;

    @Schema(title = "学制")
    private String studyYears;

    @Schema(title = "学习方式")
    private String style;

    @Schema(title = "学历")
    private String education;

    @Schema(title = "学位")
    private String degree;

    @Schema(title = "学位授予日期")
    private Date degreeDate;

    @Schema(title = "学位授予单位")
    private String degreeCompany;

    @Schema(title = "学历证书编号")
    private String educationNumber;

    @Schema(title = "学位证书编号")
    private String degreeNumber;

    @Schema(title = "是否最高学历")
    private String isHighestEducation;

    @Schema(title = "入职学历")
    private String entryEducation;

    @Schema(title = "是否入职学历")
    private String isEntryEducation;

    @Schema(title = "证明人姓名")
    private String witnessName;

    @Schema(title = "证明人电话")
    private String witnessPhone;

    @Schema(title = "备注")
    private String remarks;

}
