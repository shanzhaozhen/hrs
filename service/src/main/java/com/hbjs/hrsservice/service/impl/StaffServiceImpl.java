package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.hbjs.hrscommon.converter.StaffConverter;
import com.hbjs.hrscommon.domain.hr.StaffDO;
import com.hbjs.hrscommon.dto.StaffDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.EasyExcelUtils;
import com.hbjs.hrscommon.utils.PoiTlUtils;
import com.hbjs.hrscommon.vo.StaffExcel;
import com.hbjs.hrscommon.vo.StaffInfoVO;
import com.hbjs.hrsrepo.mapper.StaffMapper;
import com.hbjs.hrsservice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import javax.validation.constraints.NotEmpty;
import java.io.FileNotFoundException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffMapper staffMapper;
    private final StaffInfoService staffInfoService;
    private final WorkExperienceService workExperienceService;
    private final EducationalExperienceService educationalExperienceService;
    private final CertificateService certificateService;
    private final FamilyService familyService;

    @Override
    public Page<StaffDTO> getStaffPage(Page<StaffDTO> page, String keyword, Long depId) {
        return staffMapper.getStaffPage(page, keyword, depId);
    }

    @Override
    public StaffDTO getStaffById(Long staffId) {
        StaffDO staffDO = staffMapper.selectById(staffId);
        Assert.notNull(staffDO, "获取失败：没有找到该员工信息或已被删除");
        StaffDTO staffDTO = StaffConverter.toDTO(staffDO);
        return staffDTO
                .setStaffInfo(staffInfoService.getStaffInfoByStaffId(staffId))
                .setWorkExperienceList(workExperienceService.getWorkExperienceListByPid(staffId))
                .setEducationalExperienceList(educationalExperienceService.getEducationalExperienceListByPid(staffId))
                .setCertificateList(certificateService.getCertificateListByPid(staffId))
                .setFamilyList(familyService.getFamilyListByPid(staffId));
    }

    @Override
    @Transactional
    public Long addStaff(StaffDTO staffDTO) {
        StaffDO staffDO = StaffConverter.toDO(staffDTO);
        staffMapper.insert(staffDO);
        this.updateStaffMoreInfo(staffDTO, staffDO.getId());
        return staffDO.getId();
    }

    @Override
    @Transactional
    public Long updateStaff(StaffDTO staffDTO) {
        Assert.notNull(staffDTO.getId(), "员工信息id不能为空");
        StaffDTO staffInDB = staffMapper.getStaffByStaffCode(staffDTO.getStaffCode());
        Assert.isTrue(staffInDB == null || staffInDB.getId().equals(staffDTO.getId()), "创建失败：字典代码已被占用");
        StaffDO staffDO = staffMapper.selectById(staffDTO.getId());
        Assert.notNull(staffDO, "更新失败：没有找到该员工信息或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(staffDTO, staffDO);
        staffMapper.updateById(staffDO);
        this.updateStaffMoreInfo(staffDTO, staffDO.getId());
        return staffDO.getId();
    }

    @Override
    @Transactional
    public void updateStaffMoreInfo(StaffDTO staffDTO, Long staffId) {
        workExperienceService.batchAddWorkExperience(staffDTO.getWorkExperienceList(), staffId);
        educationalExperienceService.batchAddEducationalExperience(staffDTO.getEducationalExperienceList(), staffId);
        certificateService.batchAddCertificate(staffDTO.getCertificateList(), staffId);
        familyService.batchAddFamily(staffDTO.getFamilyList(), staffId);
    }

    @Override
    @Transactional
    public Long deleteStaff(Long staffId) {
        StaffDTO staffDTO = this.getStaffById(staffId);
        Assert.notNull(staffDTO, "删除失败：没有找到该员工信息或已被删除");
        staffMapper.deleteById(staffId);
        return staffId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteStaff(@NotEmpty(message = "没有需要删除的员工信息") List<Long> staffIds) {
        for (Long staffId : staffIds) {
            this.deleteStaff(staffId);
        }
        return staffIds;
    }

    @Override
    public void exportStaff(String keyword, Long depId) {
        List<StaffExcel> staffExcelList = staffMapper.getStaffExcelList(keyword, depId);
        EasyExcelUtils.exportExcel(StaffExcel.class, staffExcelList);
    }

    @Override
    public void printStaff(Long staffId) {
        StaffDO staffDO = staffMapper.selectById(staffId);
        XWPFTemplate template = null;
        try {
            ConfigureBuilder builder = Configure.builder();
            builder.useSpringEL();
            template = XWPFTemplate.compile(ResourceUtils.getFile("classpath:doc/job.docx"), builder.build()).render(staffDO);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Assert.notNull(e, "找不到对应的模板文件");
        }
        Assert.notNull(template, "生成模板文件失败");
        PoiTlUtils.exportExcel(template, "job.docx");
    }

}
