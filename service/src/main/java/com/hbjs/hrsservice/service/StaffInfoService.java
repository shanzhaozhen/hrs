package com.hbjs.hrsservice.service;

import com.hbjs.hrscommon.dto.StaffInfoDTO;

import java.util.List;

public interface StaffInfoService {

    /**
     * 通过工作履历id获取
     * @param staffInfoId
     * @return
     */
    StaffInfoDTO getStaffInfoById(Long staffInfoId);

    /**
     * 通过staffId获取员工信息
     * @param staffId
     * @return
     */
    StaffInfoDTO getStaffInfoByStaffId(Long staffId);

    /**
     * 新增员工信息
     * @param staffInfoDTO
     * @return
     */
    Long addStaffInfo(StaffInfoDTO staffInfoDTO, Long staffId);

    /**
     * 修改员工信息
     * @param staffInfoDTO
     * @return
     */
    Long updateStaffInfo(StaffInfoDTO staffInfoDTO);

    /**
     * 删除员工信息(通过员工信息id删除)
     * @param staffInfoId
     */
    Long deleteStaffInfo(Long staffInfoId);

    /**
     * 通过员工id删除工作履历
     * @param staffId
     * @return
     */
    long deleteStaffInfoByStaffId(Long staffId);

}
