package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.SalarySettingConverter;
import com.hbjs.hrscommon.dto.SalarySettingDTO;
import com.hbjs.hrscommon.form.SalarySettingForm;
import com.hbjs.hrscommon.vo.SalarySettingVO;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrsservice.service.SalarySettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "salarySetting", description = "薪资配置接口")
@RestController
@RequiredArgsConstructor
public class SalarySettingController {

    private static final String GET_SALARY_SETTING_PAGE = "/salary-setting/page";
    private static final String GET_SALARY_SETTING_BY_ID = "/salary-setting/{salarySettingId}";
    private static final String GET_SALARY_SETTING_NEW = "/salary-setting/new";
    private static final String ADD_SALARY_SETTING = "/salarySetting";
    private static final String UPDATE_SALARY_SETTING = "/salarySetting";
    private static final String DELETE_SALARY_SETTING = "/salary-setting/{salarySettingId}";
    private static final String BATCH_DELETE_SALARY_SETTING = "/salarySetting";

    private final SalarySettingService salarySettingService;

    @Operation(summary = "获取薪资配置（分页）")
    @GetMapping(GET_SALARY_SETTING_PAGE)
    public ResultBody<Page<SalarySettingVO>> getSalarySettingPage(Page<SalarySettingDTO> page) {
        return ResultBody.build(() -> SalarySettingConverter.toVO(salarySettingService.getSalarySettingPage(page)));
    }

    @Operation(summary = "获取薪资配置（通过薪资配置id）")
    @GetMapping(GET_SALARY_SETTING_BY_ID)
    public ResultBody<SalarySettingVO> getSalarySettingById(@Parameter(description = "薪资配置id", example = "1") @PathVariable("salarySettingId") Long salarySettingId) {
        return ResultBody.build(() -> SalarySettingConverter.toVO(salarySettingService.getSalarySettingById(salarySettingId)));
    }

    @Operation(summary = "获取最后一次修改的薪资配置")
    @GetMapping(GET_SALARY_SETTING_NEW)
    public ResultBody<SalarySettingVO> getSalarySettingNew() {
        return ResultBody.build(() -> SalarySettingConverter.toVO(salarySettingService.getSalarySettingNew()));
    }

    @Operation(summary = "添加薪资配置接口")
    @PostMapping(ADD_SALARY_SETTING)
    public ResultBody<Long> addSalarySetting(@RequestBody @Validated({Insert.class}) SalarySettingForm salarySettingForm) {
        return ResultBody.build(() -> salarySettingService.addSalarySetting(SalarySettingConverter.toDTO(salarySettingForm)));
    }

    @Operation(summary = "更新薪资配置接口")
    @PutMapping(UPDATE_SALARY_SETTING)
    public ResultBody<Long> updateSalarySetting(@RequestBody @Validated({Update.class}) SalarySettingForm salarySettingForm) {
        return ResultBody.build(() -> salarySettingService.updateSalarySetting(SalarySettingConverter.toDTO(salarySettingForm)));
    }

    @Operation(summary = "删除薪资配置接口")
    @DeleteMapping(DELETE_SALARY_SETTING)
    public ResultBody<Long> deleteSalarySetting(@Parameter(description = "薪资配置id", example = "1") @PathVariable("salarySettingId") Long salarySettingId) {
        return ResultBody.build(() -> salarySettingService.deleteSalarySetting(salarySettingId));
    }

    @Operation(summary = "批量删除薪资配置接口")
    @DeleteMapping(BATCH_DELETE_SALARY_SETTING)
    public ResultBody<List<Long>> batchDeleteSalarySetting(@Parameter(description = "薪资配置id", example = "[1, 2]") @RequestBody List<Long> salarySettingIds) {
        return ResultBody.build(() -> salarySettingService.batchDeleteSalarySetting(salarySettingIds));
    }

}
