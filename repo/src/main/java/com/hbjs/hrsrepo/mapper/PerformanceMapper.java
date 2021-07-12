package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.PerformanceDO;
import com.hbjs.hrscommon.dto.PerformanceDTO;
import org.apache.ibatis.annotations.Param;

public interface PerformanceMapper extends BaseMapper<PerformanceDO> {

    Page<PerformanceDTO> getPerformancePage(Page<PerformanceDTO> page, @Param("keyword") String keyword, @Param("depId") Long depId);

    PerformanceDTO getPerformanceById(@Param("performanceId") Long performanceId);

}
