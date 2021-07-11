package com.hbjs.hrscommon.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "绩效评价Form实体")
public class PerformanceForm {

    @Schema(title = "主键ID")
    @NotNull(groups = {Update.class}, message = "绩效评价id不能为空")
    private Long id;

    @Schema(title = "员工id")
    @NotNull(groups = {Insert.class, Update.class}, message = "员工id不能为空")
    private Long staffId;

    @Schema(title = "关联的考核季度id")
    private Long performanceSettingId;

    @Schema(title = "考核年度")
    private Integer year;

    @Schema(title = "考核季度")
    private Integer quarter;

    @Schema(title = "考核等级")
    private String appraise;

    @Schema(title = "备注")
    private String remarks;

}
