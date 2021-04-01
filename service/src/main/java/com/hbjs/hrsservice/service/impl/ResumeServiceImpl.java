package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.ResumeConverter;
import com.hbjs.hrscommon.domain.hr.ResumeDO;
import com.hbjs.hrscommon.dto.ResumeDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrsrepo.mapper.ResumeMapper;
import com.hbjs.hrsservice.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeMapper staffMapper;

    @Override
    public Page<ResumeDTO> getResumePage(Page<ResumeDTO> page, String keyword) {
        return staffMapper.getResumePage(page, keyword);
    }

    @Override
    public ResumeDTO getResumeById(Long staffId) {
        ResumeDO staffDO = staffMapper.selectById(staffId);
        Assert.notNull(staffDO, "获取失败：没有找到该简历或已被删除");
        return ResumeConverter.toDTO(staffDO);
    }

    @Override
    @Transactional
    public Long addResume(ResumeDTO staffDTO) {
        ResumeDO staffDO = ResumeConverter.toDO(staffDTO);
        staffMapper.insert(staffDO);
        return staffDO.getId();
    }

    @Override
    @Transactional
    public Long updateResume(ResumeDTO staffDTO) {
        Assert.notNull(staffDTO.getId(), "简历id不能为空");
        ResumeDO staffDO = staffMapper.selectById(staffDTO.getId());
        Assert.notNull(staffDO, "更新失败：没有找到该简历或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(staffDTO, staffDO);
        staffMapper.updateById(staffDO);
        return staffDO.getId();
    }

    @Override
    @Transactional
    public Long deleteResume(Long staffId) {
        ResumeDTO staffDTO = this.getResumeById(staffId);
        Assert.notNull(staffDTO, "删除失败：没有找到该简历或已被删除");
        staffMapper.deleteById(staffId);
        return staffId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteResume(@NotEmpty(message = "没有需要删除的简历") List<Long> staffIds) {
        for (Long staffId : staffIds) {
            this.deleteResume(staffId);
        }
        return staffIds;
    }

}
