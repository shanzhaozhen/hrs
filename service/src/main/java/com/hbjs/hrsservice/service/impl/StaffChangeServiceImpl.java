package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.StaffChangeConverter;
import com.hbjs.hrscommon.domain.hr.StaffChangeDO;
import com.hbjs.hrscommon.dto.StaffDTO;
import com.hbjs.hrscommon.dto.StaffChangeDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrsrepo.mapper.StaffChangeMapper;
import com.hbjs.hrsservice.service.StaffService;
import com.hbjs.hrsservice.service.StaffChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StaffChangeServiceImpl implements StaffChangeService {

    private final StaffChangeMapper staffChangeMapper;
    private final StaffService staffService;

    @Override
    public Page<StaffChangeDTO> getStaffChangePage(Page<StaffChangeDTO> page, Long staffId, String keyword, Long depId) {
        return staffChangeMapper.getStaffChangePage(page, staffId, keyword, depId);
    }

    @Override
    public StaffChangeDTO getStaffChangeById(Long staffChangeId) {
        return staffChangeMapper.getStaffChangeById(staffChangeId);
    }

    @Override
    @Transactional
    public Long addStaffChange(StaffChangeDTO staffChangeDTO) {
        StaffChangeDO staffChangeDO = StaffChangeConverter.toDO(staffChangeDTO);
        staffChangeMapper.insert(staffChangeDO);
        return staffChangeDO.getId();
    }

    @Override
    @Transactional
    public Long updateStaffChange(StaffChangeDTO staffChangeDTO) {
        Assert.notNull(staffChangeDTO.getId(), "调动记录id不能为空");
        StaffChangeDO staffChangeDO = staffChangeMapper.selectById(staffChangeDTO.getId());
        Assert.notNull(staffChangeDO, "更新失败：没有找到该调动记录或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(staffChangeDTO, staffChangeDO, "executed");
        staffChangeMapper.updateById(staffChangeDO);
        return staffChangeDO.getId();
    }

    @Override
    @Transactional
    public Long deleteStaffChange(Long staffChangeId) {
        staffChangeMapper.deleteById(staffChangeId);
        return staffChangeId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteStaffChange(List<Long> staffChangeIds) {
        for (Long staffChangeId : staffChangeIds) {
            this.deleteStaffChange(staffChangeId);
        }
        return staffChangeIds;
    }

    @Override
    @Transactional
    public Long runChange(Long staffChangeId) {
        Assert.notNull(staffChangeId, "调动记录id不能为空");
        StaffChangeDTO staffChangeDTO = this.getStaffChangeById(staffChangeId);
        Assert.notNull(staffChangeDTO, "执行失败：没有找到该调动记录或已被删除");
        return this.runChange(staffChangeDTO);
    }

    @Override
    @Transactional
    public Long runChange(StaffChangeDTO staffChangeDTO) {
        StaffDTO staffDTO = staffService.getStaffById(staffChangeDTO.getStaffId());
        staffDTO
                .setDepId(staffChangeDTO.getPostDepId())
                .setDuty(staffChangeDTO.getPostDuty())
                .setPost(staffChangeDTO.getPostPost())
                .setPostType(staffChangeDTO.getPostPostType())
                .setPostLevel(staffChangeDTO.getPostPostLevel());
        staffService.updateStaff(staffDTO);
        staffChangeDTO.setExecuted(true);
        this.updateStaffChange(staffChangeDTO);
        return staffChangeDTO.getId();
    }

    @Override
    @Transactional
    public void runChange(int days, boolean skipExecuted) {
        List<StaffChangeDTO> staffChangeDTOS = staffChangeMapper.getStaffChangeInDays(days);
        for (StaffChangeDTO staffChangeDTO : staffChangeDTOS) {
            if (skipExecuted && staffChangeDTO.getExecuted()) {
                this.runChange(staffChangeDTO);
            }

        }
    }

}
