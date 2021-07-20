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
@TableName("hr_attendance_quarter")
@Schema(description = "季度考勤数据DO实体")
public class AttendanceQuarterDO extends BaseInfo {

    private static final long serialVersionUID = -2221508473426188853L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "员工id")
    private Long staffId;

    @Schema(title = "考核年度")
    private Integer year;

    @Schema(title = "考核季度")
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
