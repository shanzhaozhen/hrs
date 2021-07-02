package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.StaffChangeDO;
import com.hbjs.hrscommon.dto.StaffChangeDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffChangeMapper extends BaseMapper<StaffChangeDO> {

    Page<StaffChangeDTO> getStaffChangePage(Page<StaffChangeDTO> page, @Param("staffId") Long staffId, @Param("keyword") String keyword);

    StaffChangeDTO getStaffChangeById(@Param("staffChangeId") Long staffChangeId);

    List<StaffChangeDTO> getStaffChangeInDays(@Param("days") int days);

}
