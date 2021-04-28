package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.CertificateConverter;
import com.hbjs.hrscommon.domain.hr.CertificateDO;
import com.hbjs.hrscommon.dto.CertificateDTO;
import com.hbjs.hrscommon.dto.CertificateDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrsrepo.mapper.CertificateMapper;
import com.hbjs.hrsservice.service.CertificateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final CertificateMapper certificateMapper;

    @Override
    public Page<CertificateDTO> getCertificatePage(Page<CertificateDTO> page, String keyword) {
        return certificateMapper.getCertificatePage(page, keyword);
    }

    @Override
    public CertificateDTO getCertificateById(Long certificateId) {
        CertificateDO certificateDO = certificateMapper.selectById(certificateId);
        Assert.notNull(certificateDO, "获取失败：没有找到该证书或已被删除");
        return CertificateConverter.toDTO(certificateDO);
    }

    @Override
    public List<CertificateDTO> getCertificateListByPid(Long pid) {
        return certificateMapper.getCertificateListByPid(pid);
    }

    @Override
    @Transactional
    public Long addCertificate(CertificateDTO certificateDTO) {
        CertificateDO certificateDO = CertificateConverter.toDO(certificateDTO);
        certificateMapper.insert(certificateDO);
        return certificateDO.getId();
    }

    @Override
    @Transactional
    public Long updateCertificate(CertificateDTO certificateDTO) {
        Assert.notNull(certificateDTO.getId(), "证书id不能为空");
        CertificateDO certificateDO = certificateMapper.selectById(certificateDTO.getId());
        Assert.notNull(certificateDO, "更新失败：没有找到该证书或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(certificateDTO, certificateDO);
        certificateMapper.updateById(certificateDO);
        return certificateDO.getId();
    }

    @Override
    @Transactional
    public Long deleteCertificate(Long certificateId) {
        CertificateDTO certificateDTO = this.getCertificateById(certificateId);
        Assert.notNull(certificateDTO, "删除失败：没有找到该证书或已被删除");
        certificateMapper.deleteById(certificateId);
        return certificateId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteCertificate(@NotEmpty(message = "没有需要删除的证书") List<Long> certificateIds) {
        for (Long certificateId : certificateIds) {
            this.deleteCertificate(certificateId);
        }
        return certificateIds;
    }

    @Override
    public long deleteCertificateByStaffId(Long staffId) {
        return certificateMapper.deleteCertificateByStaffId(staffId);
    }

    @Override
    public void batchAddCertificate(List<CertificateDTO> certificateDTOList, Long staffId) {
        this.deleteCertificateByStaffId(staffId);
        for (CertificateDTO certificateDTO : certificateDTOList) {
            certificateDTO.setId(null).setPid(staffId);
            this.addCertificate(certificateDTO);
        }
    }

}
