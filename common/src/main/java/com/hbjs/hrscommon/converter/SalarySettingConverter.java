package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.SalarySettingDO;
import com.hbjs.hrscommon.dto.SalarySettingDTO;
import com.hbjs.hrscommon.form.SalarySettingForm;
import com.hbjs.hrscommon.vo.SalarySettingVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class SalarySettingConverter {

    /**
     * SalarySettingDTO 转换 SalarySettingDO
     * @param salarySettingDTO
     * @return
     */
    public static SalarySettingDO toDO(SalarySettingDTO salarySettingDTO) {
        SalarySettingDO salarySettingDO = new SalarySettingDO();
        BeanUtils.copyProperties(salarySettingDTO, salarySettingDO);
        return salarySettingDO;
    }

    /**
     * SalarySettingDO 转换 SalarySettingDTO
     * @param salarySettingDO
     * @return
     */
    public static SalarySettingDTO toDTO(SalarySettingDO salarySettingDO) {
        SalarySettingDTO salarySettingDTO = new SalarySettingDTO();
        BeanUtils.copyProperties(salarySettingDO, salarySettingDTO);
        return salarySettingDTO;
    }

    /**
     * SalarySettingForm 转换 SalarySettingDTO
     * @param salarySettingForm
     * @return
     */
    public static SalarySettingDTO toDTO(SalarySettingForm salarySettingForm) {
        SalarySettingDTO salarySettingDTO = new SalarySettingDTO();
        BeanUtils.copyProperties(salarySettingForm, salarySettingDTO);
        return salarySettingDTO;
    }

    /**
     * SalarySettingDTO 转换 SalarySettingVO
     * @param salarySettingDTO
     * @return
     */
    public static SalarySettingVO toVO(SalarySettingDTO salarySettingDTO) {
        if (salarySettingDTO == null) return null;
        SalarySettingVO salarySettingVO = new SalarySettingVO();
        BeanUtils.copyProperties(salarySettingDTO, salarySettingVO);
        return salarySettingVO;
    }

    /**
     * List<SalarySettingDTO> 转换 List<SalarySettingVO>
     * @param salarySettingDTOList
     * @return
     */
    public static List<SalarySettingVO> toVO(List<SalarySettingDTO> salarySettingDTOList) {
        List<SalarySettingVO> salarySettingVOList = new ArrayList<>();
        for (SalarySettingDTO salarySettingDTO : salarySettingDTOList) {
            salarySettingVOList.add(toVO(salarySettingDTO));
        }
        return salarySettingVOList;
    }

    /**
     * Page<SalarySettingDTO> 转换 Page<SalarySettingVO>
     * @param fileDTOPage
     * @return
     */
    public static Page<SalarySettingVO> toVO(Page<SalarySettingDTO> fileDTOPage) {
        List<SalarySettingDTO> fileDTOList = fileDTOPage.getRecords();
        Page<SalarySettingVO> fileVOPage = new Page<>();
        BeanUtils.copyProperties(fileDTOPage, fileVOPage);
        fileVOPage.setRecords(toVO(fileDTOList));
        return fileVOPage;
    }

}
