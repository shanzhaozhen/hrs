package com.sbgs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbgs.hrscommon.dto.UserDTO;

public interface UserRoleService {

    /**
     * 通过获取角色Id获取用户
     * @param page
     * @param roleId
     * @param keyword
     * @return
     */
    Page<UserDTO> getUserRolePage(Page<UserDTO> page, Long roleId, String keyword);
}
