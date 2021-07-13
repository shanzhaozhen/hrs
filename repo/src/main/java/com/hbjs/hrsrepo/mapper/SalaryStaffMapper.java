package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.SalaryStaffDO;
import com.hbjs.hrscommon.dto.SalaryStaffDTO;
import com.hbjs.hrscommon.excel.StaffExcel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SalaryStaffMapper extends BaseMapper<SalaryStaffDO> {

    Page<SalaryStaffDTO> getSalaryStaffPage(Page<SalaryStaffDTO> page, @Param("keyword") String keyword, @Param("depId") Long depId);

    SalaryStaffDTO getSalaryStaffById(Long salaryStaffId);

    SalaryStaffDTO getSalaryStaffByStaffId(Long staffId);

    SalaryStaffDTO getSalaryStaffByStaffCode(@Param("staffCode") String staffCode);

    List<StaffExcel> getSalaryStaffExcelList(@Param("keyword") String keyword, @Param("depId") Long depId);

}
