package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.EducationalExperienceConverter;
import com.hbjs.hrscommon.domain.hr.EducationalExperienceDO;
import com.hbjs.hrscommon.dto.EducationalExperienceDTO;
import com.hbjs.hrscommon.excel.EducationalExperienceExcel;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrsrepo.mapper.EducationalExperienceMapper;
import com.hbjs.hrsservice.service.EducationalExperienceService;
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
public class EducationalExperienceServiceImpl implements EducationalExperienceService {

    private final EducationalExperienceMapper educationalExperienceMapper;

    @Override
    public Page<EducationalExperienceDTO> getEducationalExperiencePage(Page<EducationalExperienceDTO> page, String keyword) {
        return educationalExperienceMapper.getEducationalExperiencePage(page, keyword);
    }

    @Override
    public EducationalExperienceDTO getEducationalExperienceById(Long educationalExperienceId) {
        EducationalExperienceDO educationalExperienceDO = educationalExperienceMapper.selectById(educationalExperienceId);
        Assert.notNull(educationalExperienceDO, "获取失败：没有找到该教育经历或已被删除");
        return EducationalExperienceConverter.toDTO(educationalExperienceDO);
    }

    @Override
    public List<EducationalExperienceDTO> getEducationalExperienceListByPid(Long pid) {
        return educationalExperienceMapper.getEducationalExperienceListByPid(pid);
    }

    @Override
    @Transactional
    public Long addEducationalExperience(EducationalExperienceDTO educationalExperienceDTO) {
        EducationalExperienceDO educationalExperienceDO = EducationalExperienceConverter.toDO(educationalExperienceDTO);
        educationalExperienceMapper.insert(educationalExperienceDO);
        return educationalExperienceDO.getId();
    }

    @Override
    @Transactional
    public Long updateEducationalExperience(EducationalExperienceDTO educationalExperienceDTO) {
        Assert.notNull(educationalExperienceDTO.getId(), "教育经历id不能为空");
        EducationalExperienceDO educationalExperienceDO = educationalExperienceMapper.selectById(educationalExperienceDTO.getId());
        Assert.notNull(educationalExperienceDO, "更新失败：没有找到该教育经历或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(educationalExperienceDTO, educationalExperienceDO);
        educationalExperienceMapper.updateById(educationalExperienceDO);
        return educationalExperienceDO.getId();
    }

    @Override
    @Transactional
    public Long deleteEducationalExperience(Long educationalExperienceId) {
        EducationalExperienceDTO educationalExperienceDTO = this.getEducationalExperienceById(educationalExperienceId);
        Assert.notNull(educationalExperienceDTO, "删除失败：没有找到该教育经历或已被删除");
        educationalExperienceMapper.deleteById(educationalExperienceId);
        return educationalExperienceId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteEducationalExperience(@NotEmpty(message = "没有需要删除的教育经历") List<Long> educationalExperienceIds) {
        for (Long educationalExperienceId : educationalExperienceIds) {
            this.deleteEducationalExperience(educationalExperienceId);
        }
        return educationalExperienceIds;
    }

    @Override
    public long deleteEducationalExperienceByStaffId(Long staffId) {
        return educationalExperienceMapper.deleteEducationalExperienceByStaffId(staffId);
    }

    @Override
    public void batchAddEducationalExperience(List<EducationalExperienceDTO> educationalExperienceDTOList, Long staffId) {
        this.deleteEducationalExperienceByStaffId(staffId);
        if (!CollectionUtils.isEmpty(educationalExperienceDTOList)) {
            for (EducationalExperienceDTO educationalExperienceDTO : educationalExperienceDTOList) {
                educationalExperienceDTO.setId(null).setPid(staffId);
                this.addEducationalExperience(educationalExperienceDTO);
            }
        }
    }

    @Override
    public List<EducationalExperienceExcel> getEducationalExperienceExcelList(String keyword, Long depId, String companyState, String postLevel) {
        return educationalExperienceMapper.getEducationalExperienceExcelList(keyword, depId, companyState, postLevel);
    }

}
