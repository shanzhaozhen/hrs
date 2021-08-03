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
@Schema(description = "员工导入导出实体")
public class StaffExcel {

    @ExcelProperty(index = 0, value = "序号")
    private Integer rowNum;

    @ExcelProperty(index = 1, value = "部门名称")
    private String depName;

    @ExcelProperty(index = 2, value = "员工编号")
    private String staffCode;

    @ExcelProperty(index = 3, value = "员工姓名")
    private String staffName;

    @ExcelProperty(index = 4, value = "性别")
    private String sex;

    @ExcelProperty(index = 5, value = "出生日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date birthday;

    @ExcelProperty(index = 6, value = "身份证号码")
    private String idNumber;

    @ExcelProperty(index = 7, value = "在司状态")
    private String companyState;

    @ExcelProperty(index = 8, value = "职务")
    private String duty;

    @ExcelProperty(index = 9, value = "岗位")
    private String post;

    @ExcelProperty(index = 10, value = "岗位类型")
    private String postType;

    @ExcelProperty(index = 11, value = "岗位等级")
    private String postLevel;

    @ExcelProperty(index = 12, value = "开始工作时间")
    @DateTimeFormat("yyyy-MM-dd")
    private Date workDate;

    @ExcelProperty(index = 13, value = "进入商贸集团时间")
    @DateTimeFormat("yyyy-MM-dd")
    private Date entryGacDate;

    @ExcelProperty(index = 14, value = "入职日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date entryDate;

    @ExcelProperty(index = 15, value = "离职日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date departureDate;

    @ExcelProperty(index = 16, value = "社保号")
    private String socialSecurityNumber;

    @ExcelProperty(index = 17, value = "银行卡号")
    private String bankCardNumber;

    @ExcelProperty(index = 18, value = "开户行")
    private String bankName;

    @ExcelProperty(index = 19, value = "民族")
    private String nation;

    @ExcelProperty(index = 20, value = "政治面貌")
    private String politics;

    @ExcelProperty(index = 21, value = "最高学历")
    private String education;

    @ExcelProperty(index = 22, value = "最高学历专业")
    private String major;

    @ExcelProperty(index = 23, value = "最高学历毕业学校")
    private String schoolName;

    @ExcelProperty(index = 24, value = "学位")
    private String degree;

    @ExcelProperty(index = 25, value = "父母赡养情况")
    private String parentalSupport;

    @ExcelProperty(index = 26, value = "本人身体状况")
    private String physicalCondition;

    @ExcelProperty(index = 27, value = "遗传病史或传染病")
    private String medicalHistory;

    @ExcelProperty(index = 28, value = "体重kg")
    private Float weight;

    @ExcelProperty(index = 29, value = "身高cm")
    private Float height;

    @ExcelProperty(index = 30, value = "视力")
    private String vision;

    @ExcelProperty(index = 31, value = "血型")
    private String bloodType;

    @ExcelProperty(index = 32, value = "特长")
    private String specialty;

    @ExcelProperty(index = 33, value = "爱好")
    private String hobby;

    @ExcelProperty(index = 34, value = "联系电话")
    private String phone;

    @ExcelProperty(index = 35, value = "家庭电话")
    private String homePhone;

    @ExcelProperty(index = 36, value = "邮箱")
    private String email;

    @ExcelProperty(index = 37, value = "QQ")
    private String qq;

    @ExcelProperty(index = 38, value = "籍贯(省)")
    private String nativeAddressProvince;
    @ExcelProperty(index = 39, value = "籍贯(市)")
    private String nativeAddressCity;

    @ExcelProperty(index = 40, value = "出生地(省)")
    private String birthAddressProvince;
    @ExcelProperty(index = 41, value = "出生地(市)")
    private String birthAddressCity;

    @ExcelProperty(index = 42, value = "户口类型")
    private String householdType;

    @ExcelProperty(index = 43, value = "户口地址(省)")
    private String registeredAddressProvince;
    @ExcelProperty(index = 44, value = "户口地址(市)")
    private String registeredAddressCity;
    @ExcelProperty(index = 45, value = "户口地址(区)")
    private String registeredAddressArea;
    @ExcelProperty(index = 46, value = "户口地址(详细)")
    private String registeredAddressDetail;

    @ExcelProperty(index = 47, value = "家庭住址(省)")
    private String homeAddressProvince;
    @ExcelProperty(index = 48, value = "家庭住址(市)")
    private String homeAddressCity;
    @ExcelProperty(index = 49, value = "家庭住址(区)")
    private String homeAddressArea;
    @ExcelProperty(index = 50, value = "家庭住址(详细)")
    private String homeAddressDetail;

    @ExcelProperty(index = 51, value = "现住地址(省)")
    private String currentAddressProvince;
    @ExcelProperty(index = 52, value = "现住地址(市)")
    private String currentAddressCity;
    @ExcelProperty(index = 53, value = "现住地址(区)")
    private String currentAddressArea;
    @ExcelProperty(index = 54, value = "现住地址(详细)")
    private String currentAddressDetail;

    @ExcelProperty(index = 55, value = "邮递地址(省)")
    private String postalAddressProvince;
    @ExcelProperty(index = 56, value = "邮递地址(市)")
    private String postalAddressCity;
    @ExcelProperty(index = 57, value = "邮递地址(区)")
    private String postalAddressArea;
    @ExcelProperty(index = 58, value = "邮递地址(详细)")
    private String postalAddressDetail;

    @ExcelProperty(index = 59, value = "紧急联系人姓名")
    private String emergencyContactName;

    @ExcelProperty(index = 60, value = "紧急联系人关系")
    private String emergencyContactRelation;

    @ExcelProperty(index = 61, value = "紧急联系人电话")
    private String emergencyContactPhone;

    @ExcelProperty(index = 62, value = "婚姻状况")
    private String maritalStatus;

    @ExcelProperty(index = 63, value = "结婚日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date marriageDate;

    @ExcelProperty(index = 64, value = "配偶名字")
    private String spouseName;

    @ExcelProperty(index = 65, value = "配偶学历")
    private String spouseEducation;

    @ExcelProperty(index = 66, value = "配偶身体状况")
    private String spousePhysicalCondition;

    @ExcelProperty(index = 67, value = "生育情况")
    private String fertility;

    @ExcelProperty(index = 68, value = "子女人数")
    private Integer childrenNumber;

    @ExcelProperty(index = 69, value = "是否服兵役")
    private Boolean inArmy;

    @ExcelProperty(index = 70, value = "部队驻扎地")
    private String troopBase;

    @ExcelProperty(index = 71, value = "入伍时间")
    @DateTimeFormat("yyyy-MM-dd")
    private Date enlistmentDate;

    @ExcelProperty(index = 72, value = "退伍时间")
    @DateTimeFormat("yyyy-MM-dd")
    private Date dischargeDate;

    @ExcelProperty(index = 73, value = "退伍时军衔")
    private String dischargeRank;

    @ExcelProperty(index = 74, value = "立功")
    private String honour;

}
