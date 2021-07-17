package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.SalarySettingDTO;

import java.util.List;

public interface SalarySettingService {

    /**
     * 获取薪资配置的分页列表
     * @param page
     * @return
     */
    Page<SalarySettingDTO> getSalarySettingPage(Page<SalarySettingDTO> page);

    /**
     * 通过薪资配置id获取
     * @param salarySettingId
     * @return
     */
    SalarySettingDTO getSalarySettingById(Long salarySettingId);

    /**
     * 获取最后一次修改的薪资配置
     * @return
     */
    SalarySettingDTO getSalarySettingNew();

    /**
     * 新增薪资配置
     * @param salarySettingDTO
     * @return
     */
    Long addSalarySetting(SalarySettingDTO salarySettingDTO);

    /**
     * 修改薪资配置
     * @param salarySettingDTO
     * @return
     */
    Long updateSalarySetting(SalarySettingDTO salarySettingDTO);

    /**
     * 删除薪资配置(通过薪资配置id删除)
     * @param salarySettingId
     */
    Long deleteSalarySetting(Long salarySettingId);

    /**
     * 批量删除薪资配置(通过薪资配置id删除)
     * @param salarySettingIds
     * @return
     */
    List<Long> batchDeleteSalarySetting(List<Long> salarySettingIds);

}
