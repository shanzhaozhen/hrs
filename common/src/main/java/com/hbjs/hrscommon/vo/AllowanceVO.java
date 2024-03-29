package com.hbjs.hrscommon.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
public class AllowanceVO extends BaseInfoVO {

    private static final long serialVersionUID = 4309044854659052277L;

    @Schema(title = "主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Schema(title = "员工id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long staffId;

    @Schema(title = "员工编号")
    private String staffCode;

    @Schema(title = "员工姓名")
    private String staffName;

    @Schema(title = "部门ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long depId;

    @Schema(title = "津贴月份")
    private Date month;

    @Schema(title = "补发工资")
    private BigDecimal backSalary;

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

    @Schema(title = "就餐补贴")
    private BigDecimal mealAllowance;

    @Schema(title = "交通补贴")
    private BigDecimal trafficAllowance;

    @Schema(title = "通讯补贴")
    private BigDecimal communicationAllowance;

    @Schema(title = "节日慰问金")
    private BigDecimal festivalAllowance;

    @Schema(title = "其他补贴")
    private BigDecimal otherAllowance;

    @Schema(title = "生日卡")
    private BigDecimal birthdayCard;

    @Schema(title = "清凉饮料")
    private BigDecimal coolDrink;

    @Schema(title = "慰问品")
    private BigDecimal condolenceGoods;

    @Schema(title = "房租")
    private BigDecimal rent;

    @Schema(title = "话费")
    private BigDecimal phoneBill;

    @Schema(title = "备注")
    private String remarks;

}
