package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.hbjs.hrscommon.converter.PerformanceSettingConverter;
import com.hbjs.hrscommon.domain.hr.PerformanceSettingDO;
import com.hbjs.hrscommon.dto.PerformanceSettingDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.EasyExcelUtils;
import com.hbjs.hrscommon.utils.PoiTlUtils;
import com.hbjs.hrscommon.vo.StaffExcel;
import com.hbjs.hrsrepo.mapper.PerformanceSettingMapper;
import com.hbjs.hrsservice.service.PerformanceSettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
        Assert.notNull(performanceSettingDTO, "删除失败：没有找到该员工信息或已被删除");
        performanceSettingMapper.deleteById(performanceSettingId);
        return performanceSettingId;
    }

    @Override
    @Transactional
    public List<Long> batchDeletePerformanceSetting(@NotEmpty(message = "没有需要删除的员工信息") List<Long> performanceSettingIds) {
        for (Long performanceSettingId : performanceSettingIds) {
            this.deletePerformanceSetting(performanceSettingId);
        }
        return performanceSettingIds;
    }

    @Override
    public void exportPerformanceSetting(String keyword, Long depId) {
    }

    @Override
    public void printPerformanceSetting(Long performanceSettingId) {
    }

}
