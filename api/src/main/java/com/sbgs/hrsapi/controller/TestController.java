package com.sbgs.hrsapi.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api("用户测试接口")
@Controller
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public Object testInstall() {
        return "INSTALL SUCCESS!";
    }

}
