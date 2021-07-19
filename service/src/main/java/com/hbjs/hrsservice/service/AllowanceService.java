package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.AllowanceDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AllowanceService {

    /**
     * 获取福利津贴的分页列表
     * @param page
     * @param keyword
     * @return
     */
    Page<AllowanceDTO> getAllowancePage(Page<AllowanceDTO> page, String keyword, Long depId, Integer year, Integer quarter);

    /**
     * 通过福利津贴id获取
     * @param allowanceId
     * @return
     */
    AllowanceDTO getAllowanceById(Long allowanceId);

    /**
     * 新增福利津贴
     * @param allowanceDTO
     * @return
     */
    Long addAllowance(AllowanceDTO allowanceDTO);

    /**
     * 修改福利津贴
     * @param allowanceDTO
     * @return
     */
    Long updateAllowance(AllowanceDTO allowanceDTO);

    /**
     * 删除福利津贴(通过福利津贴id删除)
     * @param allowanceId
     */
    Long deleteAllowance(Long allowanceId);

    /**
     * 批量删除福利津贴(通过福利津贴id删除)
     * @param allowanceIds
     * @return
     */
    List<Long> batchDeleteAllowance(List<Long> allowanceIds);

    /**
     * 生成福利津贴导入模板
     */
    void generateAllowanceTemplate();

    /**
     * 导入福利津贴
     * @param file
     * @return
     */
    String importAllowance(MultipartFile file);

    /**
     * 导出福利津贴
     * @param keyword
     * @param depId
     * @param year
     * @param quarter
     */
    void exportAllowance(String keyword, Long depId, Integer year, Integer quarter);

    /**
     * 通过员工id、年度、季度获取绩效
     * @param staffId
     * @param month
     * @return
     */
    AllowanceDTO getAllowanceByStaffIdAndMonth(Long staffId, String month);

}
