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
@TableName("hr_work_record")
@Schema(description = "工作记录DO实体")
public class WorkRecordDO extends BaseInfo {

    private static final long serialVersionUID = -8930812127482133572L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "关联id")
    private Long staffId;

    @Schema(title = "组织")
    private String organization;

    @Schema(title = "人员类别")
    private String category;

    @Schema(title = "开始时间")
    private Date startDate;

    @Schema(title = "结束时间")
    private Date endDate;

    @Schema(title = "部门")
    private String department;

    @Schema(title = "岗位")
    private String post;

    @Schema(title = "岗位序列")
    private String postType;

    @Schema(title = "职务")
    private String duty;

    @Schema(title = "职务类别")
    private String dutyType;

    @Schema(title = "异动事件")
    private String changeEvent;

    @Schema(title = "异动类型")
    private String changeType;

    @Schema(title = "异动原因")
    private String changeReason;

    @Schema(title = "试用")
    private String trial;

    @Schema(title = "试用类型")
    private String trialType;

    @Schema(title = "部门详情")
    private String departmentDetails;

    @Schema(title = "备注")
    private String remarks;

}
