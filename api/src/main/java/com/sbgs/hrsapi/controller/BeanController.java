package com.sbgs.hrsapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.sbgs.hrscommon.dto.BeanInfo;
import com.sbgs.hrscommon.vo.CustomTaskVO;
import com.sbgs.hrscommon.vo.ResultBody;
import com.sbgs.hrsservice.service.BeanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "bean", description = "Bean接口")
@RestController
@RequiredArgsConstructor
public class BeanController {

    private static final String GET_BEAN_LIST = "/bean/list";
    private static final String GET_BEAN_BY_NAME = "/bean/{beanName}";
    private static final String GET_BEAN_METHOD = "/bean/method";

    private final BeanService beanService;

    @Operation(summary = "获取Bean列表")
    @GetMapping(GET_BEAN_LIST)
    public ResultBody<List<BeanInfo>> getBeanInfoList() {
        return ResultBody.build(beanService::getBeanInfoList);
    }

    @Operation(summary = "通过bean名称获取Bean列表")
    @GetMapping(GET_BEAN_BY_NAME)
    public ResultBody<BeanInfo> getBeanInfoByName(@PathVariable("beanName") String beanName) {
        return ResultBody.build(() -> beanService.getBeanInfoByName(beanName));
    }

    @Operation(summary = "获取Bean对应的方法")
    @PostMapping(GET_BEAN_METHOD)
    public ResultBody<CustomTaskVO> getBeanMethod(String beanName, String methodName) {
        return null;
    }

}
