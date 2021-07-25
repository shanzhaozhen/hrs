package com.hbjs.hrscommon.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "员工薪资Form实体")
public class SalaryStaffForm {

    @Schema(title = "主键ID")
    @NotNull(groups = {Update.class}, message = "主键ID不能为空")
    private Long id;

    @Schema(title = "员工ID")
    @NotNull(groups = {Insert.class, Update.class}, message = "员工ID不能为空")
    private Long staffId;

    @Schema(title = "基础工资")
    private BigDecimal basicSalary;

    @Schema(title = "岗位工资")
    private BigDecimal postSalary;

    @Schema(title = "公积金基数")
    private BigDecimal accumulationFund;

    @Schema(title = "是否享有独生子女津贴")
    private Boolean haveOneChildAllowance;

    @Schema(title = "安全津贴档次")
    private String safetyGrade;

    @Schema(title = "高温津贴档次")
    private String hotWeatherGrade;

}
