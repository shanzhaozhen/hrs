package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.TitleDO;
import com.hbjs.hrscommon.dto.TitleDTO;
import com.hbjs.hrscommon.form.TitleForm;
import com.hbjs.hrscommon.vo.TitleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class TitleConverter {
    
    /**
     * TitleDTO 转换 TitleDO
     * @param titleDTO
     * @return
     */
    public static TitleDO toDO(TitleDTO titleDTO) {
        TitleDO titleDO = new TitleDO();
        BeanUtils.copyProperties(titleDTO, titleDO);
        return titleDO;
    }

    /**
     * TitleForm 转换 TitleDTO
     * @param titleForm
     * @return
     */
    public static TitleDTO toDTO(TitleForm titleForm) {
        TitleDTO titleDTO = new TitleDTO();
        BeanUtils.copyProperties(titleForm, titleDTO);
        return titleDTO;
    }

    /**
     * TitleVO 转换 TitleDTO
     * @param titleVO
     * @return
     */
    public static TitleDTO toDTO(TitleVO titleVO) {
        TitleDTO titleDTO = new TitleDTO();
        BeanUtils.copyProperties(titleVO, titleDTO);
        return titleDTO;
    }

    /**
     * TitleDO 转换 TitleDTO
     * @param titleDO
     * @return
     */
    public static TitleDTO toDTO(TitleDO titleDO) {
        TitleDTO titleDTO = new TitleDTO();
        BeanUtils.copyProperties(titleDO, titleDTO);
        return titleDTO;
    }

    /**
     * List<TitleForm> 转换 List<TitleDTO>
     * @param titleFormList
     * @return
     */
    public static List<TitleDTO> toDTO(List<TitleForm> titleFormList) {
        if (CollectionUtils.isEmpty(titleFormList)) return null;
        List<TitleDTO> titleDTOList = new ArrayList<>();
        for (TitleForm titleForm : titleFormList) {
            titleDTOList.add(toDTO(titleForm));
        }
        return titleDTOList;
    }

    /**
     * TitleDTO 转换 TitleVO
     * @param titleDTO
     * @return
     */
    public static TitleVO toVO(TitleDTO titleDTO) {
        TitleVO titleVO = new TitleVO();
        BeanUtils.copyProperties(titleDTO, titleVO);
        return titleVO;
    }

    /**
     * List<TitleDTO> 转换 List<TitleVO>
     * @param titleDTOList
     * @return
     */
    public static List<TitleVO> toVO(List<TitleDTO> titleDTOList) {
        if (CollectionUtils.isEmpty(titleDTOList)) return null;
        List<TitleVO> titleVOList = new ArrayList<>();
        for (TitleDTO titleDTO : titleDTOList) {
            titleVOList.add(toVO(titleDTO));
        }
        return titleVOList;
    }

    /**
     * Page<TitleDTO> 转换 Page<TitleVO>
     * @param titleDTOPage
     * @return
     */
    public static Page<TitleVO> toVO(Page<TitleDTO> titleDTOPage) {
        List<TitleDTO> titleDTOList = titleDTOPage.getRecords();
        Page<TitleVO> titleVOPage = new Page<>();
        BeanUtils.copyProperties(titleDTOPage, titleVOPage);
        titleVOPage.setRecords(toVO(titleDTOList));
        return titleVOPage;
    }

}
