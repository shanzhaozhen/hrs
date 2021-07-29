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

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = false, chain = false)
@HeadFontStyle(fontHeightInPoints = 12)
@ContentFontStyle(fontHeightInPoints = 11)
@Schema(description = "工资导入导出实体")
public class SalaryExcel {

    @ExcelProperty(index = 0, value = {"工资明细表", "序号", "序号"})
    private Integer rowNum;

    @ExcelProperty(index = 1, value = {"工资明细表", "员工信息", "部门名称"})
    private String depName;

    @ExcelProperty(index = 2, value = {"工资明细表", "员工信息", "员工编号"})
    private String staffCode;

    @ExcelProperty(index = 3, value = {"工资明细表", "员工信息", "员工姓名"})
    private String staffName;

    @ExcelProperty(index = 4, value = {"工资明细表", "员工信息", "发放月份"})
    @DateTimeFormat("yyyy-MM")
    private Date month;

    @ExcelProperty(index = 5, value = {"工资明细表", "员工信息", "发薪类型（工资、奖金）"})
    private String type;

    @ExcelProperty(index = 6, value = {"工资明细表", "员工信息", "考核等级"})
    private String appraise;

    /**
     * 工资
     */

    @ExcelProperty(index = 7, value = {"工资明细表", "工资", "基础工资"})
    private BigDecimal basicSalary;

    @ExcelProperty(index = 8, value = {"工资明细表", "工资", "岗位工资"})
    private BigDecimal postSalary;

    @ExcelProperty(index = 9, value = {"工资明细表", "工资", "绩效工资"})
    private BigDecimal meritSalary;

    @ExcelProperty(index = 10, value = {"工资明细表", "工资", "病假工资"})
    private BigDecimal sickSalary;

    @ExcelProperty(index = 11, value = {"工资明细表", "工资", "补发工资"})
    private BigDecimal backSalary;

    @ExcelProperty(index = 12, value = {"工资明细表", "工资", "加班工资"})
    private BigDecimal overtimeSalary;

    @ExcelProperty(index = 13, value = {"工资明细表", "工资", "小计"})
    private BigDecimal salarySubtotal;

    /**
     * 奖金
     */

    @ExcelProperty(index = 14, value = {"工资明细表", "奖金", "年终奖"})
    private BigDecimal annualBonus;

    @ExcelProperty(index = 15, value = {"工资明细表", "奖金", "安全奖"})
    private BigDecimal safetyBonus;

    @ExcelProperty(index = 16, value = {"工资明细表", "奖金", "综治奖"})
    private BigDecimal stabilityBonus;

    @ExcelProperty(index = 17, value = {"工资明细表", "奖金", "计生奖"})
    private BigDecimal familyPlanningBonus;

    @ExcelProperty(index = 18, value = {"工资明细表", "奖金", "先进奖"})
    private BigDecimal excellenceBonus;

    @ExcelProperty(index = 19, value = {"工资明细表", "奖金", "专项奖"})
    private BigDecimal specialBonus;

    @ExcelProperty(index = 20, value = {"工资明细表", "奖金", "小计"})
    private BigDecimal bonusSubtotal;

    /**
     * 津贴
     */

    @ExcelProperty(index = 21, value = {"工资明细表", "津贴补贴", "独生子女津贴"})
    private BigDecimal oneChildAllowance;

    @ExcelProperty(index = 22, value = {"工资明细表", "津贴补贴", "高温津贴"})
    private BigDecimal hotWeatherAllowance;

    @ExcelProperty(index = 23, value = {"工资明细表", "津贴补贴", "全勤津贴"})
    private BigDecimal fullAttendanceAllowance;

    @ExcelProperty(index = 24, value = {"工资明细表", "津贴补贴", "夜班津贴"})
    private BigDecimal nightShiftAllowance;

    @ExcelProperty(index = 25, value = {"工资明细表", "津贴补贴", "值班补贴"})
    private BigDecimal onDutyAllowance;

    @ExcelProperty(index = 26, value = {"工资明细表", "津贴补贴", "就餐补贴"})
    private BigDecimal mealAllowance;

    @ExcelProperty(index = 27, value = {"工资明细表", "津贴补贴", "交通补贴"})
    private BigDecimal trafficAllowance;

