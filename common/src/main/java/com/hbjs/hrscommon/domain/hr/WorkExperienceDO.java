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
@TableName("hr_work_experience")
@Schema(description = "工作履历DO实体")
public class WorkExperienceDO extends BaseInfo {

    private static final long serialVersionUID = 5897116770305160409L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "关联id（关联职工表）")
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
