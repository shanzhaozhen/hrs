package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.ResumeDO;
import com.hbjs.hrscommon.dto.ResumeDTO;
import com.hbjs.hrscommon.form.ResumeForm;
import com.hbjs.hrscommon.vo.ResumeVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ResumeConverter {

    /**
     * ResumeDTO 转换 ResumeDO
     * @param resumeDTO
     * @return
     */
    public static ResumeDO toDO(ResumeDTO resumeDTO) {
        ResumeDO resumeDO = new ResumeDO();
        BeanUtils.copyProperties(resumeDTO, resumeDO);
        return resumeDO;
    }

    /**
     * ResumeForm 转换 ResumeDTO
     * @param resumeForm
     * @return
     */
    public static ResumeDTO toDTO(ResumeForm resumeForm) {
        ResumeDTO resumeDTO = new ResumeDTO();
        BeanUtils.copyProperties(resumeForm, resumeDTO);
        resumeDTO.setWorkExperienceList(WorkExperienceConverter.toDTO(resumeForm.getWorkExperienceList()));
        resumeDTO.setEducationalExperienceList(EducationalExperienceConverter.toDTO(resumeForm.getEducationalExperienceList()));
        resumeDTO.setCertificateList(CertificateConverter.toDTO(resumeForm.getCertificateList()));
        resumeDTO.setFamilyList(FamilyConverter.toDTO(resumeForm.getFamilyList()));
        return resumeDTO;
    }

    /**
     * ResumeVO 转换 ResumeDTO
     * @param resumeVO
     * @return
     */
    public static ResumeDTO toDTO(ResumeVO resumeVO) {
        ResumeDTO resumeDTO = new ResumeDTO();
        BeanUtils.copyProperties(resumeVO, resumeDTO);
        return resumeDTO;
    }

    /**
     * ResumeDO 转换 ResumeDTO
     * @param resumeDO
     * @return
     */
    public static ResumeDTO toDTO(ResumeDO resumeDO) {
        ResumeDTO resumeDTO = new ResumeDTO();
        BeanUtils.copyProperties(resumeDO, resumeDTO);
        return resumeDTO;
    }

    /**
     * ResumeDTO 转换 ResumeVO
     * @param resumeDTO
     * @return
     */
    public static ResumeVO toVO(ResumeDTO resumeDTO) {
        ResumeVO resumeVO = new ResumeVO();
        BeanUtils.copyProperties(resumeDTO, resumeVO);
        resumeVO.setWorkExperienceList(WorkExperienceConverter.toVO(resumeDTO.getWorkExperienceList()));
        resumeVO.setEducationalExperienceList(EducationalExperienceConverter.toVO(resumeDTO.getEducationalExperienceList()));
        resumeVO.setCertificateList(CertificateConverter.toVO(resumeDTO.getCertificateList()));
        resumeVO.setFamilyList(FamilyConverter.toVO(resumeDTO.getFamilyList()));
        return resumeVO;
    }

    /**
     * List<ResumeDTO> 转换 List<ResumeVO>
     * @param resumeDTOList
     * @return
     */
    public static List<ResumeVO> toVO(List<ResumeDTO> resumeDTOList) {
        List<ResumeVO> resumeVOList = new ArrayList<>();
        for (ResumeDTO resumeDTO : resumeDTOList) {
            resumeVOList.add(toVO(resumeDTO));
        }
        return resumeVOList;
    }

    /**
     * Page<ResumeDTO> 转换 Page<ResumeVO>
     * @param resumeDTOPage
     * @return
     */
    public static Page<ResumeVO> toVO(Page<ResumeDTO> resumeDTOPage) {
        List<ResumeDTO> resumeDTOList = resumeDTOPage.getRecords();
        Page<ResumeVO> resumeVOPage = new Page<>();
        BeanUtils.copyProperties(resumeDTOPage, resumeVOPage);
        resumeVOPage.setRecords(toVO(resumeDTOList));
        return resumeVOPage;
    }

}
