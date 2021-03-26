package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.sys.DictionaryDO;
import com.hbjs.hrscommon.dto.DictionaryDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DictionaryMapper extends BaseMapper<DictionaryDO> {

    List<DictionaryDTO> getDictionaryByPid(@Param("pid") Long pid);

    Page<DictionaryDTO> getDictionaryByPid(Page<DictionaryDTO> page, @Param("pid") Long pid);

    @Select("select name, code, pid, priority, description, " +
            "created_by, created_date, last_modified_by, last_modified_date " +
            "from sys_resource order by priority")
    List<DictionaryDTO> getDictionaryList();

}
