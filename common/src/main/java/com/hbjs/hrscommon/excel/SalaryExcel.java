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

    @Schema(title = "基础工资")
    private BigDecimal basicSalary;

    @Schema(title = "岗位工资")
    private BigDecimal postSalary;

    @Schema(title = "绩效工资")
    private BigDecimal meritSalary;

    @Schema(title = "病假工资")
    private BigDecimal sickSalary;

    @Schema(title = "补发工资")
    private BigDecimal backSalary;

    @Schema(title = "加班工资")
    private BigDecimal overtimeSalary;

    @Schema(title = "工资小计")
    private BigDecimal salarySubtotal;

    /**
     * 奖金
     */

    @Schema(title = "年终奖")
    private BigDecimal annualBonus;

    @Schema(title = "安全奖")
    private BigDecimal safetyBonus;

    @Schema(title = "综治奖")
    private BigDecimal stabilityBonus;

    @Schema(title = "计生奖")
    private BigDecimal familyPlanningBonus;

    @Schema(title = "先进奖")
    private BigDecimal excellenceBonus;

    @Schema(title = "专项奖")
    private BigDecimal specialBonus;

    @Schema(title = "奖金小计")
    private BigDecimal bonusSubtotal;

    /**
     * 津贴
     */

    @Schema(title = "独生子女津贴")
    private BigDecimal oneChildAllowance;

    @Schema(title = "高温津贴")
    private BigDecimal hotWeatherAllowance;

    @Schema(title = "全勤津贴")
    private BigDecimal fullAttendanceAllowance;

    @Schema(title = "夜班津贴")
    private BigDecimal nightShiftAllowance;

    @Schema(title = "值班补贴")
    private BigDecimal onDutyAllowance;

    @Schema(title = "就餐补贴")
    private BigDecimal mealAllowance;

    @Schema(title = "交通补贴")
    private BigDecimal trafficAllowance;

    @Schema(title = "节日慰问金")
    private BigDecimal festivalAllowance;

    @Schema(title = "安全岗岗位津贴")
    private BigDecimal safetyAllowance;

    @Schema(title = "通讯补贴")
    private BigDecimal communicationAllowance;

    @Schema(title = "其他")
    private BigDecimal otherAllowance;

    @Schema(title = "津贴小计")
    private BigDecimal allowanceSubtotal;

    /**
     * 税前扣款
     */

    @Schema(title = "扣病假工资")
    private BigDecimal sickLeaveDeduct;

    @Schema(title = "扣试用期/入离职结算")
    private BigDecimal entryExitDeduct;

    @Schema(title = "扣全勤")
    private BigDecimal fullAttendanceDeduct;

    @Schema(title = "扣季度绩效")
    private BigDecimal meritDeduct;

    @Schema(title = "税前扣款小计")
    private BigDecimal preTaxDeductSubtotal;

    /**
     * 实物
     */
    @Schema(title = "生日卡")
    private BigDecimal birthdayCard;

    @Schema(title = "清凉饮料")
    private BigDecimal coolDrink;

    @Schema(title = "慰问品")
    private BigDecimal condolenceGoods;

    @Schema(title = "实物小计")
    private BigDecimal materialSubtotal;

    /**
     * 税后应扣
     */
    @Schema(title = "公积金")
    private BigDecimal accumulationFund;

    @Schema(title = "养老保险")
    private BigDecimal endowmentInsurance;

    @Schema(title = "失业保险")
    private BigDecimal unemploymentInsurance;

    @Schema(title = "医疗保险")
    private BigDecimal medicalInsurance;

    @Schema(title = "工会费")
    private BigDecimal unionFees;

    @Schema(title = "房租")
    private BigDecimal rent;

    @Schema(title = "话费")
    private BigDecimal phoneBill;

    @Schema(title = "个税")
    private BigDecimal individualIncomeTax;

    @Schema(title = "其他税后应扣")
    private BigDecimal otherAftTaxDeduct;

    @Schema(title = "税后应扣小计")
    private BigDecimal aftTaxDeductSubtotal;


    @Schema(title = "计税收入")
    private BigDecimal preTaxSalary;

    @Schema(title = "应发工资")
    private BigDecimal shouldSalary;

    @Schema(title = "实发工资")
    private BigDecimal actualSalary;


    @Schema(title = "备注")
    private String remarks;

}
