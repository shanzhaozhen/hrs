package com.sbgs.hrsapi.controller;

import com.sbgs.hrscommon.converter.DepartmentConverter;
import com.sbgs.hrscommon.form.DepartmentForm;
import com.sbgs.hrscommon.vo.ResultBody;
import com.sbgs.hrscommon.vo.DepartmentVO;
import com.sbgs.hrsservice.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "department", description = "部门接口")
@RestController
@RequiredArgsConstructor
public class DepartmentController {

    private static final String GET_DEPARTMENT_ALL = "/department/all";
    private static final String GET_DEPARTMENT_TREE = "/department/tree";
    private static final String GET_DEPARTMENT_BY_ID = "/department/{departmentId}";
    private static final String ADD_DEPARTMENT = "/department";
    private static final String UPDATE_DEPARTMENT = "/department";
    private static final String DELETE_DEPARTMENT = "/department";

    private final DepartmentService departmentService;

    @Operation(summary = "获取所有部门")
    @GetMapping(GET_DEPARTMENT_ALL)
    public ResultBody<List<DepartmentVO>> getAllDepartments() {
        return ResultBody.build(() -> DepartmentConverter.toVO(departmentService.getAllDepartments()));
    }

    @Operation(summary = "获取所有部门（树状）")
    @GetMapping(GET_DEPARTMENT_TREE)
    public ResultBody<List<DepartmentVO>> getDepartmentTree() {
        return ResultBody.build(() -> DepartmentConverter.toVO(departmentService.getDepartmentTree()));
    }

    @Operation(summary = "获取部门信息（通过部门id）")
    @GetMapping(GET_DEPARTMENT_BY_ID)
    public ResultBody<DepartmentVO> getDepartmentByDepartmentId(@PathVariable("departmentId") @Parameter(description = "部门id", example = "1") Long departmentId) {
        return ResultBody.build(() -> DepartmentConverter.toVO(departmentService.getDepartmentById(departmentId)));
    }

    @Operation(summary = "添加部门接口")
    @PostMapping(ADD_DEPARTMENT)
    public ResultBody<Long> addDepartment(@RequestBody @Validated({Insert.class}) DepartmentForm departmentForm) {
        return ResultBody.build(() -> departmentService.addDepartment(DepartmentConverter.toDTO(departmentForm)));
    }

    @Operation(summary = "更新部门接口")
    @PutMapping(UPDATE_DEPARTMENT)
    public ResultBody<Long> updateDepartment(@RequestBody @Validated({Update.class}) DepartmentForm departmentForm) {
        return ResultBody.build(() -> departmentService.updateDepartment(DepartmentConverter.toDTO(departmentForm)));
    }

    @Operation(summary = "删除部门接口")
    @DeleteMapping(DELETE_DEPARTMENT)
    public ResultBody<List<Long>> deleteDepartments(@Parameter(description = "部门id", example = "[1, 2]") List<Long> departmentIds) {
        return ResultBody.build(() -> departmentService.deleteDepartments(departmentIds));
    }

}
