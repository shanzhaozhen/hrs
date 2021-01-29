package com.sbgs.hrsapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.sbgs.hrscommon.dto.BeanInfo;
import com.sbgs.hrscommon.vo.DynamicScheduledTaskVO;
import com.sbgs.hrscommon.vo.ResultObject;
import com.sbgs.hrsservice.service.BeanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Bean接口")
@RestController
@RequiredArgsConstructor
public class BeanController {

    private static final String GET_BEAN_LIST = "/bean/list";
    private static final String GET_BEAN_BY_NAME = "/bean/{beanName}";
    private static final String GET_BEAN_METHOD = "/bean/method";

    private final BeanService beanService;

    @GetMapping(GET_BEAN_LIST)
    @ApiOperation("获取Bean列表")
    public ResultObject<List<BeanInfo>> getBeanInfoList() {
        return ResultObject.build(result -> beanService.getBeanInfoList());
    }

    @GetMapping(GET_BEAN_BY_NAME)
    @ApiOperation("获取Bean列表")
    public ResultObject<BeanInfo> getBeanInfoByName(@PathVariable("beanName") String beanName) {
        return ResultObject.build(result -> beanService.getBeanInfoByName(beanName));
    }

    @PostMapping(GET_BEAN_METHOD)
    @ApiOperation("获取Bean对应的方法")
    public ResultObject<DynamicScheduledTaskVO> getBeanMethod(String beanName, String methodName) {
        return ResultObject.build(result -> null);
    }

}
