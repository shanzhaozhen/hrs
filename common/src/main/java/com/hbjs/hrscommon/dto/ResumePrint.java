package com.hbjs.hrscommon.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "简历模板实体")
public class ResumePrint {

    @Schema(title = "简历id")
    private Long id;

    @Schema(title = "姓名")
    private String name;

    @Schema(title = "个人照片")
    private Long personalPhoto;

    @Schema(title = "性别")
    private String sex;

    @Schema(title = "民族")
    private String nation;

    @Schema(title = "出生日期")
    private String birthday;

    @Schema(title = "身份证号码")
    private String idNumber;

    @Schema(title = "政治面貌")
    private String politics;

    @Schema(title = "最高学历")
    private String education;

    @Schema(title = "学位")
    private String degree;

    @Schema(title = "联系电话")
    private String phone;

    @Schema(title = "家庭电话")
    private String homePhone;

    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "QQ")
    private String qq;

    @Schema(title = "籍贯")
    private String nativeAddress;

    @Schema(title = "出生地")
    private String birthAddress;

    @Schema(title = "户口类型")
    private String householdType;

    @Schema(title = "户口地址")
    private String registeredAddress;

    @Schema(title = "家庭住址")
    private String homeAddress;

    @Schema(title = "现住地址")
    private String currentAddress;

    @Schema(title = "邮递地址")
    private String postalAddress;

    @Schema(title = "应聘途径")
    private String applyFor;

    @Schema(title = "期望月薪")
    private String expectedSalary;

    @Schema(title = "希望服务年限")
    private String serviceYears;

    @Schema(title = "职称")
    private String title;

    @Schema(title = "紧急联系人")
    private String emergencyContactName;

    @Schema(title = "紧急联系人关系")
    private String emergencyContactRelation;

    @Schema(title = "紧急联系人电话")
    private String emergencyContactPhone;

    @Schema(title = "父母赡养情况")
    private String parentalSupport;

    @Schema(title = "本人身体状况")
    private String physicalCondition;

    @Schema(title = "遗传病史或传染病")
    private String medicalHistory;

    @Schema(title = "体重kg")
    private Float weight;

    @Schema(title = "身高cm")
    private Float height;

    @Schema(title = "视力")
    private String vision;

    @Schema(title = "血型")
    private String bloodType;

    @Schema(title = "特长")
    private String specialty;

    @Schema(title = "爱好")
    private String hobby;
    
    @Schema(title = "是否有亲友在司")
    private Boolean haveFriend;

    @Schema(title = "亲友姓名")
    private String friendName;

    @Schema(title = "亲友关系")
    private String friendRelation;

    @Schema(title = "亲友部门")
    private String friendDepartment;

    @Schema(title = "亲友职务")
    private String friendDuty;

    @Schema(title = "婚姻状况")
    private String maritalStatus;

    @Schema(title = "结婚日期")
    private String marriageDate;

    @Schema(title = "配偶名字")
    private String spouseName;

    @Schema(title = "配偶学历")
    private String spouseEducation;

    @Schema(title = "配偶身体状况")
    private String spousePhysicalCondition;

    @Schema(title = "结婚证件")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long marriageCertificate;

    @Schema(title = "生育情况")
    private String fertility;

    @Schema(title = "子女人数")
    private Integer childrenNumber;

    @Schema(title = "是否服兵役")
    private Boolean inArmy;

    @Schema(title = "部队驻扎地")
    private String troopBase;

    @Schema(title = "入伍时间")
    private String enlistmentDate;

    @Schema(title = "退伍时间")
    private String dischargeDate;

    @Schema(title = "退伍时军衔")
    private String dischargeRank;

    @Schema(title = "立功")
    private String honour;

    @Schema(title = "驾驶证类型")
    private String driverLicenseType;

    @Schema(title = "驾驶证领证时间")
    private String driverLicenseDate;

    @Schema(title = "驾龄")
    private Integer driveYear;

    @Schema(title = "熟悉的驾驶路线")
    private String driveLines;

    @Schema(title = "驾驶车种")
    private String vehicleType;

    @Schema(title = "是否愿意加入人才库")
    private Boolean willJoin;

    private ResumeMoreInfo resumeMoreInfo;

    List<WorkExperienceDTO> workExperienceDTOList;

}
