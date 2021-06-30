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
@TableName("hr_salary")
@Schema(description = "工资DO实体")
public class SalaryDO extends BaseInfo {

    private static final long serialVersionUID = -8372741137366505438L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "员工id")
    private Long staffId;

    @Schema(title = "发薪类型（工资、奖金）")
    private String type;

    @Schema(title = "基础工资")
    private BigDecimal basicSalary;

    @Schema(title = "岗位工资")
    private BigDecimal postSalary;

    @Schema(title = "绩效工资")
    private BigDecimal meritSalary;

    @Schema(title = "病假工资")
    private BigDecimal sickSalary;

    @Schema(title = "补发工资")
    private BigDecimal backSalary;

    @Schema(title = "加班工资")
    private BigDecimal overtimeSalary;



}
