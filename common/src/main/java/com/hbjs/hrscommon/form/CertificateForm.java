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
@Schema(description = "证件Form实体")
public class CertificateForm {

    @Schema(title = "主键ID")
    @NotNull(groups = {Update.class}, message = "证件id不能为空")
    private Long id;

    @Schema(title = "关联id")
    @NotNull(groups = {Insert.class, Update.class}, message = "关联id不能为空")
    private Long pid;

    @Schema(title = "证件名称")
    private String name;

    @Schema(title = "证件类型")
    private String type;

    @Schema(title = "证件号")
    private String number;

    @Schema(title = "取证日期")
    private Date obtainDate;

    @Schema(title = "发证单位")
    private String issueCompany;

    @Schema(title = "附件")
    private Long fileId;

}
