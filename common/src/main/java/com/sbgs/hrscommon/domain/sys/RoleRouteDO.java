package com.sbgs.hrscommon.domain.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sbgs.hrscommon.domain.BaseInfo;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role_route")
@Schema(description = "角色-路由关系DO实体")
public class RoleRouteDO extends BaseInfo {

    private static final long serialVersionUID = 1642473344166749722L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(title = "角色ID")
    private Long roleId;

    @Schema(title = "路由ID")
    private Long routeId;

}
