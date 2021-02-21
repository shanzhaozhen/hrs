package com.sbgs.hrsapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import com.sbgs.hrscommon.converter.RouteConverter;
import com.sbgs.hrscommon.form.RouteForm;
import com.sbgs.hrscommon.vo.ResultBody;
import com.sbgs.hrscommon.vo.RouteVO;
import com.sbgs.hrsservice.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "route", description = "用户路由接口")
@RestController
@RequiredArgsConstructor
public class RouteController {

    private static final String GET_ALL_ROUTE_TREE = "/route/tree";
    private static final String GET_ROUTE_BY_ID = "/route/{routeId}";
    private static final String ADD_ROUTE = "/route";
    private static final String UPDATE_ROUTE = "/route";
    private static final String DELETE_ROUTE = "/route/{routeId}";

    private final RouteService routeService;

    @GetMapping(GET_ALL_ROUTE_TREE)
    @Operation(summary = "获取所有路由信息（树状结构）")
    public ResultBody<List<RouteVO>> getAllRouteTree() {
        return ResultBody.build(result -> RouteConverter.toVO(routeService.getAllRouteTree()));
    }

    @GetMapping(GET_ROUTE_BY_ID)
    @Operation(summary = "获取路由信息（通过路由id）")
    public ResultBody<RouteVO> getRouteByRouteId(@PathVariable("routeId") @Parameter(description = "路由id", example = "1") Long routeId) {
        return ResultBody.build(result -> RouteConverter.toVO(routeService.getRouteById(routeId)));
    }

    @PostMapping(ADD_ROUTE)
    @Operation(summary = "添加路由接口")
    public ResultBody<Long> addRoute(@RequestBody @Validated RouteForm routeForm) {
        return ResultBody.build(result -> routeService.addRoute(RouteConverter.toDTO(routeForm)));
    }

    @PutMapping(UPDATE_ROUTE)
    @Operation(summary = "更新路由接口")
    public ResultBody<Long> updateRoute(@RequestBody @Validated RouteForm routeForm) {
        return ResultBody.build(result -> routeService.updateRoute(RouteConverter.toDTO(routeForm)));
    }

    @DeleteMapping(DELETE_ROUTE)
    @Operation(summary = "删除路由接口")
    public ResultBody<Long> deleteRoute(@PathVariable("routeId") @Parameter(description = "路由id", example = "1") Long routeId) {
        return ResultBody.build(result -> routeService.deleteRoute(routeId));
    }

}
