package com.hbjs.hrscommon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Bean信息")
public class BeanInfo implements Serializable {

    private static final long serialVersionUID = 7133367401915668556L;

    @Schema(title = "bean名称")
    private String beanName;

    @Schema(title = "bean对应的Class名")
    private String className;

    @Schema(title = "bean对应的所含有的方法")
    private List<MethodInfo> methods;

}
