package com.sbgs.hrscommon.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "前端菜单实体（用于前端菜单的存放）")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AsyncRoute {

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

    @Schema(title = "排序等级")
    private Integer priority;

    @Schema(title = "菜单是否隐藏")
    private Boolean hidden;

    @Schema(title = "菜单是否总是显示")
    private Boolean alwaysShow;

    @Schema(title = "菜单是否总是显示")
    private Meta meta;

    @Schema(title = "参数")
    private String props;

    @Schema(title = "菜单描述")
    private String description;

    @Schema(title = "下级菜单")
    private List<AsyncRoute> children;

}
