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

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("hr_salary_staff")
@Schema(description = "员工薪资DO实体")
public class SalaryStaffDO extends BaseInfo {

    private static final long serialVersionUID = -8372741137366505438L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "关联的员工id")
    private Long staffId;

    @Schema(title = "基础工资")
    private BigDecimal basicSalary;

    @Schema(title = "岗位工资")
    private BigDecimal postSalary;

    @Schema(title = "是否享有独生子女津贴")
    private Boolean haveOneChildAllowance;

    @Schema(title = "安全津贴档次")
    private String safetyAllowance;

    @Schema(title = "高温津贴档次")
    private String highTemperatureAllowance;

    @Schema(title = "备注")
    private String remarks;

}
