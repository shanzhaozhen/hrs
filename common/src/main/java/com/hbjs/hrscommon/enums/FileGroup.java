package com.hbjs.hrscommon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum FileGroup implements IEnumType {

    COMMON("普通文件" ,0);

    private String name;

    private Integer value;

}
