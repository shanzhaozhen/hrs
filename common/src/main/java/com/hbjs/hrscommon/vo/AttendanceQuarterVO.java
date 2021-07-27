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
@Schema(description = "季度月度考勤DTO实体")
public class AttendanceQuarterVO extends BaseInfoVO {

    private static final long serialVersionUID = 1901046647896217810L;

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

    @Schema(title = "考勤年度")
    private Integer year;

    @Schema(title = "考勤季度")
    private Integer quarter;

    @Schema(title = "应出勤天数")
    private Integer shouldAttendanceDays;

    @Schema(title = "实出勤天数")
    private Float actualAttendanceDays;

    @Schema(title = "是否冻结")
    private Boolean freeze;

    @Schema(title = "备注")
    private String remarks;

}
