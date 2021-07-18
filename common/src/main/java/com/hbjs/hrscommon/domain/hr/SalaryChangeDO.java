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
@TableName("hr_salary_change")
@Schema(description = "员工薪资变动DO实体")
public class SalaryChangeDO extends BaseInfo {

    private static final long serialVersionUID = -8372741137366505438L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "关联的员工id")
    private Long staffId;

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
    private String preSafetyAllowance;

    @Schema(title = "变更后安全津贴档次")
    private String postSafetyAllowance;

    @Schema(title = "变更前高温津贴档次")
    private String preHighTemperatureAllowance;

    @Schema(title = "变更后高温津贴档次")
    private String postHighTemperatureAllowance;

    @Schema(title = "生效日期")
    private Date effectiveDate;

    @Schema(title = "是否已执行")
    private Boolean executed;

    @Schema(title = "备注")
    private String remarks;

}
