package com.hbjs.hrscommon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpResult {

    //状态码
    private int status;

    //访问地址
    private String url;

    private Object data;

}
