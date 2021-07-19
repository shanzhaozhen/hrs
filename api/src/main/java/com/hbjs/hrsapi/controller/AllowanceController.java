package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.AllowanceConverter;
import com.hbjs.hrscommon.dto.AllowanceDTO;
import com.hbjs.hrscommon.form.AllowanceForm;
import com.hbjs.hrscommon.vo.AllowanceVO;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrsservice.service.AllowanceService;
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

@Tag(name = "allowance", description = "福利津贴接口")
@RestController
@RequiredArgsConstructor
public class AllowanceController {

    private static final String GET_ALLOWANCE_PAGE = "/allowance/page";
    private static final String GET_ALLOWANCE_BY_ID = "/allowance/{allowanceId}";
    private static final String ADD_ALLOWANCE = "/allowance";
    private static final String UPDATE_ALLOWANCE = "/allowance";
    private static final String DELETE_ALLOWANCE = "/allowance/{allowanceId}";
    private static final String BATCH_DELETE_ALLOWANCE = "/allowance";
    private static final String GENERATE_ALLOWANCE_TEMPLATE = "/allowance/template";
    private static final String IMPORT_ALLOWANCE = "/allowance/import";
    private static final String EXPORT_ALLOWANCE = "/allowance/export";

    private final AllowanceService allowanceService;

    @Operation(summary = "获取福利津贴（分页）")
    @GetMapping(GET_ALLOWANCE_PAGE)
    public ResultBody<Page<AllowanceVO>> getAllowancePage(Page<AllowanceDTO> page, String keyword, Long depId, Integer year, Integer quarter) {
        return ResultBody.build(() -> AllowanceConverter.toVO(allowanceService.getAllowancePage(page, keyword, depId, year, quarter)));
    }

    @Operation(summary = "获取福利津贴（通过福利津贴id）")
    @GetMapping(GET_ALLOWANCE_BY_ID)
    public ResultBody<AllowanceVO> getAllowanceById(@Parameter(description = "福利津贴id", example = "1") @PathVariable("allowanceId") Long allowanceId) {
        return ResultBody.build(() -> AllowanceConverter.toVO(allowanceService.getAllowanceById(allowanceId)));
    }

    @Operation(summary = "添加福利津贴接口")
    @PostMapping(ADD_ALLOWANCE)
    public ResultBody<Long> addAllowance(@RequestBody @Validated({Insert.class}) AllowanceForm allowanceForm) {
        return ResultBody.build(() -> allowanceService.addAllowance(AllowanceConverter.toDTO(allowanceForm)));
    }

    @Operation(summary = "更新福利津贴接口")
    @PutMapping(UPDATE_ALLOWANCE)
    public ResultBody<Long> updateAllowance(@RequestBody @Validated({Update.class}) AllowanceForm allowanceForm) {
        return ResultBody.build(() -> allowanceService.updateAllowance(AllowanceConverter.toDTO(allowanceForm)));
    }

    @Operation(summary = "删除福利津贴接口")
    @DeleteMapping(DELETE_ALLOWANCE)
    public ResultBody<Long> deleteAllowance(@Parameter(description = "福利津贴id", example = "1") @PathVariable("allowanceId") Long allowanceId) {
        return ResultBody.build(() -> allowanceService.deleteAllowance(allowanceId));
    }

    @Operation(summary = "批量删除福利津贴接口")
    @DeleteMapping(BATCH_DELETE_ALLOWANCE)
    public ResultBody<List<Long>> batchDeleteAllowance(@Parameter(description = "福利津贴id", example = "[1, 2]") @RequestBody List<Long> allowanceIds) {
        return ResultBody.build(() -> allowanceService.batchDeleteAllowance(allowanceIds));
    }

    @Operation(summary = "生成福利津贴导入模板")
    @GetMapping(GENERATE_ALLOWANCE_TEMPLATE)
    public void generateAllowanceTemplate() {
        allowanceService.generateAllowanceTemplate();
    }

    @Operation(summary = "导入福利津贴")
    @PostMapping(IMPORT_ALLOWANCE)
    public ResultBody<String> importAllowance(MultipartFile file) {
        return ResultBody.build(() -> allowanceService.importAllowance(file));
    }

    @Operation(summary = "导出福利津贴")
    @GetMapping(EXPORT_ALLOWANCE)
    public void exportAllowance(String keyword, Long depId, Integer year, Integer quarter) {
        allowanceService.exportAllowance(keyword, depId, year, quarter);
    }

}
