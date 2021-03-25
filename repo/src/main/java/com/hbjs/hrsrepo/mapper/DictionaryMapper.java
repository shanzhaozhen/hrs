package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hbjs.hrscommon.domain.sys.DictionaryDO;
import com.hbjs.hrscommon.dto.DictionaryDTO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DictionaryMapper extends BaseMapper<DictionaryDO> {

    List<DictionaryDTO> getDictionaryByPid(Long pid);

    @Select("select name, code, pid, priority, description, " +
            "created_by, created_date, last_modified_by, last_modified_date " +
            "from sys_resource order by priority")
    List<DictionaryDTO> getDictionaryList();

}
