package com.sbgs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbgs.hrscommon.dto.CustomTaskDTO;
import com.sbgs.hrscommon.dto.RegionDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import com.sbgs.hrscommon.converter.RegionConverter;
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

    @Operation(summary = "获取区域信息信息（分页）")
    @GetMapping(GET_REGION_PAGE)
    public ResultBody<Page<RegionVO>> getRegionPage(Page<RegionDTO> page, String keyword) {
        return ResultBody.build(() -> RegionConverter.toVO(regionService.getRegionPage(page, keyword)));
    }

    @Operation(summary = "获取所有区域信息")
    @GetMapping(GET_REGION_ALL)
    public ResultBody<List<RegionVO>> getAllRegions() {
        return ResultBody.build(() -> RegionConverter.toVO(regionService.getAllRegions()));
    }

    @Operation(summary = "获取所有区域信息")
    @GetMapping(GET_REGION_TREE)
    public ResultBody<List<RegionVO>> getRegionTree() {
        return ResultBody.build(() -> RegionConverter.toVO(regionService.getRegionTree()));
    }

    @Operation(summary = "获取区域信息信息（通过区域信息id）")
    @GetMapping(GET_REGION_BY_ID)
    public ResultBody<RegionVO> getRegionByRegionId(@PathVariable("regionId") @Parameter(description = "区域信息id", example = "1") Long regionId) {
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

}
