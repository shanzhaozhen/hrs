package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.SalaryStaffDTO;

import java.util.List;

public interface SalaryStaffService {

    /**
     * 获取员工薪资信息的分页列表
     * @param page
     * @param keyword
     * @return
     */
    Page<SalaryStaffDTO> getSalaryStaffPage(Page<SalaryStaffDTO> page, String keyword, Long depId);

    /**
     * 通过员工薪资信息id获取
     * @param staffId
     * @return
     */
    SalaryStaffDTO getSalaryStaffById(Long staffId);

    /**
     * 新增员工薪资信息
     * @param staffDTO
     * @return
     */
    Long addSalaryStaff(SalaryStaffDTO staffDTO);

    /**
     * 修改员工薪资信息
     * @param staffDTO
     * @return
     */
    Long updateSalaryStaff(SalaryStaffDTO staffDTO);

    /**
     * 删除员工薪资信息(通过员工薪资信息id删除)
     * @param staffId
     */
    Long deleteSalaryStaff(Long staffId);

    /**
     * 批量删除员工薪资信息(通过员工薪资信息id删除)
     * @param staffIds
     * @return
     */
    List<Long> batchDeleteSalaryStaff(List<Long> staffIds);

    /**
     * 导出员工薪资信息
     * @param keyword
     * @param depId
     */
    void exportSalaryStaff(String keyword, Long depId);

    /**
     * 打印员工薪资信息
     * @param staffId
     */
    void printSalaryStaff(Long staffId);
}
