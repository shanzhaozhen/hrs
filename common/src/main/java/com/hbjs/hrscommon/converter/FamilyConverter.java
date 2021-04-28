package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.FamilyDO;
import com.hbjs.hrscommon.dto.FamilyDTO;
import com.hbjs.hrscommon.form.FamilyForm;
import com.hbjs.hrscommon.vo.FamilyVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class FamilyConverter {
    
    /**
     * FamilyDTO 转换 FamilyDO
     * @param familyDTO
     * @return
     */
    public static FamilyDO toDO(FamilyDTO familyDTO) {
        FamilyDO familyDO = new FamilyDO();
        BeanUtils.copyProperties(familyDTO, familyDO);
        return familyDO;
    }

    /**
     * FamilyForm 转换 FamilyDTO
     * @param familyForm
     * @return
     */
    public static FamilyDTO toDTO(FamilyForm familyForm) {
        FamilyDTO familyDTO = new FamilyDTO();
        BeanUtils.copyProperties(familyForm, familyDTO);
        return familyDTO;
    }

    /**
     * FamilyVO 转换 FamilyDTO
     * @param familyVO
     * @return
     */
    public static FamilyDTO toDTO(FamilyVO familyVO) {
        FamilyDTO familyDTO = new FamilyDTO();
        BeanUtils.copyProperties(familyVO, familyDTO);
        return familyDTO;
    }

    /**
     * FamilyDO 转换 FamilyDTO
     * @param familyDO
     * @return
     */
    public static FamilyDTO toDTO(FamilyDO familyDO) {
        FamilyDTO familyDTO = new FamilyDTO();
        BeanUtils.copyProperties(familyDO, familyDTO);
        return familyDTO;
    }

    /**
     * List<FamilyForm> 转换 List<FamilyDTO>
     * @param familyFormList
     * @return
     */
    public static List<FamilyDTO> toDTO(List<FamilyForm> familyFormList) {
        if (CollectionUtils.isEmpty(familyFormList)) return null;
        List<FamilyDTO> familyDTOList = new ArrayList<>();
        for (FamilyForm familyForm : familyFormList) {
            familyDTOList.add(toDTO(familyForm));
        }
        return familyDTOList;
    }

    /**
     * FamilyDTO 转换 FamilyVO
     * @param familyDTO
     * @return
     */
    public static FamilyVO toVO(FamilyDTO familyDTO) {
        FamilyVO familyVO = new FamilyVO();
        BeanUtils.copyProperties(familyDTO, familyVO);
        return familyVO;
    }

    /**
     * List<FamilyDTO> 转换 List<FamilyVO>
     * @param familyDTOList
     * @return
     */
    public static List<FamilyVO> toVO(List<FamilyDTO> familyDTOList) {
        if (CollectionUtils.isEmpty(familyDTOList)) return null;
        List<FamilyVO> familyVOList = new ArrayList<>();
        for (FamilyDTO familyDTO : familyDTOList) {
            familyVOList.add(toVO(familyDTO));
        }
        return familyVOList;
    }

    /**
     * Page<FamilyDTO> 转换 Page<FamilyVO>
     * @param familyDTOPage
     * @return
     */
    public static Page<FamilyVO> toVO(Page<FamilyDTO> familyDTOPage) {
        List<FamilyDTO> familyDTOList = familyDTOPage.getRecords();
        Page<FamilyVO> familyVOPage = new Page<>();
        BeanUtils.copyProperties(familyDTOPage, familyVOPage);
        familyVOPage.setRecords(toVO(familyDTOList));
        return familyVOPage;
    }

}
