package com.hbjs.hrscommon.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "简历导出excel实体")
public class ResumeExcel {

    @ExcelProperty(value = "序号")
    private Integer rowNum;
    
    @ExcelProperty(value = "员工编号")
    private String staffCode;

    @ExcelProperty(value = "员工姓名")
    private String staffName;

    @ExcelProperty(value = "部门")
    private String depName;

    @ExcelProperty(value = "在司状态")
    private String companyState;

    @ExcelProperty(value = "职务")
    private String duty;

    @ExcelProperty(value = "岗位")
    private String post;

    @ExcelProperty(value = "岗位类型")
    private String postType;

    @ExcelProperty(value = "岗位等级")
    private String postLevel;

    @ExcelProperty(value = "性别")
    private String sex;

    @ExcelProperty(value = "民族")
    private String nation;

    @ExcelProperty(value = "出生日期")
    private Date birthday;

    @ExcelProperty(value = "开始工作时间")
    private Date workDate;

    @ExcelProperty(value = "入职日期")
    private Date entryDate;

    @ExcelProperty(value = "政治面貌")
    private String politics;

    @ExcelProperty(value = "最高学历")
    private String education;

    @ExcelProperty(value = "学位")
    private String degree;

    @ExcelProperty(value = "婚姻状况")
    private String maritalStatus;

    @ExcelProperty(value = "配偶名字")
    private String spouseName;

    @ExcelProperty(value = "结婚日期")
    private Date marriageDate;

    @ExcelProperty(value = "子女人数")
    private Integer childrenNumber;

    @ExcelProperty(value = "身份证号码")
    private String idNumber;

    @ExcelProperty(value = "联系电话")
    private String phone;

    @ExcelProperty(value = "社保号")
    private String socialSecurityNumber;

    @Schema(title = "劳动合同")
    private Long laborContract;

    @ExcelProperty(value = "出生地")
    private String birthAddress;

    @ExcelProperty(value = "籍贯")
    private String nativeAddress;

    @ExcelProperty(value = "户口类型")
    private String householdType;

    @ExcelProperty(value = "户口地址")
    private String registeredAddressProvince;

    @ExcelProperty(value = "家庭住址")
    private String homeAddressProvince;

    @ExcelProperty(value = "现住地址")
    private String currentAddress;

    @ExcelProperty(value = "邮递地址")
    private String postalAddress;

    @ExcelProperty(value = "紧急联系人姓名")
    private String emergencyContactName;

    @ExcelProperty(value = "紧急联系人关系")
    private String emergencyContactRelation;

    @ExcelProperty(value = "紧急联系人电话")
    private String emergencyContactPhone;

}
