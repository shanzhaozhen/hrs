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
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "角色VO实体")
public class RoleVO {

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "标识名称")
    private String identification;

    @Schema(title = "描述")
    private String description;

    @Schema(title = "关联的路由id")
    private List<Long> routeIds;

    @Schema(title = "关联的资源id")
    private List<Long> resourceIds;

}
