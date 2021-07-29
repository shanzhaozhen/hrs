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
@Schema(description = "个税导入实体")
public class SalaryTaxExcel {

    @ExcelProperty(index = 0, value = {"序号", "序号"})
    private Integer rowNum;

    @ExcelProperty(index = 1, value = {"员工信息", "部门名称"})
    private String depName;

    @ExcelProperty(index = 2, value = {"员工信息", "员工编号"})
    private String staffCode;

    @ExcelProperty(index = 3, value = {"员工信息", "员工姓名"})
    private String staffName;

    @ExcelProperty(index = 4, value = {"员工信息", "发放月份"})
    @DateTimeFormat("yyyy-MM")
    private Date month;

    @ExcelProperty(index = 5, value = {"员工信息", "发薪类型（工资、奖金）"})
    private String type;

    @ExcelProperty(index = 6, value = {"个税", "个税"})
    private BigDecimal individualIncomeTax;

}
