package com.hbjs.hrscommon.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hbjs.hrscommon.domain.hr.WorkRecordDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "员工DO实体")
public class StaffForm {

    @Schema(title = "主键ID")
    @NotNull(groups = {Update.class}, message = "员工信息id不能为空")
    private Long id;

    @Schema(title = "员工编号")
    private String staffCode;

    @Schema(title = "员工姓名")
    private String staffName;

    @Schema(title = "性别")
    private String sex;

    @Schema(title = "出生日期")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
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
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date workDate;

    @Schema(title = "进入商贸集团时间")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date entryGacDate;

    @Schema(title = "入职日期")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date entryDate;

    @Schema(title = "离职日期")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date departureDate;

    @Schema(title = "社保号")
    private String socialSecurityNumber;

    @Schema(title = "银行卡号")
    private String bankCardNumber;

    @Schema(title = "开户行")
    private String bankName;

    @Schema(title = "个人照片")
    private Long personalPhoto;

    @Schema(title = "员工信息")
    private StaffInfoForm staffInfo;

    @Schema(title = "工作记录")
    private List<WorkRecordForm> workRecordList;

    @Schema(title = "工作履历")
    private List<WorkExperienceForm> workExperienceList;

    @Schema(title = "教育经历")
    private List<EducationalExperienceForm> educationalExperienceList;

    @Schema(title = "家庭成员")
    private List<FamilyForm> familyList;

    @Schema(title = "合同信息")
    private List<ContractForm> contractList;

    @Schema(title = "职称信息")
    private List<TitleForm> titleList;

    @Schema(title = "职业资格")
    private List<QualificationForm> qualificationList;

    @Schema(title = "驾驶证信息")
    private List<DriverLicenseForm> driverLicenseList;

}
