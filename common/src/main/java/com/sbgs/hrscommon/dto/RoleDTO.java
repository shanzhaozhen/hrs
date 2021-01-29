package com.sbgs.hrscommon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "角色DTO实体")
public class RoleDTO {

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "标识名称")
    private String identification;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "关联的路由")
    private List<RouteDTO> routes;

    @ApiModelProperty(value = "关联的路由id")
    private List<Long> routeIds;

    @ApiModelProperty(value = "关联的资源")
    private List<ResourceDTO> resources;

    @ApiModelProperty(value = "关联的资源id")
    private List<Long> resourceIds;

}
