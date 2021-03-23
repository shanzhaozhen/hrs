package com.sbgs.hrscommon.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import com.sbgs.hrscommon.domain.BaseInfo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "动态定时任务From实体")
public class DynamicScheduledTaskForm {

    @Schema(title = "主键ID")
    @NotNull(groups = {Update.class}, message = "任务id不能为空")
    private Long id;

    @Schema(title = "名称")
    @NotEmpty(groups = {Insert.class, Update.class}, message = "任务名称不能为空")
    private String name;

    @Schema(title = "cron表达式")
    @NotEmpty(groups = {Insert.class, Update.class}, message = "cron表达式不能为空")
    private String cron;

    @Schema(title = "注册在容器的bean名称")
    private String beanName;

    @Schema(title = "对应的方法信息")
    private String methodInfo;

    @Schema(title = "参数")
    private String paramInfo;

    @Schema(title = "开启状态")
    @NotNull(groups = {Insert.class, Update.class}, message = "开启状态不能为空")
    private Boolean open;

    @Schema(title = "描述")
    private String description;

}
