package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.SalaryChangeDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SalaryChangeService {

    /**
     * 获取薪资变动
     * @param page
     * @param staffId
     * @param keyword
     * @param depId
     * @return
     */
    Page<SalaryChangeDTO> getSalaryChangePage(Page<SalaryChangeDTO> page, Long staffId, String keyword, Long depId);

    /**
     * 通过ID获取薪资变动
     * @param salaryChangeId
     * @return
     */
    SalaryChangeDTO getSalaryChangeById(Long salaryChangeId);

    /**
     * 获取近几天的薪资变动
     * @param days
     * @return
     */
    List<SalaryChangeDTO> getSalaryChangeInDays(int days);

    /**
     * 添加薪资变动
     * @param salaryChangeDTO
     * @return
     */
    Long addSalaryChange(SalaryChangeDTO salaryChangeDTO);

    /**
     * 更新薪资变动
     * @param salaryChangeDTO
     * @return
     */
    Long updateSalaryChange(SalaryChangeDTO salaryChangeDTO);

    /**
     * 删除薪资变动
     * @param salaryChangeId
     * @return
     */
    Long deleteSalaryChange(Long salaryChangeId);

    /**
     * 批量删除薪资变动
     * @param salaryChangeIds
     * @return
     */
    List<Long> batchDeleteSalaryChange(List<Long> salaryChangeIds);

    /**
     * 生成薪资变动导入模板
     */
    void generateSalaryChangeTemplate();

    /**
     * 导出薪资变动
     * @param staffId
     * @param keyword
     * @param depId
     */
    void exportSalaryChange(Long staffId, String keyword, Long depId);

}
