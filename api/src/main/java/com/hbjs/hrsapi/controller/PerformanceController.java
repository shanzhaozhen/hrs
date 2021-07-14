package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.PerformanceConverter;
import com.hbjs.hrscommon.dto.PerformanceDTO;
import com.hbjs.hrscommon.form.PerformanceForm;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrscommon.vo.PerformanceVO;
import com.hbjs.hrsservice.service.PerformanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "performance", description = "绩效评价接口")
@RestController
@RequiredArgsConstructor
public class PerformanceController {

    private static final String GET_PERFORMANCE_PAGE = "/performance/page";
    private static final String GET_PERFORMANCE_BY_ID = "/performance/{performanceId}";
    private static final String ADD_PERFORMANCE = "/performance";
    private static final String UPDATE_PERFORMANCE = "/performance";
    private static final String DELETE_PERFORMANCE = "/performance/{performanceId}";
    private static final String BATCH_DELETE_PERFORMANCE = "/performance";
    private static final String GENERATE_PERFORMANCE_TEMPLATE = "/performance/template";
    private static final String IMPORT_PERFORMANCE = "/performance/import";
    private static final String EXPORT_PERFORMANCE = "/performance/export";
    private static final String PRINT_PERFORMANCE = "/performance/print";


    private final PerformanceService performanceService;

    @Operation(summary = "获取绩效评价（分页）")
    @GetMapping(GET_PERFORMANCE_PAGE)
    public ResultBody<Page<PerformanceVO>> getPerformancePage(Page<PerformanceDTO> page, String keyword, Long depId, Integer year, Integer quarter) {
        return ResultBody.build(() -> PerformanceConverter.toVO(performanceService.getPerformancePage(page, keyword, depId, year, quarter)));
    }

    @Operation(summary = "获取绩效评价（通过绩效评价id）")
    @GetMapping(GET_PERFORMANCE_BY_ID)
    public ResultBody<PerformanceVO> getPerformanceById(@Parameter(description = "绩效评价id", example = "1") @PathVariable("performanceId") Long performanceId) {
        return ResultBody.build(() -> PerformanceConverter.toVO(performanceService.getPerformanceById(performanceId)));
    }

    @Operation(summary = "添加绩效评价接口")
    @PostMapping(ADD_PERFORMANCE)
    public ResultBody<Long> addPerformance(@RequestBody @Validated({Insert.class}) PerformanceForm performanceForm) {
        return ResultBody.build(() -> performanceService.addPerformance(PerformanceConverter.toDTO(performanceForm)));
    }

    @Operation(summary = "更新绩效评价接口")
    @PutMapping(UPDATE_PERFORMANCE)
    public ResultBody<Long> updatePerformance(@RequestBody @Validated({Update.class}) PerformanceForm performanceForm) {
        return ResultBody.build(() -> performanceService.updatePerformance(PerformanceConverter.toDTO(performanceForm)));
    }

    @Operation(summary = "删除绩效评价接口")
    @DeleteMapping(DELETE_PERFORMANCE)
    public ResultBody<Long> deletePerformance(@Parameter(description = "绩效评价id", example = "1") @PathVariable("performanceId") Long performanceId) {
        return ResultBody.build(() -> performanceService.deletePerformance(performanceId));
    }

    @Operation(summary = "批量删除绩效评价接口")
    @DeleteMapping(BATCH_DELETE_PERFORMANCE)
    public ResultBody<List<Long>> batchDeletePerformance(@Parameter(description = "绩效评价id", example = "[1, 2]") @RequestBody List<Long> performanceIds) {
        return ResultBody.build(() -> performanceService.batchDeletePerformance(performanceIds));
    }

    @Operation(summary = "生成绩效评价导入模板")
    @GetMapping(GENERATE_PERFORMANCE_TEMPLATE)
    public void generatePerformanceTemplate() {
        performanceService.generatePerformanceTemplate();
    }

    @Operation(summary = "导入绩效评价")
    @PostMapping(IMPORT_PERFORMANCE)
    public ResultBody<String> importPerformance(MultipartFile file) {
        return ResultBody.build(() -> performanceService.importPerformance(file));
    }

    @Operation(summary = "导出绩效评价")
    @GetMapping(EXPORT_PERFORMANCE)
    public void exportPerformance(String keyword, Long depId, Integer year, Integer quarter) {
        performanceService.exportPerformance(keyword, depId, year, quarter);
    }

}
