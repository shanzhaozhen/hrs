package com.sbgs.hrscommon.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Schema(description = "基础信息VO实体")
public class BaseInfoVO implements Serializable {

    private static final long serialVersionUID = -4890503939284694535L;

    @Schema(title = "创建人")
    private String createdBy;

    @Schema(title = "创建人名称")
    private String createdByName;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    @Schema(title = "创建时间")
    private Date createdDate;

    @Schema(title = "修改人")
    private Long lastModifiedBy;

    @Schema(title = "修改人名称")
    private String lastModifiedByName;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    @Schema(title = "修改时间")
    private Date lastModifiedDate;

}
