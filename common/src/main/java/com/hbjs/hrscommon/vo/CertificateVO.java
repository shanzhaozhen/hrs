package com.hbjs.hrscommon.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "证件DTO实体")
public class CertificateVO extends BaseInfoVO {

    @Schema(title = "主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Schema(title = "关联ID")
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long fileId;

}
