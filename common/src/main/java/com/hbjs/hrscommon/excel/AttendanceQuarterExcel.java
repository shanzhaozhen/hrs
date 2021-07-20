package com.hbjs.hrscommon.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
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
@Schema(description = "季度考勤导入导出Excel实体")
public class AttendanceQuarterExcel {

    @ExcelProperty(index = 0, value = "序号")
    private Integer rowNum;

    @ExcelProperty(index = 1, value = "部门名称")
    private String depName;

    @ExcelProperty(index = 2, value = "员工编号")
    private String staffCode;

    @ExcelProperty(index = 3, value = "员工姓名")
    private String staffName;

    @ExcelProperty(index = 4, value = "考勤年度")
    private Integer year;

    @ExcelProperty(index = 5, value = "考勤季度")
    private Integer quarter;

    @ExcelProperty(index = 6, value = "应出勤天数")
    private Integer shouldAttendanceDays;

    @ExcelProperty(index = 7, value = "实出勤天数")
    private Float actualAttendanceDays;

    @ExcelProperty(index = 8, value = "是否冻结")
    private Boolean freeze;

    @ExcelProperty(index = 9, value = "备注")
    private String remarks;

}
