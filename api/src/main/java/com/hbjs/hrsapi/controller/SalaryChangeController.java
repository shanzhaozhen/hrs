package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.SalaryChangeConverter;
import com.hbjs.hrscommon.dto.SalaryChangeDTO;
import com.hbjs.hrscommon.form.SalaryChangeForm;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrscommon.vo.SalaryChangeVO;
import com.hbjs.hrsservice.service.SalaryChangeService;
import com.hbjs.hrsservice.service.SalaryStaffService;
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

@Tag(name = "salary-change", description = "员工薪资变动记录接口")
@RestController
@RequiredArgsConstructor
public class SalaryChangeController {

    private static final String GET_SALARY_CHANGE_PAGE = "/salary-change/page";
    private static final String GET_SALARY_CHANGE_BY_ID = "/salary-change/{salaryChangeId}";
    private static final String ADD_SALARY_CHANGE = "/salary-change";
    private static final String UPDATE_SALARY_CHANGE = "/salary-change";
    private static final String DELETE_SALARY_CHANGE = "/salary-change/{salaryChangeId}";
    private static final String BATCH_DELETE_SALARY_CHANGE = "/salary-change";
    private static final String GENERATE_SALARY_CHANGE_TEMPLATE = "/salary-change/template";
    private static final String IMPORT_SALARY_CHANGE = "/salary-change/import";
    private static final String EXPORT_SALARY_CHANGE = "/salary-change/export";
    private static final String RUN_SALARY_CHANGE = "/salary-change/run/{salaryChangeId}";

    private final SalaryChangeService salaryChangeService;
    private final SalaryStaffService salaryStaffService;

    @Operation(summary = "获取员工薪资变动记录信息（分页）")
    @GetMapping(GET_SALARY_CHANGE_PAGE)
    public ResultBody<Page<SalaryChangeVO>> getSalaryChangePage(Page<SalaryChangeDTO> page, Long staffId, String keyword, Long depId) {
        return ResultBody.build(() -> SalaryChangeConverter.toVO(salaryChangeService.getSalaryChangePage(page, staffId, keyword, depId)));
    }

    @Operation(summary = "获取员工薪资变动记录（通过员工薪资变动记录id）")
    @GetMapping(GET_SALARY_CHANGE_BY_ID)
    public ResultBody<SalaryChangeVO> getSalaryChangeById(@Parameter(description = "员工薪资变动记录id", example = "1")  @PathVariable Long salaryChangeId) {
        return ResultBody.build(() -> SalaryChangeConverter.toVO(salaryChangeService.getSalaryChangeById(salaryChangeId)));
    }

    @Operation(summary = "添加员工薪资变动记录接口")
    @PostMapping(ADD_SALARY_CHANGE)
    public ResultBody<Long> addSalaryChange(@RequestBody @Validated({Insert.class}) SalaryChangeForm salaryChangeForm) {
        return ResultBody.build(() -> salaryChangeService.addSalaryChange(SalaryChangeConverter.toDTO(salaryChangeForm)));
    }

    @Operation(summary = "修改员工薪资变动记录接口")
    @PutMapping(UPDATE_SALARY_CHANGE)
    public ResultBody<Long> updateSalaryChange(@RequestBody @Validated({Update.class}) SalaryChangeForm salaryChangeForm) {
        return ResultBody.build(() -> salaryChangeService.updateSalaryChange(SalaryChangeConverter.toDTO(salaryChangeForm)));
    }

    @Operation(summary = "删除员工薪资变动记录接口")
    @DeleteMapping(DELETE_SALARY_CHANGE)
    public ResultBody<Long> deleteSalaryChange(@Parameter(description = "员工薪资变动记录id", example = "1")  @PathVariable Long salaryChangeId) {
        return ResultBody.build(() -> salaryChangeService.deleteSalaryChange(salaryChangeId));
    }

    @Operation(summary = "批量删除员工薪资变动记录接口")
    @DeleteMapping(BATCH_DELETE_SALARY_CHANGE)
    public ResultBody<List<Long>> batchDeleteSalaryChange(@Parameter(description = "员工薪资变动记录id", example = "[1, 2]") @RequestBody List<Long> salaryChangeIds) {
        return ResultBody.build(() -> salaryChangeService.batchDeleteSalaryChange(salaryChangeIds));
    }

    @Operation(summary = "生成薪资变动导入模板")
    @GetMapping(GENERATE_SALARY_CHANGE_TEMPLATE)
    public void generateSalaryChangeTemplate() {
        salaryChangeService.generateSalaryChangeTemplate();
    }
    
    @Operation(summary = "导入薪资变动")
    @PostMapping(IMPORT_SALARY_CHANGE)
    public ResultBody<String> importSalaryChange(MultipartFile file) {
        return ResultBody.build(() -> salaryStaffService.importSalaryChange(file));
    }

    @Operation(summary = "导出薪资变动")
    @GetMapping(EXPORT_SALARY_CHANGE)
    public void exportSalaryChange(String keyword, Long depId) {
        salaryChangeService.exportSalaryChange(keyword, depId);
    }

    @Operation(summary = "执行员工薪资变动")
    @GetMapping(RUN_SALARY_CHANGE)
    public ResultBody<Long> runChange(@Parameter(description = "员工薪资变动记录id", example = "1")  @PathVariable Long salaryChangeId) {
        return ResultBody.build(() -> salaryStaffService.runChange(salaryChangeId));
    }

}
