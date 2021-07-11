package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.PerformanceSettingDTO;

import java.util.List;

public interface PerformanceSettingService {

    /**
     * 获取绩效设置的分页列表
     * @param page
     * @param keyword
     * @return
     */
    Page<PerformanceSettingDTO> getPerformanceSettingPage(Page<PerformanceSettingDTO> page, String keyword);

    /**
     * 通过绩效设置id获取
     * @param staffId
     * @return
     */
    PerformanceSettingDTO getPerformanceSettingById(Long staffId);

    /**
     * 新增绩效设置
     * @param staffDTO
     * @return
     */
    Long addPerformanceSetting(PerformanceSettingDTO staffDTO);

    /**
     * 修改绩效设置
     * @param staffDTO
     * @return
     */
    Long updatePerformanceSetting(PerformanceSettingDTO staffDTO);

    /**
     * 删除绩效设置(通过绩效设置id删除)
     * @param staffId
     */
    Long deletePerformanceSetting(Long staffId);

    /**
     * 批量删除绩效设置(通过绩效设置id删除)
     * @param staffIds
     * @return
     */
    List<Long> batchDeletePerformanceSetting(List<Long> staffIds);

    /**
     * 导出绩效设置
     * @param keyword
     * @param depId
     */
    void exportPerformanceSetting(String keyword, Long depId);

    /**
     * 打印绩效设置
     * @param staffId
     */
    void printPerformanceSetting(Long staffId);

}
