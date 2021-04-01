package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.StaffConverter;
import com.hbjs.hrscommon.dto.StaffDTO;
import com.hbjs.hrscommon.form.StaffForm;
import com.hbjs.hrscommon.vo.StaffVO;
import com.hbjs.hrscommon.vo.ResultBody;
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

@Tag(name = "staff", description = "员工信息接口")
@RestController
@RequiredArgsConstructor
public class StaffController {

    private static final String GET_STAFF_PAGE = "/staff/page";
    private static final String GET_STAFF_BY_ID = "/staff/{staffId}";
    private static final String ADD_STAFF = "/staff";
    private static final String UPDATE_STAFF = "/staff";
    private static final String DELETE_STAFF = "/staff/{staffId}";
    private static final String BATCH_DELETE_STAFF = "/staff";

    private final StaffService staffService;

    @Operation(summary = "获取员工信息（分页）")
    @GetMapping(GET_STAFF_PAGE)
    public ResultBody<Page<StaffVO>> getStaffPage(Page<StaffDTO> page, String keyword) {
        return ResultBody.build(() -> StaffConverter.toVO(staffService.getStaffPage(page, keyword)));
    }

    @Operation(summary = "获取员工信息（通过员工id）")
    @GetMapping(GET_STAFF_BY_ID)
    public ResultBody<StaffVO> getStaffById(@Parameter(description = "员工id", example = "1") @PathVariable("staffId") Long staffId) {
        return ResultBody.build(() -> StaffConverter.toVO(staffService.getStaffById(staffId)));
    }

    @Operation(summary = "添加员工接口")
    @PostMapping(ADD_STAFF)
    public ResultBody<Long> addStaff(@RequestBody @Validated({Insert.class}) StaffForm customStaffForm) {
        return ResultBody.build(() -> staffService.addStaff(StaffConverter.toDTO(customStaffForm)));
    }

    @Operation(summary = "更新员工接口")
    @PutMapping(UPDATE_STAFF)
    public ResultBody<Long> updateStaff(@RequestBody @Validated({Update.class}) StaffForm customStaffForm) {
        return ResultBody.build(() -> staffService.updateStaff(StaffConverter.toDTO(customStaffForm)));
    }

    @Operation(summary = "删除员工接口")
    @DeleteMapping(DELETE_STAFF)
    public ResultBody<Long> deleteStaff(@Parameter(description = "员工id", example = "1") @PathVariable("staffId") Long staffId) {
        return ResultBody.build(() -> staffService.deleteStaff(staffId));
    }

    @Operation(summary = "批量删除员工接口")
    @DeleteMapping(BATCH_DELETE_STAFF)
    public ResultBody<List<Long>> batchDeleteStaff(@Parameter(description = "员工id", example = "[1, 2]") @RequestBody List<Long> staffIds) {
        return ResultBody.build(() -> staffService.batchDeleteStaff(staffIds));
    }

}
