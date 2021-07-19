package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.PerformanceSettingConverter;
import com.hbjs.hrscommon.domain.hr.PerformanceSettingDO;
import com.hbjs.hrscommon.dto.PerformanceSettingDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrsrepo.mapper.PerformanceSettingMapper;
import com.hbjs.hrsservice.service.PerformanceSettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PerformanceSettingServiceImpl implements PerformanceSettingService {

    private final PerformanceSettingMapper performanceSettingMapper;

    @Override
    public Page<PerformanceSettingDTO> getPerformanceSettingPage(Page<PerformanceSettingDTO> page, String keyword) {
        return performanceSettingMapper.getPerformanceSettingPage(page, keyword);
    }

    @Override
    public List<PerformanceSettingDTO> getPerformanceSettingList(String keyword) {
        return performanceSettingMapper.getPerformanceSettingList(keyword);
    }

    @Override
    public PerformanceSettingDTO getPerformanceSettingById(Long performanceSettingId) {
        PerformanceSettingDO performanceSettingDO = performanceSettingMapper.selectById(performanceSettingId);
        Assert.notNull(performanceSettingDO, "获取失败：没有找到该绩效配置或已被删除");
        return PerformanceSettingConverter.toDTO(performanceSettingDO);
    }

    @Override
    @Transactional
    public Long addPerformanceSetting(PerformanceSettingDTO performanceSettingDTO) {
        PerformanceSettingDO performanceSettingDO = PerformanceSettingConverter.toDO(performanceSettingDTO);
        PerformanceSettingDO performanceSettingInDB = new LambdaQueryChainWrapper<>(performanceSettingMapper)
                .eq(PerformanceSettingDO::getYear, performanceSettingDO.getYear())
                .eq(PerformanceSettingDO::getQuarter, performanceSettingDO.getQuarter())
                .one();
        Assert.isNull(performanceSettingInDB, "新增失败：该绩效设置已存在（存在相同的年份和季度）");
        // 检验是否存在重叠的生效日期
        List<PerformanceSettingDTO> performanceSettingListByStartMonthAndEndMonth = performanceSettingMapper.getPerformanceSettingListByStartMonthAndEndMonth(performanceSettingDTO.getStartMonth(), performanceSettingDTO.getEndMonth());
        Assert.isTrue(CollectionUtils.isEmpty(performanceSettingListByStartMonthAndEndMonth), "存在重叠的绩效生效日期，请检查之前的配置");
        performanceSettingMapper.insert(performanceSettingDO);
        return performanceSettingDO.getId();
    }

    @Override
    @Transactional
    public Long updatePerformanceSetting(PerformanceSettingDTO performanceSettingDTO) {
        Assert.notNull(performanceSettingDTO.getId(), "绩效设置id不能为空");
        PerformanceSettingDO performanceSettingDO = performanceSettingMapper.selectById(performanceSettingDTO.getId());
        Assert.notNull(performanceSettingDO, "更新失败：没有找到该绩效设置或已被删除");
        PerformanceSettingDO performanceSettingInDB = new LambdaQueryChainWrapper<>(performanceSettingMapper)
                .eq(PerformanceSettingDO::getYear, performanceSettingDO.getYear())
                .eq(PerformanceSettingDO::getQuarter, performanceSettingDO.getQuarter())
                .one();
        Assert.isTrue(performanceSettingInDB == null ||
                        performanceSettingInDB.getId().equals(performanceSettingDO.getId()),
                "更新失败：该绩效设置已存在（存在相同的年份和季度）");
        CustomBeanUtils.copyPropertiesExcludeMeta(performanceSettingDTO, performanceSettingDO);
        performanceSettingMapper.updateById(performanceSettingDO);
        return performanceSettingDO.getId();
    }

    @Override
    @Transactional
    public Long deletePerformanceSetting(Long performanceSettingId) {
        PerformanceSettingDTO performanceSettingDTO = this.getPerformanceSettingById(performanceSettingId);
        Assert.notNull(performanceSettingDTO, "删除失败：没有找到该绩效设置或已被删除");
        performanceSettingMapper.deleteById(performanceSettingId);
        return performanceSettingId;
    }

    @Override
    @Transactional
    public List<Long> batchDeletePerformanceSetting(@NotEmpty(message = "没有需要删除的绩效设置") List<Long> performanceSettingIds) {
        for (Long performanceSettingId : performanceSettingIds) {
            this.deletePerformanceSetting(performanceSettingId);
        }
        return performanceSettingIds;
    }

    @Override
    public PerformanceSettingDTO getPerformanceSettingByMonth(String month) {
        return performanceSettingMapper.getPerformanceSettingByMonth(month);
    }

}
