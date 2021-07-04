package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.StaffInfoDO;
import com.hbjs.hrscommon.dto.StaffInfoDTO;
import com.hbjs.hrscommon.form.StaffInfoForm;
import com.hbjs.hrscommon.vo.StaffInfoVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class StaffInfoConverter {
    
    /**
     * StaffInfoDTO 转换 StaffInfoDO
     * @param staffInfoDTO
     * @return
     */
    public static StaffInfoDO toDO(StaffInfoDTO staffInfoDTO) {
        StaffInfoDO staffInfoDO = new StaffInfoDO();
        BeanUtils.copyProperties(staffInfoDTO, staffInfoDO);
        return staffInfoDO;
    }

    /**
     * StaffInfoForm 转换 StaffInfoDTO
     * @param staffInfoForm
     * @return
     */
    public static StaffInfoDTO toDTO(StaffInfoForm staffInfoForm) {
        StaffInfoDTO staffInfoDTO = new StaffInfoDTO();
        BeanUtils.copyProperties(staffInfoForm, staffInfoDTO);
        return staffInfoDTO;
    }

    /**
     * StaffInfoVO 转换 StaffInfoDTO
     * @param staffInfoVO
     * @return
     */
    public static StaffInfoDTO toDTO(StaffInfoVO staffInfoVO) {
        StaffInfoDTO staffInfoDTO = new StaffInfoDTO();
        BeanUtils.copyProperties(staffInfoVO, staffInfoDTO);
        return staffInfoDTO;
    }

    /**
     * StaffInfoDO 转换 StaffInfoDTO
     * @param staffInfoDO
     * @return
     */
    public static StaffInfoDTO toDTO(StaffInfoDO staffInfoDO) {
        StaffInfoDTO staffInfoDTO = new StaffInfoDTO();
        BeanUtils.copyProperties(staffInfoDO, staffInfoDTO);
        return staffInfoDTO;
    }

    /**
     * StaffInfoDTO 转换 StaffInfoVO
     * @param staffInfoDTO
     * @return
     */
    public static StaffInfoVO toVO(StaffInfoDTO staffInfoDTO) {
        if (staffInfoDTO == null) return null;
        StaffInfoVO staffInfoVO = new StaffInfoVO();
        BeanUtils.copyProperties(staffInfoDTO, staffInfoVO);
        return staffInfoVO;
    }

    /**
     * List<StaffInfoDTO> 转换 List<StaffInfoVO>
     * @param staffInfoDTOList
     * @return
     */
    public static List<StaffInfoVO> toVO(List<StaffInfoDTO> staffInfoDTOList) {
        List<StaffInfoVO> staffInfoVOList = new ArrayList<>();
        for (StaffInfoDTO staffInfoDTO : staffInfoDTOList) {
            staffInfoVOList.add(toVO(staffInfoDTO));
        }
        return staffInfoVOList;
    }

    /**
     * Page<StaffInfoDTO> 转换 Page<StaffInfoVO>
     * @param staffInfoDTOPage
     * @return
     */
    public static Page<StaffInfoVO> toVO(Page<StaffInfoDTO> staffInfoDTOPage) {
        List<StaffInfoDTO> staffInfoDTOList = staffInfoDTOPage.getRecords();
        Page<StaffInfoVO> staffInfoVOPage = new Page<>();
        BeanUtils.copyProperties(staffInfoDTOPage, staffInfoVOPage);
        staffInfoVOPage.setRecords(toVO(staffInfoDTOList));
        return staffInfoVOPage;
    }

}
