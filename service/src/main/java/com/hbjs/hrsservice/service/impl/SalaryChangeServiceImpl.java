package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.SalaryChangeConverter;
import com.hbjs.hrscommon.domain.hr.SalaryChangeDO;
import com.hbjs.hrscommon.dto.SalaryChangeDTO;
import com.hbjs.hrscommon.excel.SalaryChangeExportExcel;
import com.hbjs.hrscommon.excel.SalaryChangeImportExcel;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.EasyExcelUtils;
import com.hbjs.hrsrepo.mapper.SalaryChangeMapper;
import com.hbjs.hrsservice.service.SalaryChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SalaryChangeServiceImpl implements SalaryChangeService {

    private final SalaryChangeMapper salaryChangeMapper;

    @Override
    public Page<SalaryChangeDTO> getSalaryChangePage(Page<SalaryChangeDTO> page, Long staffId, String keyword, Long depId) {
        return salaryChangeMapper.getSalaryChangePage(page, staffId, keyword, depId);
    }

    @Override
    public SalaryChangeDTO getSalaryChangeById(Long salaryChangeId) {
        return salaryChangeMapper.getSalaryChangeById(salaryChangeId);
    }

    @Override
    public List<SalaryChangeDTO> getSalaryChangeInDays(int days) {
        return salaryChangeMapper.getSalaryChangeInDays(days);
    }

    @Override
    @Transactional
    public Long addSalaryChange(SalaryChangeDTO salaryChangeDTO) {
        SalaryChangeDO salaryChangeDO = SalaryChangeConverter.toDO(salaryChangeDTO);
        salaryChangeMapper.insert(salaryChangeDO);
        return salaryChangeDO.getId();
    }

    @Override
    @Transactional
    public Long updateSalaryChange(SalaryChangeDTO salaryChangeDTO) {
        Assert.notNull(salaryChangeDTO.getId(), "员工薪资变动记录id不能为空");
        SalaryChangeDO salaryChangeDO = salaryChangeMapper.selectById(salaryChangeDTO.getId());
        Assert.notNull(salaryChangeDO, "更新失败：没有找到该员工薪资变动记录或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(salaryChangeDTO, salaryChangeDO);
        salaryChangeMapper.updateById(salaryChangeDO);
        return salaryChangeDO.getId();
    }

    @Override
    @Transactional
    public Long deleteSalaryChange(Long salaryChangeId) {
        salaryChangeMapper.deleteById(salaryChangeId);
        return salaryChangeId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteSalaryChange(List<Long> salaryChangeIds) {
        for (Long salaryChangeId : salaryChangeIds) {
            this.deleteSalaryChange(salaryChangeId);
        }
        return salaryChangeIds;
    }

    @Override
    public void generateSalaryChangeTemplate() {
        EasyExcelUtils.exportExcel(SalaryChangeImportExcel.class, new ArrayList<>(), "模板", "薪资变动导入模板");
    }

    @Override
    public void exportSalaryChange(Long staffId, String keyword, Long depId) {
        List<SalaryChangeExportExcel> salaryChangeList = salaryChangeMapper.getSalaryChangeExcelList(staffId, keyword, depId);
        EasyExcelUtils.exportExcel(SalaryChangeExportExcel.class, salaryChangeList, "薪资变动数据");
    }

}
