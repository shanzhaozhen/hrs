package com.sbgs.hrsapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.sbgs.hrscommon.converter.UserConverter;
import com.sbgs.hrscommon.form.UserLoginForm;
import com.sbgs.hrscommon.vo.ResultObject;
import com.sbgs.hrsservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(value = "用户注册接口")
@RestController
@RequiredArgsConstructor
public class RegisterController {

    private static final String REGISTER = "/register";
    private static final String CHECK_USERNAME = "/register/{username}";

    private final UserService sysUserService;

    @PostMapping(REGISTER)
    @ApiOperation("用户注册接口")
    public ResultObject<Long> register(@RequestBody @Validated UserLoginForm userLoginForm) {
        return ResultObject.build(result -> sysUserService.register(UserConverter.toDTO(userLoginForm)));
    }

    @GetMapping(CHECK_USERNAME)
    @ApiOperation("检查用户是否已被注册")
    public ResultObject<Boolean> checkUserName(@PathVariable("username") @ApiParam("用户名") String username) {
        return ResultObject.build(result -> sysUserService.isExistUser(username));
    }

}
