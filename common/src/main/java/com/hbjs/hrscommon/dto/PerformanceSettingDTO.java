package com.hbjs.hrscommon.dto;

import com.hbjs.hrscommon.domain.BaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "绩效考核季度设置DO实体")
public class PerformanceSettingDTO extends BaseInfo {

    private static final long serialVersionUID = -7636549628657407059L;

    @Schema(title = "主键ID")
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
