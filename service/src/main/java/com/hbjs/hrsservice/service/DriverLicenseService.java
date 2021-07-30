package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.DriverLicenseDTO;

import java.util.List;

public interface DriverLicenseService {

    /**
     * 获取驾驶证信息的分页列表
     * @param page
     * @param keyword
     * @return
     */
    Page<DriverLicenseDTO> getDriverLicensePage(Page<DriverLicenseDTO> page, String keyword);

    /**
     * 通过驾驶证信息id获取
     * @param driverLicenseId
     * @return
     */
    DriverLicenseDTO getDriverLicenseById(Long driverLicenseId);

    /**
     * 通过pid获取驾驶证信息信息
     * @param pid
     * @return
     */
    List<DriverLicenseDTO> getDriverLicenseListByPid(Long pid);

    /**
     * 新增驾驶证信息
     * @param driverLicenseDTO
     * @return
     */
    Long addDriverLicense(DriverLicenseDTO driverLicenseDTO);

    /**
     * 修改驾驶证信息
     * @param driverLicenseDTO
     * @return
     */
    Long updateDriverLicense(DriverLicenseDTO driverLicenseDTO);

    /**
     * 删除驾驶证信息(通过驾驶证信息id删除)
     * @param driverLicenseId
     */
    Long deleteDriverLicense(Long driverLicenseId);

    /**
     * 批量删除驾驶证信息(通过驾驶证信息id删除)
     * @param driverLicenseIds
     * @return
     */
    List<Long> batchDeleteDriverLicense(List<Long> driverLicenseIds);

    /**
     * 通过员工id删除工作履历
     * @param staffId
     * @return
     */
    long deleteDriverLicenseByStaffId(Long staffId);

    /**
     * 批量添加工作履历
     * @param driverLicenseDTOList
     * @param staffId
     */
    void batchAddDriverLicense(List<DriverLicenseDTO> driverLicenseDTOList, Long staffId);

}
