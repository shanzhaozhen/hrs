package com.sbgs.hrscommon.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "路由Form实体")
public class RouteForm {

    @Schema(title = "主键ID")
    @NotNull(groups = {Update.class}, message = "资源id不能为空")
    private Long id;

    @Schema(title = "权限名称")
    @NotEmpty(groups = {Insert.class, Update.class}, message = "资源名称不能为空")
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

}
