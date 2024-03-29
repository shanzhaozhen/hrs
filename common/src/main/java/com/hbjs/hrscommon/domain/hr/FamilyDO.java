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
@TableName("hr_family")
@Schema(description = "家庭成员DO实体")
public class FamilyDO extends BaseInfo {

    private static final long serialVersionUID = -4602415110086721033L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "关联id")
    private Long pid;

    @Schema(title = "姓名")
    private String name;

    @Schema(title = "关系")
    private String relation;

    @Schema(title = "出生日期")
    private Date birthday;

    @Schema(title = "政治面貌")
    private String politics;

    @Schema(title = "工作单位")
    private String workCompany;

    @Schema(title = "职务")
    private String duty;

    @Schema(title = "移动电话")
    private String mobilePhone;

    @Schema(title = "固话")
    private String landlinePhone;

    @Schema(title = "是否紧急联系人")
    private String isEmergency;

    @Schema(title = "备注")
    private String remarks;

}
