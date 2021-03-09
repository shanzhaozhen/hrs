package com.sbgs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbgs.hrscommon.converter.RoleConverter;
import com.sbgs.hrscommon.domain.sys.RoleDO;
import com.sbgs.hrscommon.domain.sys.RoleResourceDO;
import com.sbgs.hrscommon.domain.sys.RoleMenuDO;
import com.sbgs.hrscommon.dto.RoleDTO;
import com.sbgs.hrscommon.form.BaseSearchForm;
import com.sbgs.hrscommon.utils.CustomBeanUtils;
import com.sbgs.hrsrepo.mapper.RoleMapper;
import com.sbgs.hrsrepo.mapper.RoleResourceMapper;
import com.sbgs.hrsrepo.mapper.RoleMenuMapper;
import com.sbgs.hrsservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    private final RoleMenuMapper roleMenuMapper;

    private final RoleResourceMapper roleResourceMapper;

    @Override
    public List<RoleDTO> getRolesByUserId(Long userId) {
        return roleMapper.getRoleListByUserId(userId);
    }

    @Override
    public Page<RoleDTO> getRolePage(BaseSearchForm<RoleDTO> baseSearchForm) {
        return roleMapper.getRolePage(baseSearchForm.getPage(), baseSearchForm.getKeyword());
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    @Override
    public RoleDTO getRoleById(Long roleId) {
        RoleDTO roleDTO = roleMapper.getRoleByRoleId(roleId);
        Assert.notNull(roleDTO, "获取失败：没有找到该角色或已被删除");
        return roleDTO;
    }

    @Override
    public RoleDTO getRoleDetailById(Long roleId) {
        RoleDTO roleDTO = roleMapper.getRoleDetailById(roleId);
        Assert.notNull(roleDTO, "获取失败：没有找到该角色或已被删除");
        return roleDTO;
    }

    @Override
    @Transactional
    public Long addRole(RoleDTO roleDTO) {
        RoleDTO roleInDB = roleMapper.getRoleByIdentification(roleDTO.getIdentification());
        Assert.isNull(roleInDB, "创建失败：标识名称已被占用");
        RoleDO roleDO = RoleConverter.toDO(roleDTO);
        roleMapper.insert(roleDO);
        updateMenuAndResource(roleDO.getId(), roleDTO.getMenuIds(), roleDTO.getResourceIds());
        return roleDO.getId();
    }

    @Override
    @Transactional
    public Long updateRole(RoleDTO roleDTO) {
        Assert.notNull(roleDTO.getId(), "角色id不能为空");
        RoleDTO roleInDB = roleMapper.getRoleByIdNotEqualAndIdentificationEqual(roleDTO.getId(), roleDTO.getIdentification());
        Assert.isNull(roleInDB, "更新失败：标识名称已被占用");
        RoleDO roleDO = roleMapper.selectById(roleDTO.getId());
        Assert.notNull(roleDO, "更新失败：没有找到该角色或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(roleDTO, roleDO);
        roleMapper.updateById(roleDO);
        updateMenuAndResource(roleDO.getId(), roleDTO.getMenuIds(), roleDTO.getResourceIds());
        return roleDO.getId();
    }

    @Override
    @Transactional
    public Long deleteRole(Long roleId) {
        roleMenuMapper.deleteByRoleId(roleId);
        roleResourceMapper.deleteByRoleId(roleId);
        roleMapper.deleteById(roleId);
        return roleId;
    }

    @Override
    @Transactional
    public Long[] deleteRoles(Long[] roleIds) {
        Assert.isTrue(null != roleIds && roleIds.length > 0, "没有需要删除的角色");
        for (Long roleId : roleIds) {
            this.deleteRole(roleId);
        }
        return roleIds;
    }

    @Override
    @Transactional
    public void updateMenuAndResource(@NotNull Long roleId, List<Long> menuIds, List<Long> resourceIds) {
        // 添加角色-菜单关联
        if (menuIds != null && menuIds.size() > 0) {
            roleMenuMapper.deleteByRoleId(roleId);
            this.bathAddRoleMenu(roleId, menuIds);
        }
        // 添加角色-资源关联
        if (resourceIds != null && resourceIds.size() > 0) {
            roleResourceMapper.deleteByRoleId(roleId);
            this.bathAddRoleResource(roleId, resourceIds);
        }
    }

    @Override
    @Transactional
    public void bathAddRoleMenu(Long roleId, List<Long> menuIds) {
        for (Long menuId : menuIds) {
            RoleMenuDO RoleMenuDO = new RoleMenuDO(null, roleId, menuId);
            roleMenuMapper.insert(RoleMenuDO);
        }
    }

    @Override
    @Transactional
    public void bathAddRoleResource(Long roleId, List<Long> resourceIds) {
        for (Long resourceId : resourceIds) {
            RoleResourceDO roleResourceDO = new RoleResourceDO(null, roleId, resourceId);
            roleResourceMapper.insert(roleResourceDO);
        }
    }

}
