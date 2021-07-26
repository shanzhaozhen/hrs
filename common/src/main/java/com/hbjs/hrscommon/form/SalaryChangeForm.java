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
@Schema(description = "员工薪资变更记录Form实体")
public class SalaryChangeForm {

    @Schema(title = "主键ID")
    @NotNull(groups = {Update.class}, message = "id不能为空")
    private Long id;

    @Schema(title = "员工id")
    @NotNull(groups = {Insert.class, Update.class}, message = "未选择员工")
    private Long staffId;

    @Schema(title = "变更前基础工资")
    private BigDecimal preBasicSalary;

    @Schema(title = "变更后基础工资")
    private BigDecimal postBasicSalary;

    @Schema(title = "变更前岗位工资")
    private BigDecimal prePostSalary;

    @Schema(title = "变更后岗位工资")
    private BigDecimal postPostSalary;

    @Schema(title = "变更前公积金基数")
    private BigDecimal preAccumulationFund;

    @Schema(title = "变更后公积金基数")
    private BigDecimal postAccumulationFund;

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
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date effectiveDate;

    @Schema(title = "变更日期")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date changeDate;

    @Schema(title = "是否已执行")
    private Boolean executed;

    @Schema(title = "备注")
    private String remarks;

}
