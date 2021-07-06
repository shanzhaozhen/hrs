package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.SalaryChangeDO;
import com.hbjs.hrscommon.dto.SalaryChangeDTO;
import com.hbjs.hrscommon.form.SalaryChangeForm;
import com.hbjs.hrscommon.vo.SalaryChangeVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class SalaryChangeConverter {
    
    /**
     * SalaryChangeDTO 转换 SalaryChangeDO
     * @param salaryChangeDTO
     * @return
     */
    public static SalaryChangeDO toDO(SalaryChangeDTO salaryChangeDTO) {
        SalaryChangeDO salaryChangeDO = new SalaryChangeDO();
        BeanUtils.copyProperties(salaryChangeDTO, salaryChangeDO);
        return salaryChangeDO;
    }

    /**
     * SalaryChangeForm 转换 SalaryChangeDTO
     * @param salaryChangeForm
     * @return
     */
    public static SalaryChangeDTO toDTO(SalaryChangeForm salaryChangeForm) {
        SalaryChangeDTO salaryChangeDTO = new SalaryChangeDTO();
        BeanUtils.copyProperties(salaryChangeForm, salaryChangeDTO);
        return salaryChangeDTO;
    }

    /**
     * SalaryChangeVO 转换 SalaryChangeDTO
     * @param salaryChangeVO
     * @return
     */
    public static SalaryChangeDTO toDTO(SalaryChangeVO salaryChangeVO) {
        SalaryChangeDTO salaryChangeDTO = new SalaryChangeDTO();
        BeanUtils.copyProperties(salaryChangeVO, salaryChangeDTO);
        return salaryChangeDTO;
    }

    /**
     * SalaryChangeDO 转换 SalaryChangeDTO
     * @param salaryChangeDO
     * @return
     */
    public static SalaryChangeDTO toDTO(SalaryChangeDO salaryChangeDO) {
        SalaryChangeDTO salaryChangeDTO = new SalaryChangeDTO();
        BeanUtils.copyProperties(salaryChangeDO, salaryChangeDTO);
        return salaryChangeDTO;
    }

    /**
     * SalaryChangeDTO 转换 SalaryChangeVO
     * @param salaryChangeDTO
     * @return
     */
    public static SalaryChangeVO toVO(SalaryChangeDTO salaryChangeDTO) {
        SalaryChangeVO salaryChangeVO = new SalaryChangeVO();
        BeanUtils.copyProperties(salaryChangeDTO, salaryChangeVO);
        return salaryChangeVO;
    }

    /**
     * List<SalaryChangeDTO> 转换 List<SalaryChangeVO>
     * @param salaryChangeDTOList
     * @return
     */
    public static List<SalaryChangeVO> toVO(List<SalaryChangeDTO> salaryChangeDTOList) {
        List<SalaryChangeVO> salaryChangeVOList = new ArrayList<>();
        for (SalaryChangeDTO salaryChangeDTO : salaryChangeDTOList) {
            salaryChangeVOList.add(toVO(salaryChangeDTO));
        }
        return salaryChangeVOList;
    }

    /**
     * Page<SalaryChangeDTO> 转换 Page<SalaryChangeVO>
     * @param salaryChangeDTOPage
     * @return
     */
    public static Page<SalaryChangeVO> toVO(Page<SalaryChangeDTO> salaryChangeDTOPage) {
        List<SalaryChangeDTO> salaryChangeDTOList = salaryChangeDTOPage.getRecords();
        Page<SalaryChangeVO> salaryChangeVOPage = new Page<>();
        BeanUtils.copyProperties(salaryChangeDTOPage, salaryChangeVOPage);
        salaryChangeVOPage.setRecords(toVO(salaryChangeDTOList));
        return salaryChangeVOPage;
    }

}
