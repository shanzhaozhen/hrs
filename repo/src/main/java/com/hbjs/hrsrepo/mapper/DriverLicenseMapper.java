package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.DriverLicenseDO;
import com.hbjs.hrscommon.dto.DriverLicenseDTO;
import com.hbjs.hrscommon.excel.DriverLicenseExcel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DriverLicenseMapper extends BaseMapper<DriverLicenseDO> {

    Page<DriverLicenseDTO> getDriverLicensePage(Page<DriverLicenseDTO> page, @Param("keyword") String keyword);

    List<DriverLicenseDTO> getDriverLicenseListByPid(@Param("pid") Long pid);

    long deleteDriverLicenseByStaffId(@Param("staffId") Long staffId);

    List<DriverLicenseExcel> getDriverLicenseExcelList(@Param("keyword") String keyword, @Param("depId") Long depId, @Param("companyState") String companyState, @Param("postLevel") String postLevel);

}
