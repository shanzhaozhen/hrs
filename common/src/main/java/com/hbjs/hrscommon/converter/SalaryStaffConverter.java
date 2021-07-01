package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.SalaryStaffDO;
import com.hbjs.hrscommon.dto.SalaryStaffDTO;
import com.hbjs.hrscommon.form.SalaryStaffForm;
import com.hbjs.hrscommon.vo.SalaryStaffVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class SalaryStaffConverter {
    
    /**
     * SalaryStaffDTO 转换 SalaryStaffDO
     * @param salaryStaffDTO
     * @return
     */
    public static SalaryStaffDO toDO(SalaryStaffDTO salaryStaffDTO) {
        SalaryStaffDO salaryStaffDO = new SalaryStaffDO();
        BeanUtils.copyProperties(salaryStaffDTO, salaryStaffDO);
        return salaryStaffDO;
    }

    /**
     * SalaryStaffForm 转换 SalaryStaffDTO
     * @param salaryStaffForm
     * @return
     */
    public static SalaryStaffDTO toDTO(SalaryStaffForm salaryStaffForm) {
        SalaryStaffDTO salaryStaffDTO = new SalaryStaffDTO();
        BeanUtils.copyProperties(salaryStaffForm, salaryStaffDTO);
        return salaryStaffDTO;
    }

    /**
     * SalaryStaffVO 转换 SalaryStaffDTO
     * @param salaryStaffVO
     * @return
     */
    public static SalaryStaffDTO toDTO(SalaryStaffVO salaryStaffVO) {
        SalaryStaffDTO salaryStaffDTO = new SalaryStaffDTO();
        BeanUtils.copyProperties(salaryStaffVO, salaryStaffDTO);
        return salaryStaffDTO;
    }

    /**
     * SalaryStaffDO 转换 SalaryStaffDTO
     * @param salaryStaffDO
     * @return
     */
    public static SalaryStaffDTO toDTO(SalaryStaffDO salaryStaffDO) {
        SalaryStaffDTO salaryStaffDTO = new SalaryStaffDTO();
        BeanUtils.copyProperties(salaryStaffDO, salaryStaffDTO);
        return salaryStaffDTO;
    }

    /**
     * SalaryStaffDTO 转换 SalaryStaffVO
     * @param salaryStaffDTO
     * @return
     */
    public static SalaryStaffVO toVO(SalaryStaffDTO salaryStaffDTO) {
        SalaryStaffVO salaryStaffVO = new SalaryStaffVO();
        BeanUtils.copyProperties(salaryStaffDTO, salaryStaffVO);
        return salaryStaffVO;
    }

    /**
     * List<SalaryStaffDTO> 转换 List<SalaryStaffVO>
     * @param salaryStaffDTOList
     * @return
     */
    public static List<SalaryStaffVO> toVO(List<SalaryStaffDTO> salaryStaffDTOList) {
        List<SalaryStaffVO> salaryStaffVOList = new ArrayList<>();
        for (SalaryStaffDTO salaryStaffDTO : salaryStaffDTOList) {
            salaryStaffVOList.add(toVO(salaryStaffDTO));
        }
        return salaryStaffVOList;
    }

    /**
     * Page<SalaryStaffDTO> 转换 Page<SalaryStaffVO>
     * @param salaryStaffDTOPage
     * @return
     */
    public static Page<SalaryStaffVO> toVO(Page<SalaryStaffDTO> salaryStaffDTOPage) {
        List<SalaryStaffDTO> salaryStaffDTOList = salaryStaffDTOPage.getRecords();
        Page<SalaryStaffVO> salaryStaffVOPage = new Page<>();
        BeanUtils.copyProperties(salaryStaffDTOPage, salaryStaffVOPage);
        salaryStaffVOPage.setRecords(toVO(salaryStaffDTOList));
        return salaryStaffVOPage;
    }

}
