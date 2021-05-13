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
     * @param resumeId
     * @return
     */
    ResumeDTO getResumeById(Long resumeId);

    /**
     * 新增简历
     *
     * @param resumeDTO
     * @return
     */
    Long addResume(ResumeDTO resumeDTO);

    /**
     * 修改简历
     *
     * @param resumeDTO
     * @return
     */
    Long updateResume(ResumeDTO resumeDTO);

    /**
     * 更新员工其他信息
     * @param resumeDTO
     * @return
     */
    void updateResumeMoreInfo(ResumeDTO resumeDTO, Long resumeId);


    /**
     * 删除简历(通过简历id删除)
     *
     * @param resumeId
     */
    Long deleteResume(Long resumeId);

    /**
     * 批量删除简历(通过简历id删除)
     *
     * @param resumeIds
     * @return
     */
    List<Long> batchDeleteResume(List<Long> resumeIds);

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