package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.CustomTaskDTO;

import java.util.List;

public interface TaskService {

    /**
     * 获取定时任务的分页列表
     * @param page
     * @param keyword
     * @return
     */
    Page<CustomTaskDTO> getTaskPage(Page<CustomTaskDTO> page, String keyword);

    /**
     * 获取所有定时任务
     * @return
     */
    List<CustomTaskDTO> getAllTask();

    /**
     * 通过定时任务id获取
     * @param taskId
     * @return
     */
    CustomTaskDTO getTaskById(Long taskId);

    /**
     * 新增定时任务
     * @param customTaskDTO
     * @return
     */
    Long addTask(CustomTaskDTO customTaskDTO);

    /**
     * 修改定时任务
     * @param customTaskDTO
     * @return
     */
    Long updateTask(CustomTaskDTO customTaskDTO);

    /**
     * 检查定时任务调用的方式和参数是否有误
     * @param customTaskDTO
     */
    void validateTask(CustomTaskDTO customTaskDTO);

    /**
     * 删除定时任务(通过定时任务id删除)
     * @param taskId
     */
    Long deleteTask(Long taskId);

    /**
     * 批量删除定时任务(通过定时任务id删除)
     * @param taskIds
     * @return
     */
    List<Long> batchDeleteTask(List<Long> taskIds);

    /**
     * 执行定时任务（通过id查找）
     * @param taskId
     * @return
     */
    Object runTask(Long taskId);

    /**
     * 执行定时任务（通过实体执行）
     * @param customTaskDTO
     * @return
     */
    Object runTask(CustomTaskDTO customTaskDTO);

    /**
     * 开始定时任务（通过id查找）
     * @param taskId
     * @return
     */
    Long startTask(Long taskId);

    /**
     * 开始定时任务（通过实体执行）
     * @param customTaskDTO
     * @return
     */
    Long startTask(CustomTaskDTO customTaskDTO);

    /**
     * 停止定时任务（通过id查找）
     * @param taskId
     * @return
     */
    Long stopTask(Long taskId);

    /**
     * 初始化定时任务
     * @return
     */
    void initTask();

}
