package com.sbgs.hrsservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbgs.hrscommon.utils.CronUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.sbgs.hrscommon.converter.CustomTaskConverter;
import com.sbgs.hrscommon.domain.sys.CustomTaskDO;
import com.sbgs.hrscommon.dto.CustomTaskDTO;
import com.sbgs.hrscommon.dto.MethodInfo;
import com.sbgs.hrscommon.utils.CustomBeanUtils;
import com.sbgs.hrscommon.utils.CustomClassUtils;
import com.sbgs.hrscommon.utils.SpringContextUtils;
import com.sbgs.hrsrepo.mapper.TaskMapper;
import com.sbgs.hrsservice.schedule.CronTaskRegistrar;
import com.sbgs.hrsservice.schedule.SchedulingRunnable;
import com.sbgs.hrsservice.service.TaskService;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final CronTaskRegistrar cronTaskRegistrar;

    private final TaskMapper taskMapper;

    @Override
    public Page<CustomTaskDTO> getTaskPage(Page<CustomTaskDTO> page, String keyword) {
        return taskMapper.getCustomTaskPage(page, keyword);
    }

    @Override
    public List<CustomTaskDTO> getAllTask() {
        return taskMapper.getAllCustomTask();
    }

    @Override
    public CustomTaskDTO getTaskById(Long customTaskId) {
        CustomTaskDTO customTaskDTO = taskMapper.getCustomTaskById(customTaskId);
        Assert.notNull(customTaskDTO, "获取失败：没有找到该定时任务或已被删除");
        return customTaskDTO;
    }

    @Override
    @Transactional
    public Long addTask(CustomTaskDTO customTaskDTO) {
        Assert.isTrue(CronUtils.isValidExpression(customTaskDTO.getCron()), "cron表达式不正确");
        CustomTaskDO customTaskDO = CustomTaskConverter.toDO(customTaskDTO);
        this.validateTask(customTaskDTO);
        taskMapper.insert(customTaskDO);
        if (customTaskDO.getOpen()) {
            this.startTask(customTaskDTO);
        }
        return customTaskDO.getId();
    }

    @Override
    @Transactional
    public Long updateTask(CustomTaskDTO customTaskDTO) {
        Assert.notNull(customTaskDTO.getId(), "定时任务id不能为空");
        CustomTaskDO customTaskDO = taskMapper.selectById(customTaskDTO.getId());
        Assert.notNull(customTaskDO, "更新失败：没有找到该定时任务或已被删除");
        this.validateTask(customTaskDTO);
        CustomBeanUtils.copyPropertiesExcludeMeta(customTaskDTO, customTaskDO);
        taskMapper.updateById(customTaskDO);

        if (customTaskDO.getOpen()) {
            this.startTask(customTaskDTO);
        } else {
            this.stopTask(customTaskDO.getId());
        }
        return customTaskDO.getId();
    }

    @Override
    public void validateTask(CustomTaskDTO customTaskDTO) {
        Assert.isTrue(CronSequenceGenerator.isValidExpression(customTaskDTO.getCron()), "cron表达式不正确");
        Object object = SpringContextUtils.getBean(customTaskDTO.getBeanName());
        Assert.notNull(object, "Bean不存在");
        MethodInfo methodInfo = JSON.parseObject(customTaskDTO.getMethodInfo(), MethodInfo.class);
        Assert.isTrue(ClassUtils.hasMethod(object.getClass(), methodInfo.getMethodName(), methodInfo.getParamTypes()), "该方法不存在");
        // 验证参数是否能正常转换，没报错则正常
        CustomClassUtils.methodParamInfoToParams(customTaskDTO.getParamInfo());
        Assert.notNull(methodInfo, "没有有效的方法信息");
    }

    @Override
    @Transactional
    public Long deleteTask(Long customTaskId) {
        CustomTaskDTO customTaskDTO = this.getTaskById(customTaskId);
        Assert.notNull(customTaskDTO, "删除失败：没有找到该定时任务或已被删除");
        taskMapper.deleteById(customTaskId);
        stopTask(customTaskId);
        return customTaskId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteTask(@NotEmpty(message = "没有需要删除的任务") List<Long> taskIds) {
        for (Long taskId : taskIds) {
            this.deleteTask(taskId);
        }
        return taskIds;
    }

    @Override
    public Object runTask(Long taskId) {
        CustomTaskDTO customTaskDTO = this.getTaskById(taskId);
        Assert.notNull(customTaskDTO, "运行失败：没有找到该定时任务或已被删除");
        return this.runTask(customTaskDTO);
    }

    @Override
    public Object runTask(@NotNull CustomTaskDTO customTaskDTO) {
        try {
            MethodInfo methodInfo = JSON.parseObject(customTaskDTO.getMethodInfo(), MethodInfo.class);
            Object[] params = CustomClassUtils.methodParamInfoToParams(customTaskDTO.getParamInfo());
            return CustomClassUtils.executeMethod(customTaskDTO.getBeanName(), methodInfo.getMethodName(), methodInfo.getParamTypes(), params);
        } catch (Exception e) {
            log.error("定时任务执行异常 - bean：{}，方法：{}，参数：{}", customTaskDTO.getBeanName(), customTaskDTO.getMethodInfo(), customTaskDTO.getParamInfo(), e);
            throw new IllegalArgumentException("定时任务执行异常");
        }
    }

    @Override
    @Transactional
    public Long startTask(Long taskId) {
        CustomTaskDTO customTaskDTO = this.getTaskById(taskId);
        Assert.notNull(customTaskDTO, "开始失败：没有找到该定时任务或已被删除");
        return this.startTask(customTaskDTO);
    }

    @Override
    @Transactional
    public Long startTask(@NotNull CustomTaskDTO customTaskDTO) {
        MethodInfo methodInfo = JSON.parseObject(customTaskDTO.getMethodInfo(), MethodInfo.class);
        Object[] params = CustomClassUtils.methodParamInfoToParams(customTaskDTO.getParamInfo());
        SchedulingRunnable task = new SchedulingRunnable(customTaskDTO.getBeanName(), methodInfo.getMethodName(), methodInfo.getParamTypes(),params);
        cronTaskRegistrar.addCronTask(customTaskDTO.getId(), task, customTaskDTO.getCron());
        if (!customTaskDTO.getOpen()) {
            CustomTaskDO customTaskDO = taskMapper.selectById(customTaskDTO.getId());
            Assert.notNull(customTaskDO, "开启失败：没有找到该定时任务或已被删除");
            customTaskDO.setOpen(true);
            taskMapper.updateById(customTaskDO);
        }
        return customTaskDTO.getId();
    }

    @Override
    public Long stopTask(@NotNull Long taskId) {
        cronTaskRegistrar.removeCronTask(taskId);
        CustomTaskDO customTaskDO = taskMapper.selectById(taskId);
        Assert.notNull(customTaskDO, "停止失败：没有找到该定时任务或已被删除");
        if (customTaskDO.getOpen()) {
            customTaskDO.setOpen(false);
            taskMapper.updateById(customTaskDO);
        }
        return taskId;
    }

    @Override
    public void initTask() {
        log.info("===开始初始化定时任务===");
        List<CustomTaskDTO> list = this.getAllTask();
        for (CustomTaskDTO customTaskDTO : list) {
            if (customTaskDTO.getOpen()) {
                this.startTask(customTaskDTO);
            }
        }
        log.info("===定时任务初始化完成===");
    }
}
