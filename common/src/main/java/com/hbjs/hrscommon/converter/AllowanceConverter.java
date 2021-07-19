package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.AllowanceDO;
import com.hbjs.hrscommon.dto.AllowanceDTO;
import com.hbjs.hrscommon.form.AllowanceForm;
import com.hbjs.hrscommon.vo.AllowanceVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class AllowanceConverter {

    /**
     * AllowanceDTO 转换 AllowanceDO
     * @param allowanceDTO
     * @return
     */
    public static AllowanceDO toDO(AllowanceDTO allowanceDTO) {
        AllowanceDO allowanceDO = new AllowanceDO();
        BeanUtils.copyProperties(allowanceDTO, allowanceDO);
        return allowanceDO;
    }

    /**
     * AllowanceDO 转换 AllowanceDTO
     * @param allowanceDO
     * @return
     */
    public static AllowanceDTO toDTO(AllowanceDO allowanceDO) {
        AllowanceDTO allowanceDTO = new AllowanceDTO();
        BeanUtils.copyProperties(allowanceDO, allowanceDTO);
        return allowanceDTO;
    }

    /**
     * AllowanceForm 转换 AllowanceDTO
     * @param allowanceForm
     * @return
     */
    public static AllowanceDTO toDTO(AllowanceForm allowanceForm) {
        AllowanceDTO allowanceDTO = new AllowanceDTO();
        BeanUtils.copyProperties(allowanceForm, allowanceDTO);
        return allowanceDTO;
    }

    /**
     * AllowanceDTO 转换 AllowanceVO
     * @param allowanceDTO
     * @return
     */
    public static AllowanceVO toVO(AllowanceDTO allowanceDTO) {
        AllowanceVO allowanceVO = new AllowanceVO();
        BeanUtils.copyProperties(allowanceDTO, allowanceVO);
        return allowanceVO;
    }

    /**
     * List<AllowanceDTO> 转换 List<AllowanceVO>
     * @param allowanceDTOList
     * @return
     */
    public static List<AllowanceVO> toVO(List<AllowanceDTO> allowanceDTOList) {
        List<AllowanceVO> allowanceVOList = new ArrayList<>();
        for (AllowanceDTO allowanceDTO : allowanceDTOList) {
            allowanceVOList.add(toVO(allowanceDTO));
        }
        return allowanceVOList;
    }

    /**
     * Page<AllowanceDTO> 转换 Page<AllowanceVO>
     * @param fileDTOPage
     * @return
     */
    public static Page<AllowanceVO> toVO(Page<AllowanceDTO> fileDTOPage) {
        List<AllowanceDTO> fileDTOList = fileDTOPage.getRecords();
        Page<AllowanceVO> fileVOPage = new Page<>();
        BeanUtils.copyProperties(fileDTOPage, fileVOPage);
        fileVOPage.setRecords(toVO(fileDTOList));
        return fileVOPage;
    }

}
