package com.hbjs.hrscommon.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = false, chain = false)
@Schema(description = "员工导入导出excel实体")
public class PerformanceExcel {

    @ExcelProperty(index = 0, value = "序号")
    private Integer rowNum;
    
    @ExcelProperty(index = 1, value = "员工编号")
    private String staffCode;

    @ExcelProperty(index = 2, value = "员工姓名")
    private String staffName;

    @ExcelProperty(index = 3, value = "考核年度")
    private Integer year;

    @ExcelProperty(index = 4, value = "考核季度")
    private Integer quarter;

    @ExcelProperty(index = 5, value = "考核等级")
    private String appraise;

    @ExcelProperty(index = 6, value = "备注")
    private String remarks;

}
