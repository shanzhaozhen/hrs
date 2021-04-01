package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.WorkExperienceDTO;

import java.util.List;

public interface WorkExperienceService {

    /**
     * 获取工作履历的分页列表
     * @param page
     * @param keyword
     * @return
     */
    Page<WorkExperienceDTO> getWorkExperiencePage(Page<WorkExperienceDTO> page, String keyword);

    /**
     * 通过工作履历id获取
     * @param familyId
     * @return
     */
    WorkExperienceDTO getWorkExperienceById(Long familyId);

    /**
     * 通过pid获取工作履历信息
     * @param pid
     * @return
     */
    List<WorkExperienceDTO> getWorkExperienceListByPid(Long pid);

    /**
     * 新增工作履历
     * @param familyDTO
     * @return
     */
    Long addWorkExperience(WorkExperienceDTO familyDTO);

    /**
     * 修改工作履历
     * @param familyDTO
     * @return
     */
    Long updateWorkExperience(WorkExperienceDTO familyDTO);

    /**
     * 删除工作履历(通过工作履历id删除)
     * @param familyId
     */
    Long deleteWorkExperience(Long familyId);

    /**
     * 批量删除工作履历(通过工作履历id删除)
     * @param familyIds
     * @return
     */
    List<Long> batchDeleteWorkExperience(List<Long> familyIds);

}
