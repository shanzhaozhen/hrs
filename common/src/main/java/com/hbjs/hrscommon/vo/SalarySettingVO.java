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
@Schema(description = "薪资配置DTO实体")
public class SalarySettingVO extends BaseInfoVO {

    private static final long serialVersionUID = 6496201198427599779L;

    @Schema(title = "主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Schema(title = "公积金基数比例")
    private BigDecimal accumulationFundRate;

    @Schema(title = "养老保险基数比例")
    private BigDecimal endowmentInsuranceRate;

    @Schema(title = "失业保险基数比例")
    private BigDecimal unemploymentInsuranceRate;

    @Schema(title = "医疗保险基数比例")
    private BigDecimal medicalInsuranceRate;

    @Schema(title = "养老保险基数下限")
    private BigDecimal endowmentInsuranceLower;

    @Schema(title = "养老保险基数上限")
    private BigDecimal endowmentInsuranceUpper;

    @Schema(title = "失业保险基数下限")
    private BigDecimal unemploymentInsuranceLower;

    @Schema(title = "失业保险基数上限")
    private BigDecimal unemploymentInsuranceUpper;

    @Schema(title = "医疗保险基数下限")
    private BigDecimal medicalInsuranceLower;

    @Schema(title = "医疗保险基数上限")
    private BigDecimal medicalInsuranceUpper;

    @Schema(title = "绩效工资基数")
    private BigDecimal meritSalary;

    @Schema(title = "绩效A发放比例")
    private Integer meritA;

    @Schema(title = "绩效B发放比例")
    private Integer meritB;

    @Schema(title = "绩效C发放比例")
    private Integer meritC;

    @Schema(title = "绩效D发放比例")
    private Integer meritD;

    @Schema(title = "绩效E发放比例")
    private Integer meritE;

    @Schema(title = "绩效F发放比例")
    private Integer meritF;

    @Schema(title = "全勤津贴标准（元/月）")
    private BigDecimal fullAttendanceAllowance;

    @Schema(title = "就餐补贴（元/月）")
    private BigDecimal mealAllowance;

    @Schema(title = "交通补贴（自行到达）A（元/月）")
    private BigDecimal trafficAllowanceOwnA;

    @Schema(title = "交通补贴（自行到达）B（元/月）")
    private BigDecimal trafficAllowanceOwnB;

    @Schema(title = "交通补贴（自行到达）C（元/月）")
    private BigDecimal trafficAllowanceOwnC;

    @Schema(title = "交通补贴（乘坐接驳车）A（元/月）")
    private BigDecimal trafficAllowanceBusA;

    @Schema(title = "交通补贴（乘坐接驳车）B（元/月）")
    private BigDecimal trafficAllowanceBusB;

    @Schema(title = "交通补贴（乘坐接驳车）C（元/月）")
    private BigDecimal trafficAllowanceBusC;

    @Schema(title = "安全岗岗位津贴A")
    private BigDecimal safetyAllowanceA;

    @Schema(title = "安全岗岗位津贴B")
    private BigDecimal safetyAllowanceB;

    @Schema(title = "安全岗岗位津贴C")
    private BigDecimal safetyAllowanceC;

    @Schema(title = "独生子女津贴标准（元/月）")
    private BigDecimal oneChildAllowance;

    @Schema(title = "高温津贴开始生效月份")
    private Integer hotWeatherStartMonth;

    @Schema(title = "高温津贴结束生效月份")
    private Integer hotWeatherEndMonth;

    @Schema(title = "高温津贴A标准")
    private BigDecimal hotWeatherAllowanceA;

    @Schema(title = "高温津贴B标准")
    private BigDecimal hotWeatherAllowanceB;

    @Schema(title = "高温津贴C标准")
    private BigDecimal hotWeatherAllowanceC;

    @Schema(title = "值班费（工作日）（元/天）")
    private BigDecimal dutyWeekFee;

    @Schema(title = "值班费（休息日前一天）（元/天）")
    private BigDecimal dutyBeforeWeekFee;

    @Schema(title = "值班费（法定节假日前一天）（元/天）")
    private BigDecimal dutyBeforeFestivalFee;

    @Schema(title = "值班费（休息日）（元/天）")
    private BigDecimal dutyWeekendFee;

    @Schema(title = "值班费（法定节假日（春节假期除外））（元/天）")
    private BigDecimal dutyFestivalFee;

    @Schema(title = "值班费（春节假期（不含除夕、初一、初二））（元/天）")
    private BigDecimal dutyOutSpringFee;

    @Schema(title = "值班费（春节假期（除夕、初一、初二））（元/天）")
    private BigDecimal dutyInSpringFee;

    @Schema(title = "工会费")
    private BigDecimal unionFees;

    @Schema(title = "加班费基数")
    private BigDecimal overtimeFees;

    @Schema(title = "加班费基数计算方式")
    private String overtimeMode;

    @Schema(title = "备注")
    private String remarks;

}
