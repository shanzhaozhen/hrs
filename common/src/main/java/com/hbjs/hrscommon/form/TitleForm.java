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
@Schema(description = "职称信息Form实体")
public class TitleForm {

    @Schema(title = "主键ID")
    @NotNull(groups = {Update.class}, message = "职称信息id不能为空")
    private Long id;

    @Schema(title = "关联ID")
    @NotNull(groups = {Insert.class, Update.class}, message = "关联员工id不能为空")
    private Long staffId;

    @Schema(title = "职称")
    private String title;

    @Schema(title = "职称等级")
    private String level;

    @Schema(title = "证书编号")
    private String number;

    @Schema(title = "评定日期")
    private Date evaluationDate;

    @Schema(title = "终止日期")
    private Date endDate;

    @Schema(title = "评定机构")
    private String issueCompany;

    @Schema(title = "是否最高")
    private String highest;

    @Schema(title = "附件")
    private Long fileId;

    @Schema(title = "备注")
    private String remarks;

}
