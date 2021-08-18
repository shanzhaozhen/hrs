package com.hbjs.hrscommon.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ContentFontStyle;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.hbjs.hrscommon.domain.BaseInfo;
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
@Schema(description = "调动记录导入实体")
public class StaffChangeImportExcel {

    @ExcelProperty(index = 0, value = "序号")
    private Integer rowNum;
    
    @ExcelProperty(index = 1, value = "员工编号")
    private String staffCode;

    @ExcelProperty(index = 2, value = "员工姓名")
    private String staffName;

    @ExcelProperty(index = 3, value = "变更后部门")
    private String postDepName;

    @ExcelProperty(index = 4, value = "变更后职务")
    private String postDuty;

    @ExcelProperty(index = 5, value = "变更后岗位")
    private String postPost;

    @ExcelProperty(index = 6, value = "变更后岗位类型")
    private String postPostType;

    @ExcelProperty(index = 7, value = "变更后岗位等级")
    private String postPostLevel;

    @ExcelProperty(index = 8, value = "变更后在司状态")
    private String postCompanyState;

    @ExcelProperty(index = 9, value = "变更后用工性质")
    private String postEmployType;

    @ExcelProperty(index = 10, value = "生效日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date effectiveDate;

    @ExcelProperty(index = 11, value = "变更日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date changeDate;

    @ExcelProperty(index = 12, value = "备注")
    private String remarks;

}
