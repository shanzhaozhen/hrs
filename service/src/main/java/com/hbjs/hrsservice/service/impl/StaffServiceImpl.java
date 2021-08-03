package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.hbjs.hrscommon.converter.StaffConverter;
import com.hbjs.hrscommon.domain.hr.StaffDO;
import com.hbjs.hrscommon.dto.*;
import com.hbjs.hrscommon.excel.ExcelExport;
import com.hbjs.hrscommon.excel.*;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.EasyExcelUtils;
import com.hbjs.hrscommon.utils.PoiTlUtils;
import com.hbjs.hrsrepo.mapper.StaffMapper;
import com.hbjs.hrsservice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final DepartmentService departmentService;
    private final StaffMapper staffMapper;
    private final StaffInfoService staffInfoService;
    private final WorkRecordService workRecordService;
    private final WorkExperienceService workExperienceService;
    private final EducationalExperienceService educationalExperienceService;
    private final QualificationService qualificationService;
    private final TitleService titleService;
    private final FamilyService familyService;
    private final DriverLicenseService driverLicenseService;
    private final ContractService contractService;
    private final StaffChangeService staffChangeService;
    

    @Override
    public Page<StaffDTO> getStaffPage(Page<StaffDTO> page, String keyword, Long depId, String companyState, String postLevel) {
        return staffMapper.getStaffPage(page, keyword, depId, companyState, postLevel);
    }

    @Override
    public StaffDTO getStaffById(Long staffId) {
        StaffDO staffDO = staffMapper.selectById(staffId);
        Assert.notNull(staffDO, "获取失败：没有找到该员工信息或已被删除");
        StaffDTO staffDTO = StaffConverter.toDTO(staffDO);
        return staffDTO
                .setStaffInfo(staffInfoService.getStaffInfoByStaffId(staffId))
                .setWorkRecordList(workRecordService.getWorkRecordListByStaffId(staffId))
                .setWorkExperienceList(workExperienceService.getWorkExperienceListByPid(staffId))
                .setEducationalExperienceList(educationalExperienceService.getEducationalExperienceListByPid(staffId))
                .setFamilyList(familyService.getFamilyListByPid(staffId))
                .setContractList(contractService.getContractListByStaffId(staffId))
                .setTitleList(titleService.getTitleListByStaffId(staffId))
                .setQualificationList(qualificationService.getQualificationListByStaffId(staffId))
                .setDriverLicenseList(driverLicenseService.getDriverLicenseListByPid(staffId));
    }

    @Override
    public StaffDTO getStaffByStaffCode(String staffCode) {
        StaffDO staffDO = new LambdaQueryChainWrapper<>(staffMapper).eq(StaffDO::getStaffCode, staffCode).one();
        return staffDO == null ? null : StaffConverter.toDTO(staffDO);
    }

    @Override
    @Transactional
    public Long addStaff(StaffDTO staffDTO, boolean updateOtherInfo) {
        StaffDO staffDO = StaffConverter.toDO(staffDTO);
        Assert.notNull(staffDO.getStaffCode(), "员工编号不能为空");
        StaffDTO staffInDB = staffMapper.getStaffByStaffCode(staffDTO.getStaffCode());
        Assert.isNull(staffInDB, "员工编号已存在");
        staffMapper.insert(staffDO);
        staffInfoService.updateStaffInfo(staffDTO.getStaffInfo(), staffDO.getId());
        if (updateOtherInfo) {
            this.updateStaffMoreInfo(staffDTO, staffDO.getId());
        }
        return staffDO.getId();
    }

    @Override
    @Transactional
    public Long updateStaff(StaffDTO staffDTO, boolean updateOtherInfo) {
        Assert.notNull(staffDTO.getId(), "员工信息id不能为空");
        StaffDTO staffInDB = staffMapper.getStaffByStaffCode(staffDTO.getStaffCode());
        Assert.isTrue(staffInDB == null || staffInDB.getId().equals(staffDTO.getId()), "更新失败：员工变化已被占用");
        StaffDO staffDO = staffMapper.selectById(staffDTO.getId());
        Assert.notNull(staffDO, "更新失败：没有找到该员工信息或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(staffDTO, staffDO);
        staffMapper.updateById(staffDO);
        staffInfoService.updateStaffInfo(staffDTO.getStaffInfo(), staffDO.getId());
        // 更新其他关联信息
        if (updateOtherInfo) {
            this.updateStaffMoreInfo(staffDTO, staffDO.getId());
        }
        return staffDO.getId();
    }

    @Override
    @Transactional
    public void updateStaffMoreInfo(StaffDTO staffDTO, Long staffId) {
        workRecordService.batchAddWorkRecord(staffDTO.getWorkRecordList(), staffId);
        workExperienceService.batchAddWorkExperience(staffDTO.getWorkExperienceList(), staffId);
        educationalExperienceService.batchAddEducationalExperience(staffDTO.getEducationalExperienceList(), staffId);
        familyService.batchAddFamily(staffDTO.getFamilyList(), staffId);
        contractService.batchAddContract(staffDTO.getContractList(), staffId);
        titleService.batchAddTitle(staffDTO.getTitleList(), staffId);
        qualificationService.batchAddQualification(staffDTO.getQualificationList(), staffId);
        driverLicenseService.batchAddDriverLicense(staffDTO.getDriverLicenseList(), staffId);
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
    @Transactional
    public Long runChange(Long staffChangeId) {
        Assert.notNull(staffChangeId, "调动记录id不能为空");
        StaffChangeDTO staffChangeDTO = staffChangeService.getStaffChangeById(staffChangeId);
        Assert.notNull(staffChangeDTO, "执行失败：没有找到该调动记录或已被删除");
        return this.runChange(staffChangeDTO);
    }

    @Override
    @Transactional
    public Long runChange(StaffChangeDTO staffChangeDTO) {
        StaffDO staffDO = staffMapper.selectById(staffChangeDTO.getStaffId());
        staffDO
                .setDepId(staffChangeDTO.getPostDepId())
                .setDuty(staffChangeDTO.getPostDuty())
                .setPost(staffChangeDTO.getPostPost())
                .setPostType(staffChangeDTO.getPostPostType())
                .setPostLevel(staffChangeDTO.getPostPostLevel());
        staffMapper.updateById(staffDO);
        staffChangeDTO.setExecuted(true);
        staffChangeService.updateStaffChange(staffChangeDTO);
        return staffChangeDTO.getId();
    }

    @Override
    @Transactional
    public void runChange(int days, boolean skipExecuted) {
        List<StaffChangeDTO> staffChangeDTOS = staffChangeService.getStaffChangeInDays(days);
        for (StaffChangeDTO staffChangeDTO : staffChangeDTOS) {
            if (skipExecuted && staffChangeDTO.getExecuted()) {
                this.runChange(staffChangeDTO);
            }

        }
    }
    
    @Override
    public void generateStaffTemplate() {
        List<ExcelExport<?>> list = new ArrayList<>();
        ExcelExport<StaffExcel> staffData = new ExcelExport<>();
        staffData.setTClass(StaffExcel.class).setData(new ArrayList<>()).setSheetName("人员基本信息");
        ExcelExport<WorkRecordExcel> workRecordData = new ExcelExport<>();
        workRecordData.setTClass(WorkRecordExcel.class).setData(new ArrayList<>()).setSheetName("工作记录");
        ExcelExport<WorkExperienceExcel> workExperienceData = new ExcelExport<>();
        workExperienceData.setTClass(WorkExperienceExcel.class).setData(new ArrayList<>()).setSheetName("履历记录");
        ExcelExport<EducationalExperienceExcel> educationalExperienceData = new ExcelExport<>();
        educationalExperienceData.setTClass(EducationalExperienceExcel.class).setData(new ArrayList<>()).setSheetName("学历信息");
        ExcelExport<FamilyExcel> familyData = new ExcelExport<>();
        familyData.setTClass(FamilyExcel.class).setData(new ArrayList<>()).setSheetName("家庭信息");
        ExcelExport<ContractExcel> contractData = new ExcelExport<>();
        contractData.setTClass(ContractExcel.class).setData(new ArrayList<>()).setSheetName("合同信息");
        ExcelExport<TitleExcel> titleData = new ExcelExport<>();
        titleData.setTClass(TitleExcel.class).setData(new ArrayList<>()).setSheetName("职称信息");
        ExcelExport<QualificationExcel> qualificationData = new ExcelExport<>();
        qualificationData.setTClass(QualificationExcel.class).setData(new ArrayList<>()).setSheetName("职业资格");
        ExcelExport<DriverLicenseExcel> driverLicenseData = new ExcelExport<>();
        driverLicenseData.setTClass(DriverLicenseExcel.class).setData(new ArrayList<>()).setSheetName("驾驶证信息");
        ConstructExcelList(list, staffData, workRecordData, workExperienceData, educationalExperienceData, familyData, contractData, titleData, qualificationData, driverLicenseData);
        EasyExcelUtils.exportExcel(list, "员工导入模板");
    }

    @Override
    @Transactional
    public String importStaff(MultipartFile file) {
        List<StaffExcel> staffData;
        List<WorkRecordExcel> workRecordData;
        List<WorkExperienceExcel> workExperienceData;
        List<EducationalExperienceExcel> educationalExperienceData;
        List<FamilyExcel> familyData;
        List<ContractExcel> contractData;
        List<TitleExcel> titleData;
        List<QualificationExcel> qualificationData;
        List<DriverLicenseExcel> driverLicenseData;

        try {
            staffData = EasyExcelUtils.readExcel(file.getInputStream(), StaffExcel.class, 0);
            workRecordData = EasyExcelUtils.readExcel(file.getInputStream(), WorkRecordExcel.class, 1);
            workExperienceData = EasyExcelUtils.readExcel(file.getInputStream(), WorkExperienceExcel.class, 2);
            educationalExperienceData = EasyExcelUtils.readExcel(file.getInputStream(), EducationalExperienceExcel.class, 3);
            familyData = EasyExcelUtils.readExcel(file.getInputStream(), FamilyExcel.class, 4);
            contractData = EasyExcelUtils.readExcel(file.getInputStream(), ContractExcel.class, 5);
            titleData = EasyExcelUtils.readExcel(file.getInputStream(), TitleExcel.class, 6);
            qualificationData = EasyExcelUtils.readExcel(file.getInputStream(), QualificationExcel.class, 7);
            driverLicenseData = EasyExcelUtils.readExcel(file.getInputStream(), DriverLicenseExcel.class, 8);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("文件读取失败");
        }

        // todo: 这里使用异步处理

        String importStaffResult = this.importStaff(staffData);
        String importWorkRecordResult = this.importWorkRecord(workRecordData);
        String importWorkExperienceResult = this.importWorkExperience(workExperienceData);
        String importEducationalExperienceResult = this.importEducationalExperience(educationalExperienceData);
        String importFamilyResult = this.importFamily(familyData);
        String importContractResult = this.importContract(contractData);
        String importTitleResult = this.importTitle(titleData);
        String importQualificationResult = this.importQualification(qualificationData);
        String importDriverLicenseResult = this.importDriverLicense(driverLicenseData);

        return "人员基本信息：" + importStaffResult + "\n" +
                "工作记录：" + importWorkRecordResult + "\n" +
                "履历记录：" + importWorkExperienceResult + "\n" +
                "学历信息：" + importEducationalExperienceResult + "\n" +
                "家庭信息：" + importFamilyResult + "\n" +
                "合同信息：" + importContractResult + "\n" +
                "职称信息：" + importTitleResult + "\n" +
                "职业资格：" + importQualificationResult + "\n" +
                "驾驶证信息：" + importDriverLicenseResult + "\n";
    }

    @Override
    @Transactional
    public String importStaff(List<StaffExcel> staffExcelList) {
        for (StaffExcel staffExcel : staffExcelList) {
            StaffDTO staffDTO = staffMapper.getStaffByStaffCode(staffExcel.getStaffCode());

            if (staffDTO == null) {
                staffDTO = new StaffDTO();
            }

            CustomBeanUtils.copyPropertiesExcludeMeta(staffExcel, staffDTO, true);

            // 查询部门
            if (StringUtils.hasText(staffExcel.getDepName())) {
                DepartmentDTO departmentDTO = departmentService.getDepartmentByName(staffExcel.getDepName());
                if (departmentDTO != null) {
                    staffDTO.setDepId(departmentDTO.getId());
                }
            }

            // 默认为在职状态
            staffDTO.setCompanyState("在职");

            if (staffDTO.getId() == null) {
                this.addStaff(staffDTO, false);
            } else {
                this.updateStaff(staffDTO, false);
            }

            // 更新staffDTO
            staffDTO = staffMapper.getStaffByStaffCode(staffExcel.getStaffCode());

            // 更新StaffInfo内容
            StaffInfoDTO staffInfoDTO = staffInfoService.getStaffInfoByStaffId(staffDTO.getId());
            if (staffInfoDTO == null) {
                staffInfoDTO = new StaffInfoDTO();
            }

            CustomBeanUtils.copyPropertiesExcludeMeta(staffExcel, staffInfoDTO, true);

            staffInfoService.updateStaffInfo(staffInfoDTO, staffDTO.getId());
        }

        return String.format("成功导入%s条数据", staffExcelList.size());
    }

    @Override
    public List<StaffExcel> getStaffExcelList(String keyword, Long depId, String companyState, String postLevel) {
        return staffMapper.getStaffExcelList(keyword, depId, companyState, postLevel);
    }

    @Override
    public void exportStaff(String keyword, Long depId, String companyState, String postLevel) {
        List<ExcelExport<?>> list = new ArrayList<>();
        ExcelExport<StaffExcel> staffData = new ExcelExport<>();
        staffData.setTClass(StaffExcel.class).setData(this.getStaffExcelList(keyword, depId, companyState, postLevel)).setSheetName("人员基本信息");
        ExcelExport<WorkRecordExcel> workRecordData = new ExcelExport<>();
        workRecordData.setTClass(WorkRecordExcel.class).setData(workRecordService.getWorkRecordExcelList(keyword, depId, companyState, postLevel)).setSheetName("工作记录");
        ExcelExport<WorkExperienceExcel> workExperienceData = new ExcelExport<>();
        workExperienceData.setTClass(WorkExperienceExcel.class).setData(workExperienceService.getWorkExperienceExcelList(keyword, depId, companyState, postLevel)).setSheetName("履历记录");
        ExcelExport<EducationalExperienceExcel> educationalExperienceData = new ExcelExport<>();
        educationalExperienceData.setTClass(EducationalExperienceExcel.class).setData(educationalExperienceService.getEducationalExperienceExcelList(keyword, depId, companyState, postLevel)).setSheetName("学历信息");
        ExcelExport<FamilyExcel> familyData = new ExcelExport<>();
        familyData.setTClass(FamilyExcel.class).setData(familyService.getFamilyExcelList(keyword, depId, companyState, postLevel)).setSheetName("家庭信息");
        ExcelExport<ContractExcel> contractData = new ExcelExport<>();
        contractData.setTClass(ContractExcel.class).setData(contractService.getContractExcelList(keyword, depId, companyState, postLevel)).setSheetName("合同信息");
        ExcelExport<TitleExcel> titleData = new ExcelExport<>();
        titleData.setTClass(TitleExcel.class).setData(titleService.getTitleExcelList(keyword, depId, companyState, postLevel)).setSheetName("职称信息");
        ExcelExport<QualificationExcel> qualificationData = new ExcelExport<>();
        qualificationData.setTClass(QualificationExcel.class).setData(qualificationService.getQualificationExcelList(keyword, depId, companyState, postLevel)).setSheetName("职业资格");
        ExcelExport<DriverLicenseExcel> driverLicenseData = new ExcelExport<>();
        driverLicenseData.setTClass(DriverLicenseExcel.class).setData(driverLicenseService.getDriverLicenseExcelList(keyword, depId, companyState, postLevel)).setSheetName("驾驶证信息");
        ConstructExcelList(list, staffData, workRecordData, workExperienceData, educationalExperienceData, familyData, contractData, titleData, qualificationData, driverLicenseData);
        EasyExcelUtils.exportExcel(list, "员工导出信息");
    }

    private void ConstructExcelList(List<ExcelExport<?>> list, ExcelExport<StaffExcel> staffData, ExcelExport<WorkRecordExcel> workRecordData, ExcelExport<WorkExperienceExcel> workExperienceData, ExcelExport<EducationalExperienceExcel> educationalExperienceData, ExcelExport<FamilyExcel> familyData, ExcelExport<ContractExcel> contractData, ExcelExport<TitleExcel> titleData, ExcelExport<QualificationExcel> qualificationData, ExcelExport<DriverLicenseExcel> driverLicenseData) {
        list.add(staffData);
        list.add(workRecordData);
        list.add(workExperienceData);
        list.add(educationalExperienceData);
        list.add(familyData);
        list.add(contractData);
        list.add(titleData);
        list.add(qualificationData);
        list.add(driverLicenseData);
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

    @Override
    public List<StaffDTO> getStaffListOnJob(String month, String depId, String staffCode) {
        return staffMapper.getStaffListOnJob(month, depId, staffCode);
    }

    @Override
    @Transactional
    public String importWorkRecord(List<WorkRecordExcel> workRecordList) {
        int errorTimes = 0;
        StringBuilder errorResult = new StringBuilder();

        for (WorkRecordExcel workRecordExcel : workRecordList) {
            StaffDTO staffDTO = staffMapper.getStaffByStaffCode(workRecordExcel.getStaffCode());
            if (staffDTO == null) {
                errorResult.append(errorTimes == 0 ? "" : "、").append(workRecordExcel.getStaffCode());
                ++errorTimes;
                continue;
            }

            WorkRecordDTO workRecordDTO = new WorkRecordDTO();
            CustomBeanUtils.copyPropertiesExcludeMeta(workRecordExcel, workRecordDTO, true);
            workRecordDTO.setStaffId(staffDTO.getId());
            workRecordService.addWorkRecord(workRecordDTO);
        }

        return getResult(errorTimes, errorResult, workRecordList.size());
    }

    @Override
    @Transactional
    public String importWorkExperience(List<WorkExperienceExcel> workExperienceExcelList) {
        int errorTimes = 0;
        StringBuilder errorResult = new StringBuilder();

        for (WorkExperienceExcel workExperienceExcel : workExperienceExcelList) {
            StaffDTO staffDTO = staffMapper.getStaffByStaffCode(workExperienceExcel.getStaffCode());
            if (staffDTO == null) {
                errorResult.append(errorTimes == 0 ? "" : "、").append(workExperienceExcel.getStaffCode());
                ++errorTimes;
                continue;
            }

            WorkExperienceDTO workExperienceDTO = new WorkExperienceDTO();
            CustomBeanUtils.copyPropertiesExcludeMeta(workExperienceExcel, workExperienceDTO, true);
            workExperienceDTO.setPid(staffDTO.getId());
            workExperienceService.addWorkExperience(workExperienceDTO);
        }

        return getResult(errorTimes, errorResult, workExperienceExcelList.size());
    }

    @Override
    @Transactional
    public String importEducationalExperience(List<EducationalExperienceExcel> educationalExperienceList) {
        int errorTimes = 0;
        StringBuilder errorResult = new StringBuilder();

        for (EducationalExperienceExcel educationalExperienceExcel : educationalExperienceList) {
            StaffDTO staffDTO = staffMapper.getStaffByStaffCode(educationalExperienceExcel.getStaffCode());
            if (staffDTO == null) {
                errorResult.append(errorTimes == 0 ? "" : "、").append(educationalExperienceExcel.getStaffCode());
                ++errorTimes;
                continue;
            }

            EducationalExperienceDTO educationalExperienceDTO = new EducationalExperienceDTO();
            CustomBeanUtils.copyPropertiesExcludeMeta(educationalExperienceExcel, educationalExperienceDTO, true);
            educationalExperienceDTO.setPid(staffDTO.getId());
            educationalExperienceService.addEducationalExperience(educationalExperienceDTO);
        }

        return getResult(errorTimes, errorResult, educationalExperienceList.size());
    }

    @Override
    @Transactional
    public String importFamily(List<FamilyExcel> familyExcelList) {
        int errorTimes = 0;
        StringBuilder errorResult = new StringBuilder();

        for (FamilyExcel familyExcel : familyExcelList) {
            StaffDTO staffDTO = staffMapper.getStaffByStaffCode(familyExcel.getStaffCode());
            if (staffDTO == null) {
                errorResult.append(errorTimes == 0 ? "" : "、").append(familyExcel.getStaffCode());
                ++errorTimes;
                continue;
            }

            FamilyDTO familyDTO = new FamilyDTO();
            CustomBeanUtils.copyPropertiesExcludeMeta(familyExcel, familyDTO, true);
            familyDTO.setPid(staffDTO.getId());
            familyService.addFamily(familyDTO);
        }

        return getResult(errorTimes, errorResult, familyExcelList.size());
    }

    @Override
    @Transactional
    public String importContract(List<ContractExcel> contractExcelList) {
        int errorTimes = 0;
        StringBuilder errorResult = new StringBuilder();

        for (ContractExcel contractExcel : contractExcelList) {
            StaffDTO staffDTO = staffMapper.getStaffByStaffCode(contractExcel.getStaffCode());
            if (staffDTO == null) {
                errorResult.append(errorTimes == 0 ? "" : "、").append(contractExcel.getStaffCode());
                ++errorTimes;
                continue;
            }

            ContractDTO contractDTO = new ContractDTO();
            CustomBeanUtils.copyPropertiesExcludeMeta(contractExcel, contractDTO, true);
            contractDTO.setStaffId(staffDTO.getId());
            contractService.addContract(contractDTO);
        }

        return getResult(errorTimes, errorResult, contractExcelList.size());
    }

    @Override
    @Transactional
    public String importTitle(List<TitleExcel> titleExcelList) {
        int errorTimes = 0;
        StringBuilder errorResult = new StringBuilder();

        for (TitleExcel titleExcel : titleExcelList) {
            StaffDTO staffDTO = staffMapper.getStaffByStaffCode(titleExcel.getStaffCode());
            if (staffDTO == null) {
                errorResult.append(errorTimes == 0 ? "" : "、").append(titleExcel.getStaffCode());
                ++errorTimes;
                continue;
            }

            TitleDTO titleDTO = new TitleDTO();
            CustomBeanUtils.copyPropertiesExcludeMeta(titleExcel, titleDTO, true);
            titleDTO.setStaffId(staffDTO.getId());
            titleService.addTitle(titleDTO);
        }

        return getResult(errorTimes, errorResult, titleExcelList.size());
    }

    @Override
    @Transactional
    public String importQualification(List<QualificationExcel> qualificationExcelList) {
        int errorTimes = 0;
        StringBuilder errorResult = new StringBuilder();

        for (QualificationExcel qualificationExcel : qualificationExcelList) {
            StaffDTO staffDTO = staffMapper.getStaffByStaffCode(qualificationExcel.getStaffCode());
            if (staffDTO == null) {
                errorResult.append(errorTimes == 0 ? "" : "、").append(qualificationExcel.getStaffCode());
                ++errorTimes;
                continue;
            }

            QualificationDTO qualificationDTO = new QualificationDTO();
            CustomBeanUtils.copyPropertiesExcludeMeta(qualificationExcel, qualificationDTO, true);
            qualificationDTO.setStaffId(staffDTO.getId());
            qualificationService.addQualification(qualificationDTO);
        }

        return getResult(errorTimes, errorResult, qualificationExcelList.size());
    }

    @Override
    @Transactional
    public String importDriverLicense(List<DriverLicenseExcel> driverLicenseExcelList) {
        int errorTimes = 0;
        StringBuilder errorResult = new StringBuilder();

        for (DriverLicenseExcel driverLicenseExcel : driverLicenseExcelList) {
            StaffDTO staffDTO = staffMapper.getStaffByStaffCode(driverLicenseExcel.getStaffCode());
            if (staffDTO == null) {
                errorResult.append(errorTimes == 0 ? "" : "、").append(driverLicenseExcel.getStaffCode());
                ++errorTimes;
                continue;
            }

            DriverLicenseDTO driverLicenseDTO = new DriverLicenseDTO();
            CustomBeanUtils.copyPropertiesExcludeMeta(driverLicenseExcel, driverLicenseDTO, true);
            driverLicenseDTO.setPid(staffDTO.getId());
            driverLicenseService.addDriverLicense(driverLicenseDTO);
        }

        return getResult(errorTimes, errorResult, driverLicenseExcelList.size());
    }

    private String getResult(int errorTimes, StringBuilder errorResult, int size) {
        StringBuilder result = new StringBuilder();
        result.append("成功导入").append(size - errorTimes).append("条数据。");

        if (errorTimes > 0) {
            result.append("存在").append(errorTimes).append("条数据导入失败。存在没有录入系统的员工编号：").append(errorResult);
        }

        return result.toString();
    }

}
