package com.sbgs.hrscommon.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户登陆信息")
public class CurrentUser {

    @ApiModelProperty(value = "用户信息")
    private UserInfo userInfo;

    @ApiModelProperty(value = "角色")
    private List<RoleBase> roles;

    @ApiModelProperty(value = "菜单")
    private List<AsyncRoute> asyncRoutes;

}
