package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.WorkExperienceDTO;
import com.hbjs.hrscommon.excel.WorkExperienceExcel;

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
     * @param workExperienceId
     * @return
     */
    WorkExperienceDTO getWorkExperienceById(Long workExperienceId);

    /**
     * 通过pid获取工作履历信息
     * @param pid
     * @return
     */
    List<WorkExperienceDTO> getWorkExperienceListByPid(Long pid);

    /**
     * 新增工作履历
     * @param workExperienceDTO
     * @return
     */
    Long addWorkExperience(WorkExperienceDTO workExperienceDTO);

    /**
     * 修改工作履历
     * @param workExperienceDTO
     * @return
     */
    Long updateWorkExperience(WorkExperienceDTO workExperienceDTO);

    /**
     * 删除工作履历(通过工作履历id删除)
     * @param workExperienceId
     */
    Long deleteWorkExperience(Long workExperienceId);

    /**
     * 批量删除工作履历(通过工作履历id删除)
     * @param workExperienceIds
     * @return
     */
    List<Long> batchDeleteWorkExperience(List<Long> workExperienceIds);

    /**
     * 通过员工id删除工作履历
     * @param staffId
     * @return
     */
    long deleteWorkExperienceByStaffId(Long staffId);

    /**
     * 批量添加工作履历
     * @param workExperienceDTOList
     * @param staffId
     */
    void batchAddWorkExperience(List<WorkExperienceDTO> workExperienceDTOList, Long staffId);

    /**
     * 获取导出Excel的内容
     * @param keyword
     * @param depId
     * @param companyState
     * @param postLevel
     * @return
     */
    List<WorkExperienceExcel> getWorkExperienceExcelList(String keyword, Long depId, String companyState, String postLevel);
}
