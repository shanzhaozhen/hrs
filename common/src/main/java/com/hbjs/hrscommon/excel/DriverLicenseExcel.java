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
@Schema(description = "驾驶证信息导入导出实体")
public class DriverLicenseExcel {

    @ExcelProperty(index = 0, value = "序号")
    private Integer rowNum;

    @ExcelProperty(index = 1, value = "部门名称")
    private String depName;

    @ExcelProperty(index = 2, value = "员工编号")
    private String staffCode;

    @ExcelProperty(index = 3, value = "员工姓名")
    private String staffName;

    @ExcelProperty(index = 4, value = "准驾车型")
    private String modal;

    @ExcelProperty(index = 5, value = "证件号码")
    private String number;

    @ExcelProperty(index = 6, value = "获得日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date obtainDate;

    @ExcelProperty(index = 7, value = "有效期至")
    @DateTimeFormat("yyyy-MM-dd")
    private Date expirationDate;

    @ExcelProperty(index = 8, value = "内部驾照")
    private String inside;

    @ExcelProperty(index = 9, value = "内部驾照有效期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date insideExpirationDate;

    @ExcelProperty(index = 10, value = "备注")
    private String remarks;

}
