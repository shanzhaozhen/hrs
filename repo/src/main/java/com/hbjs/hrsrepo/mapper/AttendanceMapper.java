package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.AttendanceDO;
import com.hbjs.hrscommon.dto.AttendanceDTO;
import com.hbjs.hrscommon.excel.AttendanceExcel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AttendanceMapper extends BaseMapper<AttendanceDO> {

    Page<AttendanceDTO> getAttendancePage(Page<AttendanceDTO> page, @Param("keyword") String keyword, @Param("depId") Long depId, @Param("month") String month);

    AttendanceDTO getAttendanceById(@Param("attendanceId") Long attendanceId);

    AttendanceDTO getAttendanceByStaffCodeAndMonth(@Param("staffCode") String staffCode, @Param("month") String month);

    List<AttendanceExcel> getAttendanceExcelList(@Param("keyword") String keyword, @Param("depId") Long depId, @Param("month") String month);

}
