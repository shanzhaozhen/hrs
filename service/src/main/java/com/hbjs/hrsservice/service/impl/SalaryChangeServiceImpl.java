package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.SalaryChangeConverter;
import com.hbjs.hrscommon.domain.hr.SalaryChangeDO;
import com.hbjs.hrscommon.dto.SalaryChangeDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrsrepo.mapper.SalaryChangeMapper;
import com.hbjs.hrsservice.service.SalaryChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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

}
