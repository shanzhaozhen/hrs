package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.hbjs.hrscommon.converter.StaffInfoConverter;
import com.hbjs.hrscommon.domain.hr.StaffInfoDO;
import com.hbjs.hrscommon.dto.StaffInfoDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrsrepo.mapper.StaffInfoMapper;
import com.hbjs.hrsservice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Slf4j
@RequiredArgsConstructor
public class StaffInfoServiceImpl implements StaffInfoService {

    private final StaffInfoMapper staffInfoMapper;

    @Override
    public StaffInfoDTO getStaffInfoById(Long id) {
        return staffInfoMapper.getStaffInfoById(id);
    }

    @Override
    public StaffInfoDTO getStaffInfoByStaffId(Long staffId) {
        return staffInfoMapper.getStaffInfoByStaffId(staffId);
    }

    @Override
    public StaffInfoDTO getStaffInfoByStaffCode(String staffCode) {
        return staffInfoMapper.getStaffInfoByStaffCode(staffCode);
    }

    @Override
    @Transactional
    public Long updateStaffInfo(StaffInfoDTO staffInfoDTO, Long staffId) {
        if (staffInfoDTO == null) return null;
        StaffInfoDO staffInfo = new LambdaQueryChainWrapper<>(staffInfoMapper).eq(StaffInfoDO::getStaffId, staffId).one();
        if (staffInfo == null) {
            staffInfo = StaffInfoConverter.toDO(staffInfoDTO);
            Assert.notNull(staffId, "员工id不能为空");
            staffInfo.setStaffId(staffId);
            staffInfoMapper.insert(staffInfo);
        } else {
            CustomBeanUtils.copyPropertiesExcludeMeta(staffInfoDTO, staffInfo);
            Assert.notNull(staffInfo.getStaffId(), "员工id不能为空");
            staffInfoMapper.updateById(staffInfo);
        }
        return staffInfo.getId();
    }

    @Override
    @Transactional
    public Long deleteStaffInfo(Long staffInfoId) {
        StaffInfoDTO staffInfoDTO = this.getStaffInfoById(staffInfoId);
        Assert.notNull(staffInfoDTO, "删除失败：没有找到该员工信息或已被删除");
        staffInfoMapper.deleteById(staffInfoId);
        return staffInfoId;
    }

    @Override
    @Transactional
    public long deleteStaffInfoByStaffId(Long staffId) {
        return staffInfoMapper.deleteStaffInfoByStaffId(staffId);
    }

}
