package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.StaffDO;
import com.hbjs.hrscommon.dto.StaffDTO;
import com.hbjs.hrscommon.excel.StaffExcel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffMapper extends BaseMapper<StaffDO> {

    Page<StaffDTO> getStaffPage(Page<StaffDTO> page, @Param("keyword") String keyword, @Param("depId") Long depId, @Param("companyState") String companyState, @Param("postLevel") String postLevel);

    StaffDTO getStaffByStaffCode(@Param("staffCode") String staffCode);

    List<StaffExcel> getStaffExcelList(@Param("keyword") String keyword, @Param("depId") Long depId);

    List<StaffDTO> getStaffListOnJob(@Param("month") String month, @Param("depId") String depId, @Param("staffCode") String staffCode);

}
