package com.hbjs.hrscommon.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "绩效评价VO实体")
public class PerformanceVO extends BaseInfoVO {

    private static final long serialVersionUID = -2186739542599669264L;

    @Schema(title = "主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Schema(title = "员工id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long staffId;

    @Schema(title = "员工编号")
    private String staffCode;

    @Schema(title = "员工姓名")
    private String staffName;

    @Schema(title = "部门ID")
    @JsonSerialize(using = ToStringSerializer.class)
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
