package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.SalaryDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SalaryService {

    /**
     * 获取薪资发放的分页列表
     * @param page
     * @param keyword
     * @return
     */
    Page<SalaryDTO> getSalaryPage(Page<SalaryDTO> page, String keyword, Long depId);

    /**
     * 通过薪资发放id获取
     * @param salaryId
     * @return
     */
    SalaryDTO getSalaryById(Long salaryId);

    /**
     * 新增薪资发放
     * @param salaryDTO
     * @return
     */
    Long addSalary(SalaryDTO salaryDTO);

    /**
     * 修改薪资发放
     * @param salaryDTO
     * @return
     */
    Long updateSalary(SalaryDTO salaryDTO);

    /**
     * 删除薪资发放(通过薪资发放id删除)
     * @param salaryId
     */
    Long deleteSalary(Long salaryId);

    /**
     * 批量删除薪资发放(通过薪资发放id删除)
     * @param salaryIds
     * @return
     */
    List<Long> batchDeleteSalary(List<Long> salaryIds);

    /**
     * 生成薪资发放导入模板
     */
    void generateSalaryTemplate();

    /**
     * 导入薪资发放
     * @param file
     * @return
     */
    String importSalary(MultipartFile file);

    /**
     * 导出薪资发放
     * @param keyword
     * @param depId
     */
    void exportSalary(String keyword, Long depId);

}
