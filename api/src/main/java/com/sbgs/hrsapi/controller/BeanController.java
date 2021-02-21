package com.sbgs.hrsapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.sbgs.hrscommon.dto.BeanInfo;
import com.sbgs.hrscommon.vo.DynamicScheduledTaskVO;
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

    @GetMapping(GET_BEAN_LIST)
    @Operation(summary = "获取Bean列表")
    public ResultBody<List<BeanInfo>> getBeanInfoList() {
        return ResultBody.build(result -> beanService.getBeanInfoList());
    }

    @GetMapping(GET_BEAN_BY_NAME)
    @Operation(summary = "获取Bean列表")
    public ResultBody<BeanInfo> getBeanInfoByName(@PathVariable("beanName") String beanName) {
        return ResultBody.build(result -> beanService.getBeanInfoByName(beanName));
    }

    @PostMapping(GET_BEAN_METHOD)
    @Operation(summary = "获取Bean对应的方法")
    public ResultBody<DynamicScheduledTaskVO> getBeanMethod(String beanName, String methodName) {
        return ResultBody.build(result -> null);
    }

}
