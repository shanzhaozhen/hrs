package com.sbgs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbgs.hrscommon.vo.CurrentUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import com.sbgs.hrscommon.converter.UserConverter;
import com.sbgs.hrscommon.dto.UserDTO;
import com.sbgs.hrscommon.form.BaseSearchForm;
import com.sbgs.hrscommon.form.UserForm;
import com.sbgs.hrscommon.vo.ResultBody;
import com.sbgs.hrscommon.vo.UserVO;
import com.sbgs.hrsservice.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    private final UserService userService;

    @GetMapping(GET_USER_INFO)
    @Operation(summary = "获取当前登录用户的个人和权限信息接口")
    public ResultBody<CurrentUser> getCurrentUserInfo() {
        return ResultBody.build(result -> userService.getUserInfo());
    }

    @GetMapping(LOGOUT)
    @Operation(summary = "登出用户接口")
    public ResultBody<Boolean> logout() {
        return ResultBody.build(result -> true);
    }

    @PostMapping(GET_USER_PAGE)
    @Operation(summary = "获取用户信息（分页）")
    public ResultBody<Page<UserVO>> getDynamicScheduledTaskPage(@RequestBody BaseSearchForm<UserDTO> baseSearchForm) {
        return ResultBody.build(result -> UserConverter.toVO(userService.getUserPage(baseSearchForm)));
    }

    @GetMapping(GET_USER_BY_ID)
    @Operation(summary = "获取角色信息（通过角色id）")
    public ResultBody<UserVO> getUserByUserId(@PathVariable("userId") @Parameter(description = "角色id", example = "1") Long userId) {
        return ResultBody.build(result -> UserConverter.toVO(userService.getUserById(userId)));
    }

    @PostMapping(ADD_USER)
    @Operation(summary = "添加角色接口")
    public ResultBody<Long> addUser(@RequestBody @Validated({Insert.class}) UserForm userForm) {
        return ResultBody.build(result -> userService.addUser(UserConverter.toDTO(userForm)));
    }

    @PutMapping(UPDATE_USER)
    @Operation(summary = "更新角色接口")
    public ResultBody<Long> updateUser(@RequestBody @Validated({Update.class}) UserForm userForm) {
        return ResultBody.build(result -> userService.updateUser(UserConverter.toDTO(userForm)));
    }

    @DeleteMapping(DELETE_USER)
    @Operation(summary = "删除角色接口")
    public ResultBody<Long> deleteUser(@PathVariable("userId") @Parameter(description = "角色id", example = "1") Long userId) {
        return ResultBody.build(result -> userService.deleteUser(userId));
    }

}
