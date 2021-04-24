package com.hbjs.hrscommon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "工作履历VO实体")
public class WorkExperienceVO extends BaseInfoVO {

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "关联id")
    private Long pid;

    @Schema(title = "工作单位")
    private String workUnit;

    @Schema(title = "开始时间")
    private Date startDate;

    @Schema(title = "结束时间")
    private Date endDate;

    @Schema(title = "职务/岗位")
    private String duty;

    @Schema(title = "单位性质")
    private String unitType;

    @Schema(title = "月薪")
    private String salary;

    @Schema(title = "证明人姓名")
    private String witnessName;

    @Schema(title = "证明人电话")
    private String witnessPhone;

}
