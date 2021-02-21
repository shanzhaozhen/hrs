package com.sbgs.hrscommon.domain.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sbgs.hrscommon.domain.BaseInfo;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_dynamic_scheduled_task")
@Schema(description = "动态定时任务DO实体")
public class DynamicScheduledTaskDO extends BaseInfo {

    private static final long serialVersionUID = 216445339652015543L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.AUTO)
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
