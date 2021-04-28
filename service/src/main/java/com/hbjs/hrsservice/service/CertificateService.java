package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.CertificateDTO;
import com.hbjs.hrscommon.dto.CertificateDTO;

import java.util.List;

public interface CertificateService {

    /**
     * 获取证书的分页列表
     * @param page
     * @param keyword
     * @return
     */
    Page<CertificateDTO> getCertificatePage(Page<CertificateDTO> page, String keyword);

    /**
     * 通过证书id获取
     * @param certificateId
     * @return
     */
    CertificateDTO getCertificateById(Long certificateId);

    /**
     * 通过pid获取证书信息
     * @param pid
     * @return
     */
    List<CertificateDTO> getCertificateListByPid(Long pid);

    /**
     * 新增证书
     * @param certificateDTO
     * @return
     */
    Long addCertificate(CertificateDTO certificateDTO);

    /**
     * 修改证书
     * @param certificateDTO
     * @return
     */
    Long updateCertificate(CertificateDTO certificateDTO);

    /**
     * 删除证书(通过证书id删除)
     * @param certificateId
     */
    Long deleteCertificate(Long certificateId);

    /**
     * 批量删除证书(通过证书id删除)
     * @param certificateIds
     * @return
     */
    List<Long> batchDeleteCertificate(List<Long> certificateIds);

    /**
     * 通过员工id删除工作履历
     * @param staffId
     */
    long deleteCertificateByStaffId(Long staffId);

    /**
     * 批量添加工作履历
     * @param certificateDTOList
     * @param staffId
     */
    void batchAddCertificate(List<CertificateDTO> certificateDTOList, Long staffId);

}
