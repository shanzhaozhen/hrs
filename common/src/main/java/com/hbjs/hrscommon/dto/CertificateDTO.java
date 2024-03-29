package com.hbjs.hrscommon.dto;

import com.hbjs.hrscommon.domain.BaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "证件DTO实体")
public class CertificateDTO extends BaseInfo {

    private static final long serialVersionUID = -8893723115590487314L;

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "关联ID")
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
