package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import com.hbjs.hrscommon.converter.ResumeConverter;
import com.hbjs.hrscommon.domain.hr.ResumeDO;
import com.hbjs.hrscommon.dto.ResumeDTO;
import com.hbjs.hrscommon.dto.WorkExperienceDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.ResumeTablePolicy;
import com.hbjs.hrscommon.utils.EasyExcelUtils;
import com.hbjs.hrscommon.utils.PoiTlUtils;
import com.hbjs.hrscommon.vo.ResumeExcel;
import com.hbjs.hrscommon.dto.ResumeMoreInfo;
import com.hbjs.hrscommon.dto.ResumePrint;
import com.hbjs.hrsrepo.mapper.ResumeMapper;
import com.hbjs.hrsservice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import javax.validation.constraints.NotEmpty;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeMapper resumeMapper;
    private final WorkExperienceService workExperienceService;
    private final EducationalExperienceService educationalExperienceService;
    private final CertificateService certificateService;
    private final FamilyService familyService;

    @Override
    public Page<ResumeDTO> getResumePage(Page<ResumeDTO> page, String keyword) {
        return resumeMapper.getResumePage(page, keyword);
    }

    @Override
    public ResumeDTO getResumeById(Long resumeId) {
        ResumeDO resumeDO = resumeMapper.selectById(resumeId);
        Assert.notNull(resumeDO, "获取失败：没有找到该简历或已被删除");
        ResumeDTO resumeDTO = ResumeConverter.toDTO(resumeDO);
        return resumeDTO
                .setWorkExperienceList(workExperienceService.getWorkExperienceListByPid(resumeId))
                .setEducationalExperienceList(educationalExperienceService.getEducationalExperienceListByPid(resumeId))
                .setCertificateList(certificateService.getCertificateListByPid(resumeId))
                .setFamilyList(familyService.getFamilyListByPid(resumeId));
    }

    @Override
    @Transactional
    public Long addResume(ResumeDTO resumeDTO) {
        ResumeDO resumeDO = ResumeConverter.toDO(resumeDTO);
        resumeMapper.insert(resumeDO);
        this.updateResumeMoreInfo(resumeDTO, resumeDO.getId());
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
        this.updateResumeMoreInfo(resumeDTO, resumeDO.getId());
        return resumeDO.getId();
    }

    @Override
    @Transactional
    public void updateResumeMoreInfo(ResumeDTO resumeDTO, Long resumeId) {
        workExperienceService.batchAddWorkExperience(resumeDTO.getWorkExperienceList(), resumeId);
        educationalExperienceService.batchAddEducationalExperience(resumeDTO.getEducationalExperienceList(), resumeId);
        certificateService.batchAddCertificate(resumeDTO.getCertificateList(), resumeId);
        familyService.batchAddFamily(resumeDTO.getFamilyList(), resumeId);
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

    @Override
    public void printResume(Long resumeId) {
        ResumePrint resumePrint = resumeMapper.getResumePrintByResumeId(resumeId);
        Assert.notNull(resumePrint, "没有找到该简历信息");
        ResumeMoreInfo resumeMoreInfo = new ResumeMoreInfo();
//        List<WorkExperienceDTO> workExperienceDTOList = new ArrayList<>();
//        workExperienceDTOList.add(new WorkExperienceDTO(1L, 1L, "22", new Date(), new Date(), "2", "2", "3", "45", "355f"));
//        workExperienceDTOList.add(new WorkExperienceDTO(1L, 1L, "33", new Date(), new Date(), "3", "3", "3", "55", "11"));
//        workExperienceDTOList.add(new WorkExperienceDTO(1L, 1L, "55", new Date(), new Date(), "4", "3", "2", "44", "22"));
////        workExperienceDTOList.add(new WorkExperienceDTO(1L, 1L, "44", new Date(), new Date(), "6", "1", "32", "44", "22"));
////        workExperienceDTOList.add(new WorkExperienceDTO(1L, 1L, "66", new Date(), new Date(), "56", "5", "2", "55", "55"));
//        workExperienceDTOList.add(new WorkExperienceDTO());
//        workExperienceDTOList.add(new WorkExperienceDTO());
//        workExperienceDTOList.add(new WorkExperienceDTO());
//        workExperienceDTOList.add(new WorkExperienceDTO());
        resumeMoreInfo
                .setWorkExperienceList(workExperienceService.getWorkExperienceListByPid(resumeId))
//                .setWorkExperienceList(workExperienceDTOList)
                .setEducationalExperienceList(educationalExperienceService.getEducationalExperienceListByPid(resumeId))
                .setCertificateList(certificateService.getCertificateListByPid(resumeId))
                .setFamilyList(familyService.getFamilyListByPid(resumeId));
        resumePrint.setResumeMoreInfo(resumeMoreInfo);
//        resumePrint.setWorkExperienceDTOList(workExperienceDTOList);
        XWPFTemplate template = null;
        try {
            Configure config = Configure.builder()
                    .useSpringEL()
//                    .bind("workExperienceDTOList", new HackLoopTableRenderPolicy())
                    .bind("resumeMoreInfo", new ResumeTablePolicy())
                    .build();
            template = XWPFTemplate.compile(ResourceUtils.getFile("classpath:doc/job.docx"), config).render(resumePrint);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Assert.notNull(e, "找不到对应的模板文件");
        }
        Assert.notNull(template, "生成模板文件失败");
        PoiTlUtils.exportExcel(template, "job.docx");
    }

}
