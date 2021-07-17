package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.SalarySettingDO;
import com.hbjs.hrscommon.dto.SalarySettingDTO;
import org.apache.ibatis.annotations.Param;

public interface SalarySettingMapper extends BaseMapper<SalarySettingDO> {

    Page<SalarySettingDTO> getSalarySettingPage(Page<SalarySettingDTO> page);

    SalarySettingDTO getSalarySettingById(@Param("performanceId") Long performanceId);

    SalarySettingDTO getSalarySettingNew();

}
