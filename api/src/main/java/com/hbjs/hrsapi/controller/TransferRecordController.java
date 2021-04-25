package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.RoleConverter;
import com.hbjs.hrscommon.converter.TransferRecordConverter;
import com.hbjs.hrscommon.dto.TransferRecordDTO;
import com.hbjs.hrscommon.form.TransferRecordForm;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrscommon.vo.RoleVO;
import com.hbjs.hrscommon.vo.TransferRecordVO;
import com.hbjs.hrsservice.service.TransferRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "transfer-record", description = "调动记录接口")
@RestController
@RequiredArgsConstructor
public class TransferRecordController {

    private static final String GET_TRANSFER_RECORD_PAGE = "/transfer-record/page";
    private static final String GET_TRANSFER_RECORD_BY_ID = "/transfer-record/{transferRecordId}";
    private static final String ADD_TRANSFER_RECORD = "/transfer-record";
    private static final String UPDATE_TRANSFER_RECORD = "/transfer-record";
    private static final String DELETE_TRANSFER_RECORD = "/transfer-record/{transferRecordId}";
    private static final String BATCH_DELETE_TRANSFER_RECORD = "/transfer-record";
    private static final String RUN_TRANSFER_RECORD = "/transfer-record/run/{transferRecordId}";


    private final TransferRecordService transferRecordService;

    @Operation(summary = "获取调动记录信息（分页）")
    @GetMapping(GET_TRANSFER_RECORD_PAGE)
    public ResultBody<Page<TransferRecordVO>> getTransferRecordPageByStaffId(Page<TransferRecordDTO> page, Long staffId, String keyword) {
        return ResultBody.build(() -> TransferRecordConverter.toVO(transferRecordService.getTransferRecordPage(page, staffId, keyword)));
    }

    @Operation(summary = "获取调动记录（通过调动记录id）")
    @GetMapping(GET_TRANSFER_RECORD_BY_ID)
    public ResultBody<TransferRecordVO> getTransferRecordById(@Parameter(description = "调动记录id", example = "1")  @PathVariable Long transferRecordId) {
        return ResultBody.build(() -> TransferRecordConverter.toVO(transferRecordService.getTransferRecordById(transferRecordId)));
    }

    @Operation(summary = "添加调动记录接口")
    @PostMapping(ADD_TRANSFER_RECORD)
    public ResultBody<Long> addTransferRecord(@RequestBody @Validated({Insert.class}) TransferRecordForm transferRecordForm) {
        return ResultBody.build(() -> transferRecordService.addTransferRecord(TransferRecordConverter.toDTO(transferRecordForm)));
    }

    @Operation(summary = "修改调动记录接口")
    @PutMapping(UPDATE_TRANSFER_RECORD)
    public ResultBody<Long> updateTransferRecord(@RequestBody @Validated({Update.class}) TransferRecordForm transferRecordForm) {
        return ResultBody.build(() -> transferRecordService.updateTransferRecord(TransferRecordConverter.toDTO(transferRecordForm)));
    }

    @Operation(summary = "删除调动记录接口")
    @DeleteMapping(DELETE_TRANSFER_RECORD)
    public ResultBody<Long> deleteTransferRecord(@Parameter(description = "调动记录id", example = "1")  @PathVariable Long transferRecordId) {
        return ResultBody.build(() -> transferRecordService.deleteTransferRecord(transferRecordId));
    }

    @Operation(summary = "批量删除调动记录接口")
    @DeleteMapping(BATCH_DELETE_TRANSFER_RECORD)
    public ResultBody<List<Long>> batchDeleteTransferRecorde(@Parameter(description = "调动记录id", example = "[1, 2]") @RequestBody List<Long> transferRecordIds) {
        return ResultBody.build(() -> transferRecordService.batchDeleteTransferRecorde(transferRecordIds));
    }

    @Operation(summary = "执行调动记录信息")
    @GetMapping(RUN_TRANSFER_RECORD)
    public ResultBody<Long> runTransfer(@Parameter(description = "调动记录id", example = "1")  @PathVariable Long transferRecordId) {
        return ResultBody.build(() -> transferRecordService.runTransfer(transferRecordId));
    }

}
