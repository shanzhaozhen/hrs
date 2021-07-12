package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.PerformanceConverter;
import com.hbjs.hrscommon.domain.hr.PerformanceDO;
import com.hbjs.hrscommon.dto.PerformanceDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrsrepo.mapper.PerformanceMapper;
import com.hbjs.hrsservice.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {

    private final PerformanceMapper performanceMapper;

    @Override
    public Page<PerformanceDTO> getPerformancePage(Page<PerformanceDTO> page, String keyword, Long depId) {
        return performanceMapper.getPerformancePage(page, keyword, depId);
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
    public void exportPerformance(String keyword, Long depId) {

    }

    @Override
    public void printPerformance(Long performanceId) {

    }
}
