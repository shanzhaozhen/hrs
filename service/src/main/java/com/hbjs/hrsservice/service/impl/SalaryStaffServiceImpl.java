package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.hbjs.hrscommon.converter.SalaryStaffConverter;
import com.hbjs.hrscommon.domain.hr.SalaryStaffDO;
import com.hbjs.hrscommon.dto.SalaryStaffDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.EasyExcelUtils;
import com.hbjs.hrscommon.utils.PoiTlUtils;
import com.hbjs.hrscommon.vo.StaffExcel;
import com.hbjs.hrsrepo.mapper.SalaryStaffMapper;
import com.hbjs.hrsservice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import javax.validation.constraints.NotEmpty;
import java.io.FileNotFoundException;
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
        SalaryStaffDO salaryStaffDO = salaryStaffMapper.selectById(salaryStaffId);
        Assert.notNull(salaryStaffDO, "获取失败：没有找到该员工信息或已被删除");
        SalaryStaffDTO salaryStaffDTO = SalaryStaffConverter.toDTO(salaryStaffDO);
        return salaryStaffDTO;
    }

    @Override
    @Transactional
    public Long addSalaryStaff(SalaryStaffDTO salaryStaffDTO) {
        SalaryStaffDO salaryStaffDO = SalaryStaffConverter.toDO(salaryStaffDTO);
        salaryStaffMapper.insert(salaryStaffDO);
        return salaryStaffDO.getId();
    }

    @Override
    @Transactional
    public Long updateSalaryStaff(SalaryStaffDTO salaryStaffDTO) {
        Assert.notNull(salaryStaffDTO.getId(), "员工信息id不能为空");
        SalaryStaffDO salaryStaffDO = salaryStaffMapper.selectById(salaryStaffDTO.getId());
        Assert.notNull(salaryStaffDO, "更新失败：没有找到该员工信息或已被删除");
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
    public void exportSalaryStaff(String keyword, Long depId) {
        List<StaffExcel> staffExcelList = salaryStaffMapper.getSalaryStaffExcelList(keyword, depId);
        EasyExcelUtils.exportExcel(StaffExcel.class, staffExcelList);
    }

    @Override
    public void printSalaryStaff(Long salaryStaffId) {
        SalaryStaffDO salaryStaffDO = salaryStaffMapper.selectById(salaryStaffId);
        XWPFTemplate template = null;
        try {
            ConfigureBuilder builder = Configure.builder();
            builder.useSpringEL();
            template = XWPFTemplate.compile(ResourceUtils.getFile("classpath:doc/job.docx"), builder.build()).render(salaryStaffDO);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Assert.notNull(e, "找不到对应的模板文件");
        }
        Assert.notNull(template, "生成模板文件失败");
        PoiTlUtils.exportExcel(template, "job.docx");
    }

}
