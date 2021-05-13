package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.ResumeConverter;
import com.hbjs.hrscommon.dto.ResumeDTO;
import com.hbjs.hrscommon.form.ResumeForm;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrscommon.vo.ResumeVO;
import com.hbjs.hrsservice.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "resume", description = "简历接口")
@RestController
@RequiredArgsConstructor
public class ResumeController {

    private static final String GET_RESUME_PAGE = "/resume/page";
    private static final String GET_RESUME_BY_ID = "/resume/{resumeId}";
    private static final String ADD_RESUME = "/resume";
    private static final String UPDATE_RESUME = "/resume";
    private static final String DELETE_RESUME = "/resume/{resumeId}";
    private static final String BATCH_DELETE_RESUME = "/resume";
    private static final String EXPORT_RESUME = "/resume/export";
    private static final String PRINT_RESUME = "/resume/print";

    private final ResumeService resumeService;

    @Operation(summary = "获取简历（分页）")
    @GetMapping(GET_RESUME_PAGE)
    public ResultBody<Page<ResumeVO>> getResumePage(Page<ResumeDTO> page, String keyword) {
        return ResultBody.build(() -> ResumeConverter.toVO(resumeService.getResumePage(page, keyword)));
    }

    @Operation(summary = "获取简历（通过简历id）")
    @GetMapping(GET_RESUME_BY_ID)
    public ResultBody<ResumeVO> getResumeById(@Parameter(description = "简历id", example = "1") @PathVariable("resumeId") Long resumeId) {
        return ResultBody.build(() -> ResumeConverter.toVO(resumeService.getResumeById(resumeId)));
    }

    @Operation(summary = "添加简历接口")
    @PostMapping(ADD_RESUME)
    public ResultBody<Long> addResume(@RequestBody @Validated({Insert.class}) ResumeForm customResumeForm) {
        return ResultBody.build(() -> resumeService.addResume(ResumeConverter.toDTO(customResumeForm)));
    }

    @Operation(summary = "更新简历接口")
    @PutMapping(UPDATE_RESUME)
    public ResultBody<Long> updateResume(@RequestBody @Validated({Update.class}) ResumeForm customResumeForm) {
        return ResultBody.build(() -> resumeService.updateResume(ResumeConverter.toDTO(customResumeForm)));
    }

    @Operation(summary = "删除简历接口")
    @DeleteMapping(DELETE_RESUME)
    public ResultBody<Long> deleteResume(@Parameter(description = "简历id", example = "1") @PathVariable("resumeId") Long resumeId) {
        return ResultBody.build(() -> resumeService.deleteResume(resumeId));
    }

    @Operation(summary = "批量删除简历接口")
    @DeleteMapping(BATCH_DELETE_RESUME)
    public ResultBody<List<Long>> batchDeleteResume(@Parameter(description = "简历id", example = "[1, 2]") @RequestBody List<Long> resumeIds) {
        return ResultBody.build(() -> resumeService.batchDeleteResume(resumeIds));
    }

    @Operation(summary = "导出简历信息")
    @GetMapping(EXPORT_RESUME)
    public void exportResume(String keyword) {
        resumeService.exportResume(keyword);
    }

    @Operation(summary = "打印简历信息")
    @GetMapping(PRINT_RESUME)
    public void printResume(Long resumeId) {
        resumeService.printResume(resumeId);
    }

}
