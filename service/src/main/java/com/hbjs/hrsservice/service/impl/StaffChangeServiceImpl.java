package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.StaffChangeConverter;
import com.hbjs.hrscommon.domain.hr.StaffChangeDO;
import com.hbjs.hrscommon.dto.StaffChangeDTO;
import com.hbjs.hrscommon.excel.SalaryStaffExcel;
import com.hbjs.hrscommon.excel.StaffChangeExportExcel;
import com.hbjs.hrscommon.excel.StaffChangeImportExcel;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.EasyExcelUtils;
import com.hbjs.hrsrepo.mapper.StaffChangeMapper;
import com.hbjs.hrsservice.service.StaffChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class StaffChangeServiceImpl implements StaffChangeService {

    private final StaffChangeMapper staffChangeMapper;

    @Override
    public Page<StaffChangeDTO> getStaffChangePage(Page<StaffChangeDTO> page, Long staffId, String keyword, Long depId) {
        return staffChangeMapper.getStaffChangePage(page, staffId, keyword, depId);
    }

    @Override
    public StaffChangeDTO getStaffChangeById(Long staffChangeId) {
        return staffChangeMapper.getStaffChangeById(staffChangeId);
    }

    @Override
    public List<StaffChangeDTO> getStaffChangeInDays(int days) {
        return staffChangeMapper.getStaffChangeInDays(days);
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
        CustomBeanUtils.copyPropertiesExcludeMeta(staffChangeDTO, staffChangeDO);
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
    public void generateStaffChangeTemplate() {
        EasyExcelUtils.exportExcel(StaffChangeImportExcel.class, new ArrayList<>(), "模板", "调动记录导入模板");
    }

    @Override
    public void exportStaffChange(Long staffId, String keyword, Long depId) {
        List<StaffChangeExportExcel> staffChangeList = staffChangeMapper.getStaffChangeExcelList(staffId, keyword, depId);
        EasyExcelUtils.exportExcel(StaffChangeExportExcel.class, staffChangeList, "调动记录数据");
    }

}
