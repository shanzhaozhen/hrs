package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.SalarySettingConverter;
import com.hbjs.hrscommon.domain.hr.SalarySettingDO;
import com.hbjs.hrscommon.dto.SalarySettingDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrsrepo.mapper.SalarySettingMapper;
import com.hbjs.hrsservice.service.SalarySettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalarySettingServiceImpl implements SalarySettingService {

    private final SalarySettingMapper salarySettingMapper;

    @Override
    public Page<SalarySettingDTO> getSalarySettingPage(Page<SalarySettingDTO> page) {
        return salarySettingMapper.getSalarySettingPage(page);
    }

    @Override
    public SalarySettingDTO getSalarySettingById(Long salarySettingId) {
        SalarySettingDTO salarySettingDTO = salarySettingMapper.getSalarySettingById(salarySettingId);
        Assert.notNull(salarySettingDTO, "获取失败：没有找到该薪资配置或已被删除");
        return salarySettingDTO;
    }

    @Override
    public SalarySettingDTO getSalarySettingNew() {
        return salarySettingMapper.getSalarySettingNew();
    }

    @Override
    public Long addSalarySetting(SalarySettingDTO salarySettingDTO) {
        SalarySettingDO salarySettingDO = SalarySettingConverter.toDO(salarySettingDTO);
        salarySettingMapper.insert(salarySettingDO);
        return salarySettingDO.getId();
    }

    @Override
    public Long updateSalarySetting(SalarySettingDTO salarySettingDTO) {
        Assert.notNull(salarySettingDTO.getId(), "薪资配置id不能为空");
        SalarySettingDO salarySettingDO = salarySettingMapper.selectById(salarySettingDTO.getId());
        Assert.notNull(salarySettingDO, "更新失败：没有找到该薪资配置或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(salarySettingDTO, salarySettingDO);
        salarySettingMapper.updateById(salarySettingDO);
        return salarySettingDO.getId();
    }

    @Override
    public Long deleteSalarySetting(Long salarySettingId) {
        SalarySettingDTO salarySettingDTO = this.getSalarySettingById(salarySettingId);
        Assert.notNull(salarySettingDTO, "删除失败：没有找到该薪资配置或已被删除");
        salarySettingMapper.deleteById(salarySettingId);
        return salarySettingId;
    }

    @Override
    public List<Long> batchDeleteSalarySetting(@NotEmpty(message = "没有需要删除的薪资配置") List<Long> salarySettingIds) {
        for (Long salarySettingId : salarySettingIds) {
            this.deleteSalarySetting(salarySettingId);
        }
        return salarySettingIds;
    }

}
