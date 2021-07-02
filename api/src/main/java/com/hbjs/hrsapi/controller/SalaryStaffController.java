package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.SalaryStaffConverter;
import com.hbjs.hrscommon.converter.StaffConverter;
import com.hbjs.hrscommon.dto.SalaryStaffDTO;
import com.hbjs.hrscommon.form.SalaryStaffForm;
import com.hbjs.hrscommon.form.StaffForm;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrscommon.vo.SalaryStaffVO;
import com.hbjs.hrsservice.service.SalaryStaffService;
import com.hbjs.hrsservice.service.StaffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "staff", description = "员工薪资信息接口")
@RestController
@RequiredArgsConstructor
public class SalaryStaffController {

    private static final String GET_STAFF_SALARY_PAGE = "/salary-staff/page";
    private static final String GET_STAFF_SALARY_BY_ID = "/salary-staff/{staffId}";
    private static final String ADD_STAFF_SALARY = "/salary-staff";
    private static final String UPDATE_STAFF_SALARY = "/salary-staff";
    private static final String DELETE_STAFF_SALARY = "/salary-staff/{staffId}";
    private static final String BATCH_DELETE_STAFF_SALARY = "/salary-staff";
    private static final String EXPORT_STAFF_SALARY = "/salary-staff/export";
    private static final String PRINT_STAFF_SALARY = "/salary-staff/print";

    private final SalaryStaffService salaryStaffService;

    @Operation(summary = "获取员工薪资信息（分页）")
    @GetMapping(GET_STAFF_SALARY_PAGE)
    public ResultBody<Page<SalaryStaffVO>> getSalaryStaffPage(Page<SalaryStaffDTO> page, String keyword, Long depId) {
        return ResultBody.build(() -> SalaryStaffConverter.toVO(salaryStaffService.getSalaryStaffPage(page, keyword, depId)));
    }

    @Operation(summary = "获取员工薪资信息（通过员工薪资id）")
    @GetMapping(GET_STAFF_SALARY_BY_ID)
    public ResultBody<SalaryStaffVO> getSalaryStaffById(@Parameter(description = "员工薪资id", example = "1") @PathVariable("staffId") Long staffId) {
        return ResultBody.build(() -> SalaryStaffConverter.toVO(salaryStaffService.getSalaryStaffById(staffId)));
    }

    @Operation(summary = "添加员工薪资接口")
    @PostMapping(ADD_STAFF_SALARY)
    public ResultBody<Long> addStaff(@RequestBody @Validated({Insert.class}) SalaryStaffForm salaryStaffForm) {
        return ResultBody.build(() -> salaryStaffService.addSalaryStaff(SalaryStaffConverter.toDTO(salaryStaffForm)));
    }

    @Operation(summary = "更新员工薪资接口")
    @PutMapping(UPDATE_STAFF_SALARY)
    public ResultBody<Long> updateSalaryStaff(@RequestBody @Validated({Update.class}) SalaryStaffForm salaryStaffForm) {
        return ResultBody.build(() -> salaryStaffService.updateSalaryStaff(SalaryStaffConverter.toDTO(salaryStaffForm)));
    }

    @Operation(summary = "删除员工薪资接口")
    @DeleteMapping(DELETE_STAFF_SALARY)
    public ResultBody<Long> deleteStaff(@Parameter(description = "员工薪资id", example = "1") @PathVariable("staffId") Long staffId) {
        return ResultBody.build(() -> salaryStaffService.deleteSalaryStaff(staffId));
    }

    @Operation(summary = "批量删除员工薪资接口")
    @DeleteMapping(BATCH_DELETE_STAFF_SALARY)
    public ResultBody<List<Long>> batchDeleteStaff(@Parameter(description = "员工薪资id", example = "[1, 2]") @RequestBody List<Long> staffIds) {
        return ResultBody.build(() -> salaryStaffService.batchDeleteSalaryStaff(staffIds));
    }

    @Operation(summary = "导出员工薪资信息")
    @GetMapping(EXPORT_STAFF_SALARY)
    public void exportStaff(String keyword, Long depId) {
        salaryStaffService.exportSalaryStaff(keyword, depId);
    }

    @Operation(summary = "导出员工薪资信息")
    @GetMapping(PRINT_STAFF_SALARY)
    public void printStaff(Long staffId) {
        salaryStaffService.printSalaryStaff(staffId);
    }


}
