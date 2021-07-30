package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.StaffChangeDTO;
import com.hbjs.hrscommon.dto.StaffDTO;
import com.hbjs.hrscommon.excel.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StaffService {

    /**
     * 获取员工信息的分页列表
     * @param page
     * @param keyword
     * @return
     */
    Page<StaffDTO> getStaffPage(Page<StaffDTO> page, String keyword, Long depId, String companyState, String postLevel);

    /**
     * 通过员工信息id获取
     * @param staffId
     * @return
     */
    StaffDTO getStaffById(Long staffId);

    /**
     * 通过员工编号获取
     * @param staffCode
     * @return
     */
    StaffDTO getStaffByStaffCode(String staffCode);

    /**
     * 新增员工信息
     * @param staffDTO
     * @param updateOtherInfo
     * @return
     */
    Long addStaff(StaffDTO staffDTO, boolean updateOtherInfo);

    /**
     * 修改员工信息
     * @param staffDTO
     * @param updateOtherInfo
     * @return
     */
    Long updateStaff(StaffDTO staffDTO, boolean updateOtherInfo);

    /**
     * 更新员工其他信息
     * @param staffDTO
     * @return
     */
    void updateStaffMoreInfo(StaffDTO staffDTO, Long staffId);

    /**
     * 删除员工信息(通过员工信息id删除)
     * @param staffId
     */
    Long deleteStaff(Long staffId);

    /**
     * 批量删除员工信息(通过员工信息id删除)
     * @param staffIds
     * @return
     */
    List<Long> batchDeleteStaff(List<Long> staffIds);

    /**
     * 执行调动记录
     * @param staffChangeId
     * @return
     */
    Long runChange(Long staffChangeId);

    /**
     * 执行调动记录
     * @param staffChangeDTO
     * @return
     */
    Long runChange(StaffChangeDTO staffChangeDTO);

    /**
     * 定期检查执行调动
     * @param days 多少天内
     * @param skipExecuted 是否跳过已执行
     * @return
     */
    void runChange(int days, boolean skipExecuted);

    /**
     * 生成员工信息导入模板
     */
    void generateStaffTemplate();

    /**
     * 导入员工信息
     * @param file
     * @return
     */
    String importStaff(MultipartFile file);

    /**
     * 导入员工信息
     * @param staffImportExcelList
     * @return
     */
    String importStaff(List<StaffImportExcel> staffImportExcelList);

    /**
     * 导出员工信息
     * @param keyword
     * @param depId
     */
    void exportStaff(String keyword, Long depId);

    /**
     * 打印员工信息
     * @param staffId
     */
    void printStaff(Long staffId);

    /**
     * 查询在职员工
     * @param date
     * @param depId
     * @param staffCode
     * @return
     */
    List<StaffDTO> getStaffListOnJob(String date, String depId, String staffCode);

    /**
     * 导入工作记录
     * @param workRecordList
     * @return
     */
    String importWorkRecord(List<WorkRecordExcel> workRecordList);

    /**
     * 导入工作履历
     * @param workExperienceExcelList
     * @return
     */
    String importWorkExperience(List<WorkExperienceExcel> workExperienceExcelList);

    /**
     * 导入教育经历
     * @param educationalExperienceList
     * @return
     */
    String importEducationalExperience(List<EducationalExperienceExcel> educationalExperienceList);

    /**
     * 导入家庭成员
     * @param familyExcelList
     * @return
     */
    String importFamily(List<FamilyExcel> familyExcelList);

    /**
     * 导入合同信息
     * @param contractExcelList
     * @return
     */
    String importContract(List<ContractExcel> contractExcelList);

    /**
     * 导入职称信息
     * @param titleExcelList
     * @return
     */
    String importTitle(List<TitleExcel> titleExcelList);

    /**
     * 导入职业资格
     * @param qualificationList
     * @return
     */
    String importQualification(List<QualificationExcel> qualificationList);

    /**
     * 导入驾驶证信息
     * @param driverLicenseList
     * @return
     */
    String importDriverLicense(List<DriverLicenseExcel> driverLicenseList);
}
