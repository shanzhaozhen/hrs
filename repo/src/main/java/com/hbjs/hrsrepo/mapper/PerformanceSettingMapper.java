package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.PerformanceSettingDO;
import com.hbjs.hrscommon.dto.PerformanceSettingDTO;
import org.apache.ibatis.annotations.Param;

public interface PerformanceSettingMapper extends BaseMapper<PerformanceSettingDO> {

    Page<PerformanceSettingDTO> getPerformanceSettingPage(Page<PerformanceSettingDTO> page, @Param("keyword") String keyword);

}
