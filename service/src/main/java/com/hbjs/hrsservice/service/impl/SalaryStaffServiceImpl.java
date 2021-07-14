package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.SalaryStaffConverter;
import com.hbjs.hrscommon.domain.hr.SalaryStaffDO;
import com.hbjs.hrscommon.dto.SalaryStaffDTO;
import com.hbjs.hrscommon.excel.SalaryStaffExcel;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.EasyExcelUtils;
import com.hbjs.hrsrepo.mapper.SalaryStaffMapper;
import com.hbjs.hrsservice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalaryStaffServiceImpl implements SalaryStaffService {

    private final SalaryStaffMapper salaryStaffMapper;

    @Override
    public Page<SalaryStaffDTO> getSalaryStaffPage(Page<SalaryStaffDTO> page, String keyword, Long depId) {
        return salaryStaffMapper.getSalaryStaffPage(page, keyword, depId);
    }

    @Override
    public SalaryStaffDTO getSalaryStaffById(Long salaryStaffId) {
        SalaryStaffDTO salaryStaffDTO = salaryStaffMapper.getSalaryStaffById(salaryStaffId);
        Assert.notNull(salaryStaffDTO, "获取失败：没有找到该员工信息或已被删除");
        return salaryStaffDTO;
    }

    @Override
    public SalaryStaffDTO getSalaryStaffByStaffId(Long staffId) {
        SalaryStaffDTO salaryStaffDTO = salaryStaffMapper.getSalaryStaffByStaffId(staffId);
        Assert.notNull(salaryStaffDTO, "获取失败：没有找到该员工信息或已被删除");
        return salaryStaffDTO;
    }

    @Override
    @Transactional
    public Long addSalaryStaff(SalaryStaffDTO salaryStaffDTO) {
        SalaryStaffDO salaryStaffDO = SalaryStaffConverter.toDO(salaryStaffDTO);
        Assert.notNull(salaryStaffDO.getStaffId(), "新增失败：员工薪资缺少员工id");
        SalaryStaffDO salaryStaffInDB = new LambdaQueryChainWrapper<>(salaryStaffMapper).eq(SalaryStaffDO::getStaffId, salaryStaffDO.getStaffId()).one();
        Assert.isNull(salaryStaffInDB, "新增失败：该员工已存在薪资记录");
        salaryStaffMapper.insert(salaryStaffDO);
        return salaryStaffDO.getId();
    }

    @Override
    @Transactional
    public Long updateSalaryStaff(SalaryStaffDTO salaryStaffDTO) {
        Assert.notNull(salaryStaffDTO.getId(), "员工信息id不能为空");
        SalaryStaffDO salaryStaffDO = salaryStaffMapper.selectById(salaryStaffDTO.getId());
        Assert.notNull(salaryStaffDO, "更新失败：没有找到该员工信息或已被删除");
        Assert.isTrue(salaryStaffDO.getStaffId().equals(salaryStaffDTO.getStaffId()), "更新失败：不允许修改关联的员工");
        CustomBeanUtils.copyPropertiesExcludeMeta(salaryStaffDTO, salaryStaffDO);
        salaryStaffMapper.updateById(salaryStaffDO);
        return salaryStaffDO.getId();
    }

    @Override
    @Transactional
    public Long deleteSalaryStaff(Long salaryStaffId) {
        SalaryStaffDTO salaryStaffDTO = this.getSalaryStaffById(salaryStaffId);
        Assert.notNull(salaryStaffDTO, "删除失败：没有找到该员工信息或已被删除");
        salaryStaffMapper.deleteById(salaryStaffId);
        return salaryStaffId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteSalaryStaff(@NotEmpty(message = "没有需要删除的员工信息") List<Long> salaryStaffIds) {
        for (Long salaryStaffId : salaryStaffIds) {
            this.deleteSalaryStaff(salaryStaffId);
        }
        return salaryStaffIds;
    }

    @Override
    public void generateSalaryStaffTemplate() {
        EasyExcelUtils.exportExcel(SalaryStaffExcel.class, new ArrayList<>(), "模板", "员工薪资导入模板");
    }

    @Override
    public String importSalaryStaff(MultipartFile file) {
        return null;
    }

    @Override
    public void exportSalaryStaff(String keyword, Long depId) {
        List<SalaryStaffExcel> salaryStaffExcelList = salaryStaffMapper.getSalaryStaffExcelList(keyword, depId);
        EasyExcelUtils.exportExcel(SalaryStaffExcel.class, salaryStaffExcelList, "员工薪资数据");
    }

}
