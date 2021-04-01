package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.FamilyConverter;
import com.hbjs.hrscommon.dto.FamilyDTO;
import com.hbjs.hrscommon.form.FamilyForm;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrscommon.vo.FamilyVO;
import com.hbjs.hrsservice.service.FamilyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "family", description = "家庭成员接口")
@RestController
@RequiredArgsConstructor
public class FamilyController {

    private static final String GET_FAMILY_PAGE = "/family/page";
    private static final String GET_FAMILY_BY_ID = "/family/{familyId}";
    private static final String GET_FAMILY_BY_PID = "/family/pid/{pid}";
    private static final String ADD_FAMILY = "/family";
    private static final String UPDATE_FAMILY = "/family";
    private static final String DELETE_FAMILY = "/family/{familyId}";
    private static final String BATCH_DELETE_FAMILY = "/family";

    private final FamilyService familyService;

    @Operation(summary = "获取家庭成员（分页）")
    @GetMapping(GET_FAMILY_PAGE)
    public ResultBody<Page<FamilyVO>> getFamilyPage(Page<FamilyDTO> page, String keyword) {
        return ResultBody.build(() -> FamilyConverter.toVO(familyService.getFamilyPage(page, keyword)));
    }

    @Operation(summary = "获取家庭成员（通过家庭成员id）")
    @GetMapping(GET_FAMILY_BY_ID)
    public ResultBody<FamilyVO> getFamilyById(@Parameter(description = "家庭成员id", example = "1") @PathVariable("familyId") Long familyId) {
        return ResultBody.build(() -> FamilyConverter.toVO(familyService.getFamilyById(familyId)));
    }

    @Operation(summary = "获取家庭成员（通过家庭成员id）")
    @GetMapping(GET_FAMILY_BY_PID)
    public ResultBody<List<FamilyVO>> getFamilyListByPid(@Parameter(description = "关联ID", example = "1") @PathVariable("pid") Long familyId) {
        return ResultBody.build(() -> FamilyConverter.toVO(familyService.getFamilyListByPid(familyId)));
    }

    @Operation(summary = "添加家庭成员接口")
    @PostMapping(ADD_FAMILY)
    public ResultBody<Long> addFamily(@RequestBody @Validated({Insert.class}) FamilyForm customFamilyForm) {
        return ResultBody.build(() -> familyService.addFamily(FamilyConverter.toDTO(customFamilyForm)));
    }

    @Operation(summary = "更新家庭成员接口")
    @PutMapping(UPDATE_FAMILY)
    public ResultBody<Long> updateFamily(@RequestBody @Validated({Update.class}) FamilyForm customFamilyForm) {
        return ResultBody.build(() -> familyService.updateFamily(FamilyConverter.toDTO(customFamilyForm)));
    }

    @Operation(summary = "删除家庭成员接口")
    @DeleteMapping(DELETE_FAMILY)
    public ResultBody<Long> deleteFamily(@Parameter(description = "家庭成员id", example = "1") @PathVariable("familyId") Long familyId) {
        return ResultBody.build(() -> familyService.deleteFamily(familyId));
    }

    @Operation(summary = "批量删除家庭成员接口")
    @DeleteMapping(BATCH_DELETE_FAMILY)
    public ResultBody<List<Long>> batchDeleteFamily(@Parameter(description = "家庭成员id", example = "[1, 2]") @RequestBody List<Long> familyIds) {
        return ResultBody.build(() -> familyService.batchDeleteFamily(familyIds));
    }

}
