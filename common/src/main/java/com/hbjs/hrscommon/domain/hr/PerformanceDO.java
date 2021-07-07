package com.hbjs.hrscommon.domain.hr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hbjs.hrscommon.domain.BaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("hr_performance")
@Schema(description = "绩效评价DO实体")
public class PerformanceDO extends BaseInfo {

    private static final long serialVersionUID = 6818710700466932371L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "员工id")
    private Long staffId;

    @Schema(title = "关联的考核季度id")
    private Long performanceSettingId;

    @Schema(title = "考核月份")
    private Date month;

    @Schema(title = "考核年份")
    private Integer year;

    @Schema(title = "考核季度")
    private Integer quarter;

    @Schema(title = "考核等级")
    private String appraise;

}
