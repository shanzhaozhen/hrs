package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.StaffConverter;
import com.hbjs.hrscommon.domain.hr.StaffDO;
import com.hbjs.hrscommon.dto.StaffDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.EasyExcelUtils;
import com.hbjs.hrscommon.vo.StaffExcel;
import com.hbjs.hrsrepo.mapper.StaffMapper;
import com.hbjs.hrsservice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffMapper staffMapper;
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
                .setWorkExperienceList(workExperienceService.getWorkExperienceListByPid(staffDTO.getId()))
                .setEducationalExperienceList(educationalExperienceService.getEducationalExperienceListByPid(staffDTO.getId()))
                .setCertificateList(certificateService.getCertificateListByPid(staffDTO.getId()))
                .setFamilyList(familyService.getFamilyListByPid(staffDTO.getId()));
    }

    @Override
    @Transactional
    public Long addStaff(StaffDTO staffDTO) {
        StaffDO staffDO = StaffConverter.toDO(staffDTO);
        staffMapper.insert(staffDO);
        this.updateStaffOtherInfo(staffDTO, staffDO.getId());
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
        this.updateStaffOtherInfo(staffDTO, staffDO.getId());
        return staffDO.getId();
    }

    @Override
    @Transactional
    public void updateStaffOtherInfo(StaffDTO staffDTO, Long staffId) {
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

}
