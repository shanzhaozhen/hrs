package com.hbjs.hrscommon.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "绩效考核季度设置DO实体")
public class PerformanceSettingForm {

    @Schema(title = "主键ID")
    @NotNull(groups = {Update.class}, message = "绩效设置id不能为空")
    private Long id;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "开始考核月份")
    @JsonFormat(pattern="yyyy-MM", timezone = "GMT+8")
    private Date startMonth;

    @Schema(title = "结束考核月份")
    @JsonFormat(pattern="yyyy-MM", timezone = "GMT+8")
    private Date endMonth;

    @Schema(title = "考核年度")
    private Integer year;

    @Schema(title = "考核季度")
    private Integer quarter;

    @Schema(title = "备注")
    private String remarks;

}
