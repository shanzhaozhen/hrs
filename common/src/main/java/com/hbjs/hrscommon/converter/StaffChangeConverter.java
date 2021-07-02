package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.StaffChangeDO;
import com.hbjs.hrscommon.dto.StaffChangeDTO;
import com.hbjs.hrscommon.form.StaffChangeForm;
import com.hbjs.hrscommon.vo.StaffChangeVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class StaffChangeConverter {
    
    /**
     * StaffChangeDTO 转换 StaffChangeDO
     * @param staffChangeDTO
     * @return
     */
    public static StaffChangeDO toDO(StaffChangeDTO staffChangeDTO) {
        StaffChangeDO staffChangeDO = new StaffChangeDO();
        BeanUtils.copyProperties(staffChangeDTO, staffChangeDO);
        return staffChangeDO;
    }

    /**
     * StaffChangeForm 转换 StaffChangeDTO
     * @param staffChangeForm
     * @return
     */
    public static StaffChangeDTO toDTO(StaffChangeForm staffChangeForm) {
        StaffChangeDTO staffChangeDTO = new StaffChangeDTO();
        BeanUtils.copyProperties(staffChangeForm, staffChangeDTO);
        return staffChangeDTO;
    }

    /**
     * StaffChangeVO 转换 StaffChangeDTO
     * @param staffChangeVO
     * @return
     */
    public static StaffChangeDTO toDTO(StaffChangeVO staffChangeVO) {
        StaffChangeDTO staffChangeDTO = new StaffChangeDTO();
        BeanUtils.copyProperties(staffChangeVO, staffChangeDTO);
        return staffChangeDTO;
    }

    /**
     * StaffChangeDO 转换 StaffChangeDTO
     * @param staffChangeDO
     * @return
     */
    public static StaffChangeDTO toDTO(StaffChangeDO staffChangeDO) {
        StaffChangeDTO staffChangeDTO = new StaffChangeDTO();
        BeanUtils.copyProperties(staffChangeDO, staffChangeDTO);
        return staffChangeDTO;
    }

    /**
     * StaffChangeDTO 转换 StaffChangeVO
     * @param staffChangeDTO
     * @return
     */
    public static StaffChangeVO toVO(StaffChangeDTO staffChangeDTO) {
        StaffChangeVO staffChangeVO = new StaffChangeVO();
        BeanUtils.copyProperties(staffChangeDTO, staffChangeVO);
        return staffChangeVO;
    }

    /**
     * List<StaffChangeDTO> 转换 List<StaffChangeVO>
     * @param staffChangeDTOList
     * @return
     */
    public static List<StaffChangeVO> toVO(List<StaffChangeDTO> staffChangeDTOList) {
        List<StaffChangeVO> staffChangeVOList = new ArrayList<>();
        for (StaffChangeDTO staffChangeDTO : staffChangeDTOList) {
            staffChangeVOList.add(toVO(staffChangeDTO));
        }
        return staffChangeVOList;
    }

    /**
     * Page<StaffChangeDTO> 转换 Page<StaffChangeVO>
     * @param staffChangeDTOPage
     * @return
     */
    public static Page<StaffChangeVO> toVO(Page<StaffChangeDTO> staffChangeDTOPage) {
        List<StaffChangeDTO> staffChangeDTOList = staffChangeDTOPage.getRecords();
        Page<StaffChangeVO> staffChangeVOPage = new Page<>();
        BeanUtils.copyProperties(staffChangeDTOPage, staffChangeVOPage);
        staffChangeVOPage.setRecords(toVO(staffChangeDTOList));
        return staffChangeVOPage;
    }

}
