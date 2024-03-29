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

import java.math.BigDecimal;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = false, chain = false)
@HeadFontStyle(fontHeightInPoints = 12)
@ContentFontStyle(fontHeightInPoints = 11)
@Schema(description = "薪资变动导入实体")
public class SalaryChangeImportExcel {

    @ExcelProperty(index = 0, value = "序号")
    private Integer rowNum;

    @ExcelProperty(index = 1, value = "部门名称")
    private String depName;

    @ExcelProperty(index = 2, value = "员工编号")
    private String staffCode;

    @ExcelProperty(index = 3, value = "员工姓名")
    private String staffName;

    @ExcelProperty(index = 4, value = "变更后基础工资")
    private BigDecimal postBasicSalary;

    @ExcelProperty(index = 5, value = "变更后岗位工资")
    private BigDecimal postPostSalary;

    @ExcelProperty(index = 6, value = "变更后公积金基数")
    private BigDecimal postAccumulationFund;

    @ExcelProperty(index = 7, value = "变更后是否享有独生子女津贴")
    private Boolean postHaveOneChildAllowance;

    @ExcelProperty(index = 8, value = "变更后安全津贴档次")
    private String postSafetyGrade;

    @ExcelProperty(index = 9, value = "变更后高温津贴档次")
    private String postHotWeatherGrade;

    @ExcelProperty(index = 10, value = "生效日期")
    private Date effectiveDate;

    @ExcelProperty(index = 11, value = "变更日期")
    private Date changeDate;

    @ExcelProperty(index = 12, value = "备注")
    private String remarks;

}
