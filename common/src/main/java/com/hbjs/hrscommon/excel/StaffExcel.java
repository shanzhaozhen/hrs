package com.hbjs.hrscommon.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ContentFontStyle;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = false, chain = false)
@HeadFontStyle(fontHeightInPoints = 12)
@ContentFontStyle(fontHeightInPoints = 11)
@Schema(description = "员工导出excel实体")
public class StaffExcel {

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
    @DateTimeFormat("yyyy-MM-dd")
    private Date birthday;

    @ExcelProperty(value = "开始工作时间")
    @DateTimeFormat("yyyy-MM-dd")
    private Date workDate;

    @ExcelProperty(value = "入职日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date entryDate;

    @ExcelProperty(value = "政治面貌")
    private String politics;

    @ExcelProperty(value = "最高学历")
    private String education;

    @ExcelProperty(value = "学位")
    private String degree;

    @Schema(title = "父母赡养情况")
    private String parentalSupport;

    @Schema(title = "特长")
    private String specialty;

    @Schema(title = "爱好")
    private String hobby;

    @Schema(title = "本人身体状况")
    private String physicalCondition;

    @Schema(title = "体重kg")
    private Float weight;

    @Schema(title = "身高cm")
    private Float height;

    @Schema(title = "视力")
    private String vision;

    @Schema(title = "血型")
    private String bloodType;

    @Schema(title = "遗传病史或传染病")
    private String medicalHistory;

    @ExcelProperty(value = "婚姻状况")
    private String maritalStatus;

    @ExcelProperty(value = "结婚日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date marriageDate;

    @ExcelProperty(value = "配偶名字")
    private String spouseName;

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
    private String registeredAddress;

    @ExcelProperty(value = "家庭住址")
    private String homeAddress;

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
