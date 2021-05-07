package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.ResumeConverter;
import com.hbjs.hrscommon.domain.hr.ResumeDO;
import com.hbjs.hrscommon.dto.ResumeDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.EasyExcelUtils;
import com.hbjs.hrscommon.vo.ResumeExcel;
import com.hbjs.hrscommon.vo.StaffExcel;
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

    private final ResumeMapper resumeMapper;

    @Override
    public Page<ResumeDTO> getResumePage(Page<ResumeDTO> page, String keyword) {
        return resumeMapper.getResumePage(page, keyword);
    }

    @Override
    public ResumeDTO getResumeById(Long resumeId) {
        ResumeDO resumeDO = resumeMapper.selectById(resumeId);
        Assert.notNull(resumeDO, "获取失败：没有找到该简历或已被删除");
        return ResumeConverter.toDTO(resumeDO);
    }

    @Override
    @Transactional
    public Long addResume(ResumeDTO resumeDTO) {
        ResumeDO resumeDO = ResumeConverter.toDO(resumeDTO);
        resumeMapper.insert(resumeDO);
        return resumeDO.getId();
    }

    @Override
    @Transactional
    public Long updateResume(ResumeDTO resumeDTO) {
        Assert.notNull(resumeDTO.getId(), "简历id不能为空");
        ResumeDO resumeDO = resumeMapper.selectById(resumeDTO.getId());
        Assert.notNull(resumeDO, "更新失败：没有找到该简历或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(resumeDTO, resumeDO);
        resumeMapper.updateById(resumeDO);
        return resumeDO.getId();
    }

    @Override
    @Transactional
    public Long deleteResume(Long resumeId) {
        ResumeDTO resumeDTO = this.getResumeById(resumeId);
        Assert.notNull(resumeDTO, "删除失败：没有找到该简历或已被删除");
        resumeMapper.deleteById(resumeId);
        return resumeId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteResume(@NotEmpty(message = "没有需要删除的简历") List<Long> resumeIds) {
        for (Long resumeId : resumeIds) {
            this.deleteResume(resumeId);
        }
        return resumeIds;
    }

    @Override
    public void exportResume(String keyword) {
        List<ResumeExcel> resumeExcelList = resumeMapper.getResumeExcelList(keyword);
        EasyExcelUtils.exportExcel(ResumeExcel.class, resumeExcelList);
    }

}
