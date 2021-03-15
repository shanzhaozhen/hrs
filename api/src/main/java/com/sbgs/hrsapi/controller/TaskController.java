package com.sbgs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import com.sbgs.hrscommon.converter.DynamicScheduledTaskConverter;
import com.sbgs.hrscommon.dto.DynamicScheduledTaskDTO;
import com.sbgs.hrscommon.form.DynamicScheduledTaskForm;
import com.sbgs.hrscommon.vo.DynamicScheduledTaskVO;
import com.sbgs.hrscommon.vo.ResultBody;
import com.sbgs.hrsservice.service.TaskService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "task", description = "用户注册接口")
@RestController
@RequiredArgsConstructor
public class TaskController {

    private static final String GET_TASK_PAGE = "/task/page";
    private static final String GET_TASK_BY_ID = "/task/{taskId}";
    private static final String ADD_TASK = "/task";
    private static final String UPDATE_TASK = "/task";
    private static final String DELETE_TASK = "/task/{taskId}";
    private static final String RUN_TASK = "/task/{taskId}/run";
    private static final String START_TASK = "/task/{taskId}/start";
    private static final String STOP_TASK = "/task/{taskId}/stop";

    private final TaskService taskService;

    @GetMapping(GET_TASK_PAGE)
    @Operation(summary = "获取任务信息（分页）")
    public ResultBody<Page<DynamicScheduledTaskVO>> getDynamicScheduledTaskPage(Page<DynamicScheduledTaskDTO> page, String keyword) {
        return ResultBody.build(DynamicScheduledTaskConverter.toVO(taskService.getTaskPage(page, keyword)));
    }

    @GetMapping(GET_TASK_BY_ID)
    @Operation(summary = "获取任务信息（通过任务id）")
    public ResultBody<DynamicScheduledTaskVO> getDynamicScheduledTaskByDynamicScheduledTaskId(@PathVariable("taskId") @Parameter(description = "任务id", example = "1") Long taskId) {
        return ResultBody.build(DynamicScheduledTaskConverter.toVO(taskService.getTaskById(taskId)));
    }

    @PostMapping(ADD_TASK)
    @Operation(summary = "添加定时任务接口")
    public ResultBody<Long> addDynamicScheduledTask(@RequestBody @Validated({Insert.class}) DynamicScheduledTaskForm dynamicScheduledTaskForm) {
        return ResultBody.build(taskService.addTask(DynamicScheduledTaskConverter.toDTO(dynamicScheduledTaskForm)));
    }

    @PutMapping(UPDATE_TASK)
    @Operation(summary = "更新定时任务接口")
    public ResultBody<Long> updateDynamicScheduledTask(@RequestBody @Validated({Update.class}) DynamicScheduledTaskForm dynamicScheduledTaskForm) {
        return ResultBody.build(taskService.updateTask(DynamicScheduledTaskConverter.toDTO(dynamicScheduledTaskForm)));
    }

    @DeleteMapping(DELETE_TASK)
    @Operation(summary = "删除定时任务接口")
    public ResultBody<Long> deleteDynamicScheduledTask(@PathVariable("taskId") @Parameter(description = "任务id", example = "1") Long taskId) {
        return ResultBody.build(taskService.deleteTask(taskId));
    }

    @GetMapping(RUN_TASK)
    @Operation(summary = "运行定时任务接口")
    public ResultBody<Object> runDynamicScheduledTask(@PathVariable("taskId") @Parameter(description = "任务id", example = "1") Long taskId) {
        return ResultBody.build(taskService.runTask(taskId));
    }

    @GetMapping(START_TASK)
    @Operation(summary = "开始定时任务接口")
    public ResultBody<Object> startDynamicScheduledTask(@PathVariable("taskId") @Parameter(description = "任务id", example = "1") Long taskId) {
        return ResultBody.build(taskService.startTask(taskId));
    }

    @GetMapping(STOP_TASK)
    @Operation(summary = "停止定时任务接口")
    public ResultBody<Object> stopDynamicScheduledTask(@PathVariable("taskId") @Parameter(description = "任务id", example = "1") Long taskId) {
        return ResultBody.build(taskService.stopTask(taskId));
    }

}
