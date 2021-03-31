package com.hbjs.hrscommon.domain.hr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hbjs.hrscommon.domain.BaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("hr_staff")
@Schema(description = "员工DO实体")
public class StaffDO extends BaseInfo {

    private static final long serialVersionUID = -4602415110086721033L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "员工编号")
    private String staffCode;

    @Schema(title = "姓名")
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

//    毕业日期	毕业院校	专业

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

    @Schema(title = "户籍所在地(省)")
    private String registeredPlaceProvince;
    @Schema(title = "户籍所在地(市)")
    private String registeredPlaceCity;

    @Schema(title = "出生地(省)")
    private String birthPlaceProvince;
    @Schema(title = "出生地(市)")
    private String birthPlaceCity;

    @Schema(title = "籍贯(省)")
    private String nativePlaceProvince;
    @Schema(title = "籍贯(市)")
    private String nativePlaceCity;

    @Schema(title = "户口类型")
    private String householdType;

    @Schema(title = "现住地址(省)")
    private String currentProvince;
    @Schema(title = "现住地址(市)")
    private String currentCity;
    @Schema(title = "现住地址(区)")
    private String currentArea;
    @Schema(title = "现住地址(详细)")
    private String currentDetail;

    @Schema(title = "紧急联系人")
    private String contactName;

    @Schema(title = "紧急联系人电话")
    private String contactPhone;

}
