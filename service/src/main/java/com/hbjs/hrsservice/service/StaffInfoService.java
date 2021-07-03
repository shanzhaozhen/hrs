package com.hbjs.hrsservice.service;

import com.hbjs.hrscommon.dto.StaffInfoDTO;

import java.util.List;

public interface StaffInfoService {

    /**
     * 通过员工信息id获取
     * @param id
     * @return
     */
    StaffInfoDTO getStaffInfoById(Long id);

    /**
     * 通过staffId获取员工信息
     * @param staffId
     * @return
     */
    StaffInfoDTO getStaffInfoByStaffId(Long staffId);

    /**
     * 通过staffCode获取员工信息
     * @param staffCode
     * @return
     */
    StaffInfoDTO getStaffInfoByStaffCode(String staffCode);

    /**
     * 修改员工信息
     * @param staffInfoDTO
     * @param staffId
     * @return
     */
    Long updateStaffInfo(StaffInfoDTO staffInfoDTO, Long staffId);

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
