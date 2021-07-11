package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.PerformanceSettingDO;
import com.hbjs.hrscommon.dto.PerformanceSettingDTO;
import com.hbjs.hrscommon.form.PerformanceSettingForm;
import com.hbjs.hrscommon.vo.PerformanceSettingVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class PerformanceSettingConverter {

    /**
     * PerformanceSettingDTO 转换 PerformanceSettingDO
     * @param performanceSettingDTO
     * @return
     */
    public static PerformanceSettingDO toDO(PerformanceSettingDTO performanceSettingDTO) {
        PerformanceSettingDO performanceSettingDO = new PerformanceSettingDO();
        BeanUtils.copyProperties(performanceSettingDTO, performanceSettingDO);
        return performanceSettingDO;
    }

    /**
     * PerformanceSettingDO 转换 PerformanceSettingDTO
     * @param performanceSettingDO
     * @return
     */
    public static PerformanceSettingDTO toDTO(PerformanceSettingDO performanceSettingDO) {
        PerformanceSettingDTO performanceSettingDTO = new PerformanceSettingDTO();
        BeanUtils.copyProperties(performanceSettingDO, performanceSettingDTO);
        return performanceSettingDTO;
    }

    /**
     * PerformanceSettingDO 转换 PerformanceSettingDTO
     * @param performanceSettingForm
     * @return
     */
    public static PerformanceSettingDTO toDTO(PerformanceSettingForm performanceSettingForm) {
        PerformanceSettingDTO performanceSettingDTO = new PerformanceSettingDTO();
        BeanUtils.copyProperties(performanceSettingForm, performanceSettingDTO);
        return performanceSettingDTO;
    }

    /**
     * PerformanceSettingDTO 转换 PerformanceSettingVO
     * @param performanceSettingDTO
     * @return
     */
    public static PerformanceSettingVO toVO(PerformanceSettingDTO performanceSettingDTO) {
        PerformanceSettingVO performanceSettingVO = new PerformanceSettingVO();
        BeanUtils.copyProperties(performanceSettingDTO, performanceSettingVO);
        return performanceSettingVO;
    }

    /**
     * List<PerformanceSettingDTO> 转换 List<PerformanceSettingVO>
     * @param performanceSettingDTOList
     * @return
     */
    public static List<PerformanceSettingVO> toVO(List<PerformanceSettingDTO> performanceSettingDTOList) {
        List<PerformanceSettingVO> performanceSettingVOList = new ArrayList<>();
        for (PerformanceSettingDTO performanceSettingDTO : performanceSettingDTOList) {
            performanceSettingVOList.add(toVO(performanceSettingDTO));
        }
        return performanceSettingVOList;
    }

    /**
     * Page<PerformanceSettingDTO> 转换 Page<PerformanceSettingVO>
     * @param fileDTOPage
     * @return
     */
    public static Page<PerformanceSettingVO> toVO(Page<PerformanceSettingDTO> fileDTOPage) {
        List<PerformanceSettingDTO> fileDTOList = fileDTOPage.getRecords();
        Page<PerformanceSettingVO> fileVOPage = new Page<>();
        BeanUtils.copyProperties(fileDTOPage, fileVOPage);
        fileVOPage.setRecords(toVO(fileDTOList));
        return fileVOPage;
    }

}
