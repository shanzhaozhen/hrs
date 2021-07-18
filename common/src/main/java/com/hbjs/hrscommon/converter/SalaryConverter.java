package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.SalaryDO;
import com.hbjs.hrscommon.dto.SalaryDTO;
import com.hbjs.hrscommon.form.SalaryForm;
import com.hbjs.hrscommon.vo.SalaryVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class SalaryConverter {

    /**
     * SalaryDTO 转换 SalaryDO
     * @param salaryDTO
     * @return
     */
    public static SalaryDO toDO(SalaryDTO salaryDTO) {
        SalaryDO salaryDO = new SalaryDO();
        BeanUtils.copyProperties(salaryDTO, salaryDO);
        return salaryDO;
    }

    /**
     * SalaryDO 转换 SalaryDTO
     * @param salaryDO
     * @return
     */
    public static SalaryDTO toDTO(SalaryDO salaryDO) {
        SalaryDTO salaryDTO = new SalaryDTO();
        BeanUtils.copyProperties(salaryDO, salaryDTO);
        return salaryDTO;
    }

    /**
     * SalaryForm 转换 SalaryDTO
     * @param salaryForm
     * @return
     */
    public static SalaryDTO toDTO(SalaryForm salaryForm) {
        SalaryDTO salaryDTO = new SalaryDTO();
        BeanUtils.copyProperties(salaryForm, salaryDTO);
        return salaryDTO;
    }

    /**
     * SalaryDTO 转换 SalaryVO
     * @param salaryDTO
     * @return
     */
    public static SalaryVO toVO(SalaryDTO salaryDTO) {
        SalaryVO salaryVO = new SalaryVO();
        BeanUtils.copyProperties(salaryDTO, salaryVO);
        return salaryVO;
    }

    /**
     * List<SalaryDTO> 转换 List<SalaryVO>
     * @param salaryDTOList
     * @return
     */
    public static List<SalaryVO> toVO(List<SalaryDTO> salaryDTOList) {
        List<SalaryVO> salaryVOList = new ArrayList<>();
        for (SalaryDTO salaryDTO : salaryDTOList) {
            salaryVOList.add(toVO(salaryDTO));
        }
        return salaryVOList;
    }

    /**
     * Page<SalaryDTO> 转换 Page<SalaryVO>
     * @param fileDTOPage
     * @return
     */
    public static Page<SalaryVO> toVO(Page<SalaryDTO> fileDTOPage) {
        List<SalaryDTO> fileDTOList = fileDTOPage.getRecords();
        Page<SalaryVO> fileVOPage = new Page<>();
        BeanUtils.copyProperties(fileDTOPage, fileVOPage);
        fileVOPage.setRecords(toVO(fileDTOList));
        return fileVOPage;
    }

}
