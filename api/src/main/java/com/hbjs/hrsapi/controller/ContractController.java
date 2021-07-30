package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.ContractConverter;
import com.hbjs.hrscommon.dto.ContractDTO;
import com.hbjs.hrscommon.form.ContractForm;
import com.hbjs.hrscommon.vo.ContractVO;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrsservice.service.ContractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "contract", description = "合同信息接口")
@RestController
@RequiredArgsConstructor
public class ContractController {

    private static final String GET_CONTRACT_PAGE = "/contract/page";
    private static final String GET_CONTRACT_BY_ID = "/contract/{contractId}";
    private static final String GET_CONTRACT_BY_PID = "/contract/pid/{pid}";
    private static final String ADD_CONTRACT = "/contract";
    private static final String UPDATE_CONTRACT = "/contract";
    private static final String DELETE_CONTRACT = "/contract/{contractId}";
    private static final String BATCH_DELETE_CONTRACT = "/contract";

    private final ContractService contractService;

    @Operation(summary = "获取合同信息（分页）")
    @GetMapping(GET_CONTRACT_PAGE)
    public ResultBody<Page<ContractVO>> getContractPage(Page<ContractDTO> page, String keyword) {
        return ResultBody.build(() -> ContractConverter.toVO(contractService.getContractPage(page, keyword)));
    }

    @Operation(summary = "获取合同信息（通过合同信息id）")
    @GetMapping(GET_CONTRACT_BY_ID)
    public ResultBody<ContractVO> getContractById(@Parameter(description = "合同信息id", example = "1") @PathVariable("contractId") Long contractId) {
        return ResultBody.build(() -> ContractConverter.toVO(contractService.getContractById(contractId)));
    }

    @Operation(summary = "获取合同信息（通过合同信息id）")
    @GetMapping(GET_CONTRACT_BY_PID)
    public ResultBody<List<ContractVO>> getContractListByStaffId(@Parameter(description = "关联ID", example = "1") @PathVariable("pid") Long contractId) {
        return ResultBody.build(() -> ContractConverter.toVO(contractService.getContractListByStaffId(contractId)));
    }

    @Operation(summary = "添加合同信息接口")
    @PostMapping(ADD_CONTRACT)
    public ResultBody<Long> addContract(@RequestBody @Validated({Insert.class}) ContractForm customContractForm) {
        return ResultBody.build(() -> contractService.addContract(ContractConverter.toDTO(customContractForm)));
    }

    @Operation(summary = "更新合同信息接口")
    @PutMapping(UPDATE_CONTRACT)
    public ResultBody<Long> updateContract(@RequestBody @Validated({Update.class}) ContractForm customContractForm) {
        return ResultBody.build(() -> contractService.updateContract(ContractConverter.toDTO(customContractForm)));
    }

    @Operation(summary = "删除合同信息接口")
    @DeleteMapping(DELETE_CONTRACT)
    public ResultBody<Long> deleteContract(@Parameter(description = "合同信息id", example = "1") @PathVariable("contractId") Long contractId) {
        return ResultBody.build(() -> contractService.deleteContract(contractId));
    }

    @Operation(summary = "批量删除合同信息接口")
    @DeleteMapping(BATCH_DELETE_CONTRACT)
    public ResultBody<List<Long>> batchDeleteContract(@Parameter(description = "合同信息id", example = "[1, 2]") @RequestBody List<Long> contractIds) {
        return ResultBody.build(() -> contractService.batchDeleteContract(contractIds));
    }

}
