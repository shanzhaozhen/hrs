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

import java.util.List;

@Tag(name = "task", description = "用户注册接口")
@RestController
@RequiredArgsConstructor
public class TaskController {

    private static final String GET_TASK_PAGE = "/task/page";
    private static final String GET_TASK_BY_ID = "/task/{taskId}";
    private static final String ADD_TASK = "/task";
    private static final String UPDATE_TASK = "/task";
    private static final String DELETE_TASK = "/task/{taskId}";
    private static final String BATCH_DELETE_TASK = "/task";
    private static final String RUN_TASK = "/task/{taskId}/run";
    private static final String START_TASK = "/task/{taskId}/start";
    private static final String STOP_TASK = "/task/{taskId}/stop";

    private final TaskService taskService;

    @Operation(summary = "获取任务信息（分页）")
    @GetMapping(GET_TASK_PAGE)
    public ResultBody<Page<DynamicScheduledTaskVO>> getDynamicScheduledTaskPage(Page<DynamicScheduledTaskDTO> page, String keyword) {
        return ResultBody.build(() -> DynamicScheduledTaskConverter.toVO(taskService.getTaskPage(page, keyword)));
    }

    @Operation(summary = "获取任务信息（通过任务id）")
    @GetMapping(GET_TASK_BY_ID)
    public ResultBody<DynamicScheduledTaskVO> getDynamicScheduledTaskByDynamicScheduledTaskId(@Parameter(description = "任务id", example = "1") @PathVariable("taskId") Long taskId) {
        return ResultBody.build(() -> DynamicScheduledTaskConverter.toVO(taskService.getTaskById(taskId)));
    }

    @Operation(summary = "添加定时任务接口")
    @PostMapping(ADD_TASK)
    public ResultBody<Long> addDynamicScheduledTask(@RequestBody @Validated({Insert.class}) DynamicScheduledTaskForm dynamicScheduledTaskForm) {
        return ResultBody.build(() -> taskService.addTask(DynamicScheduledTaskConverter.toDTO(dynamicScheduledTaskForm)));
    }

    @Operation(summary = "更新定时任务接口")
    @PutMapping(UPDATE_TASK)
    public ResultBody<Long> updateDynamicScheduledTask(@RequestBody @Validated({Update.class}) DynamicScheduledTaskForm dynamicScheduledTaskForm) {
        return ResultBody.build(() -> taskService.updateTask(DynamicScheduledTaskConverter.toDTO(dynamicScheduledTaskForm)));
    }

    @Operation(summary = "删除定时任务接口")
    @DeleteMapping(DELETE_TASK)
    public ResultBody<Long> deleteDynamicScheduledTask(@Parameter(description = "任务id", example = "1") @PathVariable("taskId") Long taskId) {
        return ResultBody.build(() -> taskService.deleteTask(taskId));
    }

    @Operation(summary = "批量删除定时任务接口")
    @DeleteMapping(BATCH_DELETE_TASK)
    public ResultBody<List<Long>> batchDeleteDynamicScheduledTask(@Parameter(description = "任务id", example = "[1, 2]")  @RequestBody List<Long> taskIds) {
        return ResultBody.build(() -> taskService.batchDeleteTask(taskIds));
    }

    @Operation(summary = "运行定时任务接口")
    @GetMapping(RUN_TASK)
    public ResultBody<Object> runDynamicScheduledTask(@Parameter(description = "任务id", example = "1") @PathVariable("taskId") Long taskId) {
        return ResultBody.build(() -> taskService.runTask(taskId));
    }

    @Operation(summary = "开始定时任务接口")
    @GetMapping(START_TASK)
    public ResultBody<Object> startDynamicScheduledTask(@Parameter(description = "任务id", example = "1") @PathVariable("taskId") Long taskId) {
        return ResultBody.build(() -> taskService.startTask(taskId));
    }

    @Operation(summary = "停止定时任务接口")
    @GetMapping(STOP_TASK)
    public ResultBody<Object> stopDynamicScheduledTask(@Parameter(description = "任务id", example = "1") @PathVariable("taskId") Long taskId) {
        return ResultBody.build(() -> taskService.stopTask(taskId));
    }

}
