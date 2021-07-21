package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.AllowanceDO;
import com.hbjs.hrscommon.dto.AllowanceDTO;
import com.hbjs.hrscommon.excel.AllowanceExcel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AllowanceMapper extends BaseMapper<AllowanceDO> {

    Page<AllowanceDTO> getAllowancePage(Page<AllowanceDTO> page, @Param("keyword") String keyword, @Param("depId") Long depId, @Param("month") String month);

    AllowanceDTO getAllowanceById(@Param("allowanceId") Long allowanceId);

    AllowanceDTO getAllowanceByStaffIdAndMonth(@Param("staffId") Long staffId, @Param("month") String month);

    List<AllowanceExcel> getAllowanceExcelList(@Param("keyword") String keyword, @Param("depId") Long depId, @Param("month") String month);

}
