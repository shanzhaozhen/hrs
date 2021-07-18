package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.SalaryConverter;
import com.hbjs.hrscommon.domain.hr.SalaryDO;
import com.hbjs.hrscommon.dto.SalaryDTO;
import com.hbjs.hrscommon.dto.StaffDTO;
import com.hbjs.hrscommon.excel.SalaryExcel;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.EasyExcelUtils;
import com.hbjs.hrsrepo.mapper.SalaryMapper;
import com.hbjs.hrsservice.service.SalaryService;
import com.hbjs.hrsservice.service.StaffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
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
public class SalaryServiceImpl implements SalaryService {

    private final SalaryMapper salaryMapper;

    private final StaffService staffService;

    @Override
    public Page<SalaryDTO> getSalaryPage(Page<SalaryDTO> page, String keyword, Long depId) {
        return salaryMapper.getSalaryPage(page, keyword, depId);
    }

    @Override
    public SalaryDTO getSalaryById(Long salaryId) {
        SalaryDTO salaryDTO = salaryMapper.getSalaryById(salaryId);
        Assert.notNull(salaryDTO, "获取失败：没有找到该薪资发放或已被删除");
        return salaryDTO;
    }

    @Override
    public Long addSalary(SalaryDTO salaryDTO) {
        SalaryDO salaryDO = SalaryConverter.toDO(salaryDTO);
        SalaryDO salaryInDB = new LambdaQueryChainWrapper<>(salaryMapper)
                .eq(SalaryDO::getStaffId, salaryDO.getStaffId())
                .one();
        Assert.isNull(salaryInDB, "新增失败：该员工的薪资发放已存在（存在相同的年份和季度）");
        salaryMapper.insert(salaryDO);
        return salaryDO.getId();
    }

    @Override
    public Long updateSalary(SalaryDTO salaryDTO) {
        Assert.notNull(salaryDTO.getId(), "薪资发放id不能为空");
        SalaryDO salaryDO = salaryMapper.selectById(salaryDTO.getId());
        Assert.notNull(salaryDO, "更新失败：没有找到该薪资发放或已被删除");
        SalaryDO salaryInDB = new LambdaQueryChainWrapper<>(salaryMapper)
                .eq(SalaryDO::getStaffId, salaryDO.getStaffId())
                .one();
        Assert.isTrue(salaryInDB == null ||
                        salaryInDB.getId().equals(salaryDO.getId()),
                "更新失败：该员工薪资发放已存在（存在相同的年份和季度）");
        CustomBeanUtils.copyPropertiesExcludeMeta(salaryDTO, salaryDO);
        salaryMapper.updateById(salaryDO);
        return salaryDO.getId();
    }

    @Override
    public Long deleteSalary(Long salaryId) {
        SalaryDTO salaryDTO = this.getSalaryById(salaryId);
        Assert.notNull(salaryDTO, "删除失败：没有找到该薪资发放或已被删除");
        salaryMapper.deleteById(salaryId);
        return salaryId;
    }

    @Override
    public List<Long> batchDeleteSalary(@NotEmpty(message = "没有需要删除的薪资发放") List<Long> salaryIds) {
        for (Long salaryId : salaryIds) {
            this.deleteSalary(salaryId);
        }
        return salaryIds;
    }

    @Override
    public void generateSalaryTemplate() {
        EasyExcelUtils.exportExcel(SalaryExcel.class, new ArrayList<>(), "模板", "薪资发放导入模板");
    }

    @Override
    public String importSalary(MultipartFile file) {
        List<SalaryExcel> list;
        try {
            list = EasyExcelUtils.readExcel(file.getInputStream(), SalaryExcel.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("文件读取失败");
        }

        Assert.isTrue(!CollectionUtils.isEmpty(list), "导入的文件不存在记录，请填写好再导入");

        StringBuffer stringBuffer = new StringBuffer();
        int errorTimes = 0;

        // 先检查是否存在部分缺少参数的，缺少参数则跳过
        List<SalaryExcel> errorItems = list.stream().filter(s -> !StringUtils.hasText(s.getStaffCode())).collect(Collectors.toList());
        Assert.isTrue(CollectionUtils.isEmpty(errorItems), "存在未填写的参数（员工编号、考核年份或考核季度），本次导入失败");

        for (SalaryExcel salaryExcel : list) {
            // 根据查找员工编号查找staffId
            StaffDTO staffDTO = staffService.getStaffByStaffCode(salaryExcel.getStaffCode());
            if (staffDTO == null) {
                stringBuffer.append("员工编号：").append(salaryExcel.getStaffCode()).append("未录入本系统;\n");
                ++errorTimes;
                continue;
            }

            SalaryDTO salaryDTO = null;
            if (salaryDTO == null) {
                salaryDTO = new SalaryDTO();
                BeanUtils.copyProperties(salaryExcel, salaryDTO);
                salaryDTO.setStaffId(staffDTO.getId());
                this.addSalary(salaryDTO);
            } else {
                BeanUtils.copyProperties(salaryExcel, salaryDTO);
                salaryDTO.setStaffId(staffDTO.getId());
                this.updateSalary(salaryDTO);
            }
        }

        if (StringUtils.hasText(stringBuffer)) {
            Assert.isTrue(list.size() != errorTimes, "导入失败，情况如下：\n" + stringBuffer);
            return String.format("成功导入%s条记录, %s条数据导入失败。\n详细如下：\n%s", list.size() - errorTimes, errorTimes, stringBuffer);
        }

        return String.format("成功导入%s条记录", list.size());
    }

    @Override
    public void exportSalary(String keyword, Long depId) {
        List<SalaryExcel> salaryList = salaryMapper.getSalaryExcelList(keyword, depId);
        EasyExcelUtils.exportExcel(SalaryExcel.class, salaryList, "薪资发放记录");
    }

}
