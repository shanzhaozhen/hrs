package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.form.UserDepartmentForm;
import com.hbjs.hrscommon.vo.CurrentUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import com.hbjs.hrscommon.converter.UserConverter;
import com.hbjs.hrscommon.dto.UserDTO;
import com.hbjs.hrscommon.form.UserForm;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrscommon.vo.UserVO;
import com.hbjs.hrsservice.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "user", description = "用户信息接口")
@RestController
@RequiredArgsConstructor
public class UserController {

    private static final String GET_USER_INFO = "/user/current";
    private static final String LOGOUT = "/user/logout";
    private static final String GET_USER_PAGE = "/user/page";
    private static final String GET_USER_BY_ID = "/user/{userId}";
    private static final String ADD_USER = "/user";
    private static final String UPDATE_USER = "/user";
    private static final String DELETE_USER = "/user/{userId}";
    private static final String BATCH_DELETE_USER = "/user";
    private static final String GET_USER_ROLE_PAGE = "/user/role/page";
    private static final String GET_USER_DEPARTMENT_PAGE = "/user/department/page";
    private static final String BATCH_UPDATE_USER_DEPARTMENT = "/user/department";


    private final UserService userService;

    @GetMapping(GET_USER_INFO)
    @Operation(summary = "获取当前登录用户的个人和权限信息接口")
    public ResultBody<CurrentUser> getCurrentUserInfo() {
        return ResultBody.build(userService::getUserInfo);
    }

    @Operation(summary = "登出用户接口")
    @GetMapping(LOGOUT)
    public ResultBody<Boolean> logout() {
        return ResultBody.build(() -> true);
    }

    @Operation(summary = "获取用户信息（分页）")
    @GetMapping(GET_USER_PAGE)
    public ResultBody<Page<UserVO>> getUserPage(Page<UserDTO> page, String keyword) {
        return ResultBody.build(() -> UserConverter.toVO(userService.getUserPage(page, keyword)));
    }

    @Operation(summary = "获取用户信息（通过用户id）")
    @GetMapping(GET_USER_BY_ID)
    public ResultBody<UserVO> getUserById(@Parameter(description = "用户id", example = "1") @PathVariable("userId") Long userId) {
        return ResultBody.build(() -> UserConverter.toVO(userService.getUserById(userId)));
    }

    @Operation(summary = "添加用户接口")
    @PostMapping(ADD_USER)
    public ResultBody<Long> addUser(@RequestBody @Validated({Insert.class}) UserForm userForm) {
        return ResultBody.build(() -> userService.addUser(UserConverter.toDTO(userForm)));
    }

    @Operation(summary = "更新用户接口")
    @PutMapping(UPDATE_USER)
    public ResultBody<Long> updateUser(@RequestBody @Validated({Update.class}) UserForm userForm) {
        return ResultBody.build(() -> userService.updateUser(UserConverter.toDTO(userForm)));
    }

    @Operation(summary = "删除用户接口")
    @DeleteMapping(DELETE_USER)
    public ResultBody<Long> deleteUser(@Parameter(description = "用户id", example = "[1, 2]") @PathVariable("userId") Long userId) {
        return ResultBody.build(() -> userService.deleteUser(userId));
    }

    @Operation(summary = "批量删除用户接口")
    @DeleteMapping(BATCH_DELETE_USER)
    public ResultBody<List<Long>> batchDeleteUser(@Parameter(description = "用户id", example = "[1, 2]") @RequestBody List<Long> userIds) {
        return ResultBody.build(() -> userService.batchDeleteUser(userIds));
    }


    @Operation(summary = "通过角色ID获取用户信息（分页）")
    @GetMapping(GET_USER_ROLE_PAGE)
    public ResultBody<Page<UserVO>> getUserPageByRoleId(Page<UserDTO> page, Long roleId, String keyword) {
        return ResultBody.build(() -> UserConverter.toVO(userService.getUserPageByRoleId(page, roleId ,keyword)));
    }

    @Operation(summary = "通过部门ID获取用户信息（分页）")
    @GetMapping(GET_USER_DEPARTMENT_PAGE)
    public ResultBody<Page<UserVO>> getUserPageByDepartmentId(Page<UserDTO> page, Long departmentId, String keyword) {
        return ResultBody.build(() -> UserConverter.toVO(userService.getUserPageByDepartmentId(page, departmentId, keyword)));
    }

    @Operation(summary = "批量更新用户部门接口")
    @PutMapping(BATCH_UPDATE_USER_DEPARTMENT)
    public ResultBody<List<Long>> batchUpdateUserDepartment(@RequestBody UserDepartmentForm userDepartmentForm) {
        return ResultBody.build(() -> userService.batchUpdateUserDepartment(userDepartmentForm));
    }

}