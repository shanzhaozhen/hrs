package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.DriverLicenseConverter;
import com.hbjs.hrscommon.dto.DriverLicenseDTO;
import com.hbjs.hrscommon.form.DriverLicenseForm;
import com.hbjs.hrscommon.vo.DriverLicenseVO;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrsservice.service.DriverLicenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "driver-license", description = "驾驶证信息接口")
@RestController
@RequiredArgsConstructor
public class DriverLicenseController {

    private static final String GET_DRIVER_LICENSE_PAGE = "/driver-license/page";
    private static final String GET_DRIVER_LICENSE_BY_ID = "/driver-license/{driverLicenseId}";
    private static final String GET_DRIVER_LICENSE_BY_PID = "/driver-license/pid/{pid}";
    private static final String ADD_DRIVER_LICENSE = "/driver-license";
    private static final String UPDATE_DRIVER_LICENSE = "/driver-license";
    private static final String DELETE_DRIVER_LICENSE = "/driver-license/{driverLicenseId}";
    private static final String BATCH_DELETE_DRIVER_LICENSE = "/driver-license";

    private final DriverLicenseService driverLicenseService;

    @Operation(summary = "获取驾驶证信息（分页）")
    @GetMapping(GET_DRIVER_LICENSE_PAGE)
    public ResultBody<Page<DriverLicenseVO>> getDriverLicensePage(Page<DriverLicenseDTO> page, String keyword) {
        return ResultBody.build(() -> DriverLicenseConverter.toVO(driverLicenseService.getDriverLicensePage(page, keyword)));
    }

    @Operation(summary = "获取驾驶证信息（通过驾驶证信息id）")
    @GetMapping(GET_DRIVER_LICENSE_BY_ID)
    public ResultBody<DriverLicenseVO> getDriverLicenseById(@Parameter(description = "驾驶证信息id", example = "1") @PathVariable("driverLicenseId") Long driverLicenseId) {
        return ResultBody.build(() -> DriverLicenseConverter.toVO(driverLicenseService.getDriverLicenseById(driverLicenseId)));
    }

    @Operation(summary = "获取驾驶证信息（通过驾驶证信息id）")
    @GetMapping(GET_DRIVER_LICENSE_BY_PID)
    public ResultBody<List<DriverLicenseVO>> getDriverLicenseListByPid(@Parameter(description = "关联ID", example = "1") @PathVariable("pid") Long driverLicenseId) {
        return ResultBody.build(() -> DriverLicenseConverter.toVO(driverLicenseService.getDriverLicenseListByPid(driverLicenseId)));
    }

    @Operation(summary = "添加驾驶证信息接口")
    @PostMapping(ADD_DRIVER_LICENSE)
    public ResultBody<Long> addDriverLicense(@RequestBody @Validated({Insert.class}) DriverLicenseForm customDriverLicenseForm) {
        return ResultBody.build(() -> driverLicenseService.addDriverLicense(DriverLicenseConverter.toDTO(customDriverLicenseForm)));
    }

    @Operation(summary = "更新驾驶证信息接口")
    @PutMapping(UPDATE_DRIVER_LICENSE)
    public ResultBody<Long> updateDriverLicense(@RequestBody @Validated({Update.class}) DriverLicenseForm customDriverLicenseForm) {
        return ResultBody.build(() -> driverLicenseService.updateDriverLicense(DriverLicenseConverter.toDTO(customDriverLicenseForm)));
    }

    @Operation(summary = "删除驾驶证信息接口")
    @DeleteMapping(DELETE_DRIVER_LICENSE)
    public ResultBody<Long> deleteDriverLicense(@Parameter(description = "驾驶证信息id", example = "1") @PathVariable("driverLicenseId") Long driverLicenseId) {
        return ResultBody.build(() -> driverLicenseService.deleteDriverLicense(driverLicenseId));
    }

    @Operation(summary = "批量删除驾驶证信息接口")
    @DeleteMapping(BATCH_DELETE_DRIVER_LICENSE)
    public ResultBody<List<Long>> batchDeleteDriverLicense(@Parameter(description = "驾驶证信息id", example = "[1, 2]") @RequestBody List<Long> driverLicenseIds) {
        return ResultBody.build(() -> driverLicenseService.batchDeleteDriverLicense(driverLicenseIds));
    }

}
