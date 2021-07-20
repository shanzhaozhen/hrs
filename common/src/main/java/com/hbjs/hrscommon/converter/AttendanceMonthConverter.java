package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.AttendanceMonthDO;
import com.hbjs.hrscommon.dto.AttendanceMonthDTO;
import com.hbjs.hrscommon.form.AttendanceMonthForm;
import com.hbjs.hrscommon.vo.AttendanceMonthVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class AttendanceMonthConverter {

    /**
     * AttendanceMonthDTO 转换 AttendanceMonthDO
     * @param attendanceMonthDTO
     * @return
     */
    public static AttendanceMonthDO toDO(AttendanceMonthDTO attendanceMonthDTO) {
        AttendanceMonthDO attendanceMonthDO = new AttendanceMonthDO();
        BeanUtils.copyProperties(attendanceMonthDTO, attendanceMonthDO);
        return attendanceMonthDO;
    }

    /**
     * AttendanceMonthDO 转换 AttendanceMonthDTO
     * @param attendanceMonthDO
     * @return
     */
    public static AttendanceMonthDTO toDTO(AttendanceMonthDO attendanceMonthDO) {
        AttendanceMonthDTO attendanceMonthDTO = new AttendanceMonthDTO();
        BeanUtils.copyProperties(attendanceMonthDO, attendanceMonthDTO);
        return attendanceMonthDTO;
    }

    /**
     * AttendanceMonthForm 转换 AttendanceMonthDTO
     * @param attendanceMonthForm
     * @return
     */
    public static AttendanceMonthDTO toDTO(AttendanceMonthForm attendanceMonthForm) {
        AttendanceMonthDTO attendanceMonthDTO = new AttendanceMonthDTO();
        BeanUtils.copyProperties(attendanceMonthForm, attendanceMonthDTO);
        return attendanceMonthDTO;
    }

    /**
     * AttendanceMonthDTO 转换 AttendanceMonthVO
     * @param attendanceMonthDTO
     * @return
     */
    public static AttendanceMonthVO toVO(AttendanceMonthDTO attendanceMonthDTO) {
        AttendanceMonthVO attendanceVO = new AttendanceMonthVO();
        BeanUtils.copyProperties(attendanceMonthDTO, attendanceVO);
        return attendanceVO;
    }

    /**
     * List<AttendanceMonthDTO> 转换 List<AttendanceMonthVO>
     * @param attendanceMonthDTOList
     * @return
     */
    public static List<AttendanceMonthVO> toVO(List<AttendanceMonthDTO> attendanceMonthDTOList) {
        List<AttendanceMonthVO> attendanceVOList = new ArrayList<>();
        for (AttendanceMonthDTO attendanceMonthDTO : attendanceMonthDTOList) {
            attendanceVOList.add(toVO(attendanceMonthDTO));
        }
        return attendanceVOList;
    }

    /**
     * Page<AttendanceMonthDTO> 转换 Page<AttendanceMonthVO>
     * @param fileDTOPage
     * @return
     */
    public static Page<AttendanceMonthVO> toVO(Page<AttendanceMonthDTO> fileDTOPage) {
        List<AttendanceMonthDTO> fileDTOList = fileDTOPage.getRecords();
        Page<AttendanceMonthVO> fileVOPage = new Page<>();
        BeanUtils.copyProperties(fileDTOPage, fileVOPage);
        fileVOPage.setRecords(toVO(fileDTOList));
        return fileVOPage;
    }

}
