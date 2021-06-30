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
@TableName("hr_attendance")
@Schema(description = "考勤DO实体")
public class AttendanceDO extends BaseInfo {

    private static final long serialVersionUID = 6193931886975211897L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "员工id")
    private Long staffId;

    @Schema(title = "考勤月份")
    private Date month;

    @Schema(title = "迟到时数")
    private Float lateHours;

    @Schema(title = "事假时数")
    private Float absenceLeaveHours;

    @Schema(title = "病假时数")
    private Float sickLeaveHours;

    @Schema(title = "签卡次数")
    private Integer signCardTimes;

    @Schema(title = "平时加班时数")
    private Float overtimeWeekdays;

    @Schema(title = "周末加班时数")
    private Float overtimeWeekend;

    @Schema(title = "节日加班时数")
    private Float overtimeFestival;

    @Schema(title = "应出勤天数")
    private Integer shouldAttendanceDays;

    @Schema(title = "实出勤天数")
    private Float actualAttendanceDays;

}
