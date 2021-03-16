package com.sbgs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sbgs.hrscommon.domain.sys.DepartmentDO;
import com.sbgs.hrscommon.dto.DepartmentDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DepartmentMapper extends BaseMapper<DepartmentDO> {

    @Select("select id, name, code, priority, " +
            "created_by, created_date, last_modified_by, last_modified_date " +
            "from sys_menu order by priority")
    List<DepartmentDTO> getAllDepartments();

    @Select("select id, name, code, priority, " +
            "created_by, created_date, last_modified_by, last_modified_date " +
            "from sys_menu order by priority where code = #{departmentId}")
    DepartmentDTO getDepartmentByDepartmentId(@Param("departmentId") Long departmentId);

    @Select("select id, name, code, priority, " +
            "created_by, created_date, last_modified_by, last_modified_date " +
            "from sys_menu order by priority where code = #{code}")
    DepartmentDTO getDepartmentByCode(@Param("code") String code);

}
