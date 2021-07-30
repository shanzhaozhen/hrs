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
@Schema(description = "职称信息VO实体")
public class TitleVO extends BaseInfoVO {

    private static final long serialVersionUID = 7382522842392967080L;

    @Schema(title = "主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Schema(title = "关联ID")
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long fileId;

    @Schema(title = "备注")
    private String remarks;

}
