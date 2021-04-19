package com.hbjs.hrscommon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "方法信息")
public class MethodInfo implements Serializable {

    private static final long serialVersionUID = -8563675280258431593L;

    @Schema(title = "方法名")
    private String methodName;

    @Schema(title = "方法名（简易）")
    private String methodSimpleName;

    @Schema(title = "方法名（完全）")
    private String methodFullName;

    @Schema(title = "参数类型")
    private Class<?>[] paramTypes;

}
