package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.AttendanceQuarterDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AttendanceQuarterService {

    /**
     * 获取季度考勤的分页列表
     * @param page
     * @param keyword
     * @param depId
     * @param year
     * @param quarter
     * @return
     */
    Page<AttendanceQuarterDTO> getAttendanceQuarterPage(Page<AttendanceQuarterDTO> page, String keyword, Long depId, Integer year, Integer quarter);

    /**
     * 通过季度考勤id获取
     * @param attendanceQuarterId
     * @return
     */
    AttendanceQuarterDTO getAttendanceQuarterById(Long attendanceQuarterId);

    /**
     * 通过员工ID、考勤年月获取季度考勤
     * @param staffId
     * @param year
     * @param quarter
     * @return
     */
    AttendanceQuarterDTO getAttendanceQuarterByStaffIdAndYearQuarter(Long staffId, Integer year, Integer quarter);

    /**
     * 新增季度考勤
     * @param attendanceQuarterDTO
     * @return
     */
    Long addAttendanceQuarter(AttendanceQuarterDTO attendanceQuarterDTO);

    /**
     * 修改季度考勤
     * @param attendanceQuarterDTO
     * @return
     */
    Long updateAttendanceQuarter(AttendanceQuarterDTO attendanceQuarterDTO);

    /**
     * 删除季度考勤(通过季度考勤id删除)
     * @param attendanceQuarterId
     */
    Long deleteAttendanceQuarter(Long attendanceQuarterId);

    /**
     * 批量删除季度考勤(通过季度考勤id删除)
     * @param attendanceQuarterIds
     * @return
     */
    List<Long> batchDeleteAttendanceQuarter(List<Long> attendanceQuarterIds);

    /**
     * 通过ID冻结季度考勤
     * @param attendanceQuarterIds
     * @param freeze
     * @return
     */
    String freezeAttendanceQuarterByIds(List<Long> attendanceQuarterIds, Boolean freeze);

    /**
     * 通过季度冻结季度考勤
     * @param year
     * @param quarter
     * @param freeze
     * @return
     */
    String freezeAttendanceQuarterByQuarter(Integer year, Integer quarter, Boolean freeze);

    /**
     * 生成季度考勤导入模板
     */
    void generateAttendanceQuarterTemplate();

    /**
     * 导入季度考勤
     * @param file
     * @return
     */
    String importAttendanceQuarter(MultipartFile file);

    /**
     * 导出季度考勤
     * @param keyword
     * @param depId
     * @param year
     * @param quarter
     */
    void exportAttendanceQuarter(String keyword, Long depId, Integer year, Integer quarter);

    /**
     * 计算季度考勤
     * @param staffId
     * @param year
     * @param quarter
     * @return
     */
    AttendanceQuarterDTO calculateAttendanceQuarter(Long staffId, Integer year, Integer quarter);

}
