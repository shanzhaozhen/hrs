package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.PerformanceConverter;
import com.hbjs.hrscommon.domain.hr.PerformanceDO;
import com.hbjs.hrscommon.dto.PerformanceDTO;
import com.hbjs.hrscommon.dto.StaffDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.EasyExcelUtils;
import com.hbjs.hrscommon.excel.PerformanceExcel;
import com.hbjs.hrsrepo.mapper.PerformanceMapper;
import com.hbjs.hrsservice.service.PerformanceService;
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
public class PerformanceServiceImpl implements PerformanceService {

    private final PerformanceMapper performanceMapper;

    private final StaffService staffService;

    @Override
    public Page<PerformanceDTO> getPerformancePage(Page<PerformanceDTO> page, String keyword, Long depId, Integer year, Integer quarter) {
        return performanceMapper.getPerformancePage(page, keyword, depId, year, quarter);
    }

    @Override
    public PerformanceDTO getPerformanceById(Long performanceId) {
        PerformanceDTO performanceDTO = performanceMapper.getPerformanceById(performanceId);
        Assert.notNull(performanceDTO, "获取失败：没有找到该绩效评价或已被删除");
        return performanceDTO;
    }

    @Override
    public Long addPerformance(PerformanceDTO performanceDTO) {
        PerformanceDO performanceDO = PerformanceConverter.toDO(performanceDTO);
        PerformanceDO performanceInDB = new LambdaQueryChainWrapper<>(performanceMapper)
                .eq(PerformanceDO::getStaffId, performanceDO.getStaffId())
                .eq(PerformanceDO::getYear, performanceDO.getYear())
                .eq(PerformanceDO::getQuarter, performanceDO.getQuarter())
                .one();
        Assert.isNull(performanceInDB, "新增失败：该员工的绩效评价已存在（存在相同的年份和季度）");
        performanceMapper.insert(performanceDO);
        return performanceDO.getId();
    }

    @Override
    public Long updatePerformance(PerformanceDTO performanceDTO) {
        Assert.notNull(performanceDTO.getId(), "绩效评价id不能为空");
        PerformanceDO performanceDO = performanceMapper.selectById(performanceDTO.getId());
        Assert.notNull(performanceDO, "更新失败：没有找到该绩效评价或已被删除");
        PerformanceDO performanceInDB = new LambdaQueryChainWrapper<>(performanceMapper)
                .eq(PerformanceDO::getStaffId, performanceDO.getStaffId())
                .eq(PerformanceDO::getYear, performanceDO.getYear())
                .eq(PerformanceDO::getQuarter, performanceDO.getQuarter())
                .one();
        Assert.isTrue(performanceInDB == null ||
                        performanceInDB.getId().equals(performanceDO.getId()),
                "更新失败：该员工绩效评价已存在（存在相同的年份和季度）");
        CustomBeanUtils.copyPropertiesExcludeMeta(performanceDTO, performanceDO);
        performanceMapper.updateById(performanceDO);
        return performanceDO.getId();
    }

    @Override
    public Long deletePerformance(Long performanceId) {
        PerformanceDTO performanceDTO = this.getPerformanceById(performanceId);
        Assert.notNull(performanceDTO, "删除失败：没有找到该绩效评价或已被删除");
        performanceMapper.deleteById(performanceId);
        return performanceId;
    }

    @Override
    public List<Long> batchDeletePerformance(@NotEmpty(message = "没有需要删除的绩效评价") List<Long> performanceIds) {
        for (Long performanceId : performanceIds) {
            this.deletePerformance(performanceId);
        }
        return performanceIds;
    }

    @Override
    public void generatePerformanceTemplate() {
        EasyExcelUtils.exportExcel(PerformanceExcel.class, new ArrayList<>(), "模板", "绩效评价导入模板");
    }

    @Override
    public String importPerformance(MultipartFile file) {
        List<PerformanceExcel> list;
        try {
            list = EasyExcelUtils.readExcel(file.getInputStream(), PerformanceExcel.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("文件读取失败");
        }

        Assert.isTrue(!CollectionUtils.isEmpty(list), "导入的文件不存在记录，请填写好再导入");

        StringBuffer stringBuffer = new StringBuffer();
        int errorTimes = 0;

        // 先检查是否存在部分缺少参数的，缺少参数则跳过
        List<PerformanceExcel> errorItems = list.stream().filter(s -> !StringUtils.hasText(s.getStaffCode()) || s.getYear() == null || s.getQuarter() == null).collect(Collectors.toList());
        Assert.isTrue(CollectionUtils.isEmpty(errorItems), "存在未填写的参数（员工编号、考核年份或考核季度），本次导入失败");

        for (PerformanceExcel performanceExcel : list) {
            // 根据查找员工编号查找staffId
            StaffDTO staffDTO = staffService.getStaffByStaffCode(performanceExcel.getStaffCode());
            if (staffDTO == null) {
                stringBuffer.append("员工编号：").append(performanceExcel.getStaffCode()).append("未录入本系统;\n");
                ++errorTimes;
                continue;
            }

            PerformanceDTO performanceDTO = performanceMapper.getPerformanceByStaffCodeAndYearAndQuarter(performanceExcel.getStaffCode(), performanceExcel.getYear(), performanceExcel.getQuarter());
            if (performanceDTO == null) {
                performanceDTO = new PerformanceDTO();
                BeanUtils.copyProperties(performanceExcel, performanceDTO);
                performanceDTO.setStaffId(staffDTO.getId());
                this.addPerformance(performanceDTO);
            } else {
                BeanUtils.copyProperties(performanceExcel, performanceDTO);
                performanceDTO.setStaffId(staffDTO.getId());
                this.updatePerformance(performanceDTO);
            }
        }

        if (StringUtils.hasText(stringBuffer)) {
            Assert.isTrue(list.size() != errorTimes, "导入失败，情况如下：\n" + stringBuffer);
            return String.format("成功导入%s条记录, %s条数据导入失败。\n详细如下：\n%s", list.size() - errorTimes, errorTimes, stringBuffer);
        }

        return String.format("成功导入%s条记录", list.size());
    }

    @Override
    public void exportPerformance(String keyword, Long depId, Integer year, Integer quarter) {
        List<PerformanceExcel> performanceList = performanceMapper.getPerformanceExcelList(keyword, depId, year, quarter);
        EasyExcelUtils.exportExcel(PerformanceExcel.class, performanceList, "绩效评价记录");
    }

}
