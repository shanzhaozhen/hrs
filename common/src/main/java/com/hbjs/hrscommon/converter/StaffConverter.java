package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.StaffDO;
import com.hbjs.hrscommon.dto.StaffDTO;
import com.hbjs.hrscommon.form.StaffForm;
import com.hbjs.hrscommon.vo.StaffVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class StaffConverter {
    
    /**
     * StaffDTO 转换 StaffDO
     * @param staffDTO
     * @return
     */
    public static StaffDO toDO(StaffDTO staffDTO) {
        StaffDO staffDO = new StaffDO();
        BeanUtils.copyProperties(staffDTO, staffDO);
        return staffDO;
    }

    /**
     * StaffForm 转换 StaffDTO
     * @param staffForm
     * @return
     */
    public static StaffDTO toDTO(StaffForm staffForm) {
        StaffDTO staffDTO = new StaffDTO();
        BeanUtils.copyProperties(staffForm, staffDTO);
        staffDTO.setStaffInfo(StaffInfoConverter.toDTO(staffForm.getStaffInfo()))
                .setWorkRecordList(WorkRecordConverter.toDTO(staffForm.getWorkRecordList()))
                .setWorkExperienceList(WorkExperienceConverter.toDTO(staffForm.getWorkExperienceList()))
                .setEducationalExperienceList(EducationalExperienceConverter.toDTO(staffForm.getEducationalExperienceList()))
                .setFamilyList(FamilyConverter.toDTO(staffForm.getFamilyList()))
                .setContractList(ContractConverter.toDTO(staffForm.getContractList()))
                .setTitleList(TitleConverter.toDTO(staffForm.getTitleList()))
                .setQualificationList(QualificationConverter.toDTO(staffForm.getQualificationList()))
                .setDriverLicenseList(DriverLicenseConverter.toDTO(staffForm.getDriverLicenseList()));
        return staffDTO;
    }

    /**
     * StaffVO 转换 StaffDTO
     * @param staffVO
     * @return
     */
    public static StaffDTO toDTO(StaffVO staffVO) {
        StaffDTO staffDTO = new StaffDTO();
        BeanUtils.copyProperties(staffVO, staffDTO);
        return staffDTO;
    }

    /**
     * StaffDO 转换 StaffDTO
     * @param staffDO
     * @return
     */
    public static StaffDTO toDTO(StaffDO staffDO) {
        StaffDTO staffDTO = new StaffDTO();
        BeanUtils.copyProperties(staffDO, staffDTO);
        return staffDTO;
    }

    /**
     * StaffDTO 转换 StaffVO
     * @param staffDTO
     * @return
     */
    public static StaffVO toVO(StaffDTO staffDTO) {
        StaffVO staffVO = new StaffVO();
        BeanUtils.copyProperties(staffDTO, staffVO);
        staffVO.setStaffInfo(StaffInfoConverter.toVO(staffDTO.getStaffInfo()))
                .setWorkRecordList(WorkRecordConverter.toVO(staffDTO.getWorkRecordList()))
                .setWorkExperienceList(WorkExperienceConverter.toVO(staffDTO.getWorkExperienceList()))
                .setEducationalExperienceList(EducationalExperienceConverter.toVO(staffDTO.getEducationalExperienceList()))
                .setFamilyList(FamilyConverter.toVO(staffDTO.getFamilyList()))
                .setContractList(ContractConverter.toVO(staffDTO.getContractList()))
                .setTitleList(TitleConverter.toVO(staffDTO.getTitleList()))
                .setQualificationList(QualificationConverter.toVO(staffDTO.getQualificationList()))
                .setDriverLicenseList(DriverLicenseConverter.toVO(staffDTO.getDriverLicenseList()));
        return staffVO;
    }

    /**
     * List<StaffDTO> 转换 List<StaffVO>
     * @param staffDTOList
     * @return
     */
    public static List<StaffVO> toVO(List<StaffDTO> staffDTOList) {
        List<StaffVO> staffVOList = new ArrayList<>();
        for (StaffDTO staffDTO : staffDTOList) {
            staffVOList.add(toVO(staffDTO));
        }
        return staffVOList;
    }

    /**
     * Page<StaffDTO> 转换 Page<StaffVO>
     * @param staffDTOPage
     * @return
     */
    public static Page<StaffVO> toVO(Page<StaffDTO> staffDTOPage) {
        List<StaffDTO> staffDTOList = staffDTOPage.getRecords();
        Page<StaffVO> staffVOPage = new Page<>();
        BeanUtils.copyProperties(staffDTOPage, staffVOPage);
        staffVOPage.setRecords(toVO(staffDTOList));
        return staffVOPage;
    }

}
