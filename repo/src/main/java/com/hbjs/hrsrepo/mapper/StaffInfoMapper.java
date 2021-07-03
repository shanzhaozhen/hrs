package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hbjs.hrscommon.domain.hr.StaffInfoDO;
import com.hbjs.hrscommon.dto.StaffInfoDTO;

public interface StaffInfoMapper extends BaseMapper<StaffInfoDO> {

    StaffInfoDTO getStaffInfoById(Long staffId);

    StaffInfoDTO getStaffInfoByStaffId(Long staffId);

    StaffInfoDTO getStaffInfoByStaffCode(String staffCode);

    long deleteStaffInfoByStaffId(Long staffId);

}
