package com.hbjs.hrscommon.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
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
@Schema(description = "工资导入导出实体")
public class SalaryExcel {

    @ExcelProperty(index = 0, value = "序号")
    private Integer rowNum;

    @ExcelProperty(index = 1, value = "部门名称")
    private String depName;

    @ExcelProperty(index = 2, value = "员工编号")
    private String staffCode;

    @ExcelProperty(index = 3, value = "员工姓名")
    private String staffName;

    @ExcelProperty(index = 4, value = "发放月份")
    @DateTimeFormat("yyyy-MM")
    private Date month;

    @ExcelProperty(index = 5, value = "发薪类型（工资、奖金）")
    private String type;

    @ExcelProperty(index = 6, value = "考核等级")
    private String appraise;

    /**
     * 工资
     */

    @ExcelProperty(index = 7, value = "基础工资")
    private BigDecimal basicSalary;

    @ExcelProperty(index = 8, value = "岗位工资")
    private BigDecimal postSalary;

    @ExcelProperty(index = 9, value = "绩效工资")
    private BigDecimal meritSalary;

    @ExcelProperty(index = 10, value = "病假工资")
    private BigDecimal sickSalary;

    @ExcelProperty(index = 11, value = "补发工资")
    private BigDecimal backSalary;

    @ExcelProperty(index = 12, value = "加班工资")
    private BigDecimal overtimeSalary;

    /**
     * 奖金
     */

    @ExcelProperty(index = 13, value = "年终奖")
    private BigDecimal annualBonus;

    @ExcelProperty(index = 14, value = "安全奖")
    private BigDecimal safetyBonus;

    @ExcelProperty(index = 15, value = "综治奖")
    private BigDecimal stabilityBonus;

    @ExcelProperty(index = 16, value = "计生奖")
    private BigDecimal familyPlanningBonus;

    @ExcelProperty(index = 17, value = "先进奖")
    private BigDecimal excellenceBonus;

    @ExcelProperty(index = 18, value = "专项奖")
    private BigDecimal specialBonus;

    /**
     * 津贴
     */

    @ExcelProperty(index = 19, value = "独生子女津贴")
    private BigDecimal oneChildAllowance;

    @ExcelProperty(index = 20, value = "高温津贴")
    private BigDecimal hotWeatherAllowance;

    @ExcelProperty(index = 21, value = "全勤津贴")
    private BigDecimal fullAttendanceAllowance;

    @ExcelProperty(index = 22, value = "夜班津贴")
    private BigDecimal nightShiftAllowance;

    @ExcelProperty(index = 23, value = "值班补贴")
    private BigDecimal onDutyAllowance;

    @ExcelProperty(index = 24, value = "就餐补贴")
    private BigDecimal mealAllowance;

    @ExcelProperty(index = 25, value = "交通补贴")
    private BigDecimal trafficAllowance;

    @ExcelProperty(index = 26, value = "节日慰问金")
    private BigDecimal festivalAllowance;

    @ExcelProperty(index = 27, value = "安全岗岗位津贴")
    private BigDecimal safetyAllowance;

    @ExcelProperty(index = 28, value = "通讯补贴")
    private BigDecimal communicationAllowance;

    @ExcelProperty(index = 29, value = "其他")
    private BigDecimal otherAllowance;

    /**
     * 扣除项
     */

    @ExcelProperty(index = 30, value = "扣病假工资")
    private BigDecimal sickLeaveDeduct;

    @ExcelProperty(index = 31, value = "扣试用期/入离职结算")
    private BigDecimal entryExitDeduct;

    @ExcelProperty(index = 32, value = "扣全勤")
    private BigDecimal fullAttendanceDeduct;

    @ExcelProperty(index = 33, value = "扣季度绩效")
    private BigDecimal meritDeduct;

    /**
     * 实物
     */
    @ExcelProperty(index = 33, value = "生日卡")
    private BigDecimal birthdayCard;

    @ExcelProperty(index = 34, value = "清凉饮料")
    private BigDecimal coolDrink;

    @ExcelProperty(index = 35, value = "慰问品")
    private BigDecimal condolenceGoods;

    /**
     * 税后应扣
     */
    @ExcelProperty(index = 36, value = "公积金")
    private BigDecimal accumulationFund;

    @ExcelProperty(index = 37, value = "养老保险")
    private BigDecimal endowmentInsurance;

    @ExcelProperty(index = 38, value = "失业保险")
    private BigDecimal unemploymentInsurance;

    @ExcelProperty(index = 39, value = "医疗保险")
    private BigDecimal medicalInsurance;

    @ExcelProperty(index = 40, value = "工会费")
    private BigDecimal unionFees;

    @ExcelProperty(index = 41, value = "房租")
    private BigDecimal rent;

    @ExcelProperty(index = 42, value = "话费")
    private BigDecimal phoneBill;

    @ExcelProperty(index = 43, value = "个税")
    private BigDecimal individualIncomeTax;

    @ExcelProperty(index = 44, value = "其他税后应扣")
    private BigDecimal otherAftTaxDeduct;


    @ExcelProperty(index = 45, value = "应发工资")
    private BigDecimal shouldSalary;

    @ExcelProperty(index = 46, value = "实发工资")
    private BigDecimal actualSalary;


    @ExcelProperty(index = 47, value = "备注")
    private String remarks;

}
