package com.hbjs.hrscommon.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.hbjs.hrscommon.utils.ExcelBooleanConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = false, chain = false)
@Schema(description = "员工薪资导入导出excel实体")
public class SalaryStaffExcel {

    @ExcelProperty(index = 0, value = "序号")
    private Integer rowNum;

    @ExcelProperty(index = 1, value = "部门名称")
    private String depName;

    @ExcelProperty(index = 2, value = "员工编号")
    private String staffCode;

    @ExcelProperty(index = 3, value = "员工姓名")
    private String staffName;

    @ExcelProperty(index = 4, value = "基础工资")
    private BigDecimal basicSalary;

    @ExcelProperty(index = 5, value = "岗位工资")
    private BigDecimal postSalary;

    @ExcelProperty(index = 6, value = "公积金基数")
    private BigDecimal accumulationFund;

    @ExcelProperty(index = 7, value = "是否享有独生子女津贴", converter = ExcelBooleanConverter.class)
    private Boolean haveOneChildAllowance;

    @ExcelProperty(index = 8, value = "安全津贴档次")
    private String safetyGrade;

    @ExcelProperty(index = 9, value = "高温津贴档次")
    private String hotWeatherGrade;


}
