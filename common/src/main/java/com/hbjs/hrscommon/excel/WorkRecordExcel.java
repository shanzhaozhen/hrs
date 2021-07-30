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
@Schema(description = "工作记录导入导出实体")
public class WorkRecordExcel {

    @ExcelProperty(index = 0, value = "序号")
    private Integer rowNum;

    @ExcelProperty(index = 1, value = "部门名称")
    private String depName;

    @ExcelProperty(index = 2, value = "员工编号")
    private String staffCode;

    @ExcelProperty(index = 3, value = "员工姓名")
    private String staffName;

    @ExcelProperty(index = 4, value = "组织")
    private String organization;

    @ExcelProperty(index = 5, value = "人员类别")
    private String category;

    @ExcelProperty(index = 6, value = "开始时间")
    @DateTimeFormat("yyyy-MM-dd")
    private Date startDate;

    @ExcelProperty(index = 7, value = "结束时间")
    @DateTimeFormat("yyyy-MM-dd")
    private Date endDate;

    @ExcelProperty(index = 8, value = "部门")
    private String department;

    @ExcelProperty(index = 9, value = "岗位")
    private String post;

    @ExcelProperty(index = 10, value = "岗位序列")
    private String postType;

    @ExcelProperty(index = 11, value = "职务")
    private String duty;

    @ExcelProperty(index = 12, value = "职务类别")
    private String dutyType;

    @ExcelProperty(index = 13, value = "异动事件")
    private String changeEvent;

    @ExcelProperty(index = 14, value = "异动类型")
    private String changeType;

    @ExcelProperty(index = 15, value = "异动原因")
    private String changeReason;

    @ExcelProperty(index = 16, value = "试用")
    private String trial;

    @ExcelProperty(index = 17, value = "试用类型")
    private String trialType;

    @ExcelProperty(index = 18, value = "部门详情")
    private String departmentDetails;

    @ExcelProperty(index = 19, value = "备注")
    private String remarks;

}
