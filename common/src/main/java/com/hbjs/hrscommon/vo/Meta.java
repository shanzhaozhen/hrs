package com.hbjs.hrscommon.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "用于菜单的标题、图标、全局权限的存放")
public class Meta {

    @Schema(title = "显示名称")
    private String title;

    @Schema(title = "图标")
    private String icon;

    @Schema(title = "是否需要缓存")
    private Boolean noCache;

    @Schema(title = "固钉")
    private Boolean affix;

    @Schema(title = "是否打开面包屑")
    private Boolean breadcrumb;

    @Schema(title = "拥有角色")
    private List<String> roles;

}
