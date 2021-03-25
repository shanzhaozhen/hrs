package com.hbjs.hrscommon.converter;

import com.hbjs.hrscommon.domain.sys.DictionaryDO;
import com.hbjs.hrscommon.dto.DictionaryDTO;
import com.hbjs.hrscommon.form.DictionaryForm;
import com.hbjs.hrscommon.vo.DictionaryVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class DictionaryConverter {

    /**
     * DictionaryDTO 转换 DictionaryDO
     * @param dictionaryDTO
     * @return
     */
    public static DictionaryDO toDO(DictionaryDTO dictionaryDTO) {
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
        DictionaryVO dictionaryVO = new DictionaryVO();
        BeanUtils.copyProperties(dictionaryDTO, dictionaryVO);
        if (dictionaryDTO.getChildren() != null && dictionaryDTO.getChildren().size() > 0) {
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
        List<DictionaryVO> dictionaryVOList = new ArrayList<>();
        for (DictionaryDTO dictionaryDTO : dictionaryDTOList) {
            dictionaryVOList.add(toVO(dictionaryDTO));
        }
        return dictionaryVOList;
    }

}
