package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.CertificateConverter;
import com.hbjs.hrscommon.dto.CertificateDTO;
import com.hbjs.hrscommon.form.CertificateForm;
import com.hbjs.hrscommon.vo.CertificateVO;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrsservice.service.CertificateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "certificate", description = "证书接口")
@RestController
@RequiredArgsConstructor
public class CertificateController {

    private static final String GET_CERTIFICATE_PAGE = "/certificate/page";
    private static final String GET_CERTIFICATE_BY_ID = "/certificate/{certificateId}";
    private static final String GET_CERTIFICATE_BY_PID = "/certificate/pid/{pid}";
    private static final String ADD_CERTIFICATE = "/certificate";
    private static final String UPDATE_CERTIFICATE = "/certificate";
    private static final String DELETE_CERTIFICATE = "/certificate/{certificateId}";
    private static final String BATCH_DELETE_CERTIFICATE = "/certificate";

    private final CertificateService certificateService;

    @Operation(summary = "获取证书（分页）")
    @GetMapping(GET_CERTIFICATE_PAGE)
    public ResultBody<Page<CertificateVO>> getCertificatePage(Page<CertificateDTO> page, String keyword) {
        return ResultBody.build(() -> CertificateConverter.toVO(certificateService.getCertificatePage(page, keyword)));
    }

    @Operation(summary = "获取证书（通过证书id）")
    @GetMapping(GET_CERTIFICATE_BY_ID)
    public ResultBody<CertificateVO> getCertificateById(@Parameter(description = "证书id", example = "1") @PathVariable("certificateId") Long certificateId) {
        return ResultBody.build(() -> CertificateConverter.toVO(certificateService.getCertificateById(certificateId)));
    }

    @Operation(summary = "获取证书（通过证书id）")
    @GetMapping(GET_CERTIFICATE_BY_PID)
    public ResultBody<List<CertificateVO>> getCertificateListByPid(@Parameter(description = "关联ID", example = "1") @PathVariable("pid") Long certificateId) {
        return ResultBody.build(() -> CertificateConverter.toVO(certificateService.getCertificateListByPid(certificateId)));
    }

    @Operation(summary = "添加证书接口")
    @PostMapping(ADD_CERTIFICATE)
    public ResultBody<Long> addCertificate(@RequestBody @Validated({Insert.class}) CertificateForm customCertificateForm) {
        return ResultBody.build(() -> certificateService.addCertificate(CertificateConverter.toDTO(customCertificateForm)));
    }

    @Operation(summary = "更新证书接口")
    @PutMapping(UPDATE_CERTIFICATE)
    public ResultBody<Long> updateCertificate(@RequestBody @Validated({Update.class}) CertificateForm customCertificateForm) {
        return ResultBody.build(() -> certificateService.updateCertificate(CertificateConverter.toDTO(customCertificateForm)));
    }

    @Operation(summary = "删除证书接口")
    @DeleteMapping(DELETE_CERTIFICATE)
    public ResultBody<Long> deleteCertificate(@Parameter(description = "证书id", example = "1") @PathVariable("certificateId") Long certificateId) {
        return ResultBody.build(() -> certificateService.deleteCertificate(certificateId));
    }

    @Operation(summary = "批量删除证书接口")
    @DeleteMapping(BATCH_DELETE_CERTIFICATE)
    public ResultBody<List<Long>> batchDeleteCertificate(@Parameter(description = "证书id", example = "[1, 2]") @RequestBody List<Long> certificateIds) {
        return ResultBody.build(() -> certificateService.batchDeleteCertificate(certificateIds));
    }

}
