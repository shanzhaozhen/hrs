package com.sbgs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbgs.hrscommon.dto.JWTUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.sbgs.hrscommon.domain.sys.UserDO;
import com.sbgs.hrscommon.dto.UserDTO;

public interface UserMapper extends BaseMapper<UserDO> {

    @Select("select id, username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled, " +
            "name, nickname, sex, birthday, avatar, email, phone_number, address_code, detailed_address, introduction " +
            "from sys_user where username = #{username}")
    UserDO selectUserByUsername(@Param("username") String username);

    @Select("select count(id) from sys_user where username = #{username}")
    Integer countByUsername(@Param("username") String username);

    @Select("select id, username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled, " +
            "name, nickname, sex, birthday, avatar, email, phone_number, address_code, detailed_address, introduction " +
            "from sys_user where id = #{id}")
    UserDTO getUserByUserId(@Param("id") Long id);

    UserDTO getUserAndRolesByUserId(@Param("id") Long id);

    JWTUser getJWTUserByUserId(Long userId);

    @Select("select id, username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled, " +
            "name, nickname, sex, birthday, avatar, email, phone_number, address_code, detailed_address, introduction " +
            "from sys_user where username = #{username}")
    UserDTO getUserByUsername(@Param("username") String username);

    Page<UserDTO> getUserPage(Page<UserDTO> page, @Param("keyword") String keyword);

    Page<UserDTO> getUserPageByRoleId(Page<UserDTO>page, @Param("roleId") Long roleId, @Param("keyword") String keyword);

    Page<UserDTO> getUserPageByDepartmentId(Page<UserDTO> page, @Param("departmentId") Long departmentId, @Param("keyword") String keyword);
}
