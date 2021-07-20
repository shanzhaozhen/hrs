package com.hbjs.hrscommon.form;

import com.hbjs.hrscommon.vo.BaseInfoVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "季度月度考勤Form实体")
public class AttendanceQuarterForm {

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "员工id")
    private Long staffId;

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
