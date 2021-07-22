package com.hbjs.hrscommon.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.hbjs.hrscommon.vo.BaseInfoVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "福利津贴DO实体")
public class AllowanceExcel {

    @ExcelProperty(index = 0, value = "序号")
    private Integer rowNum;

    @ExcelProperty(index = 1, value = "部门名称")
    private String depName;

    @ExcelProperty(index = 2, value = "员工编号")
    private String staffCode;

    @ExcelProperty(index = 3, value = "员工姓名")
    private String staffName;

    @ExcelProperty(index = 4, value = "津贴月份")
    @DateTimeFormat("yyyy-MM")
    private Date month;

    @ExcelProperty(index = 5, value = "通讯补贴")
    private BigDecimal communicationAllowance;

    @ExcelProperty(index = 6, value = "其他补贴")
    private BigDecimal otherAllowance;

    @ExcelProperty(index = 7, value = "生日卡")
    private BigDecimal birthdayCard;

    @ExcelProperty(index = 8, value = "清凉饮料")
    private BigDecimal coolDrink;

    @ExcelProperty(index = 9, value = "慰问品")
    private BigDecimal condolenceGoods;

    @ExcelProperty(index = 10, value = "房租")
    private BigDecimal rent;

    @ExcelProperty(index = 11, value = "话费")
    private BigDecimal phoneBill;

    @ExcelProperty(index = 12, value = "备注")
    private String remarks;

}
