package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.StaffChangeDO;
import com.hbjs.hrscommon.dto.StaffChangeDTO;
import com.hbjs.hrscommon.excel.StaffChangeExportExcel;
import com.hbjs.hrscommon.excel.StaffChangeImportExcel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffChangeMapper extends BaseMapper<StaffChangeDO> {

    Page<StaffChangeDTO> getStaffChangePage(Page<StaffChangeDTO> page, @Param("staffId") Long staffId, @Param("keyword") String keyword, @Param("depId") Long depId);

    StaffChangeDTO getStaffChangeById(@Param("staffChangeId") Long staffChangeId);

    List<StaffChangeDTO> getStaffChangeInDays(@Param("days") int days);

    List<StaffChangeExportExcel> getStaffChangeExcelList(@Param("keyword") String keyword, @Param("depId") Long depId);
}
