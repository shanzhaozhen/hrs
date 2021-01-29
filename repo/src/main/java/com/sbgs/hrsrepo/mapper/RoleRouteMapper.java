package com.sbgs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import com.sbgs.hrscommon.domain.sys.RoleRouteDO;

public interface RoleRouteMapper extends BaseMapper<RoleRouteDO> {

    @Delete("delete from sys_role_route where role_id = #{roleId}")
    int deleteByRoleId(Long roleId);
}
