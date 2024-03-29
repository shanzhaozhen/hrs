package com.hbjs.hrscommon.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ContentFontStyle;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = false, chain = false)
@HeadFontStyle(fontHeightInPoints = 12)
@ContentFontStyle(fontHeightInPoints = 11)
@Schema(description = "月度考勤导入导出Excel实体")
public class AttendanceMonthExcel {

    @ExcelProperty(index = 0, value = "序号")
    private Integer rowNum;

    @ExcelProperty(index = 1, value = "部门名称")
    private String depName;

    @ExcelProperty(index = 2, value = "员工编号")
    private String staffCode;

    @ExcelProperty(index = 3, value = "员工姓名")
    private String staffName;

    @ExcelProperty(index = 4, value = "考勤月份")
    @DateTimeFormat("yyyy-MM")
    private Date month;

    @ExcelProperty(index = 5, value = "应出勤天数")
    private Integer shouldAttendanceDays;

    @ExcelProperty(index = 6, value = "实出勤天数")
    private Float actualAttendanceDays;

    @ExcelProperty(index = 7, value = "旷工天数")
    private Float absenteeismDays;

    @ExcelProperty(index = 8, value = "出差天数")
    private Float travelDays;

    @ExcelProperty(index = 9, value = "外出天数")
    private Float outDays;

    @ExcelProperty(index = 10, value = "迟到次数")
    private Integer lateTimes;

    @ExcelProperty(index = 11, value = "迟到分钟数")
    private Integer lateMinutes;

    @ExcelProperty(index = 12, value = "早退次数")
    private Integer leaveEarlyTimes;

    @ExcelProperty(index = 13, value = "早退分钟数")
    private Integer leaveEarlyMinutes;

    @ExcelProperty(index = 14, value = "缺卡次数")
    private Integer cardMissTimes;

    @ExcelProperty(index = 15, value = "签卡次数")
    private Integer signCardTimes;

    @ExcelProperty(index = 16, value = "平时加班时数")
    private Float overtimeWeekHours;

    @ExcelProperty(index = 17, value = "周末加班时数")
    private Float overtimeWeekendHours;

    @ExcelProperty(index = 18, value = "节日加班时数")
    private Float overtimeFestivalHours;

    @ExcelProperty(index = 19, value = "年假天数")
    private Float annualLeaveDays;

    @ExcelProperty(index = 20, value = "调休假天数")
    private Float compensatoryLeaveDays;

    @ExcelProperty(index = 21, value = "计生假天数")
    private Float familyPlanningLeaveDays;

    @ExcelProperty(index = 22, value = "产假天数")
    private Float maternityLeaveDays;

    @ExcelProperty(index = 23, value = "节假日请假天数")
    private Float holidayLeaveDays;

    @ExcelProperty(index = 24, value = "病假天数")
    private Float sickLeaveDays;

    @ExcelProperty(index = 25, value = "休病假总天数")
    private Float sickLeaveDaysTotal;

    @ExcelProperty(index = 26, value = "事假天数")
    private Float absenceLeaveDays;

    @ExcelProperty(index = 27, value = "特殊情况请假天数")
    private Float exceptionalCaseDays;

    @ExcelProperty(index = 28, value = "工伤假天数")
    private Float injuryLeaveDays;

    @ExcelProperty(index = 29, value = "婚假天数")
    private Float marriageLeaveDays;

    @ExcelProperty(index = 30, value = "哺乳假天数")
    private Float lactationLeaveDays;

    @ExcelProperty(index = 31, value = "独生子女父母陪护假天数")
    private Float onlyChildLeaveDays;

    @ExcelProperty(index = 32, value = "看护假天数")
    private Float nursingLeave;

    @ExcelProperty(index = 33, value = "丧假天数")
    private Float bereavementLeave;

    @ExcelProperty(index = 34, value = "值班（工作日）天数")
    private Integer dutyWeek;

    @ExcelProperty(index = 35, value = "值班（休息日前一天）天数")
    private Integer dutyBeforeWeek;

    @ExcelProperty(index = 36, value = "值班（法定节假日前一天）天数")
    private Integer dutyBeforeFestival;

    @ExcelProperty(index = 37, value = "值班（休息日）天数")
    private Integer dutyWeekend;

    @ExcelProperty(index = 38, value = "值班（法定节假日（春节假期除外））天数")
    private Integer dutyFestival;

    @ExcelProperty(index = 39, value = "值班（春节假期（不含除夕、初一、初二））天数")
    private Integer dutyOutSpring;

    @ExcelProperty(index = 40, value = "值班（春节假期（除夕、初一、初二））天数")
    private Integer dutyInSpring;

    @ExcelProperty(index = 41, value = "备注")
    private String remarks;

}
