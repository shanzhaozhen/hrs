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
@Schema(description = "家庭成员导入导出实体")
public class FamilyExcel {

    @ExcelProperty(index = 0, value = "序号")
    private Integer rowNum;

    @ExcelProperty(index = 1, value = "部门名称")
    private String depName;

    @ExcelProperty(index = 2, value = "员工编号")
    private String staffCode;

    @ExcelProperty(index = 3, value = "员工姓名")
    private String staffName;

    @ExcelProperty(index = 4, value = "姓名")
    private String name;

    @ExcelProperty(index = 5, value = "关系")
    private String relation;

    @ExcelProperty(index = 6, value = "出生日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date birthday;

    @ExcelProperty(index = 7, value = "政治面貌")
    private String politics;

    @ExcelProperty(index = 8, value = "工作单位")
    private String workCompany;

    @ExcelProperty(index = 9, value = "职务")
    private String duty;

    @ExcelProperty(index = 10, value = "移动电话")
    private String mobilePhone;

    @ExcelProperty(index = 11, value = "固话")
    private String landlinePhone;

    @ExcelProperty(index = 12, value = "是否紧急联系人")
    private String isEmergency;

    @ExcelProperty(index = 13, value = "备注")
    private String remarks;

}
