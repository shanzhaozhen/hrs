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
@TableName("hr_attendance_month")
@Schema(description = "月度考勤DO实体")
public class AttendanceMonthDO extends BaseInfo {

    private static final long serialVersionUID = 6193931886975211897L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "员工id")
    private Long staffId;

    @Schema(title = "考勤月份")
    private Date month;

    @Schema(title = "应出勤天数")
    private Integer shouldAttendanceDays;

    @Schema(title = "实出勤天数")
    private Float actualAttendanceDays;

    @Schema(title = "高温天数")
    private Integer hotWeatherDays;

    @Schema(title = "旷工天数")
    private Float absenteeismDays;

    @Schema(title = "出差天数")
    private Float travelDays;

    @Schema(title = "外出天数")
    private Float outDays;

    @Schema(title = "迟到次数")
    private Integer lateTimes;

    @Schema(title = "迟到分钟数")
    private Integer lateMinutes;

    @Schema(title = "早退次数")
    private Integer leaveEarlyTimes;

    @Schema(title = "早退分钟数")
    private Integer leaveEarlyMinutes;

    @Schema(title = "缺卡次数")
    private Integer cardMissTimes;

    @Schema(title = "签卡次数")
    private Integer signCardTimes;

    @Schema(title = "平时加班时数")
    private Float overtimeWeekHours;

    @Schema(title = "周末加班时数")
    private Float overtimeWeekendHours;

    @Schema(title = "节日加班时数")
    private Float overtimeFestivalHours;

    @Schema(title = "年假天数")
    private Float annualLeaveDays;

    @Schema(title = "调休假天数")
    private Float compensatoryLeaveDays;

    @Schema(title = "计生假天数")
    private Float familyPlanningLeaveDays;

    @Schema(title = "产假天数")
    private Float maternityLeaveDays;

    @Schema(title = "节假日请假天数")
    private Float holidayLeaveDays;

    @Schema(title = "病假天数")
    private Float sickLeaveDays;

    @Schema(title = "休病假总天数")
    private Float sickLeaveDaysTotal;

    @Schema(title = "事假天数")
    private Float absenceLeaveDays;

    @Schema(title = "特殊情况请假天数")
    private Float exceptionalCaseDays;

    @Schema(title = "工伤假天数")
    private Float injuryLeaveDays;

    @Schema(title = "婚假天数")
    private Float marriageLeaveDays;

    @Schema(title = "哺乳假天数")
    private Float lactationLeaveDays;

    @Schema(title = "独生子女父母陪护假天数")
    private Float onlyChildLeaveDays;

    @Schema(title = "看护假天数")
    private Float nursingLeave;

    @Schema(title = "丧假天数")
    private Float bereavementLeave;

    @Schema(title = "值班（工作日）天数")
    private Integer dutyWeek;

    @Schema(title = "值班（休息日前一天）天数")
    private Integer dutyBeforeWeek;

    @Schema(title = "值班（法定节假日前一天）天数")
    private Integer dutyBeforeFestival;

    @Schema(title = "值班（休息日）天数")
    private Integer dutyWeekend;

    @Schema(title = "值班（法定节假日（春节假期除外））天数")
    private Integer dutyFestival;

    @Schema(title = "值班（春节假期（不含除夕、初一、初二））天数")
    private Integer dutyOutSpring;

    @Schema(title = "值班（春节假期（除夕、初一、初二））天数")
    private Integer dutyInSpring;

    @Schema(title = "备注")
    private String remarks;

}
