package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.MenuConverter;
import com.hbjs.hrscommon.converter.RoleConverter;
import com.hbjs.hrscommon.domain.sys.UserDO;
import com.hbjs.hrscommon.dto.JWTUser;
import com.hbjs.hrscommon.dto.UserDTO;
import com.hbjs.hrscommon.form.UserDepartmentForm;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.PasswordUtils;
import com.hbjs.hrscommon.utils.UserDetailsUtils;
import com.hbjs.hrscommon.vo.CurrentUser;
import com.hbjs.hrscommon.vo.UserInfo;
import com.hbjs.hrsrepo.mapper.RoleMapper;
import com.hbjs.hrsrepo.mapper.UserMapper;
import com.hbjs.hrsrepo.mapper.UserRoleMapper;
import com.hbjs.hrsservice.service.MenuService;
import com.hbjs.hrsservice.service.UserRoleService;
import com.hbjs.hrsservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    private final UserRoleService userRoleService;

    private final UserMapper userMapper;

    private final MenuService menuService;

    private final UserRoleMapper userRoleMapper;

    @Override
    public UserDTO getUserById(Long userId) {
        UserDTO userAndRolesByUserId = userMapper.getUserAndRolesByUserId(userId);
        return userAndRolesByUserId;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    @Cacheable(key = "#root.methodName + ':' + #userId")
    public JWTUser getJWTUser(Long userId) {
        return userMapper.getJWTUserByUserId(userId);
    }

    @Override
    public UserDTO getCurrentUser() {
        UserDTO userDTO = userMapper.getUserAndRolesByUserId(UserDetailsUtils.getUserId());
        Assert.notNull(userDTO, "没有找到当前用户信息");
        return userDTO;
    }

    @Override
    @Transactional
    public Long register(UserDTO userDTO) {
        Assert.isNull(userMapper.selectUserByUsername(userDTO.getUsername()), "注册失败，该用户名已存在！");
        UserDO newUser = new UserDO();
        BeanUtils.copyProperties(userDTO, newUser, "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled");
        newUser.setPassword(PasswordUtils.encryption(userDTO.getPassword()));
        userMapper.insert(newUser);
        return newUser.getId();
    }

    @Override
    public Boolean isExistUser(String username) {
        UserDO userDO = userMapper.selectUserByUsername(username);
        return userDO == null;
    }

    @Override
    public CurrentUser getUserInfo() {
        UserDTO userDTO = this.getCurrentUser();
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userDTO, userInfo);
        return CurrentUser.builder()
                .userInfo(userInfo)
                .roles(RoleConverter.toBase(userDTO.getRoles()))
                .menus(MenuConverter.toMenuVO(menuService.getMenusByCurrentUser()))
                .build();
    }

    @Override
    @Transactional
    public Page<UserDTO> getUserPage(Page<UserDTO> page, String keyword) {
        return userMapper.getUserPage(page, keyword);
    }

    @Override
    @Transactional
    public Long addUser(UserDTO userDTO) {
        Assert.isNull(userMapper.selectUserByUsername(userDTO.getUsername()), "注册失败，该用户名已存在！");
        UserDO userDO = new UserDO();
        CustomBeanUtils.copyPropertiesExcludeMeta(userDTO, userDO, "password");
        userDO.setPassword(PasswordUtils.encryption(userDTO.getPassword()));
        userMapper.insert(userDO);
        if (!CollectionUtils.isEmpty(userDTO.getRoleIds())) {
            userRoleService.bathAddUserRole(userDO.getId(), userDTO.getRoleIds());
        }
        return userDO.getId();
    }

    @Override
    @Transactional
    public Long updateUser(UserDTO userDTO) {
        Assert.notNull(userDTO.getId(), "用户id不能为空");
        UserDO userDO = userMapper.selectById(userDTO.getId());
        Assert.notNull(userDO, "更新失败：没有找到该用户或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(userDTO, userDO, "password");
        if (StringUtils.hasText(userDTO.getPassword())) {
            userDO.setPassword(PasswordUtils.encryption(userDTO.getPassword()));
        }
        userMapper.updateById(userDO);
        userRoleMapper.deleteByUserId(userDO.getId());
        userRoleService.bathAddUserRole(userDO.getId(), userDTO.getRoleIds());
        return userDO.getId();
    }

    @Override
    @Transactional
    public Long deleteUser(Long userId) {
        userRoleMapper.deleteByUserId(userId);
        userMapper.deleteById(userId);
        return userId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteUser(List<Long> userIds) {
        Assert.notEmpty(userIds, "没有需要删除的用户");
        for (Long userId : userIds) {
            this.deleteUser(userId);
        }
        return userIds;
    }

    @Override
    public Page<UserDTO> getUserPageByRoleId(Page<UserDTO> page, Long roleId, String keyword) {
        Assert.notNull(roleId, "没有有效的角色ID！");
        return userMapper.getUserPageByRoleId(page, roleId, keyword);
    }

    @Override
    public Page<UserDTO> getUserPageByDepartmentId(Page<UserDTO> page, Long departmentId, String keyword) {
        Assert.notNull(departmentId, "没有有效的部门ID！");
        return userMapper.getUserPageByDepartmentId(page, departmentId, keyword);
    }

    @Override
    @Transactional
    public Long updateUserDepartment(Long userId, Long departmentId) {
        UserDO userDO = userMapper.selectById(userId);
        Assert.notNull(userDO, "没有找到对应的用户");
        userDO.setDepId(departmentId);
        userMapper.updateById(userDO);
        return userDO.getId();
    }

    @Override
    @Transactional
    public List<Long> batchUpdateUserDepartment(UserDepartmentForm userDepartmentForm) {
        List<Long> userIds = userDepartmentForm.getUserIds();
        Long departmentId = userDepartmentForm.getDepartmentId();
        for (Long userId : userIds) {
            this.updateUserDepartment(userId, departmentId);
        }
        return userIds;
    }

    @Override
    public Boolean logout() {
        Long userId = UserDetailsUtils.getUserId();
        return true;
    }

}
