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
public class RequestBody<T> implements Serializable {

    private static final long serialVersionUID = 6131967319590858449L;

    private Boolean success;

    private T data;

}
