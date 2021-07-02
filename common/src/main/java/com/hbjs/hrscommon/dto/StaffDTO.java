package com.hbjs.hrscommon.dto;

import com.hbjs.hrscommon.domain.BaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "员工DO实体")
public class StaffDTO extends BaseInfo {

    private static final long serialVersionUID = -4015692939311086378L;

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "员工编号")
    private String staffCode;

    @Schema(title = "员工姓名")
    private String staffName;

    @Schema(title = "性别")
    private String sex;

    @Schema(title = "出生日期")
    private Date birthday;

    @Schema(title = "身份证号码")
    private String idNumber;

    @Schema(title = "部门ID")
    private Long depId;

    @Schema(title = "在司状态")
    private String companyState;

    @Schema(title = "职务")
    private String duty;

    @Schema(title = "岗位")
    private String post;

    @Schema(title = "岗位类型")
    private String postType;

    @Schema(title = "岗位等级")
    private String postLevel;

    @Schema(title = "开始工作时间")
    private Date workDate;

    @Schema(title = "入职日期")
    private Date entryDate;

    @Schema(title = "离职日期")
    private Date departureDate;

    @Schema(title = "社保号")
    private String socialSecurityNumber;

    @Schema(title = "个人照片")
    private Long personalPhoto;

    @Schema(title = "劳动合同")
    private Long laborContract;

    @Schema(title = "员工信息")
    private StaffInfoDTO staffInfo;

    @Schema(title = "工作履历")
    private List<WorkExperienceDTO> workExperienceList;

    @Schema(title = "教育经历")
    private List<EducationalExperienceDTO> educationalExperienceList;

    @Schema(title = "证件信息")
    private List<CertificateDTO> certificateList;

    @Schema(title = "家庭成员")
    private List<FamilyDTO> familyList;

}
