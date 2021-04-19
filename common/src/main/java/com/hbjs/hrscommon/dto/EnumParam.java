package com.hbjs.hrscommon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnumParam implements Serializable {

    private static final long serialVersionUID = -7192190536120886894L;

    private String name;

    private Object value;

}
