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

    @Schema(title = "关联id（关联职工表）")
    private Long pid;

    @Schema(title = "学校")
    private String schoolName;

    @Schema(title = "开始日期")
    private Date starDate;

    @Schema(title = "结束日期")
    private Date endDate;

    @Schema(title = "学历")
    private Date education;

    @Schema(title = "专业")
    private Date major;

    @Schema(title = "学制")
    private Integer studyYears;

    @Schema(title = "是否全日制")
    private Boolean fullTime;

    @Schema(title = "证明人姓名")
    private String witnessName;

    @Schema(title = "证明人电话")
    private String witnessPhone;

}
