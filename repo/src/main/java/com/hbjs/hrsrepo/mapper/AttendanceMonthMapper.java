package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.AttendanceMonthDO;
import com.hbjs.hrscommon.dto.AttendanceMonthDTO;
import com.hbjs.hrscommon.excel.AttendanceMonthExcel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AttendanceMonthMapper extends BaseMapper<AttendanceMonthDO> {

    Page<AttendanceMonthDTO> getAttendanceMonthPage(Page<AttendanceMonthDTO> page, @Param("keyword") String keyword, @Param("depId") Long depId, @Param("month") String month);

    AttendanceMonthDTO getAttendanceMonthById(@Param("attendanceMonthId") Long attendanceMonthId);

    AttendanceMonthDTO getAttendanceMonthByStaffCodeAndMonth(@Param("staffCode") String staffCode, @Param("month") String month);

    List<AttendanceMonthExcel> getAttendanceMonthExcelList(@Param("keyword") String keyword, @Param("depId") Long depId, @Param("month") String month);

    AttendanceMonthDTO getAttendanceMonthByStaffIdAndMonth(@Param("staffId") Long staffId, @Param("month") String month);

    List<AttendanceMonthDTO> getAttendanceMonthByStaffIdAndStartMonthAndEndMonth(@Param("staffId") Long staffId, @Param("startMonth")  String startMonth, @Param("endMonth") String endMonth);

}
