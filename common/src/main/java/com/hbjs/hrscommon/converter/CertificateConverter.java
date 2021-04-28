package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.CertificateDO;
import com.hbjs.hrscommon.dto.CertificateDTO;
import com.hbjs.hrscommon.form.CertificateForm;
import com.hbjs.hrscommon.vo.CertificateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CertificateConverter {
    
    /**
     * CertificateDTO 转换 CertificateDO
     * @param certificateDTO
     * @return
     */
    public static CertificateDO toDO(CertificateDTO certificateDTO) {
        CertificateDO certificateDO = new CertificateDO();
        BeanUtils.copyProperties(certificateDTO, certificateDO);
        return certificateDO;
    }

    /**
     * CertificateForm 转换 CertificateDTO
     * @param certificateForm
     * @return
     */
    public static CertificateDTO toDTO(CertificateForm certificateForm) {
        CertificateDTO certificateDTO = new CertificateDTO();
        BeanUtils.copyProperties(certificateForm, certificateDTO);
        return certificateDTO;
    }

    /**
     * CertificateVO 转换 CertificateDTO
     * @param certificateVO
     * @return
     */
    public static CertificateDTO toDTO(CertificateVO certificateVO) {
        CertificateDTO certificateDTO = new CertificateDTO();
        BeanUtils.copyProperties(certificateVO, certificateDTO);
        return certificateDTO;
    }

    /**
     * CertificateDO 转换 CertificateDTO
     * @param certificateDO
     * @return
     */
    public static CertificateDTO toDTO(CertificateDO certificateDO) {
        CertificateDTO certificateDTO = new CertificateDTO();
        BeanUtils.copyProperties(certificateDO, certificateDTO);
        return certificateDTO;
    }

    /**
     * List<CertificateForm> 转换 List<CertificateDTO>
     * @param certificateFormList
     * @return
     */
    public static List<CertificateDTO> toDTO(List<CertificateForm> certificateFormList) {
        if (CollectionUtils.isEmpty(certificateFormList)) return null;
        List<CertificateDTO> certificateDTOList = new ArrayList<>();
        for (CertificateForm certificateForm : certificateFormList) {
            certificateDTOList.add(toDTO(certificateForm));
        }
        return certificateDTOList;
    }

    /**
     * CertificateDTO 转换 CertificateVO
     * @param certificateDTO
     * @return
     */
    public static CertificateVO toVO(CertificateDTO certificateDTO) {
        CertificateVO certificateVO = new CertificateVO();
        BeanUtils.copyProperties(certificateDTO, certificateVO);
        return certificateVO;
    }

    /**
     * List<CertificateDTO> 转换 List<CertificateVO>
     * @param certificateDTOList
     * @return
     */
    public static List<CertificateVO> toVO(List<CertificateDTO> certificateDTOList) {
        if (CollectionUtils.isEmpty(certificateDTOList)) return null;
        List<CertificateVO> certificateVOList = new ArrayList<>();
        for (CertificateDTO certificateDTO : certificateDTOList) {
            certificateVOList.add(toVO(certificateDTO));
        }
        return certificateVOList;
    }

    /**
     * Page<CertificateDTO> 转换 Page<CertificateVO>
     * @param certificateDTOPage
     * @return
     */
    public static Page<CertificateVO> toVO(Page<CertificateDTO> certificateDTOPage) {
        List<CertificateDTO> certificateDTOList = certificateDTOPage.getRecords();
        Page<CertificateVO> certificateVOPage = new Page<>();
        BeanUtils.copyProperties(certificateDTOPage, certificateVOPage);
        certificateVOPage.setRecords(toVO(certificateDTOList));
        return certificateVOPage;
    }

}
