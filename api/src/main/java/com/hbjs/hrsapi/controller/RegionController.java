package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.RegionConverter;
import com.hbjs.hrscommon.dto.RegionDTO;
import com.hbjs.hrscommon.form.RegionForm;
import com.hbjs.hrscommon.vo.RegionVO;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrsservice.service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "region", description = "区域信息接口")
@RestController
@RequiredArgsConstructor
public class RegionController {

    private static final String GET_REGION_ROOT = "/region/root";
    private static final String GET_REGION_PAGE_ROOT = "/region/page/root";
    private static final String GET_REGION_ALL = "/region/all";
    private static final String GET_REGION_TREE = "/region/tree";
    private static final String GET_REGION_TREE_LEVEL = "/region/tree/level";
    private static final String GET_REGION_CHILDREN_BY_ID = "/region/children/{regionId}";
    private static final String GET_REGION_BY_ID = "/region/{regionId}";
    private static final String ADD_REGION = "/region";
    private static final String UPDATE_REGION = "/region";
    private static final String DELETE_REGION = "/region/{regionId}";
    private static final String BATCH_DELETE_REGION = "/region";
    private static final String REFRESH_REGION = "/region/refresh";

    private final RegionService regionService;

    @Operation(summary = "获取所有区域信息根部")
    @GetMapping(GET_REGION_ROOT)
    public ResultBody<List<RegionVO>> getRegionRootList() {
        return ResultBody.build(() -> RegionConverter.toVO(regionService.getRegionRootList()));
    }

    @Operation(summary = "获取所有区域信息根部（分页）")
    @GetMapping(GET_REGION_PAGE_ROOT)
    public ResultBody<Page<RegionVO>> getRegionRootPage(Page<RegionDTO> page, String keyword) {
        return ResultBody.build(() -> RegionConverter.toVO(regionService.getRegionRootPage(page, keyword)));
    }

    @Operation(summary = "获取所有区域信息")
    @GetMapping(GET_REGION_ALL)
    public ResultBody<List<RegionVO>> getAllRegions() {
        return ResultBody.build(() -> RegionConverter.toVO(regionService.getAllRegions()));
    }

    @Operation(summary = "获取所有区域信息（树状）")
    @GetMapping(GET_REGION_TREE)
    public ResultBody<List<RegionVO>> getRegionTree() {
        return ResultBody.build(() -> RegionConverter.toVO(regionService.getRegionTree()));
    }

    @Operation(summary = "通过level获取区域信息（树状）")
    @GetMapping(GET_REGION_TREE_LEVEL)
    public ResultBody<List<RegionVO>> getRegionTreeByLevel(Integer level, Integer type) {
        return ResultBody.build(() -> RegionConverter.toVO(regionService.getRegionTreeByLevel(level, type)));
    }

    @Operation(summary = "通过父级ID获取字典子节点")
    @GetMapping(GET_REGION_CHILDREN_BY_ID)
    public ResultBody<List<RegionVO>> getRegionChildrenById(@PathVariable("regionId") @Parameter(description = "区域信息id", example = "1") Long dictionaryId) {
        return ResultBody.build(() -> RegionConverter.toVO(regionService.getRegionChildrenById(dictionaryId)));
    }

    @Operation(summary = "获取区域信息信息（通过区域信息id）")
    @GetMapping(GET_REGION_BY_ID)
    public ResultBody<RegionVO> getRegionById(@PathVariable("regionId") @Parameter(description = "区域信息id", example = "1") Long regionId) {
        return ResultBody.build(() -> RegionConverter.toVO(regionService.getRegionById(regionId)));
    }

    @Operation(summary = "添加区域信息接口")
    @PostMapping(ADD_REGION)
    public ResultBody<Long> addRegion(@RequestBody @Validated({Insert.class}) RegionForm regionForm) {
        return ResultBody.build(() -> regionService.addRegion(RegionConverter.toDTO(regionForm)));
    }

    @Operation(summary = "更新区域信息接口")
    @PutMapping(UPDATE_REGION)
    public ResultBody<Long> updateRegion(@RequestBody @Validated({Update.class}) RegionForm regionForm) {
        return ResultBody.build(() -> regionService.updateRegion(RegionConverter.toDTO(regionForm)));
    }

    @Operation(summary = "删除区域信息接口")
    @DeleteMapping(DELETE_REGION)
    public ResultBody<Long> deleteRegion(@PathVariable("regionId") @Parameter(description = "区域信息id", example = "1") Long regionId) {
        return ResultBody.build(() -> regionService.deleteRegion(regionId));
    }

    @Operation(summary = "批量删除区域接口")
    @DeleteMapping(BATCH_DELETE_REGION)
    public ResultBody<List<Long>> batchDeleteRegion(@Parameter(description = "区域id", example = "[1, 2]") @RequestBody List<Long> regionIds) {
        return ResultBody.build(() -> regionService.batchDeleteRegion(regionIds));
    }

    @Operation(summary = "更新区域信息")
    @GetMapping(REFRESH_REGION)
    public ResultBody<Boolean> refreshRegion() {
        return ResultBody.build(regionService::refreshRegion);
    }

}
