package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import com.hbjs.hrscommon.converter.CustomTaskConverter;
import com.hbjs.hrscommon.dto.CustomTaskDTO;
import com.hbjs.hrscommon.form.CustomTaskForm;
import com.hbjs.hrscommon.vo.CustomTaskVO;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrsservice.service.TaskService;
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
    public ResultBody<Page<CustomTaskVO>> getCustomTaskPage(Page<CustomTaskDTO> page, String keyword) {
        return ResultBody.build(() -> CustomTaskConverter.toVO(taskService.getTaskPage(page, keyword)));
    }

    @Operation(summary = "获取任务信息（通过任务id）")
    @GetMapping(GET_TASK_BY_ID)
    public ResultBody<CustomTaskVO> getCustomTaskById(@Parameter(description = "任务id", example = "1") @PathVariable("taskId") Long taskId) {
        return ResultBody.build(() -> CustomTaskConverter.toVO(taskService.getTaskById(taskId)));
    }

    @Operation(summary = "添加定时任务接口")
    @PostMapping(ADD_TASK)
    public ResultBody<Long> addCustomTask(@RequestBody @Validated({Insert.class}) CustomTaskForm customTaskForm) {
        return ResultBody.build(() -> taskService.addTask(CustomTaskConverter.toDTO(customTaskForm)));
    }

    @Operation(summary = "更新定时任务接口")
    @PutMapping(UPDATE_TASK)
    public ResultBody<Long> updateCustomTask(@RequestBody @Validated({Update.class}) CustomTaskForm customTaskForm) {
        return ResultBody.build(() -> taskService.updateTask(CustomTaskConverter.toDTO(customTaskForm)));
    }

    @Operation(summary = "删除定时任务接口")
    @DeleteMapping(DELETE_TASK)
    public ResultBody<Long> deleteCustomTask(@Parameter(description = "任务id", example = "1") @PathVariable("taskId") Long taskId) {
        return ResultBody.build(() -> taskService.deleteTask(taskId));
    }

    @Operation(summary = "批量删除定时任务接口")
    @DeleteMapping(BATCH_DELETE_TASK)
    public ResultBody<List<Long>> batchDeleteCustomTask(@Parameter(description = "任务id", example = "[1, 2]")  @RequestBody List<Long> taskIds) {
        return ResultBody.build(() -> taskService.batchDeleteTask(taskIds));
    }

    @Operation(summary = "运行定时任务接口")
    @GetMapping(RUN_TASK)
    public ResultBody<?> runCustomTask(@Parameter(description = "任务id", example = "1") @PathVariable("taskId") Long taskId) {
        return ResultBody.build(() -> taskService.runTask(taskId));
    }

    @Operation(summary = "开始定时任务接口")
    @GetMapping(START_TASK)
    public ResultBody<Long> startCustomTask(@Parameter(description = "任务id", example = "1") @PathVariable("taskId") Long taskId) {
        return ResultBody.build(() -> taskService.startTask(taskId));
    }

    @Operation(summary = "停止定时任务接口")
    @GetMapping(STOP_TASK)
    public ResultBody<Long> stopCustomTask(@Parameter(description = "任务id", example = "1") @PathVariable("taskId") Long taskId) {
        return ResultBody.build(() -> taskService.stopTask(taskId));
    }

}
