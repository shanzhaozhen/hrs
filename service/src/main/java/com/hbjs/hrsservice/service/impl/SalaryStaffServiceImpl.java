package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.SalaryStaffConverter;
import com.hbjs.hrscommon.domain.hr.SalaryStaffDO;
import com.hbjs.hrscommon.dto.SalaryStaffDTO;
import com.hbjs.hrscommon.dto.StaffDTO;
import com.hbjs.hrscommon.excel.SalaryStaffExcel;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.EasyExcelUtils;
import com.hbjs.hrsrepo.mapper.SalaryStaffMapper;
import com.hbjs.hrsservice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalaryStaffServiceImpl implements SalaryStaffService {

    private final SalaryStaffMapper salaryStaffMapper;
    
    private final StaffService staffService;

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
        List<SalaryStaffExcel> list;
        try {
            list = EasyExcelUtils.readExcel(file.getInputStream(), SalaryStaffExcel.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("文件读取失败");
        }

        Assert.isTrue(!CollectionUtils.isEmpty(list), "导入的文件不存在记录，请填写好再导入");

        StringBuffer stringBuffer = new StringBuffer();
        int errorTimes = 0;

        // 先检查是否存在部分缺少参数的，缺少参数则跳过
        List<SalaryStaffExcel> errorItems = list.stream().filter(s -> !StringUtils.hasText(s.getStaffCode())).collect(Collectors.toList());
        Assert.isTrue(CollectionUtils.isEmpty(errorItems), "存在未填写的参数（员工编号），本次导入失败");

        for (SalaryStaffExcel salaryStaffExcel : list) {
            // 根据查找员工编号查找staffId
            StaffDTO staffDTO = staffService.getStaffByStaffCode(salaryStaffExcel.getStaffCode());
            if (staffDTO == null) {
                stringBuffer.append("员工编号：").append(salaryStaffExcel.getStaffCode()).append("未录入本系统;\n");
                ++errorTimes;
                continue;
            }

            SalaryStaffDTO salaryStaffDTO = salaryStaffMapper.getSalaryStaffByStaffCode(salaryStaffExcel.getStaffCode());
            if (salaryStaffDTO == null) {
                salaryStaffDTO = new SalaryStaffDTO();
                BeanUtils.copyProperties(salaryStaffExcel, salaryStaffDTO);
                salaryStaffDTO.setStaffId(staffDTO.getId());
                this.addSalaryStaff(salaryStaffDTO);
            } else {
                BeanUtils.copyProperties(salaryStaffExcel, salaryStaffDTO);
                salaryStaffDTO.setStaffId(staffDTO.getId());
                this.updateSalaryStaff(salaryStaffDTO);
            }
        }

        if (StringUtils.hasText(stringBuffer)) {
            Assert.isTrue(list.size() != errorTimes, "导入失败，情况如下：\n" + stringBuffer);
            return String.format("成功导入%s条记录, %s条数据导入失败。\n详细如下：\n%s", list.size() - errorTimes, errorTimes, stringBuffer);
        }

        return String.format("成功导入%s条记录", list.size());
    }

    @Override
    public void exportSalaryStaff(String keyword, Long depId) {
        List<SalaryStaffExcel> salaryStaffExcelList = salaryStaffMapper.getSalaryStaffExcelList(keyword, depId);
        EasyExcelUtils.exportExcel(SalaryStaffExcel.class, salaryStaffExcelList, "员工薪资数据");
    }

}
