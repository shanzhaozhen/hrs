package com.hbjs.hrscommon.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "家庭成员DO实体")
public class FamilyForm {

    @Schema(title = "主键ID")
    @NotNull(groups = {Update.class}, message = "家庭成员id不能为空")
    private Long id;

    @Schema(title = "关联id")
    @NotNull(groups = {Insert.class, Update.class}, message = "关联id不能为空")
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
