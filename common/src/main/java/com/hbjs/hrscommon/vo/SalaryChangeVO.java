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
@Schema(description = "员工薪资变更记录VO实体")
public class SalaryChangeVO extends BaseInfoVO {

    private static final long serialVersionUID = -8542061240639491075L;

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

    @Schema(title = "变更前基础工资")
    private BigDecimal preBasicSalary;

    @Schema(title = "变更后基础工资")
    private BigDecimal postBasicSalary;

    @Schema(title = "变更前岗位工资")
    private BigDecimal prePostSalary;

    @Schema(title = "变更后岗位工资")
    private BigDecimal postPostSalary;

    @Schema(title = "变更前是否享有独生子女津贴")
    private Boolean preHaveOneChildAllowance;

    @Schema(title = "变更后是否享有独生子女津贴")
    private Boolean postHaveOneChildAllowance;

    @Schema(title = "变更前安全津贴档次")
    private String preSafetyGrade;

    @Schema(title = "变更后安全津贴档次")
    private String postSafetyGrade;

    @Schema(title = "变更前高温津贴档次")
    private String preHotWeatherGrade;

    @Schema(title = "变更后高温津贴档次")
    private String postHotWeatherGrade;

    @Schema(title = "生效日期")
    private Date effectiveDate;

    @Schema(title = "变更日期")
    private Date changeDate;

    @Schema(title = "是否已执行")
    private Boolean executed;

    @Schema(title = "备注")
    private String remarks;

}
