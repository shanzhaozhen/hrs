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

    @ExcelProperty(index = 0, value = "部门名称")
    private String depName;

    @ExcelProperty(index = 0, value = "员工编号")
    private String staffCode;

    @ExcelProperty(index = 0, value = "员工姓名")
    private String staffName;

    @ExcelProperty(index = 0, value = "发放月份")
    @DateTimeFormat("yyyy-MM")
    private Date month;

    @ExcelProperty(index = 0, value = "发薪类型（工资、奖金）")
    private String type;


    @ExcelProperty(index = 0, value = "是否冻结")
    private Boolean freeze;


    @ExcelProperty(index = 0, value = "考核等级")
    private String appraise;

    /**
     * 工资
     */

    @ExcelProperty(index = 0, value = "基础工资")
    private BigDecimal basicSalary;

    @ExcelProperty(index = 0, value = "岗位工资")
    private BigDecimal postSalary;

    @ExcelProperty(index = 0, value = "绩效工资")
    private BigDecimal meritSalary;

    @ExcelProperty(index = 0, value = "病假工资")
    private BigDecimal sickSalary;

    @ExcelProperty(index = 0, value = "补发工资")
    private BigDecimal backSalary;

    @ExcelProperty(index = 0, value = "加班工资")
    private BigDecimal overtimeSalary;

    /**
     * 奖金
     */

    @ExcelProperty(index = 0, value = "年终奖")
    private BigDecimal annualBonus;

    @ExcelProperty(index = 0, value = "安全奖")
    private BigDecimal safetyBonus;

    @ExcelProperty(index = 0, value = "综治奖")
    private BigDecimal stabilityBonus;

    @ExcelProperty(index = 0, value = "计生奖")
    private BigDecimal familyPlanningBonus;

    @ExcelProperty(index = 0, value = "先进奖")
    private BigDecimal excellenceBonus;

    @ExcelProperty(index = 0, value = "专项奖")
    private BigDecimal specialBonus;

    /**
     * 津贴
     */

    @ExcelProperty(index = 0, value = "独生子女津贴")
    private BigDecimal oneChildAllowance;

    @ExcelProperty(index = 0, value = "高温津贴")
    private BigDecimal hotWeatherAllowance;

    @ExcelProperty(index = 0, value = "全勤津贴")
    private BigDecimal fullAttendanceAllowance;

    @ExcelProperty(index = 0, value = "夜班津贴")
    private BigDecimal nightShiftAllowance;

    @ExcelProperty(index = 0, value = "值班补贴")
    private BigDecimal onDutyAllowance;

    @ExcelProperty(index = 0, value = "就餐补贴")
    private BigDecimal mealAllowance;

    @ExcelProperty(index = 0, value = "交通补贴")
    private BigDecimal trafficAllowance;

    @ExcelProperty(index = 0, value = "节日慰问金")
    private BigDecimal festivalAllowance;

    @ExcelProperty(index = 0, value = "安全岗岗位津贴")
    private BigDecimal safetyAllowance;

    @ExcelProperty(index = 0, value = "通讯补贴")
    private BigDecimal communicationAllowance;

    @ExcelProperty(index = 0, value = "其他")
    private BigDecimal otherAllowance;

    /**
     * 扣除项
     */

    @ExcelProperty(index = 0, value = "扣病假工资")
    private BigDecimal sickLeaveDeduct;

    @ExcelProperty(index = 0, value = "扣试用期/入离职结算")
    private BigDecimal entryExitDeduct;

    @ExcelProperty(index = 0, value = "扣全勤")
    private BigDecimal fullAttendanceDeduct;

    @ExcelProperty(index = 0, value = "扣季度绩效")
    private BigDecimal meritDeduct;

    /**
     * 实物
     */
    @ExcelProperty(index = 0, value = "生日卡")
    private BigDecimal birthdayCard;

    @ExcelProperty(index = 0, value = "清凉饮料")
    private BigDecimal coolDrink;

    @ExcelProperty(index = 0, value = "慰问品")
    private BigDecimal condolenceGoods;

    /**
     * 税后应扣
     */
    @ExcelProperty(index = 0, value = "公积金")
    private BigDecimal accumulationFund;

    @ExcelProperty(index = 0, value = "养老保险")
    private BigDecimal endowmentInsurance;

    @ExcelProperty(index = 0, value = "失业保险")
    private BigDecimal unemploymentInsurance;

    @ExcelProperty(index = 0, value = "医疗保险")
    private BigDecimal medicalInsurance;

    @ExcelProperty(index = 0, value = "工会费")
    private BigDecimal unionFees;

    @ExcelProperty(index = 0, value = "房租")
    private BigDecimal rent;

    @ExcelProperty(index = 0, value = "话费")
    private BigDecimal phoneBill;

    @ExcelProperty(index = 0, value = "个税")
    private BigDecimal individualIncomeTax;

    @ExcelProperty(index = 0, value = "其他税后应扣")
    private BigDecimal otherAftTaxDeduct;


    @ExcelProperty(index = 0, value = "应发工资")
    private BigDecimal shouldSalary;

    @ExcelProperty(index = 0, value = "实发工资")
    private BigDecimal actualSalary;


    @ExcelProperty(index = 0, value = "备注")
    private String remarks;

}
