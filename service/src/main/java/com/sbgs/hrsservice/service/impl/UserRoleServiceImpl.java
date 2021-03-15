package com.sbgs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbgs.hrscommon.dto.UserDTO;
import com.sbgs.hrsrepo.mapper.UserMapper;
import com.sbgs.hrsrepo.mapper.UserRoleMapper;
import com.sbgs.hrsservice.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserMapper userMapper;

    private final UserRoleMapper userRoleMapper;

    @Override
    public Page<UserDTO> getUserRolePage(Page<UserDTO> page, Long roleId, String keyword) {
        Assert.notNull(roleId, "没有有效的用户ID！");
        return userMapper.getUserPageByRoleId(page, roleId, keyword);
    }

}
