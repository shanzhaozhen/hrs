package com.hbjs.hrscommon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum MenuType implements IEnumType {

    PATH("路径" ,0),
    MENU("菜单" ,1),
    API("API" ,2),
    BUTTON("按钮" ,3);

    private String name;

    private Integer value;

}
