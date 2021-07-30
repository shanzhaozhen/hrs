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
@Schema(description = "驾驶证信息DTO实体")
public class DriverLicenseForm {

    @Schema(title = "主键ID")
    @NotNull(groups = {Update.class}, message = "驾驶证信息id不能为空")
    private Long id;

    @Schema(title = "关联ID")
    @NotNull(groups = {Insert.class, Update.class}, message = "关联员工id不能为空")
    private Long pid;

    @Schema(title = "准驾车型")
    private String modal;

    @Schema(title = "证件号码")
    private String number;

    @Schema(title = "获得日期")
    private Date obtainDate;

    @Schema(title = "有效期至")
    private Date expirationDate;

    @Schema(title = "内部驾照")
    private String inside;

    @Schema(title = "内部驾照有效期")
    private Date insideExpirationDate;

    @Schema(title = "附件")
    private Long fileId;

    @Schema(title = "备注")
    private String remarks;

}
