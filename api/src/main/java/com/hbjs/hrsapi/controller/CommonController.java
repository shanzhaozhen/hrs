package com.hbjs.hrsapi.controller;

import com.hbjs.hrscommon.converter.RegionConverter;
import com.hbjs.hrscommon.vo.RegionVO;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrsservice.service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "common", description = "Common接口")
@RestController
@RequiredArgsConstructor
public class CommonController {

    private final RegionService regionService;

    private static final String GET_RESUME_OPTIONS_INFO = "/common/resume/options";

    @Operation(summary = "获取所有区域信息（树状）")
    @GetMapping(GET_RESUME_OPTIONS_INFO)
    public ResultBody<List<RegionVO>> getResumeOptionsInfo() {
        return ResultBody.build(() -> RegionConverter.toVO(regionService.getRegionTree()));
    }

}
