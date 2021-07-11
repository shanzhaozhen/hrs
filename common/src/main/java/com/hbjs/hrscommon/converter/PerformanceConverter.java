package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.PerformanceDO;
import com.hbjs.hrscommon.dto.PerformanceDTO;
import com.hbjs.hrscommon.form.PerformanceForm;
import com.hbjs.hrscommon.vo.PerformanceVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class PerformanceConverter {

    /**
     * PerformanceDTO 转换 PerformanceDO
     * @param performanceDTO
     * @return
     */
    public static PerformanceDO toDO(PerformanceDTO performanceDTO) {
        PerformanceDO performanceDO = new PerformanceDO();
        BeanUtils.copyProperties(performanceDTO, performanceDO);
        return performanceDO;
    }

    /**
     * PerformanceDO 转换 PerformanceDTO
     * @param performanceDO
     * @return
     */
    public static PerformanceDTO toDTO(PerformanceDO performanceDO) {
        PerformanceDTO performanceDTO = new PerformanceDTO();
        BeanUtils.copyProperties(performanceDO, performanceDTO);
        return performanceDTO;
    }

    /**
     * PerformanceForm 转换 PerformanceDTO
     * @param performanceForm
     * @return
     */
    public static PerformanceDTO toDTO(PerformanceForm performanceForm) {
        PerformanceDTO performanceDTO = new PerformanceDTO();
        BeanUtils.copyProperties(performanceForm, performanceDTO);
        return performanceDTO;
    }

    /**
     * PerformanceDTO 转换 PerformanceVO
     * @param performanceDTO
     * @return
     */
    public static PerformanceVO toVO(PerformanceDTO performanceDTO) {
        PerformanceVO performanceVO = new PerformanceVO();
        BeanUtils.copyProperties(performanceDTO, performanceVO);
        return performanceVO;
    }

    /**
     * List<PerformanceDTO> 转换 List<PerformanceVO>
     * @param performanceDTOList
     * @return
     */
    public static List<PerformanceVO> toVO(List<PerformanceDTO> performanceDTOList) {
        List<PerformanceVO> performanceVOList = new ArrayList<>();
        for (PerformanceDTO performanceDTO : performanceDTOList) {
            performanceVOList.add(toVO(performanceDTO));
        }
        return performanceVOList;
    }

    /**
     * Page<PerformanceDTO> 转换 Page<PerformanceVO>
     * @param fileDTOPage
     * @return
     */
    public static Page<PerformanceVO> toVO(Page<PerformanceDTO> fileDTOPage) {
        List<PerformanceDTO> fileDTOList = fileDTOPage.getRecords();
        Page<PerformanceVO> fileVOPage = new Page<>();
        BeanUtils.copyProperties(fileDTOPage, fileVOPage);
        fileVOPage.setRecords(toVO(fileDTOList));
        return fileVOPage;
    }

}
