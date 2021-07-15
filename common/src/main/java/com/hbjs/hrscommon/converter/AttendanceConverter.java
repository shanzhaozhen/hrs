package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.AttendanceDO;
import com.hbjs.hrscommon.dto.AttendanceDTO;
import com.hbjs.hrscommon.form.AttendanceForm;
import com.hbjs.hrscommon.vo.AttendanceVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class AttendanceConverter {

    /**
     * AttendanceDTO 转换 AttendanceDO
     * @param attendanceDTO
     * @return
     */
    public static AttendanceDO toDO(AttendanceDTO attendanceDTO) {
        AttendanceDO attendanceDO = new AttendanceDO();
        BeanUtils.copyProperties(attendanceDTO, attendanceDO);
        return attendanceDO;
    }

    /**
     * AttendanceDO 转换 AttendanceDTO
     * @param attendanceDO
     * @return
     */
    public static AttendanceDTO toDTO(AttendanceDO attendanceDO) {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        BeanUtils.copyProperties(attendanceDO, attendanceDTO);
        return attendanceDTO;
    }

    /**
     * AttendanceForm 转换 AttendanceDTO
     * @param attendanceForm
     * @return
     */
    public static AttendanceDTO toDTO(AttendanceForm attendanceForm) {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        BeanUtils.copyProperties(attendanceForm, attendanceDTO);
        return attendanceDTO;
    }

    /**
     * AttendanceDTO 转换 AttendanceVO
     * @param attendanceDTO
     * @return
     */
    public static AttendanceVO toVO(AttendanceDTO attendanceDTO) {
        AttendanceVO attendanceVO = new AttendanceVO();
        BeanUtils.copyProperties(attendanceDTO, attendanceVO);
        return attendanceVO;
    }

    /**
     * List<AttendanceDTO> 转换 List<AttendanceVO>
     * @param attendanceDTOList
     * @return
     */
    public static List<AttendanceVO> toVO(List<AttendanceDTO> attendanceDTOList) {
        List<AttendanceVO> attendanceVOList = new ArrayList<>();
        for (AttendanceDTO attendanceDTO : attendanceDTOList) {
            attendanceVOList.add(toVO(attendanceDTO));
        }
        return attendanceVOList;
    }

    /**
     * Page<AttendanceDTO> 转换 Page<AttendanceVO>
     * @param fileDTOPage
     * @return
     */
    public static Page<AttendanceVO> toVO(Page<AttendanceDTO> fileDTOPage) {
        List<AttendanceDTO> fileDTOList = fileDTOPage.getRecords();
        Page<AttendanceVO> fileVOPage = new Page<>();
        BeanUtils.copyProperties(fileDTOPage, fileVOPage);
        fileVOPage.setRecords(toVO(fileDTOList));
        return fileVOPage;
    }

}
