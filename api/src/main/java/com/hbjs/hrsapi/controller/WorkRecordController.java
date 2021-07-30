package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.WorkRecordConverter;
import com.hbjs.hrscommon.dto.WorkRecordDTO;
import com.hbjs.hrscommon.form.WorkRecordForm;
import com.hbjs.hrscommon.vo.WorkRecordVO;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrsservice.service.WorkRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "work-record", description = "工作记录接口")
@RestController
@RequiredArgsConstructor
public class WorkRecordController {

    private static final String GET_WORK_RECORD_PAGE = "/work-record/page";
    private static final String GET_WORK_RECORD_BY_ID = "/work-record/{workRecordId}";
    private static final String GET_WORK_RECORD_BY_PID = "/work-record/pid/{pid}";
    private static final String ADD_WORK_RECORD = "/work-record";
    private static final String UPDATE_WORK_RECORD = "/work-record";
    private static final String DELETE_WORK_RECORD = "/work-record/{workRecordId}";
    private static final String BATCH_DELETE_WORK_RECORD = "/work-record";

    private final WorkRecordService workRecordService;

    @Operation(summary = "获取工作记录（分页）")
    @GetMapping(GET_WORK_RECORD_PAGE)
    public ResultBody<Page<WorkRecordVO>> getWorkRecordPage(Page<WorkRecordDTO> page, String keyword) {
        return ResultBody.build(() -> WorkRecordConverter.toVO(workRecordService.getWorkRecordPage(page, keyword)));
    }

    @Operation(summary = "获取工作记录（通过工作记录id）")
    @GetMapping(GET_WORK_RECORD_BY_ID)
    public ResultBody<WorkRecordVO> getWorkRecordById(@Parameter(description = "工作记录id", example = "1") @PathVariable("workRecordId") Long workRecordId) {
        return ResultBody.build(() -> WorkRecordConverter.toVO(workRecordService.getWorkRecordById(workRecordId)));
    }

    @Operation(summary = "获取工作记录（通过工作记录id）")
    @GetMapping(GET_WORK_RECORD_BY_PID)
    public ResultBody<List<WorkRecordVO>> getWorkRecordListByStaffId(@Parameter(description = "关联ID", example = "1") @PathVariable("pid") Long workRecordId) {
        return ResultBody.build(() -> WorkRecordConverter.toVO(workRecordService.getWorkRecordListByStaffId(workRecordId)));
    }

    @Operation(summary = "添加工作记录接口")
    @PostMapping(ADD_WORK_RECORD)
    public ResultBody<Long> addWorkRecord(@RequestBody @Validated({Insert.class}) WorkRecordForm customWorkRecordForm) {
        return ResultBody.build(() -> workRecordService.addWorkRecord(WorkRecordConverter.toDTO(customWorkRecordForm)));
    }

    @Operation(summary = "更新工作记录接口")
    @PutMapping(UPDATE_WORK_RECORD)
    public ResultBody<Long> updateWorkRecord(@RequestBody @Validated({Update.class}) WorkRecordForm customWorkRecordForm) {
        return ResultBody.build(() -> workRecordService.updateWorkRecord(WorkRecordConverter.toDTO(customWorkRecordForm)));
    }

    @Operation(summary = "删除工作记录接口")
    @DeleteMapping(DELETE_WORK_RECORD)
    public ResultBody<Long> deleteWorkRecord(@Parameter(description = "工作记录id", example = "1") @PathVariable("workRecordId") Long workRecordId) {
        return ResultBody.build(() -> workRecordService.deleteWorkRecord(workRecordId));
    }

    @Operation(summary = "批量删除工作记录接口")
    @DeleteMapping(BATCH_DELETE_WORK_RECORD)
    public ResultBody<List<Long>> batchDeleteWorkRecord(@Parameter(description = "工作记录id", example = "[1, 2]") @RequestBody List<Long> workRecordIds) {
        return ResultBody.build(() -> workRecordService.batchDeleteWorkRecord(workRecordIds));
    }

}
