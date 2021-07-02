package com.hbjs.hrsservice.service.impl;

import com.hbjs.hrscommon.dto.StaffInfoDTO;
import com.hbjs.hrsrepo.mapper.StaffInfoMapper;
import com.hbjs.hrsservice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StaffInfoServiceImpl implements StaffInfoService {

    private final StaffInfoMapper staffInfoMapper;


    @Override
    public StaffInfoDTO getStaffInfoById(Long staffInfoId) {
        return null;
    }

    @Override
    public StaffInfoDTO getStaffInfoByStaffId(Long staffId) {
        return null;
    }

    @Override
    public Long addStaffInfo(StaffInfoDTO staffInfoDTO, Long staffId) {
        return null;
    }

    @Override
    public Long updateStaffInfo(StaffInfoDTO staffInfoDTO) {
        return null;
    }

    @Override
    public Long deleteStaffInfo(Long staffInfoId) {
        return null;
    }

    @Override
    public long deleteStaffInfoByStaffId(Long staffId) {
        return 0;
    }
}
