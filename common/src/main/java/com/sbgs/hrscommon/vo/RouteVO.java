package com.sbgs.hrscommon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "路由VO实体")
public class RouteVO {

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "菜单名称")
    private String name;

    @Schema(title = "菜单路由")
    private String path;

    @Schema(title = "上级ID")
    private Long pid;

    @Schema(title = "重定向路径")
    private String redirect;

    @Schema(title = "显示名称")
    private String title;

    @Schema(title = "图标")
    private String icon;

    @Schema(title = "排序等级")
    private Integer priority;

    @Schema(title = "菜单是否隐藏")
    private Boolean hidden;

    @Schema(title = "菜单是否总是显示")
    private Boolean alwaysShow;

    @Schema(title = "是否需要缓存")
    private Boolean noCache;

    @Schema(title = "固钉")
    private Boolean affix;

    @Schema(title = "面包屑")
    private Boolean breadcrumb;

    @Schema(title = "参数")
    private String props;

    @Schema(title = "菜单描述")
    private String description;

    private List<RoleVO> roleVOList;

    private List<RouteVO> children;

}
