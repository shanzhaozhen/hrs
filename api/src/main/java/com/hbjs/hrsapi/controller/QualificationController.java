package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.QualificationConverter;
import com.hbjs.hrscommon.dto.QualificationDTO;
import com.hbjs.hrscommon.form.QualificationForm;
import com.hbjs.hrscommon.vo.QualificationVO;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrsservice.service.QualificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "qualification", description = "家庭成员接口")
@RestController
@RequiredArgsConstructor
public class QualificationController {

    private static final String GET_QUALIFICATION_PAGE = "/qualification/page";
    private static final String GET_QUALIFICATION_BY_ID = "/qualification/{qualificationId}";
    private static final String GET_QUALIFICATION_BY_PID = "/qualification/pid/{pid}";
    private static final String ADD_QUALIFICATION = "/qualification";
    private static final String UPDATE_QUALIFICATION = "/qualification";
    private static final String DELETE_QUALIFICATION = "/qualification/{qualificationId}";
    private static final String BATCH_DELETE_QUALIFICATION = "/qualification";

    private final QualificationService qualificationService;

    @Operation(summary = "获取家庭成员（分页）")
    @GetMapping(GET_QUALIFICATION_PAGE)
    public ResultBody<Page<QualificationVO>> getQualificationPage(Page<QualificationDTO> page, String keyword) {
        return ResultBody.build(() -> QualificationConverter.toVO(qualificationService.getQualificationPage(page, keyword)));
    }

    @Operation(summary = "获取家庭成员（通过家庭成员id）")
    @GetMapping(GET_QUALIFICATION_BY_ID)
    public ResultBody<QualificationVO> getQualificationById(@Parameter(description = "家庭成员id", example = "1") @PathVariable("qualificationId") Long qualificationId) {
        return ResultBody.build(() -> QualificationConverter.toVO(qualificationService.getQualificationById(qualificationId)));
    }

    @Operation(summary = "获取家庭成员（通过家庭成员id）")
    @GetMapping(GET_QUALIFICATION_BY_PID)
    public ResultBody<List<QualificationVO>> getQualificationListByStaffId(@Parameter(description = "关联ID", example = "1") @PathVariable("pid") Long qualificationId) {
        return ResultBody.build(() -> QualificationConverter.toVO(qualificationService.getQualificationListByStaffId(qualificationId)));
    }

    @Operation(summary = "添加家庭成员接口")
    @PostMapping(ADD_QUALIFICATION)
    public ResultBody<Long> addQualification(@RequestBody @Validated({Insert.class}) QualificationForm customQualificationForm) {
        return ResultBody.build(() -> qualificationService.addQualification(QualificationConverter.toDTO(customQualificationForm)));
    }

    @Operation(summary = "更新家庭成员接口")
    @PutMapping(UPDATE_QUALIFICATION)
    public ResultBody<Long> updateQualification(@RequestBody @Validated({Update.class}) QualificationForm customQualificationForm) {
        return ResultBody.build(() -> qualificationService.updateQualification(QualificationConverter.toDTO(customQualificationForm)));
    }

    @Operation(summary = "删除家庭成员接口")
    @DeleteMapping(DELETE_QUALIFICATION)
    public ResultBody<Long> deleteQualification(@Parameter(description = "家庭成员id", example = "1") @PathVariable("qualificationId") Long qualificationId) {
        return ResultBody.build(() -> qualificationService.deleteQualification(qualificationId));
    }

    @Operation(summary = "批量删除家庭成员接口")
    @DeleteMapping(BATCH_DELETE_QUALIFICATION)
    public ResultBody<List<Long>> batchDeleteQualification(@Parameter(description = "家庭成员id", example = "[1, 2]") @RequestBody List<Long> qualificationIds) {
        return ResultBody.build(() -> qualificationService.batchDeleteQualification(qualificationIds));
    }

}
