package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.PerformanceDO;
import com.hbjs.hrscommon.dto.PerformanceDTO;

public interface PerformanceMapper extends BaseMapper<PerformanceDO> {

    Page<PerformanceDTO> getPerformancePage(Page<PerformanceDTO> page, String keyword, Long depId);

}
