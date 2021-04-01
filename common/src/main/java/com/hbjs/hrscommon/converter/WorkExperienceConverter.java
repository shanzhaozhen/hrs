package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.WorkExperienceDO;
import com.hbjs.hrscommon.dto.WorkExperienceDTO;
import com.hbjs.hrscommon.form.WorkExperienceForm;
import com.hbjs.hrscommon.vo.WorkExperienceVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class WorkExperienceConverter {
    
    /**
     * WorkExperienceDTO 转换 WorkExperienceDO
     * @param workExperienceDTO
     * @return
     */
    public static WorkExperienceDO toDO(WorkExperienceDTO workExperienceDTO) {
        WorkExperienceDO workExperienceDO = new WorkExperienceDO();
        BeanUtils.copyProperties(workExperienceDTO, workExperienceDO);
        return workExperienceDO;
    }

    /**
     * WorkExperienceForm 转换 WorkExperienceDTO
     * @param workExperienceForm
     * @return
     */
    public static WorkExperienceDTO toDTO(WorkExperienceForm workExperienceForm) {
        WorkExperienceDTO workExperienceDTO = new WorkExperienceDTO();
        BeanUtils.copyProperties(workExperienceForm, workExperienceDTO);
        return workExperienceDTO;
    }

    /**
     * WorkExperienceVO 转换 WorkExperienceDTO
     * @param workExperienceVO
     * @return
     */
    public static WorkExperienceDTO toDTO(WorkExperienceVO workExperienceVO) {
        WorkExperienceDTO workExperienceDTO = new WorkExperienceDTO();
        BeanUtils.copyProperties(workExperienceVO, workExperienceDTO);
        return workExperienceDTO;
    }

    /**
     * WorkExperienceDO 转换 WorkExperienceDTO
     * @param workExperienceDO
     * @return
     */
    public static WorkExperienceDTO toDTO(WorkExperienceDO workExperienceDO) {
        WorkExperienceDTO workExperienceDTO = new WorkExperienceDTO();
        BeanUtils.copyProperties(workExperienceDO, workExperienceDTO);
        return workExperienceDTO;
    }

    /**
     * WorkExperienceDTO 转换 WorkExperienceVO
     * @param workExperienceDTO
     * @return
     */
    public static WorkExperienceVO toVO(WorkExperienceDTO workExperienceDTO) {
        WorkExperienceVO workExperienceVO = new WorkExperienceVO();
        BeanUtils.copyProperties(workExperienceDTO, workExperienceVO);
        return workExperienceVO;
    }

    /**
     * List<WorkExperienceDTO> 转换 List<WorkExperienceVO>
     * @param workExperienceDTOList
     * @return
     */
    public static List<WorkExperienceVO> toVO(List<WorkExperienceDTO> workExperienceDTOList) {
        List<WorkExperienceVO> workExperienceVOList = new ArrayList<>();
        for (WorkExperienceDTO workExperienceDTO : workExperienceDTOList) {
            workExperienceVOList.add(toVO(workExperienceDTO));
        }
        return workExperienceVOList;
    }

    /**
     * Page<WorkExperienceDTO> 转换 Page<WorkExperienceVO>
     * @param workExperienceDTOPage
     * @return
     */
    public static Page<WorkExperienceVO> toVO(Page<WorkExperienceDTO> workExperienceDTOPage) {
        List<WorkExperienceDTO> workExperienceDTOList = workExperienceDTOPage.getRecords();
        Page<WorkExperienceVO> workExperienceVOPage = new Page<>();
        BeanUtils.copyProperties(workExperienceDTOPage, workExperienceVOPage);
        workExperienceVOPage.setRecords(toVO(workExperienceDTOList));
        return workExperienceVOPage;
    }

}
