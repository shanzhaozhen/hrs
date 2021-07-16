package com.hbjs.hrscommon.domain.hr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hbjs.hrscommon.domain.BaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("hr_salary_setting")
@Schema(description = "薪资配置DO实体")
public class SalarySettingDO extends BaseInfo {

    private static final long serialVersionUID = -8372741137366505438L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "高温津贴开始生效月份")
    private Date highTemperatureStartDate;

    @Schema(title = "高温津贴结束生效月份")
    private Date highTemperatureEndDate;

    @Schema(title = "高温津贴A标准")
    private Integer highTemperatureAllowanceA;

    @Schema(title = "高温津贴B标准")
    private Integer highTemperatureAllowanceB;

    @Schema(title = "高温津贴C标准")
    private Integer highTemperatureAllowanceC;

    @Schema(title = "值班费（工作日）（元/天）")
    private Integer dutyWeekFee;

    @Schema(title = "值班费（休息日前一天）（元/天）")
    private Integer dutyBeforeWeekFee;

    @Schema(title = "值班费（法定节假日前一天）（元/天）")
    private Integer dutyBeforeFestivalFee;

    @Schema(title = "值班费（休息日）（元/天）")
    private Integer dutyWeekendFee;

    @Schema(title = "值班费（法定节假日（春节假期除外））（元/天）")
    private Integer dutyFestivalFee;

    @Schema(title = "值班费（春节假期（不含除夕、初一、初二））（元/天）")
    private Integer dutyOutSpringFee;

    @Schema(title = "值班费（春节假期（除夕、初一、初二））（元/天）")
    private Integer dutyInSpringFee;

    @Schema(title = "备注")
    private String remarks;

}
