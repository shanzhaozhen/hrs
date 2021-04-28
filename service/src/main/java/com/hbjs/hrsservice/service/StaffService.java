package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.StaffDTO;

import java.util.List;

public interface StaffService {

    /**
     * 获取员工信息的分页列表
     * @param page
     * @param keyword
     * @return
     */
    Page<StaffDTO> getStaffPage(Page<StaffDTO> page, String keyword, Long depId);

    /**
     * 通过员工信息id获取
     * @param staffId
     * @return
     */
    StaffDTO getStaffById(Long staffId);

    /**
     * 新增员工信息
     * @param staffDTO
     * @return
     */
    Long addStaff(StaffDTO staffDTO);

    /**
     * 修改员工信息
     * @param staffDTO
     * @return
     */
    Long updateStaff(StaffDTO staffDTO);

    /**
     * 更新员工其他信息
     * @param staffDTO
     * @return
     */
    void updateStaffOtherInfo(StaffDTO staffDTO, Long staffId);

    /**
     * 删除员工信息(通过员工信息id删除)
     * @param staffId
     */
    Long deleteStaff(Long staffId);

    /**
     * 批量删除员工信息(通过员工信息id删除)
     * @param staffIds
     * @return
     */
    List<Long> batchDeleteStaff(List<Long> staffIds);

}
