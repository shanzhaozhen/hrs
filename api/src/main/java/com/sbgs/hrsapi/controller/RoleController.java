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

    @Operation(summary = "获取角色信息（分页）")
    @GetMapping(GET_ROLE_PAGE)
    public ResultBody<Page<RoleVO>> getRolePage(Page<RoleDTO> page, String keyword) {
        return ResultBody.build(() -> RoleConverter.toVO(roleService.getRolePage(page, keyword)));
    }

    @Operation(summary = "获取所有角色")
    @GetMapping(GET_ROLE_ALL)
    public ResultBody<List<RoleVO>> getAllRoles() {
        return ResultBody.build(() -> RoleConverter.toVO(roleService.getAllRoles()));
    }

    @Operation(summary = "获取角色信息（通过角色id）")
    @GetMapping(GET_ROLE_BY_ID)
    public ResultBody<RoleVO> getRoleByRoleId(@PathVariable("roleId") @Parameter(description = "角色id", example = "1") Long roleId) {
        return ResultBody.build(() -> RoleConverter.toVO(roleService.getRoleDetailById(roleId)));
    }

    @Operation(summary = "添加角色接口")
    @PostMapping(ADD_ROLE)
    public ResultBody<Long> addRole(@RequestBody @Validated({Insert.class}) RoleForm roleForm) {
        return ResultBody.build(() -> roleService.addRole(RoleConverter.toDTO(roleForm)));
    }

    @Operation(summary = "更新角色接口")
    @PutMapping(UPDATE_ROLE)
    public ResultBody<Long> updateRole(@RequestBody @Validated({Update.class}) RoleForm roleForm) {
        return ResultBody.build(() -> roleService.updateRole(RoleConverter.toDTO(roleForm)));
    }

    @Operation(summary = "删除角色接口")
    @DeleteMapping(DELETE_ROLE)
    public ResultBody<List<Long>> deleteRole(@Parameter(description = "角色id", example = "[1, 2]") List<Long> roleIds) {
        return ResultBody.build(() -> roleService.deleteRoles(roleIds));
    }

}
