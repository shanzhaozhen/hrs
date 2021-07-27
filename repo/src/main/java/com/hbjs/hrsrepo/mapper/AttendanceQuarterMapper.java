package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.AttendanceQuarterDO;
import com.hbjs.hrscommon.dto.AttendanceQuarterDTO;
import com.hbjs.hrscommon.excel.AttendanceQuarterExcel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttendanceQuarterMapper extends BaseMapper<AttendanceQuarterDO> {

    Page<AttendanceQuarterDTO> getAttendanceQuarterPage(Page<AttendanceQuarterDTO> page, @Param("keyword") String keyword, @Param("depId") Long depId, @Param("year") Integer year, @Param("quarter") Integer quarter);

    AttendanceQuarterDTO getAttendanceQuarterById(@Param("attendanceQuarterId") Long attendanceQuarterId);

    AttendanceQuarterDTO getAttendanceQuarterByStaffIdAndYearAndQuarter(@Param("staffId") Long staffId, @Param("year") Integer year, @Param("quarter") Integer quarter);

    AttendanceQuarterDTO getAttendanceQuarterByStaffCodeAndYearAndQuarter(@Param("staffCode") String staffCode, @Param("year") Integer year, @Param("quarter") Integer quarter);

    List<AttendanceQuarterExcel> getAttendanceQuarterExcelList(@Param("keyword") String keyword, @Param("depId") Long depId, @Param("year") Integer year, @Param("quarter") Integer quarter);

}
