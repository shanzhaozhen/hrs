package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.QualificationDTO;

import java.util.List;

public interface QualificationService {

    /**
     * 获取职业资格的分页列表
     * @param page
     * @param keyword
     * @return
     */
    Page<QualificationDTO> getQualificationPage(Page<QualificationDTO> page, String keyword);

    /**
     * 通过职业资格id获取
     * @param qualificationId
     * @return
     */
    QualificationDTO getQualificationById(Long qualificationId);

    /**
     * 通过pid获取职业资格信息
     * @param pid
     * @return
     */
    List<QualificationDTO> getQualificationListByStaffId(Long pid);

    /**
     * 新增职业资格
     * @param qualificationDTO
     * @return
     */
    Long addQualification(QualificationDTO qualificationDTO);

    /**
     * 修改职业资格
     * @param qualificationDTO
     * @return
     */
    Long updateQualification(QualificationDTO qualificationDTO);

    /**
     * 删除职业资格(通过职业资格id删除)
     * @param qualificationId
     */
    Long deleteQualification(Long qualificationId);

    /**
     * 批量删除职业资格(通过职业资格id删除)
     * @param qualificationIds
     * @return
     */
    List<Long> batchDeleteQualification(List<Long> qualificationIds);

    /**
     * 通过员工id删除工作履历
     * @param staffId
     * @return
     */
    long deleteQualificationByStaffId(Long staffId);

    /**
     * 批量添加工作履历
     * @param qualificationDTOList
     * @param staffId
     */
    void batchAddQualification(List<QualificationDTO> qualificationDTOList, Long staffId);

}
