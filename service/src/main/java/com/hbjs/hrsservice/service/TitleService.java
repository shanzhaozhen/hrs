package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.TitleDTO;
import com.hbjs.hrscommon.excel.TitleExcel;

import java.util.List;

public interface TitleService {

    /**
     * 获取职称信息的分页列表
     * @param page
     * @param keyword
     * @return
     */
    Page<TitleDTO> getTitlePage(Page<TitleDTO> page, String keyword);

    /**
     * 通过职称信息id获取
     * @param titleId
     * @return
     */
    TitleDTO getTitleById(Long titleId);

    /**
     * 通过pid获取职称信息信息
     * @param pid
     * @return
     */
    List<TitleDTO> getTitleListByStaffId(Long pid);

    /**
     * 新增职称信息
     * @param titleDTO
     * @return
     */
    Long addTitle(TitleDTO titleDTO);

    /**
     * 修改职称信息
     * @param titleDTO
     * @return
     */
    Long updateTitle(TitleDTO titleDTO);

    /**
     * 删除职称信息(通过职称信息id删除)
     * @param titleId
     */
    Long deleteTitle(Long titleId);

    /**
     * 批量删除职称信息(通过职称信息id删除)
     * @param titleIds
     * @return
     */
    List<Long> batchDeleteTitle(List<Long> titleIds);

    /**
     * 通过员工id删除工作履历
     * @param staffId
     * @return
     */
    long deleteTitleByStaffId(Long staffId);

    /**
     * 批量添加工作履历
     * @param titleDTOList
     * @param staffId
     */
    void batchAddTitle(List<TitleDTO> titleDTOList, Long staffId);

    /**
     * 获取导出Excel的内容
     * @param keyword
     * @param depId
     * @param companyState
     * @param postLevel
     * @return
     */
    List<TitleExcel> getTitleExcelList(String keyword, Long depId, String companyState, String postLevel);
}
