package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.AttendanceMonthDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AttendanceMonthService {

    /**
     * 获取月度考勤的分页列表
     * @param page
     * @param keyword
     * @param depId
     * @param month
     * @return
     */
    Page<AttendanceMonthDTO> getAttendanceMonthPage(Page<AttendanceMonthDTO> page, String keyword, Long depId, String month);

    /**
     * 通过月度考勤id获取
     * @param attendanceMonthId
     * @return
     */
    AttendanceMonthDTO getAttendanceMonthById(Long attendanceMonthId);

    /**
     * 通过员工ID、考勤年月获取月度考勤
     * @param staffId
     * @param month
     * @return
     */
    AttendanceMonthDTO getAttendanceMonthByStaffIdAndMonth(Long staffId, String month);

    /**
     * 新增月度考勤
     * @param attendanceMonthDTO
     * @return
     */
    Long addAttendanceMonth(AttendanceMonthDTO attendanceMonthDTO);

    /**
     * 修改月度考勤
     * @param attendanceMonthDTO
     * @return
     */
    Long updateAttendanceMonth(AttendanceMonthDTO attendanceMonthDTO);

    /**
     * 删除月度考勤(通过月度考勤id删除)
     * @param attendanceMonthId
     */
    Long deleteAttendanceMonth(Long attendanceMonthId);

    /**
     * 批量删除月度考勤(通过月度考勤id删除)
     * @param attendanceMonthIds
     * @return
     */
    List<Long> batchDeleteAttendanceMonth(List<Long> attendanceMonthIds);

    /**
     * 生成月度考勤导入模板
     */
    void generateAttendanceMonthTemplate();

    /**
     * 导入月度考勤
     * @param file
     * @return
     */
    String importAttendanceMonth(MultipartFile file);

    /**
     * 导出月度考勤
     * @param keyword
     * @param depId
     * @param month
     */
    void exportAttendanceMonth(String keyword, Long depId, String month);

}
