package com.sbgs.hrscommon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户登陆信息")
public class CurrentUser {

    @Schema(title = "用户信息")
    private UserInfo userInfo;

    @Schema(title = "角色")
    private List<RoleBase> roles;

    @Schema(title = "菜单")
    private List<AsyncRoute> asyncRoutes;

}
