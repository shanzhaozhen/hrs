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
@TableName("hr_staff_change")
@Schema(description = "调动记录DO实体")
public class StaffChangeDO extends BaseInfo {

    private static final long serialVersionUID = -1739670411560420672L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "员工id")
    private Long staffId;

    @Schema(title = "变更前部门id")
    private Long preDepId;

    @Schema(title = "变更后部门id")
    private Long postDepId;

    @Schema(title = "变更前职务")
    private String preDuty;

    @Schema(title = "变更后职务")
    private String postDuty;

    @Schema(title = "变更前岗位")
    private String prePost;

    @Schema(title = "变更后岗位")
    private String postPost;

    @Schema(title = "变更前岗位类型")
    private String prePostType;

    @Schema(title = "变更后岗位类型")
    private String postPostType;

    @Schema(title = "变更前岗位等级")
    private String prePostLevel;

    @Schema(title = "变更后岗位等级")
    private String postPostLevel;

    @Schema(title = "生效日期")
    private Date effectiveDate;

    @Schema(title = "是否已执行")
    private Boolean executed;

    @Schema(title = "备注")
    private String remarks;

}
