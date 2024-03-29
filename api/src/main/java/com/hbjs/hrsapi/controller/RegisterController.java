package com.hbjs.hrsapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import com.hbjs.hrscommon.converter.UserConverter;
import com.hbjs.hrscommon.form.UserLoginForm;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrsservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "register", description = "用户注册接口")
@RestController
@RequiredArgsConstructor
public class RegisterController {

    private static final String REGISTER = "/register";
    private static final String CHECK_USERNAME = "/register/{username}";

    private final UserService sysUserService;

    @Operation(summary = "用户注册接口")
    @PostMapping(REGISTER)
    public ResultBody<Long> register(@RequestBody @Validated UserLoginForm userLoginForm) {
        return ResultBody.build(() -> sysUserService.register(UserConverter.toDTO(userLoginForm)));
    }

    @Operation(summary = "检查用户是否已被注册")
    @GetMapping(CHECK_USERNAME)
    public ResultBody<Boolean> checkUserName(@PathVariable("username") @Parameter(description = "用户名") String username) {
        return ResultBody.build(() -> sysUserService.isExistUser(username));
    }

}
