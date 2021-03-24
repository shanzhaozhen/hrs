package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.UserConverter;
import com.hbjs.hrscommon.dto.UserDTO;
import com.hbjs.hrscommon.form.UserRoleForm;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrscommon.vo.UserVO;
import com.hbjs.hrsservice.service.UserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "user-role", description = "用户角色接口")
@RestController
@RequiredArgsConstructor
public class UserRoleController {

    private static final String GET_USER_ROLE_PAGE = "/user-role";
    private static final String ADD_USER_ROLE = "/user-role";
    private static final String DELETE_USER_ROLE = "/user-role";

    private final UserRoleService userRoleService;

    @Operation(summary = "获取用户角色信息（分页）")
    @GetMapping(GET_USER_ROLE_PAGE)
    public ResultBody<Page<UserVO>> getUserPageByRoleId(Page<UserDTO> page, Long roleId, String keyword) {
        return ResultBody.build(() -> UserConverter.toVO(userRoleService.getUserRolePage(page, roleId, keyword)));
    }

    @Operation(summary = "添加用户角色")
    @PostMapping(ADD_USER_ROLE)
    public ResultBody<List<Long>> addUserRole(@RequestBody UserRoleForm userRoleForm) {
        return ResultBody.build(() -> userRoleService.bathAddUserRole(userRoleForm.getUserIds(), userRoleForm.getRoleId()));
    }

    @Operation(summary = "删除用户角色")
    @DeleteMapping(DELETE_USER_ROLE)
    public ResultBody<Integer> deleteUserRoles(@RequestBody UserRoleForm userRoleForm) {
        return ResultBody.build(() -> userRoleService.bathDeleteUserRole(userRoleForm.getUserIds(), userRoleForm.getRoleId()));
    }


}
