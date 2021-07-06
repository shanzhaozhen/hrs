package com.hbjs.hrscommon.dto;

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
@Schema(description = "调动记录DTO实体")
public class SalaryChangeDTO extends BaseInfo {

    private static final long serialVersionUID = 6900707938735015442L;

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "员工id")
    private Long staffId;

    @Schema(title = "员工编号")
    private String staffCode;

    @Schema(title = "员工姓名")
    private String staffName;

    @Schema(title = "变更前基础工资")
    private BigDecimal preBasicSalary;

    @Schema(title = "变更后基础工资")
    private BigDecimal postBasicSalary;

    @Schema(title = "变更前岗位工资")
    private BigDecimal prePostSalary;

    @Schema(title = "变更后岗位工资")
    private BigDecimal postPostSalary;

    @Schema(title = "生效日期")
    private Date effectiveDate;

    @Schema(title = "备注")
    private String remarks;

}
