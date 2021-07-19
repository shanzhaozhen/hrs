package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.PerformanceDO;
import com.hbjs.hrscommon.dto.PerformanceDTO;
import com.hbjs.hrscommon.excel.PerformanceExcel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AllowanceMapper extends BaseMapper<PerformanceDO> {

    Page<PerformanceDTO> getPerformancePage(Page<PerformanceDTO> page, @Param("keyword") String keyword, @Param("depId") Long depId, @Param("year") Integer year, @Param("quarter") Integer quarter);

    PerformanceDTO getPerformanceById(@Param("performanceId") Long performanceId);

    PerformanceDTO getPerformanceByStaffCodeAndYearAndQuarter(@Param("staffCode") String staffCode, @Param("year") int year, @Param("quarter") int quarter);

    List<PerformanceExcel> getPerformanceExcelList(@Param("keyword") String keyword, @Param("depId") Long depId, @Param("year") Integer year, @Param("quarter") Integer quarter);

    PerformanceDTO getPerformanceByStaffIdAndYearAndQuarter(@Param("staffId") Long staffId, @Param("year") Integer year, @Param("quarter") Integer quarter);

}
