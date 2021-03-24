package com.hbjs.hrsapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Tag(name = "test", description = "用户测试接口")
@Controller
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public Object testInstall() {
        return "INSTALL SUCCESS!";
    }

}
