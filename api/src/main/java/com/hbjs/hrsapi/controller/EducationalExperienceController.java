package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.EducationalExperienceConverter;
import com.hbjs.hrscommon.dto.EducationalExperienceDTO;
import com.hbjs.hrscommon.form.EducationalExperienceForm;
import com.hbjs.hrscommon.vo.EducationalExperienceVO;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrsservice.service.EducationalExperienceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "educationalExperience", description = "教育经历接口")
@RestController
@RequiredArgsConstructor
public class EducationalExperienceController {

    private static final String GET_EDUCATIONALEXPERIENCE_PAGE = "/educationa-experience/page";
    private static final String GET_EDUCATIONALEXPERIENCE_BY_ID = "/educationa-experience/{educationalExperienceId}";
    private static final String GET_EDUCATIONALEXPERIENCE_BY_PID = "/educationa-experience/pid/{pid}";
    private static final String ADD_EDUCATIONALEXPERIENCE = "/educationa-experience";
    private static final String UPDATE_EDUCATIONALEXPERIENCE = "/educationa-experience";
    private static final String DELETE_EDUCATIONALEXPERIENCE = "/educationa-experience/{educationalExperienceId}";
    private static final String BATCH_DELETE_EDUCATIONALEXPERIENCE = "/educationa-experience";

    private final EducationalExperienceService educationalExperienceService;

    @Operation(summary = "获取教育经历（分页）")
    @GetMapping(GET_EDUCATIONALEXPERIENCE_PAGE)
    public ResultBody<Page<EducationalExperienceVO>> getEducationalExperiencePage(Page<EducationalExperienceDTO> page, String keyword) {
        return ResultBody.build(() -> EducationalExperienceConverter.toVO(educationalExperienceService.getEducationalExperiencePage(page, keyword)));
    }

    @Operation(summary = "获取教育经历（通过教育经历id）")
    @GetMapping(GET_EDUCATIONALEXPERIENCE_BY_ID)
    public ResultBody<EducationalExperienceVO> getEducationalExperienceById(@Parameter(description = "教育经历id", example = "1") @PathVariable("educationalExperienceId") Long educationalExperienceId) {
        return ResultBody.build(() -> EducationalExperienceConverter.toVO(educationalExperienceService.getEducationalExperienceById(educationalExperienceId)));
    }

    @Operation(summary = "获取教育经历（通过教育经历id）")
    @GetMapping(GET_EDUCATIONALEXPERIENCE_BY_PID)
    public ResultBody<List<EducationalExperienceVO>> getEducationalExperienceListByPid(@Parameter(description = "关联ID", example = "1") @PathVariable("pid") Long educationalExperienceId) {
        return ResultBody.build(() -> EducationalExperienceConverter.toVO(educationalExperienceService.getEducationalExperienceListByPid(educationalExperienceId)));
    }

    @Operation(summary = "添加教育经历接口")
    @PostMapping(ADD_EDUCATIONALEXPERIENCE)
    public ResultBody<Long> addEducationalExperience(@RequestBody @Validated({Insert.class}) EducationalExperienceForm customEducationalExperienceForm) {
        return ResultBody.build(() -> educationalExperienceService.addEducationalExperience(EducationalExperienceConverter.toDTO(customEducationalExperienceForm)));
    }

    @Operation(summary = "更新教育经历接口")
    @PutMapping(UPDATE_EDUCATIONALEXPERIENCE)
    public ResultBody<Long> updateEducationalExperience(@RequestBody @Validated({Update.class}) EducationalExperienceForm customEducationalExperienceForm) {
        return ResultBody.build(() -> educationalExperienceService.updateEducationalExperience(EducationalExperienceConverter.toDTO(customEducationalExperienceForm)));
    }

    @Operation(summary = "删除教育经历接口")
    @DeleteMapping(DELETE_EDUCATIONALEXPERIENCE)
    public ResultBody<Long> deleteEducationalExperience(@Parameter(description = "教育经历id", example = "1") @PathVariable("educationalExperienceId") Long educationalExperienceId) {
        return ResultBody.build(() -> educationalExperienceService.deleteEducationalExperience(educationalExperienceId));
    }

    @Operation(summary = "批量删除教育经历接口")
    @DeleteMapping(BATCH_DELETE_EDUCATIONALEXPERIENCE)
    public ResultBody<List<Long>> batchDeleteEducationalExperience(@Parameter(description = "教育经历id", example = "[1, 2]") @RequestBody List<Long> educationalExperienceIds) {
        return ResultBody.build(() -> educationalExperienceService.batchDeleteEducationalExperience(educationalExperienceIds));
    }

}
