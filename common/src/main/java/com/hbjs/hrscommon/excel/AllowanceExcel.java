package com.hbjs.hrscommon.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.hbjs.hrscommon.vo.BaseInfoVO;
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
@Schema(description = "福利津贴Excel实体")
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

    @ExcelProperty(index = 5, value = "补发工资")
    private BigDecimal backSalary;

    @ExcelProperty(index = 6, value = "年终奖")
    private BigDecimal annualBonus;

    @ExcelProperty(index = 7, value = "安全奖")
    private BigDecimal safetyBonus;

    @ExcelProperty(index = 8, value = "综治奖")
    private BigDecimal stabilityBonus;

    @ExcelProperty(index = 9, value = "计生奖")
    private BigDecimal familyPlanningBonus;

    @ExcelProperty(index = 10, value = "先进奖")
    private BigDecimal excellenceBonus;

    @ExcelProperty(index = 11, value = "专项奖")
    private BigDecimal specialBonus;

    @ExcelProperty(index = 12, value = "就餐补贴")
    private BigDecimal mealAllowance;

    @ExcelProperty(index = 13, value = "交通补贴")
    private BigDecimal trafficAllowance;

    @ExcelProperty(index = 14, value = "通讯补贴")
    private BigDecimal communicationAllowance;

    @ExcelProperty(index = 15, value = "节日慰问金")
    private BigDecimal festivalAllowance;

    @ExcelProperty(index = 16, value = "其他补贴")
    private BigDecimal otherAllowance;

    @ExcelProperty(index = 17, value = "生日卡")
    private BigDecimal birthdayCard;

    @ExcelProperty(index = 18, value = "清凉饮料")
    private BigDecimal coolDrink;

    @ExcelProperty(index = 19, value = "慰问品")
    private BigDecimal condolenceGoods;

    @ExcelProperty(index = 20, value = "房租")
    private BigDecimal rent;

    @ExcelProperty(index = 21, value = "话费")
    private BigDecimal phoneBill;

    @ExcelProperty(index = 22, value = "备注")
    private String remarks;

}
