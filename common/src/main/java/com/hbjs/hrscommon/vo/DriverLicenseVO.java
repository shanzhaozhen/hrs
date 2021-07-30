package com.hbjs.hrscommon.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hbjs.hrscommon.domain.BaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "驾驶证信息VO实体")
public class DriverLicenseVO extends BaseInfo {

    private static final long serialVersionUID = -3061480025994009647L;

    @Schema(title = "主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Schema(title = "关联ID")
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long fileId;

    @Schema(title = "备注")
    private String remarks;

}
