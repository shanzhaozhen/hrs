package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.QualificationDO;
import com.hbjs.hrscommon.dto.QualificationDTO;
import com.hbjs.hrscommon.form.QualificationForm;
import com.hbjs.hrscommon.vo.QualificationVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class QualificationConverter {
    
    /**
     * QualificationDTO 转换 QualificationDO
     * @param qualificationDTO
     * @return
     */
    public static QualificationDO toDO(QualificationDTO qualificationDTO) {
        QualificationDO qualificationDO = new QualificationDO();
        BeanUtils.copyProperties(qualificationDTO, qualificationDO);
        return qualificationDO;
    }

    /**
     * QualificationForm 转换 QualificationDTO
     * @param qualificationForm
     * @return
     */
    public static QualificationDTO toDTO(QualificationForm qualificationForm) {
        QualificationDTO qualificationDTO = new QualificationDTO();
        BeanUtils.copyProperties(qualificationForm, qualificationDTO);
        return qualificationDTO;
    }

    /**
     * QualificationVO 转换 QualificationDTO
     * @param qualificationVO
     * @return
     */
    public static QualificationDTO toDTO(QualificationVO qualificationVO) {
        QualificationDTO qualificationDTO = new QualificationDTO();
        BeanUtils.copyProperties(qualificationVO, qualificationDTO);
        return qualificationDTO;
    }

    /**
     * QualificationDO 转换 QualificationDTO
     * @param qualificationDO
     * @return
     */
    public static QualificationDTO toDTO(QualificationDO qualificationDO) {
        QualificationDTO qualificationDTO = new QualificationDTO();
        BeanUtils.copyProperties(qualificationDO, qualificationDTO);
        return qualificationDTO;
    }

    /**
     * List<QualificationForm> 转换 List<QualificationDTO>
     * @param qualificationFormList
     * @return
     */
    public static List<QualificationDTO> toDTO(List<QualificationForm> qualificationFormList) {
        if (CollectionUtils.isEmpty(qualificationFormList)) return null;
        List<QualificationDTO> qualificationDTOList = new ArrayList<>();
        for (QualificationForm qualificationForm : qualificationFormList) {
            qualificationDTOList.add(toDTO(qualificationForm));
        }
        return qualificationDTOList;
    }

    /**
     * QualificationDTO 转换 QualificationVO
     * @param qualificationDTO
     * @return
     */
    public static QualificationVO toVO(QualificationDTO qualificationDTO) {
        QualificationVO qualificationVO = new QualificationVO();
        BeanUtils.copyProperties(qualificationDTO, qualificationVO);
        return qualificationVO;
    }

    /**
     * List<QualificationDTO> 转换 List<QualificationVO>
     * @param qualificationDTOList
     * @return
     */
    public static List<QualificationVO> toVO(List<QualificationDTO> qualificationDTOList) {
        if (CollectionUtils.isEmpty(qualificationDTOList)) return null;
        List<QualificationVO> qualificationVOList = new ArrayList<>();
        for (QualificationDTO qualificationDTO : qualificationDTOList) {
            qualificationVOList.add(toVO(qualificationDTO));
        }
        return qualificationVOList;
    }

    /**
     * Page<QualificationDTO> 转换 Page<QualificationVO>
     * @param qualificationDTOPage
     * @return
     */
    public static Page<QualificationVO> toVO(Page<QualificationDTO> qualificationDTOPage) {
        List<QualificationDTO> qualificationDTOList = qualificationDTOPage.getRecords();
        Page<QualificationVO> qualificationVOPage = new Page<>();
        BeanUtils.copyProperties(qualificationDTOPage, qualificationVOPage);
        qualificationVOPage.setRecords(toVO(qualificationDTOList));
        return qualificationVOPage;
    }

}
