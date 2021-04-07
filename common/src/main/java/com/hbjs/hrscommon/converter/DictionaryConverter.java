package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.sys.DictionaryDO;
import com.hbjs.hrscommon.dto.DictionaryDTO;
import com.hbjs.hrscommon.form.DictionaryForm;
import com.hbjs.hrscommon.vo.DictionaryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class DictionaryConverter {

    /**
     * DictionaryDTO 转换 DictionaryDO
     * @param dictionaryDTO
     * @return
     */
    public static DictionaryDO toDO(DictionaryDTO dictionaryDTO) {
        if (dictionaryDTO == null) return null;

        DictionaryDO dictionaryDO = new DictionaryDO();
        BeanUtils.copyProperties(dictionaryDTO, dictionaryDO);
        return dictionaryDO;
    }

    /**
     * DictionaryForm 转换 DictionaryDTO
     * @param dictionaryForm
     * @return
     */
    public static DictionaryDTO toDTO(DictionaryForm dictionaryForm) {
        if (dictionaryForm == null) return null;

        DictionaryDTO dictionaryDTO = new DictionaryDTO();
        BeanUtils.copyProperties(dictionaryForm, dictionaryDTO);
        return dictionaryDTO;
    }

    /**
     * DictionaryDO 转换 DictionaryDTO
     * @param dictionaryDO
     * @return
     */
    public static DictionaryDTO toDTO(DictionaryDO dictionaryDO) {
        if (dictionaryDO == null) return null;

        DictionaryDTO dictionaryDTO = new DictionaryDTO();
        BeanUtils.copyProperties(dictionaryDO, dictionaryDTO);
        return dictionaryDTO;
    }

    /**
     * DictionaryDTO 转换 DictionaryVO
     * @param dictionaryDTO
     * @return
     */
    public static DictionaryVO toVO(DictionaryDTO dictionaryDTO) {
        if (dictionaryDTO == null) return null;

        DictionaryVO dictionaryVO = new DictionaryVO();
        BeanUtils.copyProperties(dictionaryDTO, dictionaryVO);
        if (!CollectionUtils.isEmpty(dictionaryDTO.getChildren())) {
            dictionaryVO.setChildren(toVO(dictionaryDTO.getChildren()));
        }
        return dictionaryVO;
    }

    /**
     * List<DictionaryDTO> 转换 List<DictionaryVO>
     * @param dictionaryDTOList
     * @return
     */
    public static List<DictionaryVO> toVO(List<DictionaryDTO> dictionaryDTOList) {
        if (dictionaryDTOList == null) return null;

        List<DictionaryVO> dictionaryVOList = new ArrayList<>();
        for (DictionaryDTO dictionaryDTO : dictionaryDTOList) {
            dictionaryVOList.add(toVO(dictionaryDTO));
        }
        return dictionaryVOList;
    }

    /**
     * Page<DictionaryDTO> 转换 Page<DictionaryVO>
     * @param dictionaryDTOPage
     * @return
     */
    public static Page<DictionaryVO> toVO(Page<DictionaryDTO> dictionaryDTOPage) {
        if (dictionaryDTOPage == null) return null;

        List<DictionaryDTO> dictionaryDTOList = dictionaryDTOPage.getRecords();
        Page<DictionaryVO> dictionaryVOPage = new Page<>();
        BeanUtils.copyProperties(dictionaryDTOPage, dictionaryVOPage);
        dictionaryVOPage.setRecords(toVO(dictionaryDTOList));
        return dictionaryVOPage;
    }

}
