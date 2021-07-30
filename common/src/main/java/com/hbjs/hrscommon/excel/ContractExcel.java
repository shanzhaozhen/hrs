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
@Schema(description = "合同信息导入导出实体")
public class ContractExcel {

    @ExcelProperty(index = 0, value = "序号")
    private Integer rowNum;

    @ExcelProperty(index = 1, value = "部门名称")
    private String depName;

    @ExcelProperty(index = 2, value = "员工编号")
    private String staffCode;

    @ExcelProperty(index = 3, value = "员工姓名")
    private String staffName;

    @ExcelProperty(index = 4, value = "合同名称")
    private String name;

    @ExcelProperty(index = 5, value = "合同编号")
    private String number;

    @ExcelProperty(index = 6, value = "业务类型")
    private String type;

    @ExcelProperty(index = 7, value = "业务发生日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date occurrenceDate;

    @ExcelProperty(index = 8, value = "合同期限类型")
    private String periodType;

    @ExcelProperty(index = 9, value = "合同期限")
    private Integer period;

    @ExcelProperty(index = 10, value = "合同期限单位")
    private String periodUnit;

    @ExcelProperty(index = 11, value = "合同开始日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date startDate;

    @ExcelProperty(index = 12, value = "合同结束日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date endDate;

    @ExcelProperty(index = 13, value = "是否需要试用")
    private String hasProbation;

    @ExcelProperty(index = 14, value = "试用期限")
    private Integer probationTerm;

    @ExcelProperty(index = 15, value = "试用期限单位")
    private Integer probationTermUnit;

    @ExcelProperty(index = 16, value = "试用开始日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date probationStartDate;

    @ExcelProperty(index = 17, value = "试用结束日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date probationEndDate;

    @ExcelProperty(index = 18, value = "合同主体单位")
    private String company;

    @ExcelProperty(index = 19, value = "业务发生组织")
    private String organization;

    @ExcelProperty(index = 20, value = "备注")
    private String remarks;

}
