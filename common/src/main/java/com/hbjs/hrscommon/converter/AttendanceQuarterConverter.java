package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.AttendanceQuarterDO;
import com.hbjs.hrscommon.dto.AttendanceQuarterDTO;
import com.hbjs.hrscommon.form.AttendanceQuarterForm;
import com.hbjs.hrscommon.vo.AttendanceQuarterVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class AttendanceQuarterConverter {

    /**
     * AttendanceQuarterDTO 转换 AttendanceQuarterDO
     * @param attendanceQuarterDTO
     * @return
     */
    public static AttendanceQuarterDO toDO(AttendanceQuarterDTO attendanceQuarterDTO) {
        AttendanceQuarterDO attendanceQuarterDO = new AttendanceQuarterDO();
        BeanUtils.copyProperties(attendanceQuarterDTO, attendanceQuarterDO);
        return attendanceQuarterDO;
    }

    /**
     * AttendanceQuarterDO 转换 AttendanceQuarterDTO
     * @param attendanceQuarterDO
     * @return
     */
    public static AttendanceQuarterDTO toDTO(AttendanceQuarterDO attendanceQuarterDO) {
        AttendanceQuarterDTO attendanceQuarterDTO = new AttendanceQuarterDTO();
        BeanUtils.copyProperties(attendanceQuarterDO, attendanceQuarterDTO);
        return attendanceQuarterDTO;
    }

    /**
     * AttendanceQuarterForm 转换 AttendanceQuarterDTO
     * @param attendanceQuarterForm
     * @return
     */
    public static AttendanceQuarterDTO toDTO(AttendanceQuarterForm attendanceQuarterForm) {
        AttendanceQuarterDTO attendanceQuarterDTO = new AttendanceQuarterDTO();
        BeanUtils.copyProperties(attendanceQuarterForm, attendanceQuarterDTO);
        return attendanceQuarterDTO;
    }

    /**
     * AttendanceQuarterDTO 转换 AttendanceQuarterVO
     * @param attendanceQuarterDTO
     * @return
     */
    public static AttendanceQuarterVO toVO(AttendanceQuarterDTO attendanceQuarterDTO) {
        AttendanceQuarterVO attendanceQuarterVO = new AttendanceQuarterVO();
        BeanUtils.copyProperties(attendanceQuarterDTO, attendanceQuarterVO);
        return attendanceQuarterVO;
    }

    /**
     * List<AttendanceQuarterDTO> 转换 List<AttendanceQuarterVO>
     * @param attendanceQuarterDTOList
     * @return
     */
    public static List<AttendanceQuarterVO> toVO(List<AttendanceQuarterDTO> attendanceQuarterDTOList) {
        List<AttendanceQuarterVO> attendanceQuarterVOList = new ArrayList<>();
        for (AttendanceQuarterDTO attendanceQuarterDTO : attendanceQuarterDTOList) {
            attendanceQuarterVOList.add(toVO(attendanceQuarterDTO));
        }
        return attendanceQuarterVOList;
    }

    /**
     * Page<AttendanceQuarterDTO> 转换 Page<AttendanceQuarterVO>
     * @param fileDTOPage
     * @return
     */
    public static Page<AttendanceQuarterVO> toVO(Page<AttendanceQuarterDTO> fileDTOPage) {
        List<AttendanceQuarterDTO> fileDTOList = fileDTOPage.getRecords();
        Page<AttendanceQuarterVO> fileVOPage = new Page<>();
        BeanUtils.copyProperties(fileDTOPage, fileVOPage);
        fileVOPage.setRecords(toVO(fileDTOList));
        return fileVOPage;
    }

}
