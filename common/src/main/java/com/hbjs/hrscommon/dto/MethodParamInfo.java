package com.hbjs.hrscommon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "参数信息")
public class MethodParamInfo implements Serializable {

    private static final long serialVersionUID = 347304251609424903L;

    @Schema(title = "参数类型")
    private Class<?> paramType;

    @Schema(title = "参数值")
    private String paramValue;

}
