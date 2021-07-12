package com.hbjs.hrscommon.dto;

import com.hbjs.hrscommon.domain.BaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "绩效评价DTO实体")
public class PerformanceDTO extends BaseInfo {

    private static final long serialVersionUID = 4793915894066175662L;

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "员工id")
    private Long staffId;

    @Schema(title = "员工编号")
    private String staffCode;

    @Schema(title = "员工姓名")
    private String staffName;

    @Schema(title = "部门ID")
    private Long depId;

    @Schema(title = "考核年度")
    private Integer year;

    @Schema(title = "考核季度")
    private Integer quarter;

    @Schema(title = "考核等级")
    private String appraise;

    @Schema(title = "备注")
    private String remarks;

}
