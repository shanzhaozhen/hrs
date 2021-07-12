package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.PerformanceSettingConverter;
import com.hbjs.hrscommon.dto.PerformanceSettingDTO;
import com.hbjs.hrscommon.form.PerformanceSettingForm;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrscommon.vo.PerformanceSettingVO;
import com.hbjs.hrsservice.service.PerformanceSettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "performance-setting", description = "绩效设置接口")
@RestController
@RequiredArgsConstructor
public class PerformanceSettingController {

    private static final String GET_PERFORMANCE_SETTING_PAGE = "/performance-setting/page";
    private static final String GET_PERFORMANCE_SETTING_LIST = "/performance-setting/list";
    private static final String GET_PERFORMANCE_SETTING_BY_ID = "/performance-setting/{performanceSettingId}";
    private static final String ADD_PERFORMANCE_SETTING = "/performance-setting";
    private static final String UPDATE_PERFORMANCE_SETTING = "/performance-setting";
    private static final String DELETE_PERFORMANCE_SETTING = "/performance-setting/{staffId}";
    private static final String BATCH_DELETE_PERFORMANCE_SETTING = "/performance-setting";
    private static final String EXPORT_PERFORMANCE_SETTING = "/performance-setting/export";
    private static final String PRINT_PERFORMANCE_SETTING = "/performance-setting/print";

    private final PerformanceSettingService performanceSettingService;

    @Operation(summary = "获取绩效设置（分页）")
    @GetMapping(GET_PERFORMANCE_SETTING_PAGE)
    public ResultBody<Page<PerformanceSettingVO>> getPerformanceSettingPage(Page<PerformanceSettingDTO> page, String keyword) {
        return ResultBody.build(() -> PerformanceSettingConverter.toVO(performanceSettingService.getPerformanceSettingPage(page, keyword)));
    }

    @Operation(summary = "获取绩效设置（分页）")
    @GetMapping(GET_PERFORMANCE_SETTING_LIST)
    public ResultBody<List<PerformanceSettingVO>> getPerformanceSettingList(String keyword) {
        return ResultBody.build(() -> PerformanceSettingConverter.toVO(performanceSettingService.getPerformanceSettingList(keyword)));
    }

    @Operation(summary = "获取绩效设置（通过绩效设置id）")
    @GetMapping(GET_PERFORMANCE_SETTING_BY_ID)
    public ResultBody<PerformanceSettingVO> getPerformanceSettingById(@Parameter(description = "绩效设置id", example = "1") @PathVariable("performanceSettingId") Long staffId) {
        return ResultBody.build(() -> PerformanceSettingConverter.toVO(performanceSettingService.getPerformanceSettingById(staffId)));
    }

    @Operation(summary = "添加绩效设置接口")
    @PostMapping(ADD_PERFORMANCE_SETTING)
    public ResultBody<Long> addStaff(@RequestBody @Validated({Insert.class}) PerformanceSettingForm performanceSettingForm) {
        return ResultBody.build(() -> performanceSettingService.addPerformanceSetting(PerformanceSettingConverter.toDTO(performanceSettingForm)));
    }

    @Operation(summary = "更新绩效设置接口")
    @PutMapping(UPDATE_PERFORMANCE_SETTING)
    public ResultBody<Long> updatePerformanceSetting(@RequestBody @Validated({Update.class}) PerformanceSettingForm performanceSettingForm) {
        return ResultBody.build(() -> performanceSettingService.updatePerformanceSetting(PerformanceSettingConverter.toDTO(performanceSettingForm)));
    }

    @Operation(summary = "删除绩效设置接口")
    @DeleteMapping(DELETE_PERFORMANCE_SETTING)
    public ResultBody<Long> deletePerformanceSetting(@Parameter(description = "绩效设置id", example = "1") @PathVariable("staffId") Long staffId) {
        return ResultBody.build(() -> performanceSettingService.deletePerformanceSetting(staffId));
    }

    @Operation(summary = "批量删除绩效设置接口")
    @DeleteMapping(BATCH_DELETE_PERFORMANCE_SETTING)
    public ResultBody<List<Long>> batchDeleteStaff(@Parameter(description = "绩效设置id", example = "[1, 2]") @RequestBody List<Long> staffIds) {
        return ResultBody.build(() -> performanceSettingService.batchDeletePerformanceSetting(staffIds));
    }

    @Operation(summary = "导出绩效设置")
    @GetMapping(EXPORT_PERFORMANCE_SETTING)
    public void exportPerformanceSetting(String keyword, Long depId) {
        performanceSettingService.exportPerformanceSetting(keyword, depId);
    }

    @Operation(summary = "导出绩效设置")
    @GetMapping(PRINT_PERFORMANCE_SETTING)
    public void printPerformanceSetting(Long staffId) {
        performanceSettingService.printPerformanceSetting(staffId);
    }


}
