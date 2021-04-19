package com.hbjs.hrscommon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpResult implements Serializable {

    private static final long serialVersionUID = -5723794504506588472L;

    //状态码
    private int status;

    //访问地址
    private String url;

    private Object data;

}
