package com.sbgs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import com.sbgs.hrscommon.converter.RoleConverter;
import com.sbgs.hrscommon.dto.RoleDTO;
import com.sbgs.hrscommon.form.BaseSearchForm;
import com.sbgs.hrscommon.form.RoleForm;
import com.sbgs.hrscommon.vo.ResultBody;
import com.sbgs.hrscommon.vo.RoleVO;
import com.sbgs.hrsservice.service.RoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "role", description = "用户角色接口")
@RestController
@RequiredArgsConstructor
public class RoleController {

    private static final String GET_ROLE_PAGE = "/role/page";
    private static final String GET_ROLE_ALL = "/role/all";
    private static final String GET_ROLE_BY_ID = "/role/{roleId}";
    private static final String ADD_ROLE = "/role";
    private static final String UPDATE_ROLE = "/role";
    private static final String DELETE_ROLE = "/role";

    private final RoleService roleService;

    @PostMapping(GET_ROLE_PAGE)
    @Operation(summary = "获取角色信息（分页）")
    public ResultBody<Page<RoleVO>> getRolePage(@RequestBody BaseSearchForm<RoleDTO> baseSearchForm) {
        return ResultBody.build(result -> RoleConverter.toVO(roleService.getRolePage(baseSearchForm)));
    }

    @GetMapping(GET_ROLE_ALL)
    @Operation(summary = "获取所有角色")
    public ResultBody<List<RoleVO>> getAllRoles() {
        return ResultBody.build(result -> RoleConverter.toVO(roleService.getAllRoles()));
    }

    @GetMapping(GET_ROLE_BY_ID)
    @Operation(summary = "获取角色信息（通过角色id）")
    public ResultBody<RoleVO> getRoleByRoleId(@PathVariable("roleId") @Parameter(description = "角色id", example = "1") Long roleId) {
        return ResultBody.build(result -> RoleConverter.toVO(roleService.getRoleById(roleId)));
    }

    @PostMapping(ADD_ROLE)
    @Operation(summary = "添加角色接口")
    public ResultBody<Long> addRole(@RequestBody @Validated({Insert.class}) RoleForm roleForm) {
        return ResultBody.build(result -> roleService.addRole(RoleConverter.toDTO(roleForm)));
    }

    @PutMapping(UPDATE_ROLE)
    @Operation(summary = "更新角色接口")
    public ResultBody<Long> updateRole(@RequestBody @Validated({Update.class}) RoleForm roleForm) {
        return ResultBody.build(result -> roleService.updateRole(RoleConverter.toDTO(roleForm)));
    }

    @DeleteMapping(DELETE_ROLE)
    @Operation(summary = "删除角色接口")
    public ResultBody<Long[]> deleteRole(@Parameter(description = "角色id", example = "[1, 2]") Long[] roleIds) {
        return ResultBody.build(result -> roleService.deleteRoles(roleIds));
    }

}
