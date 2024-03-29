package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.StaffChangeConverter;
import com.hbjs.hrscommon.dto.StaffChangeDTO;
import com.hbjs.hrscommon.form.StaffChangeForm;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrscommon.vo.StaffChangeVO;
import com.hbjs.hrsservice.service.StaffChangeService;
import com.hbjs.hrsservice.service.StaffService;
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

@Tag(name = "staff-change", description = "调动记录接口")
@RestController
@RequiredArgsConstructor
public class StaffChangeController {

    private static final String GET_STAFF_CHANGE_PAGE = "/staff-change/page";
    private static final String GET_STAFF_CHANGE_BY_ID = "/staff-change/{staffChangeId}";
    private static final String ADD_STAFF_CHANGE = "/staff-change";
    private static final String UPDATE_STAFF_CHANGE = "/staff-change";
    private static final String DELETE_STAFF_CHANGE = "/staff-change/{staffChangeId}";
    private static final String BATCH_DELETE_STAFF_CHANGE = "/staff-change";
    private static final String RUN_STAFF_CHANGE = "/staff-change/run/{staffChangeId}";
    private static final String GENERATE_STAFF_CHANGE_TEMPLATE = "/staff-change/template";
    private static final String IMPORT_STAFF_CHANGE = "/staff-change/import";
    private static final String EXPORT_STAFF_CHANGE = "/staff-change/export";

    private final StaffChangeService staffChangeService;
    private final StaffService staffService;

    @Operation(summary = "获取调动记录信息（分页）")
    @GetMapping(GET_STAFF_CHANGE_PAGE)
    public ResultBody<Page<StaffChangeVO>> getStaffChangePage(Page<StaffChangeDTO> page, Long staffId, String keyword, Long depId) {
        return ResultBody.build(() -> StaffChangeConverter.toVO(staffChangeService.getStaffChangePage(page, staffId, keyword, depId)));
    }

    @Operation(summary = "获取调动记录（通过调动记录id）")
    @GetMapping(GET_STAFF_CHANGE_BY_ID)
    public ResultBody<StaffChangeVO> getStaffChangeById(@Parameter(description = "调动记录id", example = "1")  @PathVariable Long staffChangeId) {
        return ResultBody.build(() -> StaffChangeConverter.toVO(staffChangeService.getStaffChangeById(staffChangeId)));
    }

    @Operation(summary = "添加调动记录接口")
    @PostMapping(ADD_STAFF_CHANGE)
    public ResultBody<Long> addStaffChange(@RequestBody @Validated({Insert.class}) StaffChangeForm staffChangeForm) {
        return ResultBody.build(() -> staffChangeService.addStaffChange(StaffChangeConverter.toDTO(staffChangeForm)));
    }

    @Operation(summary = "修改调动记录接口")
    @PutMapping(UPDATE_STAFF_CHANGE)
    public ResultBody<Long> updateStaffChange(@RequestBody @Validated({Update.class}) StaffChangeForm staffChangeForm) {
        return ResultBody.build(() -> staffChangeService.updateStaffChange(StaffChangeConverter.toDTO(staffChangeForm)));
    }

    @Operation(summary = "删除调动记录接口")
    @DeleteMapping(DELETE_STAFF_CHANGE)
    public ResultBody<Long> deleteStaffChange(@Parameter(description = "调动记录id", example = "1")  @PathVariable Long staffChangeId) {
        return ResultBody.build(() -> staffChangeService.deleteStaffChange(staffChangeId));
    }

    @Operation(summary = "批量删除调动记录接口")
    @DeleteMapping(BATCH_DELETE_STAFF_CHANGE)
    public ResultBody<List<Long>> batchDeleteStaffChange(@Parameter(description = "调动记录id", example = "[1, 2]") @RequestBody List<Long> staffChangeIds) {
        return ResultBody.build(() -> staffChangeService.batchDeleteStaffChange(staffChangeIds));
    }

    @Operation(summary = "生成调动记录导入模板")
    @GetMapping(GENERATE_STAFF_CHANGE_TEMPLATE)
    public void generateStaffChangeTemplate() {
        staffChangeService.generateStaffChangeTemplate();
    }
    
    @Operation(summary = "导入调动记录")
    @PostMapping(IMPORT_STAFF_CHANGE)
    public ResultBody<String> importStaffChange(MultipartFile file) {
        return ResultBody.build(() -> staffService.importStaffChange(file));
    }

    @Operation(summary = "导出调动记录")
    @GetMapping(EXPORT_STAFF_CHANGE)
    public void exportStaffChange(Long staffId, String keyword, Long depId) {
        staffChangeService.exportStaffChange(staffId, keyword, depId);
    }
    
    @Operation(summary = "执行调动")
    @GetMapping(RUN_STAFF_CHANGE)
    public ResultBody<Long> runChange(@Parameter(description = "调动记录id", example = "1")  @PathVariable Long staffChangeId) {
        return ResultBody.build(() -> staffService.runChange(staffChangeId));
    }


}
