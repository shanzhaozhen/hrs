package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.EducationalExperienceDO;
import com.hbjs.hrscommon.dto.EducationalExperienceDTO;
import com.hbjs.hrscommon.form.EducationalExperienceForm;
import com.hbjs.hrscommon.vo.EducationalExperienceVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class EducationalExperienceConverter {
    
    /**
     * EducationalExperienceDTO 转换 EducationalExperienceDO
     * @param educationalExperienceDTO
     * @return
     */
    public static EducationalExperienceDO toDO(EducationalExperienceDTO educationalExperienceDTO) {
        EducationalExperienceDO educationalExperienceDO = new EducationalExperienceDO();
        BeanUtils.copyProperties(educationalExperienceDTO, educationalExperienceDO);
        return educationalExperienceDO;
    }

    /**
     * EducationalExperienceForm 转换 EducationalExperienceDTO
     * @param educationalExperienceForm
     * @return
     */
    public static EducationalExperienceDTO toDTO(EducationalExperienceForm educationalExperienceForm) {
        EducationalExperienceDTO educationalExperienceDTO = new EducationalExperienceDTO();
        BeanUtils.copyProperties(educationalExperienceForm, educationalExperienceDTO);
        return educationalExperienceDTO;
    }

    /**
     * EducationalExperienceVO 转换 EducationalExperienceDTO
     * @param educationalExperienceVO
     * @return
     */
    public static EducationalExperienceDTO toDTO(EducationalExperienceVO educationalExperienceVO) {
        EducationalExperienceDTO educationalExperienceDTO = new EducationalExperienceDTO();
        BeanUtils.copyProperties(educationalExperienceVO, educationalExperienceDTO);
        return educationalExperienceDTO;
    }

    /**
     * EducationalExperienceDO 转换 EducationalExperienceDTO
     * @param educationalExperienceDO
     * @return
     */
    public static EducationalExperienceDTO toDTO(EducationalExperienceDO educationalExperienceDO) {
        EducationalExperienceDTO educationalExperienceDTO = new EducationalExperienceDTO();
        BeanUtils.copyProperties(educationalExperienceDO, educationalExperienceDTO);
        return educationalExperienceDTO;
    }

    /**
     * EducationalExperienceDTO 转换 EducationalExperienceVO
     * @param educationalExperienceDTO
     * @return
     */
    public static EducationalExperienceVO toVO(EducationalExperienceDTO educationalExperienceDTO) {
        EducationalExperienceVO educationalExperienceVO = new EducationalExperienceVO();
        BeanUtils.copyProperties(educationalExperienceDTO, educationalExperienceVO);
        return educationalExperienceVO;
    }

    /**
     * List<EducationalExperienceDTO> 转换 List<EducationalExperienceVO>
     * @param educationalExperienceDTOList
     * @return
     */
    public static List<EducationalExperienceVO> toVO(List<EducationalExperienceDTO> educationalExperienceDTOList) {
        List<EducationalExperienceVO> educationalExperienceVOList = new ArrayList<>();
        for (EducationalExperienceDTO educationalExperienceDTO : educationalExperienceDTOList) {
            educationalExperienceVOList.add(toVO(educationalExperienceDTO));
        }
        return educationalExperienceVOList;
    }

    /**
     * Page<EducationalExperienceDTO> 转换 Page<EducationalExperienceVO>
     * @param educationalExperienceDTOPage
     * @return
     */
    public static Page<EducationalExperienceVO> toVO(Page<EducationalExperienceDTO> educationalExperienceDTOPage) {
        List<EducationalExperienceDTO> educationalExperienceDTOList = educationalExperienceDTOPage.getRecords();
        Page<EducationalExperienceVO> educationalExperienceVOPage = new Page<>();
        BeanUtils.copyProperties(educationalExperienceDTOPage, educationalExperienceVOPage);
        educationalExperienceVOPage.setRecords(toVO(educationalExperienceDTOList));
        return educationalExperienceVOPage;
    }

}
