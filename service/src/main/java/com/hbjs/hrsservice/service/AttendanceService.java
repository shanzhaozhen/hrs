package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.AttendanceDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface AttendanceService {

    /**
     * 获取考勤数据的分页列表
     * @param page
     * @param keyword
     * @param depId
     * @param month
     * @return
     */
    Page<AttendanceDTO> getAttendancePage(Page<AttendanceDTO> page, String keyword, Long depId, String month);

    /**
     * 通过考勤数据id获取
     * @param attendanceId
     * @return
     */
    AttendanceDTO getAttendanceById(Long attendanceId);

    /**
     * 新增考勤数据
     * @param attendanceDTO
     * @return
     */
    Long addAttendance(AttendanceDTO attendanceDTO);

    /**
     * 修改考勤数据
     * @param attendanceDTO
     * @return
     */
    Long updateAttendance(AttendanceDTO attendanceDTO);

    /**
     * 删除考勤数据(通过考勤数据id删除)
     * @param attendanceId
     */
    Long deleteAttendance(Long attendanceId);

    /**
     * 批量删除考勤数据(通过考勤数据id删除)
     * @param attendanceIds
     * @return
     */
    List<Long> batchDeleteAttendance(List<Long> attendanceIds);

    /**
     * 生成考勤数据导入模板
     */
    void generateAttendanceTemplate();

    /**
     * 导入考勤数据
     * @param file
     * @return
     */
    String importAttendance(MultipartFile file);

    /**
     * 导出考勤数据
     * @param keyword
     * @param depId
     * @param month
     */
    void exportAttendance(String keyword, Long depId, String month);

}
