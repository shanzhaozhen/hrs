package com.sbgs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import com.sbgs.hrscommon.domain.sys.RoleMenuDO;

public interface RoleMenuMapper extends BaseMapper<RoleMenuDO> {

    @Delete("delete from sys_role_menu where role_id = #{roleId}")
    int deleteByRoleId(Long roleId);
}
