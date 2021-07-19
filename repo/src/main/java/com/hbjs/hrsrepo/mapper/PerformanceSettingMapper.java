package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.PerformanceSettingDO;
import com.hbjs.hrscommon.dto.PerformanceSettingDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PerformanceSettingMapper extends BaseMapper<PerformanceSettingDO> {

    Page<PerformanceSettingDTO> getPerformanceSettingPage(Page<PerformanceSettingDTO> page, @Param("keyword") String keyword);

    List<PerformanceSettingDTO> getPerformanceSettingList(@Param("keyword") String keyword);

    PerformanceSettingDTO getPerformanceSettingByMonth(@Param("month") String month);

    List<PerformanceSettingDTO> getPerformanceSettingListByStartMonthAndEndMonth(@Param("startMonth") Date startMonth, @Param("endMonth") Date endMonth);
}
