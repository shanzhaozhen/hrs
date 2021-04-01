package com.hbjs.hrscommon.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hbjs.hrscommon.domain.BaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "员工VO实体")
public class StaffVO extends BaseInfo {

    @Schema(title = "主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Schema(title = "员工编号")
    private String staffCode;

    @Schema(title = "员工姓名")
    private String staffName;

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

    @Schema(title = "性别")
    private Integer sex;

    @Schema(title = "民族")
    private String nation;

    @Schema(title = "出生日期")
    private Date birthday;

    @Schema(title = "开始工作时间")
    private Date workDate;

    @Schema(title = "入职日期")
    private Date entryDate;

    @Schema(title = "政治面貌")
    private String politics;

    @Schema(title = "最高学历")
    private String education;

    @Schema(title = "学位")
    private String degree;

    @Schema(title = "婚姻状况")
    private String maritalStatus;

    @Schema(title = "配偶名字")
    private String spouseName;

    @Schema(title = "结婚证件")
    private String marriageCertificate;

    @Schema(title = "结婚日期")
    private Date marriageDate;

    @Schema(title = "子女人数")
    private Integer childrenNumber;

    @Schema(title = "身份证号码")
    private String idNumber;

    @Schema(title = "联系电话")
    private String phone;

    @Schema(title = "社保号")
    private String socialSecurityNumber;

    @Schema(title = "出生地(省)")
    private String birthAddressProvince;
    @Schema(title = "出生地(市)")
    private String birthAddressCity;

    @Schema(title = "籍贯(省)")
    private String nativeAddressProvince;
    @Schema(title = "籍贯(市)")
    private String nativeAddressCity;

    @Schema(title = "户口类型")
    private String householdType;

    @Schema(title = "户口地址(省)")
    private String registeredAddressProvince;
    @Schema(title = "户口地址(市)")
    private String registeredAddressCity;
    @Schema(title = "户口地址(区)")
    private String registeredAddressArea;
    @Schema(title = "户口地址(市)")
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
    private String contactName;

    @Schema(title = "紧急联系人关系")
    private String contactRelation;

    @Schema(title = "紧急联系人电话")
    private String contactPhone;

}
