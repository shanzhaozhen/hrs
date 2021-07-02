package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.StaffChangeDTO;

import java.util.List;

public interface StaffChangeService {

    /**
     * 获取调动记录
     * @param page
     * @param staffId
     * @param keyword
     * @return
     */
    Page<StaffChangeDTO> getStaffChangePage(Page<StaffChangeDTO> page, Long staffId, String keyword);

    /**
     * 通过ID获取调动记录
     * @param staffChangeId
     * @return
     */
    StaffChangeDTO getStaffChangeById(Long staffChangeId);

    /**
     * 添加调动记录
     * @param staffChangeDTO
     * @return
     */
    Long addStaffChange(StaffChangeDTO staffChangeDTO);

    /**
     * 更新调动记录
     * @param staffChangeDTO
     * @return
     */
    Long updateStaffChange(StaffChangeDTO staffChangeDTO);

    /**
     * 删除调动记录
     * @param staffChangeId
     * @return
     */
    Long deleteStaffChange(Long staffChangeId);

    /**
     * 批量删除调动记录
     * @param staffChangeIds
     * @return
     */
    List<Long> batchDeleteStaffChangee(List<Long> staffChangeIds);

    /**
     * 执行调动记录
     * @param staffChangeId
     * @return
     */
    Long runTransfer(Long staffChangeId);

    /**
     * 执行调动记录
     * @param staffChangeDTO
     * @return
     */
    Long runTransfer(StaffChangeDTO staffChangeDTO);

    /**
     * 定期检查执行调动
     * @param days 多少天内
     * @return
     */
    void runTransfer(int days);

}
