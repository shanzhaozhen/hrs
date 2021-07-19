package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.PerformanceDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AllowanceService {

    /**
     * 获取绩效评价的分页列表
     * @param page
     * @param keyword
     * @return
     */
    Page<PerformanceDTO> getPerformancePage(Page<PerformanceDTO> page, String keyword, Long depId, Integer year, Integer quarter);

    /**
     * 通过绩效评价id获取
     * @param performanceId
     * @return
     */
    PerformanceDTO getPerformanceById(Long performanceId);

    /**
     * 新增绩效评价
     * @param performanceDTO
     * @return
     */
    Long addPerformance(PerformanceDTO performanceDTO);

    /**
     * 修改绩效评价
     * @param performanceDTO
     * @return
     */
    Long updatePerformance(PerformanceDTO performanceDTO);

    /**
     * 删除绩效评价(通过绩效评价id删除)
     * @param performanceId
     */
    Long deletePerformance(Long performanceId);

    /**
     * 批量删除绩效评价(通过绩效评价id删除)
     * @param performanceIds
     * @return
     */
    List<Long> batchDeletePerformance(List<Long> performanceIds);

    /**
     * 生成绩效评价导入模板
     */
    void generatePerformanceTemplate();

    /**
     * 导入绩效评价
     * @param file
     * @return
     */
    String importPerformance(MultipartFile file);

    /**
     * 导出绩效评价
     * @param keyword
     * @param depId
     * @param year
     * @param quarter
     */
    void exportPerformance(String keyword, Long depId, Integer year, Integer quarter);

    /**
     * 通过员工id、年度、季度获取绩效
     * @param staffId
     * @param year
     * @param quarter
     * @return
     */
    PerformanceDTO getPerformanceByStaffIdAndYearAndQuarter(Long staffId, Integer year, Integer quarter);

}
