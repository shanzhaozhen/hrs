package com.hbjs.hrscommon.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "工资Form实体")
public class SalaryForm {

    @Schema(title = "主键ID")
    @NotNull(groups = {Update.class}, message = "主键ID不能为空")
    private Long id;

    @Schema(title = "员工id")
    @NotNull(groups = {Insert.class, Update.class}, message = "员工ID不能为空")
    private Long staffId;

    @Schema(title = "发放月份")
    @JsonFormat(pattern="yyyy-MM", timezone = "GMT+8")
    private Date month;

    @Schema(title = "发薪类型（工资、奖金）")
    private String type;

    @Schema(title = "岗位等级")
    private String postLevel;

    @Schema(title = "考核等级")
    private String appraise;

    @Schema(title = "是否冻结")
    private Boolean freeze;

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
