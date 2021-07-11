package com.hbjs.hrscommon.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "绩效考核季度设置VO实体")
public class PerformanceSettingVO extends BaseInfoVO {

    private static final long serialVersionUID = -5954089402792661484L;

    @Schema(title = "主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "开始考核月份")
    private Date startMonth;

    @Schema(title = "结束考核月份")
    private Date endMonth;

    @Schema(title = "考核年度")
    private Integer year;

    @Schema(title = "考核季度")
    private Integer quarter;

    @Schema(title = "备注")
    private String remarks;

}
