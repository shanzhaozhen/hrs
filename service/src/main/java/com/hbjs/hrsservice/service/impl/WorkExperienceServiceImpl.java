package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.WorkExperienceConverter;
import com.hbjs.hrscommon.domain.hr.WorkExperienceDO;
import com.hbjs.hrscommon.dto.WorkExperienceDTO;
import com.hbjs.hrscommon.excel.WorkExperienceExcel;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrsrepo.mapper.WorkExperienceMapper;
import com.hbjs.hrsservice.service.WorkExperienceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkExperienceServiceImpl implements WorkExperienceService {

    private final WorkExperienceMapper workExperienceMapper;

    @Override
    public Page<WorkExperienceDTO> getWorkExperiencePage(Page<WorkExperienceDTO> page, String keyword) {
        return workExperienceMapper.getWorkExperiencePage(page, keyword);
    }

    @Override
    public WorkExperienceDTO getWorkExperienceById(Long workExperienceId) {
        WorkExperienceDO workExperienceDO = workExperienceMapper.selectById(workExperienceId);
        Assert.notNull(workExperienceDO, "获取失败：没有找到该工作履历或已被删除");
        return WorkExperienceConverter.toDTO(workExperienceDO);
    }

    @Override
    public List<WorkExperienceDTO> getWorkExperienceListByPid(Long pid) {
        return workExperienceMapper.getWorkExperienceListByPid(pid);
    }

    @Override
    @Transactional
    public Long addWorkExperience(WorkExperienceDTO workExperienceDTO) {
        WorkExperienceDO workExperienceDO = WorkExperienceConverter.toDO(workExperienceDTO);
        workExperienceMapper.insert(workExperienceDO);
        return workExperienceDO.getId();
    }

    @Override
    @Transactional
    public Long updateWorkExperience(WorkExperienceDTO workExperienceDTO) {
        Assert.notNull(workExperienceDTO.getId(), "工作履历id不能为空");
        WorkExperienceDO workExperienceDO = workExperienceMapper.selectById(workExperienceDTO.getId());
        Assert.notNull(workExperienceDO, "更新失败：没有找到该工作履历或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(workExperienceDTO, workExperienceDO);
        workExperienceMapper.updateById(workExperienceDO);
        return workExperienceDO.getId();
    }

    @Override
    @Transactional
    public Long deleteWorkExperience(Long workExperienceId) {
        WorkExperienceDTO workExperienceDTO = this.getWorkExperienceById(workExperienceId);
        Assert.notNull(workExperienceDTO, "删除失败：没有找到该工作履历或已被删除");
        workExperienceMapper.deleteById(workExperienceId);
        return workExperienceId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteWorkExperience(@NotEmpty(message = "没有需要删除的工作履历") List<Long> workExperienceIds) {
        for (Long workExperienceId : workExperienceIds) {
            this.deleteWorkExperience(workExperienceId);
        }
        return workExperienceIds;
    }

    @Override
    public long deleteWorkExperienceByStaffId(Long staffId) {
        return workExperienceMapper.deleteWorkExperienceByStaffId(staffId);
    }

    @Override
    public void batchAddWorkExperience(List<WorkExperienceDTO> workExperienceDTOList, Long staffId) {
        this.deleteWorkExperienceByStaffId(staffId);
        if (!CollectionUtils.isEmpty(workExperienceDTOList)) {
            for (WorkExperienceDTO workExperienceDTO : workExperienceDTOList) {
                workExperienceDTO.setId(null).setPid(staffId);
                this.addWorkExperience(workExperienceDTO);
            }
        }
    }

    @Override
    public List<WorkExperienceExcel> getWorkExperienceExcelList(String keyword, Long depId, String companyState, String postLevel) {
        return workExperienceMapper.getWorkExperienceExcelList(keyword, depId, companyState, postLevel);
    }

}
