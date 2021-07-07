package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.SalaryChangeDTO;

import java.util.List;

public interface SalaryChangeService {

    /**
     * 获取调动记录
     * @param page
     * @param staffId
     * @param keyword
     * @param depId
     * @return
     */
    Page<SalaryChangeDTO> getSalaryChangePage(Page<SalaryChangeDTO> page, Long staffId, String keyword, Long depId);

    /**
     * 通过ID获取调动记录
     * @param salaryChangeId
     * @return
     */
    SalaryChangeDTO getSalaryChangeById(Long salaryChangeId);

    /**
     * 添加调动记录
     * @param salaryChangeDTO
     * @return
     */
    Long addSalaryChange(SalaryChangeDTO salaryChangeDTO);

    /**
     * 更新调动记录
     * @param salaryChangeDTO
     * @return
     */
    Long updateSalaryChange(SalaryChangeDTO salaryChangeDTO);

    /**
     * 删除调动记录
     * @param salaryChangeId
     * @return
     */
    Long deleteSalaryChange(Long salaryChangeId);

    /**
     * 批量删除调动记录
     * @param salaryChangeIds
     * @return
     */
    List<Long> batchDeleteSalaryChange(List<Long> salaryChangeIds);

    /**
     * 执行调动记录
     * @param salaryChangeId
     * @return
     */
    Long runChange(Long salaryChangeId);

    /**
     * 执行调动记录
     * @param salaryChangeDTO
     * @return
     */
    Long runChange(SalaryChangeDTO salaryChangeDTO);

    /**
     * 定期检查执行调动
     * @param days 多少天内
     * @return
     */
    void runChange(int days, boolean skipExecuted);

}
