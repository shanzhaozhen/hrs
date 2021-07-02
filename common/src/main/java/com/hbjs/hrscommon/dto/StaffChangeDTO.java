package com.hbjs.hrscommon.dto;

import com.hbjs.hrscommon.domain.BaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "部门变更记录DTO实体")
public class StaffChangeDTO extends BaseInfo {

    private static final long serialVersionUID = 6900707938735015442L;

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "员工id")
    private Long staffId;

    @Schema(title = "员工编号")
    private String staffCode;

    @Schema(title = "员工姓名")
    private String staffName;

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

    @Schema(title = "备注")
    private String remarks;

}
