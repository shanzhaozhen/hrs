package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hbjs.hrscommon.domain.sys.DepartmentDO;
import com.hbjs.hrscommon.dto.DepartmentDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DepartmentMapper extends BaseMapper<DepartmentDO> {

    @Select("select id, name, code, pid, priority, " +
            "created_by, created_date, last_modified_by, last_modified_date " +
            "from sys_department order by priority")
    List<DepartmentDTO> getAllDepartments();

    @Select("select id, name, code, pid, priority, " +
            "created_by, created_date, last_modified_by, last_modified_date " +
            "from sys_department order by priority where id = #{departmentId}")
    DepartmentDTO getDepartmentById(@Param("departmentId") Long departmentId);

    @Select("select id, name, code, pid, priority, " +
            "created_by, created_date, last_modified_by, last_modified_date " +
            "from sys_department order by priority where code = #{code}")
    DepartmentDTO getDepartmentByCode(@Param("code") String code);

}
