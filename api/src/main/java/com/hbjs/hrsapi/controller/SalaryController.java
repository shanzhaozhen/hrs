package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.SalaryConverter;
import com.hbjs.hrscommon.dto.SalaryDTO;
import com.hbjs.hrscommon.form.SalaryForm;
import com.hbjs.hrscommon.vo.SalaryVO;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrsservice.service.SalaryService;
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

@Tag(name = "salary", description = "薪资发放接口")
@RestController
@RequiredArgsConstructor
public class SalaryController {

    private static final String GET_PERFORMANCE_PAGE = "/salary/page";
    private static final String GET_PERFORMANCE_BY_ID = "/salary/{salaryId}";
    private static final String ADD_PERFORMANCE = "/salary";
    private static final String UPDATE_PERFORMANCE = "/salary";
    private static final String DELETE_PERFORMANCE = "/salary/{salaryId}";
    private static final String BATCH_DELETE_PERFORMANCE = "/salary";
    private static final String GENERATE_PERFORMANCE_TEMPLATE = "/salary/template";
    private static final String IMPORT_PERFORMANCE = "/salary/import";
    private static final String EXPORT_PERFORMANCE = "/salary/export";

    private final SalaryService salaryService;

    @Operation(summary = "获取薪资发放（分页）")
    @GetMapping(GET_PERFORMANCE_PAGE)
    public ResultBody<Page<SalaryVO>> getSalaryPage(Page<SalaryDTO> page, String keyword, Long depId) {
        return ResultBody.build(() -> SalaryConverter.toVO(salaryService.getSalaryPage(page, keyword, depId)));
    }

    @Operation(summary = "获取薪资发放（通过薪资发放id）")
    @GetMapping(GET_PERFORMANCE_BY_ID)
    public ResultBody<SalaryVO> getSalaryById(@Parameter(description = "薪资发放id", example = "1") @PathVariable("salaryId") Long salaryId) {
        return ResultBody.build(() -> SalaryConverter.toVO(salaryService.getSalaryById(salaryId)));
    }

    @Operation(summary = "添加薪资发放接口")
    @PostMapping(ADD_PERFORMANCE)
    public ResultBody<Long> addSalary(@RequestBody @Validated({Insert.class}) SalaryForm salaryForm) {
        return ResultBody.build(() -> salaryService.addSalary(SalaryConverter.toDTO(salaryForm)));
    }

    @Operation(summary = "更新薪资发放接口")
    @PutMapping(UPDATE_PERFORMANCE)
    public ResultBody<Long> updateSalary(@RequestBody @Validated({Update.class}) SalaryForm salaryForm) {
        return ResultBody.build(() -> salaryService.updateSalary(SalaryConverter.toDTO(salaryForm)));
    }

    @Operation(summary = "删除薪资发放接口")
    @DeleteMapping(DELETE_PERFORMANCE)
    public ResultBody<Long> deleteSalary(@Parameter(description = "薪资发放id", example = "1") @PathVariable("salaryId") Long salaryId) {
        return ResultBody.build(() -> salaryService.deleteSalary(salaryId));
    }

    @Operation(summary = "批量删除薪资发放接口")
    @DeleteMapping(BATCH_DELETE_PERFORMANCE)
    public ResultBody<List<Long>> batchDeleteSalary(@Parameter(description = "薪资发放id", example = "[1, 2]") @RequestBody List<Long> salaryIds) {
        return ResultBody.build(() -> salaryService.batchDeleteSalary(salaryIds));
    }

    @Operation(summary = "生成薪资发放导入模板")
    @GetMapping(GENERATE_PERFORMANCE_TEMPLATE)
    public void generateSalaryTemplate() {
        salaryService.generateSalaryTemplate();
    }

    @Operation(summary = "导入薪资发放")
    @PostMapping(IMPORT_PERFORMANCE)
    public ResultBody<String> importSalary(MultipartFile file) {
        return ResultBody.build(() -> salaryService.importSalary(file));
    }

    @Operation(summary = "导出薪资发放")
    @GetMapping(EXPORT_PERFORMANCE)
    public void exportSalary(String keyword, Long depId) {
        salaryService.exportSalary(keyword, depId);
    }

}
