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
@Schema(description = "工作履历导入导出实体")
public class WorkExperienceExcel {

    @ExcelProperty(index = 0, value = "序号")
    private Integer rowNum;

    @ExcelProperty(index = 1, value = "部门名称")
    private String depName;

    @ExcelProperty(index = 2, value = "员工编号")
    private String staffCode;

    @ExcelProperty(index = 3, value = "员工姓名")
    private String staffName;

    @ExcelProperty(index = 4, value = "工作单位")
    private String workCompany;

    @ExcelProperty(index = 5, value = "开始时间")
    @DateTimeFormat("yyyy-MM-dd")
    private Date startDate;

    @ExcelProperty(index = 6, value = "结束时间")
    @DateTimeFormat("yyyy-MM-dd")
    private Date endDate;

    @ExcelProperty(index = 7, value = "部门")
    private String department;

    @ExcelProperty(index = 8, value = "职务/岗位")
    private String duty;

    @ExcelProperty(index = 9, value = "单位性质")
    private String companyType;

    @ExcelProperty(index = 10, value = "月薪")
    private BigDecimal salary;

    @ExcelProperty(index = 11, value = "证明人姓名")
    private String witnessName;

    @ExcelProperty(index = 12, value = "证明人电话")
    private String witnessPhone;

    @ExcelProperty(index = 13, value = "备注")
    private String remarks;

}
