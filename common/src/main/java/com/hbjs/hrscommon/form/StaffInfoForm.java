package com.hbjs.hrscommon.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hbjs.hrscommon.domain.BaseInfo;
import com.hbjs.hrscommon.dto.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "员工信息form实体")
public class StaffInfoForm {


    @Schema(title = "主键ID")
    @NotNull(groups = {Update.class}, message = "员工信息id不能为空")
    private Long id;

    @Schema(title = "关联的员工id")
    @NotNull(groups = {Insert.class, Update.class}, message = "关联的员工信息id不能为空")
    private Long staffId;

    @Schema(title = "民族")
    private String nation;

    @Schema(title = "政治面貌")
    private String politics;

    @Schema(title = "最高学历")
    private String education;

    @Schema(title = "学位")
    private String degree;

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

    @Schema(title = "联系电话")
    private String phone;

    @Schema(title = "家庭电话")
    private String homePhone;

    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "QQ")
    private String qq;

    @Schema(title = "籍贯(省)")
    private String nativeAddressProvince;
    @Schema(title = "籍贯(市)")
    private String nativeAddressCity;

    @Schema(title = "出生地(省)")
    private String birthAddressProvince;
    @Schema(title = "出生地(市)")
    private String birthAddressCity;

    @Schema(title = "户口类型")
    private String householdType;

    @Schema(title = "户口地址(省)")
    private String registeredAddressProvince;
    @Schema(title = "户口地址(市)")
    private String registeredAddressCity;
    @Schema(title = "户口地址(区)")
    private String registeredAddressArea;
    @Schema(title = "户口地址(详细)")
    private String registeredAddressDetail;

    @Schema(title = "家庭住址(省)")
    private String homeAddressProvince;
    @Schema(title = "家庭住址(市)")
    private String homeAddressCity;
    @Schema(title = "家庭住址(区)")
    private String homeAddressArea;
    @Schema(title = "家庭住址(详细)")
    private String homeAddressDetail;

    @Schema(title = "现住地址(省)")
    private String currentAddressProvince;
    @Schema(title = "现住地址(市)")
    private String currentAddressCity;
    @Schema(title = "现住地址(区)")
    private String currentAddressArea;
    @Schema(title = "现住地址(详细)")
    private String currentAddressDetail;

    @Schema(title = "邮递地址(省)")
    private String postalAddressProvince;
    @Schema(title = "邮递地址(市)")
    private String postalAddressCity;
    @Schema(title = "邮递地址(区)")
    private String postalAddressArea;
    @Schema(title = "邮递地址(详细)")
    private String postalAddressDetail;

    @Schema(title = "紧急联系人姓名")
    private String emergencyContactName;

    @Schema(title = "紧急联系人关系")
    private String emergencyContactRelation;

    @Schema(title = "紧急联系人电话")
    private String emergencyContactPhone;

    @Schema(title = "婚姻状况")
    private String maritalStatus;

    @Schema(title = "结婚日期")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date marriageDate;

    @Schema(title = "配偶名字")
    private String spouseName;

    @Schema(title = "配偶学历")
    private String spouseEducation;

    @Schema(title = "配偶身体状况")
    private String spousePhysicalCondition;

    @Schema(title = "结婚证件")
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
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date enlistmentDate;

    @Schema(title = "退伍时间")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date dischargeDate;

    @Schema(title = "退伍时军衔")
    private String dischargeRank;

    @Schema(title = "立功")
    private String honour;

    @Schema(title = "驾驶证类型")
    private String driverLicenseType;

    @Schema(title = "驾驶证领证时间")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date driverLicenseDate;

    @Schema(title = "驾龄")
    private Integer driveYear;

    @Schema(title = "熟悉的驾驶路线")
    private String driveLines;

    @Schema(title = "驾驶车种")
    private String vehicleType;

}
