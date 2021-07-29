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

    private static final String GET_SALARY_PAGE = "/salary/page";
    private static final String GET_SALARY_BY_ID = "/salary/{salaryId}";
    private static final String ADD_SALARY = "/salary";
    private static final String UPDATE_SALARY = "/salary";
    private static final String DELETE_SALARY = "/salary/{salaryId}";
    private static final String BATCH_DELETE_SALARY = "/salary";
    private static final String GENERATE_SALARY = "/salary/generate";
    private static final String FREEZE_SALARY = "/salary/freeze";
    private static final String FREEZE_SALARY_MONTH = "/salary/freeze/month";
    private static final String GENERATE_SALARY_TEMPLATE = "/salary/template";
    private static final String IMPORT_SALARY = "/salary/import";
    private static final String EXPORT_SALARY = "/salary/export";
    private static final String GENERATE_SALARY_TAX_TEMPLATE = "/salary/tax/template";
    private static final String IMPORT_SALARY_TAX = "/salary/tax/import";

    private final SalaryService salaryService;

    @Operation(summary = "获取薪资发放（分页）")
    @GetMapping(GET_SALARY_PAGE)
    public ResultBody<Page<SalaryVO>> getSalaryPage(Page<SalaryDTO> page, String keyword, Long depId, String type, Boolean freeze) {
        return ResultBody.build(() -> SalaryConverter.toVO(salaryService.getSalaryPage(page, keyword, depId, type, freeze)));
    }

    @Operation(summary = "获取薪资发放（通过薪资发放id）")
    @GetMapping(GET_SALARY_BY_ID)
    public ResultBody<SalaryVO> getSalaryById(@Parameter(description = "薪资发放id", example = "1") @PathVariable("salaryId") Long salaryId) {
        return ResultBody.build(() -> SalaryConverter.toVO(salaryService.getSalaryById(salaryId)));
    }

    @Operation(summary = "添加薪资发放接口")
    @PostMapping(ADD_SALARY)
    public ResultBody<Long> addSalary(@RequestBody @Validated({Insert.class}) SalaryForm salaryForm) {
        return ResultBody.build(() -> salaryService.addSalary(SalaryConverter.toDTO(salaryForm)));
    }

    @Operation(summary = "更新薪资发放接口")
    @PutMapping(UPDATE_SALARY)
    public ResultBody<Long> updateSalary(@RequestBody @Validated({Update.class}) SalaryForm salaryForm) {
        return ResultBody.build(() -> salaryService.updateSalary(SalaryConverter.toDTO(salaryForm)));
    }

    @Operation(summary = "删除薪资发放接口")
    @DeleteMapping(DELETE_SALARY)
    public ResultBody<Long> deleteSalary(@Parameter(description = "薪资发放id", example = "1") @PathVariable("salaryId") Long salaryId) {
        return ResultBody.build(() -> salaryService.deleteSalary(salaryId));
    }

    @Operation(summary = "批量删除薪资发放接口")
    @DeleteMapping(BATCH_DELETE_SALARY)
    public ResultBody<List<Long>> batchDeleteSalary(@Parameter(description = "薪资发放id", example = "[1, 2]") @RequestBody List<Long> salaryIds) {
        return ResultBody.build(() -> salaryService.batchDeleteSalary(salaryIds));
    }

    @Operation(summary = "生成薪资发放")
    @GetMapping(GENERATE_SALARY)
    public ResultBody<String> generateSalaryData(String month, String depId, String staffCode) {
        return ResultBody.build(() -> salaryService.generateSalaryData(month, depId, staffCode));
    }

    @Operation(summary = "冻结薪资编辑")
    @GetMapping(FREEZE_SALARY)
    public ResultBody<String> freezeSalaryByIds(@RequestParam(value = "salaryIds") List<Long> salaryIds, Boolean freeze) {
        return ResultBody.build(() -> salaryService.freezeSalaryByIds(salaryIds, freeze));
    }

    @Operation(summary = "冻结薪资编辑")
    @GetMapping(FREEZE_SALARY_MONTH)
    public ResultBody<String> freezeSalaryByMonth(String month, Boolean freeze) {
        return ResultBody.build(() -> salaryService.freezeSalaryByMonth(month, freeze));
    }

    @Operation(summary = "生成薪资发放导入模板")
    @GetMapping(GENERATE_SALARY_TEMPLATE)
    public void generateSalaryTemplate() {
        salaryService.generateSalaryTemplate();
    }

    @Operation(summary = "导入薪资发放")
    @PostMapping(IMPORT_SALARY)
    public ResultBody<String> importSalary(MultipartFile file) {
        return ResultBody.build(() -> salaryService.importSalary(file));
    }

    @Operation(summary = "导出薪资发放")
    @GetMapping(EXPORT_SALARY)
    public void exportSalary(String keyword, Long depId) {
        salaryService.exportSalary(keyword, depId);
    }

    @Operation(summary = "生成个税导入模板")
    @GetMapping(GENERATE_SALARY_TAX_TEMPLATE)
    public void generateSalaryTaxTemplate() {
        salaryService.generateSalaryTaxTemplate();
    }

    @Operation(summary = "个税导入")
    @PostMapping(IMPORT_SALARY_TAX)
    public ResultBody<String> importSalaryTax(MultipartFile file) {
        return ResultBody.build(() -> salaryService.importSalaryTax(file));
    }

}
