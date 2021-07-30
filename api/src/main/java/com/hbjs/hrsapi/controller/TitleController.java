package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.TitleConverter;
import com.hbjs.hrscommon.dto.TitleDTO;
import com.hbjs.hrscommon.form.TitleForm;
import com.hbjs.hrscommon.vo.TitleVO;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrsservice.service.TitleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "title", description = "职称信息接口")
@RestController
@RequiredArgsConstructor
public class TitleController {

    private static final String GET_TITLE_PAGE = "/title/page";
    private static final String GET_TITLE_BY_ID = "/title/{titleId}";
    private static final String GET_TITLE_BY_PID = "/title/pid/{pid}";
    private static final String ADD_TITLE = "/title";
    private static final String UPDATE_TITLE = "/title";
    private static final String DELETE_TITLE = "/title/{titleId}";
    private static final String BATCH_DELETE_TITLE = "/title";

    private final TitleService titleService;

    @Operation(summary = "获取职称信息（分页）")
    @GetMapping(GET_TITLE_PAGE)
    public ResultBody<Page<TitleVO>> getTitlePage(Page<TitleDTO> page, String keyword) {
        return ResultBody.build(() -> TitleConverter.toVO(titleService.getTitlePage(page, keyword)));
    }

    @Operation(summary = "获取职称信息（通过职称信息id）")
    @GetMapping(GET_TITLE_BY_ID)
    public ResultBody<TitleVO> getTitleById(@Parameter(description = "职称信息id", example = "1") @PathVariable("titleId") Long titleId) {
        return ResultBody.build(() -> TitleConverter.toVO(titleService.getTitleById(titleId)));
    }

    @Operation(summary = "获取职称信息（通过职称信息id）")
    @GetMapping(GET_TITLE_BY_PID)
    public ResultBody<List<TitleVO>> getTitleListByStaffId(@Parameter(description = "关联ID", example = "1") @PathVariable("pid") Long titleId) {
        return ResultBody.build(() -> TitleConverter.toVO(titleService.getTitleListByStaffId(titleId)));
    }

    @Operation(summary = "添加职称信息接口")
    @PostMapping(ADD_TITLE)
    public ResultBody<Long> addTitle(@RequestBody @Validated({Insert.class}) TitleForm customTitleForm) {
        return ResultBody.build(() -> titleService.addTitle(TitleConverter.toDTO(customTitleForm)));
    }

    @Operation(summary = "更新职称信息接口")
    @PutMapping(UPDATE_TITLE)
    public ResultBody<Long> updateTitle(@RequestBody @Validated({Update.class}) TitleForm customTitleForm) {
        return ResultBody.build(() -> titleService.updateTitle(TitleConverter.toDTO(customTitleForm)));
    }

    @Operation(summary = "删除职称信息接口")
    @DeleteMapping(DELETE_TITLE)
    public ResultBody<Long> deleteTitle(@Parameter(description = "职称信息id", example = "1") @PathVariable("titleId") Long titleId) {
        return ResultBody.build(() -> titleService.deleteTitle(titleId));
    }

    @Operation(summary = "批量删除职称信息接口")
    @DeleteMapping(BATCH_DELETE_TITLE)
    public ResultBody<List<Long>> batchDeleteTitle(@Parameter(description = "职称信息id", example = "[1, 2]") @RequestBody List<Long> titleIds) {
        return ResultBody.build(() -> titleService.batchDeleteTitle(titleIds));
    }

}
