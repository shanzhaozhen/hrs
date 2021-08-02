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
@Schema(description = "教育经历导入导出实体")
public class EducationalExperienceExcel {

    @ExcelProperty(index = 0, value = "序号")
    private Integer rowNum;

    @ExcelProperty(index = 1, value = "部门名称")
    private String depName;

    @ExcelProperty(index = 2, value = "员工编号")
    private String staffCode;

    @ExcelProperty(index = 3, value = "员工姓名")
    private String staffName;

    @ExcelProperty(index = 4, value = "学校")
    private String schoolName;

    @ExcelProperty(index = 5, value = "开始日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date startDate;

    @ExcelProperty(index = 6, value = "结束日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date endDate;

    @ExcelProperty(index = 7, value = "专业")
    private String major;

    @ExcelProperty(index = 8, value = "学制")
    private String studyYears;

    @ExcelProperty(index = 9, value = "学习方式")
    private String style;

    @ExcelProperty(index = 10, value = "学历")
    private String education;

    @ExcelProperty(index = 11, value = "学位")
    private String degree;

    @ExcelProperty(index = 12, value = "学位授予日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date degreeDate;

    @ExcelProperty(index = 13, value = "学位授予单位")
    private String degreeCompany;

    @ExcelProperty(index = 14, value = "学历证书编号")
    private String educationNumber;

    @ExcelProperty(index = 15, value = "学位证书编号")
    private String degreeNumber;

    @ExcelProperty(index = 16, value = "是否最高学历")
    private String isHighestEducation;

    @ExcelProperty(index = 17, value = "入职学历")
    private String entryEducation;

    @ExcelProperty(index = 18, value = "是否入职学历")
    private String isEntryEducation;

    @ExcelProperty(index = 19, value = "证明人姓名")
    private String witnessName;

    @ExcelProperty(index = 20, value = "证明人电话")
    private String witnessPhone;

    @ExcelProperty(index = 21, value = "备注")
    private String remarks;

}
