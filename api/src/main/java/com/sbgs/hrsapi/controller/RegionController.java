package com.sbgs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import com.sbgs.hrscommon.converter.RegionConverter;
import com.sbgs.hrscommon.dto.RegionDTO;
import com.sbgs.hrscommon.form.BaseSearchForm;
import com.sbgs.hrscommon.form.RegionForm;
import com.sbgs.hrscommon.vo.RegionVO;
import com.sbgs.hrscommon.vo.ResultBody;
import com.sbgs.hrsservice.service.RegionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "region", description = "区域信息接口")
@RestController
@RequiredArgsConstructor
public class RegionController {

    private static final String GET_REGION_PAGE = "/region/page";
    private static final String GET_REGION_ALL = "/region/all";
    private static final String GET_REGION_TREE = "/region/tree";
    private static final String GET_REGION_BY_ID = "/region/{regionId}";
    private static final String ADD_REGION = "/region";
    private static final String UPDATE_REGION = "/region";
    private static final String DELETE_REGION = "/region/{regionId}";

    private final RegionService regionService;

    @PostMapping(GET_REGION_PAGE)
    @Operation(summary = "获取区域信息信息（分页）")
    public ResultBody<Page<RegionVO>> getRegionPage(@RequestBody BaseSearchForm<RegionDTO> baseSearchForm) {
        return ResultBody.build(result -> RegionConverter.toVO(regionService.getRegionPage(baseSearchForm)));
    }

    @GetMapping(GET_REGION_ALL)
    @Operation(summary = "获取所有区域信息")
    public ResultBody<List<RegionVO>> getAllRegions() {
        return ResultBody.build(result -> RegionConverter.toVO(regionService.getAllRegions()));
    }

    @GetMapping(GET_REGION_TREE)
    @Operation(summary = "获取所有区域信息")
    public ResultBody<List<RegionVO>> getRegionTree() {
        return ResultBody.build(result -> RegionConverter.toVO(regionService.getRegionTree()));
    }

    @GetMapping(GET_REGION_BY_ID)
    @Operation(summary = "获取区域信息信息（通过区域信息id）")
    public ResultBody<RegionVO> getRegionByRegionId(@PathVariable("regionId") @Parameter(description = "区域信息id", example = "1") Long regionId) {
        return ResultBody.build(result -> RegionConverter.toVO(regionService.getRegionById(regionId)));
    }

    @PostMapping(ADD_REGION)
    @Operation(summary = "添加区域信息接口")
    public ResultBody<Long> addRegion(@RequestBody @Validated({Insert.class}) RegionForm regionForm) {
        return ResultBody.build(result -> regionService.addRegion(RegionConverter.toDTO(regionForm)));
    }

    @PutMapping(UPDATE_REGION)
    @Operation(summary = "更新区域信息接口")
    public ResultBody<Long> updateRegion(@RequestBody @Validated({Update.class}) RegionForm regionForm) {
        return ResultBody.build(result -> regionService.updateRegion(RegionConverter.toDTO(regionForm)));
    }

    @DeleteMapping(DELETE_REGION)
    @Operation(summary = "删除区域信息接口")
    public ResultBody<Long> deleteRegion(@PathVariable("regionId") @Parameter(description = "区域信息id", example = "1") Long regionId) {
        return ResultBody.build(result -> regionService.deleteRegion(regionId));
    }

}
