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
@Schema(description = "职业资格Form实体")
public class QualificationForm {

    @Schema(title = "主键ID")
    @NotNull(groups = {Update.class}, message = "职业资格id不能为空")
    private Long id;

    @Schema(title = "关联ID")
    @NotNull(groups = {Insert.class, Update.class}, message = "关联员工id不能为空")
    private Long staffId;

    @Schema(title = "职业资格")
    private String qualification;

    @Schema(title = "职业")
    private String profession;

    @Schema(title = "资格等级")
    private String level;

    @Schema(title = "工种")
    private String workType;

    @Schema(title = "证书编号")
    private String number;

    @Schema(title = "获得日期")
    private Date obtainDate;

    @Schema(title = "评定机构")
    private String issueCompany;

    @Schema(title = "是否最高")
    private String highest;

    @Schema(title = "附件")
    private Long fileId;

    @Schema(title = "备注")
    private String remarks;

}
