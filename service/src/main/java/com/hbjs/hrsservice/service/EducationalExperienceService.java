package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.EducationalExperienceDTO;

import java.util.List;

public interface EducationalExperienceService {

    /**
     * 获取教育经历的分页列表
     * @param page
     * @param keyword
     * @return
     */
    Page<EducationalExperienceDTO> getEducationalExperiencePage(Page<EducationalExperienceDTO> page, String keyword);

    /**
     * 通过教育经历id获取
     * @param educationalExperienceId
     * @return
     */
    EducationalExperienceDTO getEducationalExperienceById(Long educationalExperienceId);

    /**
     * 通过pid获取教育经历信息
     * @param pid
     * @return
     */
    List<EducationalExperienceDTO> getEducationalExperienceListByPid(Long pid);

    /**
     * 新增教育经历
     * @param educationalExperienceDTO
     * @return
     */
    Long addEducationalExperience(EducationalExperienceDTO educationalExperienceDTO);

    /**
     * 修改教育经历
     * @param educationalExperienceDTO
     * @return
     */
    Long updateEducationalExperience(EducationalExperienceDTO educationalExperienceDTO);

    /**
     * 删除教育经历(通过教育经历id删除)
     * @param educationalExperienceId
     */
    Long deleteEducationalExperience(Long educationalExperienceId);

    /**
     * 批量删除教育经历(通过教育经历id删除)
     * @param educationalExperienceIds
     * @return
     */
    List<Long> batchDeleteEducationalExperience(List<Long> educationalExperienceIds);

}