package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.SalaryStaffConverter;
import com.hbjs.hrscommon.dto.SalaryStaffDTO;
import com.hbjs.hrscommon.form.SalaryStaffForm;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrscommon.vo.SalaryStaffVO;
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

@Tag(name = "salary-staff", description = "员工薪资信息接口")
@RestController
@RequiredArgsConstructor
public class SalaryStaffController {

    private static final String GET_SALARY_STAFF_PAGE = "/salary-staff/page";
    private static final String GET_SALARY_STAFF_BY_ID = "/salary-staff/{salaryStaffId}";
    private static final String GET_SALARY_STAFF_BY_STAFF_ID = "/salary-staff/staff-id/{staffId}";
    private static final String ADD_SALARY_STAFF = "/salary-staff";
    private static final String UPDATE_SALARY_STAFF = "/salary-staff";
    private static final String DELETE_SALARY_STAFF = "/salary-staff/{staffId}";
    private static final String BATCH_DELETE_SALARY_STAFF = "/salary-staff";
    private static final String GENERATE_SALARY_STAFF_TEMPLATE = "/salary-staff/template";
    private static final String IMPORT_SALARY_STAFF = "/salary-staff/import";
    private static final String EXPORT_SALARY_STAFF = "/salary-staff/export";

    private final SalaryStaffService salaryStaffService;

    @Operation(summary = "获取员工薪资信息（分页）")
    @GetMapping(GET_SALARY_STAFF_PAGE)
    public ResultBody<Page<SalaryStaffVO>> getSalaryStaffPage(Page<SalaryStaffDTO> page, String keyword, Long depId) {
        return ResultBody.build(() -> SalaryStaffConverter.toVO(salaryStaffService.getSalaryStaffPage(page, keyword, depId)));
    }

    @Operation(summary = "获取员工薪资信息（通过员工薪资id）")
    @GetMapping(GET_SALARY_STAFF_BY_ID)
    public ResultBody<SalaryStaffVO> getSalaryStaffById(@Parameter(description = "员工薪资id", example = "1") @PathVariable("salaryStaffId") Long staffId) {
        return ResultBody.build(() -> SalaryStaffConverter.toVO(salaryStaffService.getSalaryStaffById(staffId)));
    }

    @Operation(summary = "获取员工薪资信息（通过员工id）")
    @GetMapping(GET_SALARY_STAFF_BY_STAFF_ID)
    public ResultBody<SalaryStaffVO> getSalaryStaffByStaffId(@Parameter(description = "员工id", example = "1") @PathVariable("staffId") Long staffId) {
        return ResultBody.build(() -> SalaryStaffConverter.toVO(salaryStaffService.getSalaryStaffByStaffId(staffId)));
    }

    @Operation(summary = "添加员工薪资接口")
    @PostMapping(ADD_SALARY_STAFF)
    public ResultBody<Long> addSalaryStaff(@RequestBody @Validated({Insert.class}) SalaryStaffForm salaryStaffForm) {
        return ResultBody.build(() -> salaryStaffService.addSalaryStaff(SalaryStaffConverter.toDTO(salaryStaffForm)));
    }

    @Operation(summary = "更新员工薪资接口")
    @PutMapping(UPDATE_SALARY_STAFF)
    public ResultBody<Long> updateSalaryStaff(@RequestBody @Validated({Update.class}) SalaryStaffForm salaryStaffForm) {
        return ResultBody.build(() -> salaryStaffService.updateSalaryStaff(SalaryStaffConverter.toDTO(salaryStaffForm)));
    }

    @Operation(summary = "删除员工薪资接口")
    @DeleteMapping(DELETE_SALARY_STAFF)
    public ResultBody<Long> deleteSalaryStaff(@Parameter(description = "员工薪资id", example = "1") @PathVariable("staffId") Long staffId) {
        return ResultBody.build(() -> salaryStaffService.deleteSalaryStaff(staffId));
    }

    @Operation(summary = "批量删除员工薪资接口")
    @DeleteMapping(BATCH_DELETE_SALARY_STAFF)
    public ResultBody<List<Long>> batchDeleteSalaryStaff(@Parameter(description = "员工薪资id", example = "[1, 2]") @RequestBody List<Long> staffIds) {
        return ResultBody.build(() -> salaryStaffService.batchDeleteSalaryStaff(staffIds));
    }

    @Operation(summary = "生成绩效评价导入模板")
    @GetMapping(GENERATE_SALARY_STAFF_TEMPLATE)
    public void generateSalaryStaffTemplate() {
        salaryStaffService.generateSalaryStaffTemplate();
    }

    @Operation(summary = "导入绩效评价")
    @PostMapping(IMPORT_SALARY_STAFF)
    public ResultBody<String> importSalaryStaff(MultipartFile file) {
        return ResultBody.build(() -> salaryStaffService.importSalaryStaff(file));
    }

    @Operation(summary = "导出员工薪资信息")
    @GetMapping(EXPORT_SALARY_STAFF)
    public void exportSalaryStaff(String keyword, Long depId) {
        salaryStaffService.exportSalaryStaff(keyword, depId);
    }

}
