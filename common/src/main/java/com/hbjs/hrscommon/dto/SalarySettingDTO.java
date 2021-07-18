package com.hbjs.hrscommon.dto;

import com.hbjs.hrscommon.domain.BaseInfo;
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
@Schema(description = "薪资配置DTO实体")
public class SalarySettingDTO extends BaseInfoVO {

    private static final long serialVersionUID = -8372741137366505438L;

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "加班费基数")
    private BigDecimal overTimeBase;

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

    @Schema(title = "独生子女津贴标准（元/天）")
    private BigDecimal oneChildAllowance;

    @Schema(title = "高温津贴开始生效月份")
    private Date highTemperatureStartDate;

    @Schema(title = "高温津贴结束生效月份")
    private Date highTemperatureEndDate;

    @Schema(title = "高温津贴A标准")
    private BigDecimal highTemperatureAllowanceA;

    @Schema(title = "高温津贴B标准")
    private BigDecimal highTemperatureAllowanceB;

    @Schema(title = "高温津贴C标准")
    private BigDecimal highTemperatureAllowanceC;

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

    @Schema(title = "备注")
    private String remarks;

}
