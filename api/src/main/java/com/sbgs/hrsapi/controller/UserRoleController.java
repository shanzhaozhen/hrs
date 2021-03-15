package com.sbgs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbgs.hrscommon.converter.UserConverter;
import com.sbgs.hrscommon.dto.UserDTO;
import com.sbgs.hrscommon.vo.ResultBody;
import com.sbgs.hrscommon.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRoleController {

    private static final String GET_USER_ROLE_PAGE = "/user-role";
    private static final String ADD_USER_ROLE = "/user-role";


    @GetMapping(GET_USER_ROLE_PAGE)
    @Operation(summary = "获取用户角色信息（分页）")
    public ResultBody<Page<UserVO>> getUserPageByRoleId(Page<UserDTO> page, Long roleId, String keyword) {
        return ResultBody.build(UserConverter.toVO(userService.getUserPageByRoleId(page, roleId, keyword)));
    }

    @PostMapping(ADD_USER_ROLE)
    @Operation(summary = "获取用户角色信息（分页）")
    public ResultBody<Page<UserVO>> addUserRole(List<Long> userIds, Long roleId) {
        return ResultBody.build(UserConverter.toVO(userService.getUserPageByRoleId(page, roleId, keyword)));
    }

}
