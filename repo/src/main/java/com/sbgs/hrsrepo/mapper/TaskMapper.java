package com.sbgs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.sbgs.hrscommon.domain.sys.CustomTaskDO;
import com.sbgs.hrscommon.dto.CustomTaskDTO;

import java.util.List;

public interface TaskMapper extends BaseMapper<CustomTaskDO> {

    @Select("select id, name, cron, bean_name, method_info, param_info, open, description, " +
            "created_by, created_date, last_modified_by, last_modified_date " +
            "from sys_task " +
            "where name like concat ('%', #{keyword}, '%') or description like concat ('%', #{keyword}, '%')")
    Page<CustomTaskDTO> getCustomTaskPage(Page<CustomTaskDTO> page, @Param("keyword") String keyword);

    @Select("select id, name, cron, bean_name, method_info, param_info, open, description, " +
            "created_by, created_date, last_modified_by, last_modified_date " +
            "from sys_task " +
            "where id = #{customTaskId} ")
    CustomTaskDTO getCustomTaskById(@Param("customTaskId") Long customTaskId);

    @Select("select id, name, cron, bean_name, method_info, param_info, open, description, " +
            "created_by, created_date, last_modified_by, last_modified_date " +
            "from sys_task")
    List<CustomTaskDTO> getAllCustomTask();
}
