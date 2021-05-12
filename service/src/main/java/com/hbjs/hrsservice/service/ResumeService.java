package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.ResumeDTO;

import java.util.List;

public interface ResumeService {

    /**
     * 获取简历的分页列表
     *
     * @param page
     * @param keyword
     * @return
     */
    Page<ResumeDTO> getResumePage(Page<ResumeDTO> page, String keyword);

    /**
     * 通过简历id获取
     *
     * @param staffId
     * @return
     */
    ResumeDTO getResumeById(Long staffId);

    /**
     * 新增简历
     *
     * @param staffDTO
     * @return
     */
    Long addResume(ResumeDTO staffDTO);

    /**
     * 修改简历
     *
     * @param staffDTO
     * @return
     */
    Long updateResume(ResumeDTO staffDTO);

    /**
     * 删除简历(通过简历id删除)
     *
     * @param staffId
     */
    Long deleteResume(Long staffId);

    /**
     * 批量删除简历(通过简历id删除)
     *
     * @param staffIds
     * @return
     */
    List<Long> batchDeleteResume(List<Long> staffIds);

    /**
     * 导出简历信息
     *
     * @param keyword
     */
    void exportResume(String keyword);

    /**
     * 打印求职申请表
     * @param resumeId
     */
    void printResume(Long resumeId);

}