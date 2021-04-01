package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.WorkExperienceConverter;
import com.hbjs.hrscommon.dto.WorkExperienceDTO;
import com.hbjs.hrscommon.form.WorkExperienceForm;
import com.hbjs.hrscommon.vo.WorkExperienceVO;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrsservice.service.WorkExperienceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "workExperience", description = "工作履历接口")
@RestController
@RequiredArgsConstructor
public class WorkExperienceController {

    private static final String GET_WORK_EXPERIENCE_PAGE = "/work-experience/page";
    private static final String GET_WORK_EXPERIENCE_BY_ID = "/work-experience/{workExperienceId}";
    private static final String GET_WORK_EXPERIENCE_BY_PID = "/work-experience/pid/{pid}";
    private static final String ADD_WORK_EXPERIENCE = "/work-experience";
    private static final String UPDATE_WORK_EXPERIENCE = "/work-experience";
    private static final String DELETE_WORK_EXPERIENCE = "/work-experience/{workExperienceId}";
    private static final String BATCH_DELETE_WORK_EXPERIENCE = "/work-experience";

    private final WorkExperienceService workExperienceService;

    @Operation(summary = "获取工作履历（分页）")
    @GetMapping(GET_WORK_EXPERIENCE_PAGE)
    public ResultBody<Page<WorkExperienceVO>> getWorkExperiencePage(Page<WorkExperienceDTO> page, String keyword) {
        return ResultBody.build(() -> WorkExperienceConverter.toVO(workExperienceService.getWorkExperiencePage(page, keyword)));
    }

    @Operation(summary = "获取工作履历（通过工作履历id）")
    @GetMapping(GET_WORK_EXPERIENCE_BY_ID)
    public ResultBody<WorkExperienceVO> getWorkExperienceById(@Parameter(description = "工作履历id", example = "1") @PathVariable("workExperienceId") Long workExperienceId) {
        return ResultBody.build(() -> WorkExperienceConverter.toVO(workExperienceService.getWorkExperienceById(workExperienceId)));
    }

    @Operation(summary = "获取工作履历（通过工作履历id）")
    @GetMapping(GET_WORK_EXPERIENCE_BY_PID)
    public ResultBody<List<WorkExperienceVO>> getWorkExperienceListByPid(@Parameter(description = "关联ID", example = "1") @PathVariable("pid") Long workExperienceId) {
        return ResultBody.build(() -> WorkExperienceConverter.toVO(workExperienceService.getWorkExperienceListByPid(workExperienceId)));
    }

    @Operation(summary = "添加工作履历接口")
    @PostMapping(ADD_WORK_EXPERIENCE)
    public ResultBody<Long> addWorkExperience(@RequestBody @Validated({Insert.class}) WorkExperienceForm customWorkExperienceForm) {
        return ResultBody.build(() -> workExperienceService.addWorkExperience(WorkExperienceConverter.toDTO(customWorkExperienceForm)));
    }

    @Operation(summary = "更新工作履历接口")
    @PutMapping(UPDATE_WORK_EXPERIENCE)
    public ResultBody<Long> updateWorkExperience(@RequestBody @Validated({Update.class}) WorkExperienceForm customWorkExperienceForm) {
        return ResultBody.build(() -> workExperienceService.updateWorkExperience(WorkExperienceConverter.toDTO(customWorkExperienceForm)));
    }

    @Operation(summary = "删除工作履历接口")
    @DeleteMapping(DELETE_WORK_EXPERIENCE)
    public ResultBody<Long> deleteWorkExperience(@Parameter(description = "工作履历id", example = "1") @PathVariable("workExperienceId") Long workExperienceId) {
        return ResultBody.build(() -> workExperienceService.deleteWorkExperience(workExperienceId));
    }

    @Operation(summary = "批量删除工作履历接口")
    @DeleteMapping(BATCH_DELETE_WORK_EXPERIENCE)
    public ResultBody<List<Long>> batchDeleteWorkExperience(@Parameter(description = "工作履历id", example = "[1, 2]") @RequestBody List<Long> workExperienceIds) {
        return ResultBody.build(() -> workExperienceService.batchDeleteWorkExperience(workExperienceIds));
    }

}
