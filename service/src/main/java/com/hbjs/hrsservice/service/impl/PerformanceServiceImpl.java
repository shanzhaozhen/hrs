package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.PerformanceDTO;
import com.hbjs.hrsrepo.mapper.PerformanceMapper;
import com.hbjs.hrsservice.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public Long addPerformance(PerformanceDTO performanceDTO) {
        return null;
    }

    @Override
    public Long updatePerformance(PerformanceDTO performanceDTO) {
        return null;
    }

    @Override
    public void updatePerformanceMoreInfo(PerformanceDTO performanceDTO, Long performanceId) {

    }

    @Override
    public Long deletePerformance(Long performanceId) {
        return null;
    }

    @Override
    public List<Long> batchDeletePerformance(List<Long> performanceIds) {
        return null;
    }

    @Override
    public void exportPerformance(String keyword, Long depId) {

    }

    @Override
    public void printPerformance(Long performanceId) {

    }
}
