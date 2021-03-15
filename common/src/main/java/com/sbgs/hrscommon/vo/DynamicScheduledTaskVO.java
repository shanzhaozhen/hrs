package com.sbgs.hrscommon.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "动态定时任务VO实体")
public class DynamicScheduledTaskVO extends BaseInfoVO {

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "cron表达式")
    private String cron;

    @Schema(title = "注册在容器的bean名称")
    private String beanName;

    @Schema(title = "对应的方法信息")
    private String methodInfo;

    @Schema(title = "参数")
    private String paramInfo;

    @Schema(title = "开启状态")
    private Boolean open;

    @Schema(title = "描述")
    private String description;

}