    @ExcelProperty(index = 28, value = {"工资明细表", "津贴补贴", "节日慰问金"})
    private BigDecimal festivalAllowance;

    @ExcelProperty(index = 29, value = {"工资明细表", "津贴补贴", "安全岗岗位津贴"})
    private BigDecimal safetyAllowance;

    @ExcelProperty(index = 30, value = {"工资明细表", "津贴补贴", "其他"})
    private BigDecimal otherAllowance;

    @ExcelProperty(index = 31, value = {"工资明细表", "津贴补贴", "小计"})
    private BigDecimal allowanceSubtotal;

    /**
     * 税前扣款
     */

    @ExcelProperty(index = 32, value = {"工资明细表", "税前扣款", "扣病假工资"})
    private BigDecimal sickLeaveDeduct;

    @ExcelProperty(index = 33, value = {"工资明细表", "税前扣款", "扣试用期/入离职结算"})
    private BigDecimal entryExitDeduct;

    @ExcelProperty(index = 34, value = {"工资明细表", "税前扣款", "扣全勤"})
    private BigDecimal fullAttendanceDeduct;

    @ExcelProperty(index = 35, value = {"工资明细表", "税前扣款", "扣季度绩效"})
    private BigDecimal meritDeduct;

    @ExcelProperty(index = 36, value = {"工资明细表", "税前扣款", "小计"})
    private BigDecimal preTaxDeductSubtotal;


    @ExcelProperty(index = 37, value = {"工资明细表", "应发工资", "应发工资"})
    private BigDecimal shouldSalary;


    /**
     * 实物
     */

    @ExcelProperty(index = 38, value = {"工资明细表", "实物", "生日卡"})
    private BigDecimal birthdayCard;

    @ExcelProperty(index = 39, value = {"工资明细表", "实物", "清凉饮料"})
    private BigDecimal coolDrink;

    @ExcelProperty(index = 40, value = {"工资明细表", "实物", "慰问品"})
    private BigDecimal condolenceGoods;

    @ExcelProperty(index = 41, value = {"工资明细表", "实物", "实物小计"})
    private BigDecimal materialSubtotal;


    @ExcelProperty(index = 42, value = {"工资明细表", "计税收入", "计税收入"})
    private BigDecimal preTaxSalary;


    /**
     * 税后应扣
     */

    @ExcelProperty(index = 43, value = {"工资明细表", "税后应扣", "公积金"})
    private BigDecimal accumulationFund;

    @ExcelProperty(index = 44, value = {"工资明细表", "税后应扣", "养老保险"})
    private BigDecimal endowmentInsurance;

    @ExcelProperty(index = 45, value = {"工资明细表", "税后应扣", "失业保险"})
    private BigDecimal unemploymentInsurance;

    @ExcelProperty(index = 46, value = {"工资明细表", "税后应扣", "医疗保险"})
    private BigDecimal medicalInsurance;

    @ExcelProperty(index = 47, value = {"工资明细表", "税后应扣", "工会费"})
    private BigDecimal unionFees;

    @ExcelProperty(index = 48, value = {"工资明细表", "税后应扣", "房租"})
    private BigDecimal rent;

    @ExcelProperty(index = 49, value = {"工资明细表", "税后应扣", "话费"})
    private BigDecimal phoneBill;

    @ExcelProperty(index = 50, value = {"工资明细表", "税后应扣", "个税"})
    private BigDecimal individualIncomeTax;

    @ExcelProperty(index = 51, value = {"工资明细表", "税后应扣", "其他"})
    private BigDecimal otherAftTaxDeduct;

    @ExcelProperty(index = 52, value = {"工资明细表", "税后应扣", "应扣合计"})
    private BigDecimal aftTaxDeductSubtotal;


    /**
     * 实报实销
     */

    @ExcelProperty(index = 53, value = {"工资明细表", "实报实销", "通讯补贴"})
    private BigDecimal communicationAllowance;


    @ExcelProperty(index = 54, value = {"工资明细表", "实发工资", "实发工资"})
    private BigDecimal actualSalary;


    @ExcelProperty(index = 55, value = {"工资明细表", "备注", "备注"})
    private String remarks;


}
