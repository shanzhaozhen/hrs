package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.ContractDO;
import com.hbjs.hrscommon.dto.ContractDTO;
import com.hbjs.hrscommon.form.ContractForm;
import com.hbjs.hrscommon.vo.ContractVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ContractConverter {
    
    /**
     * ContractDTO 转换 ContractDO
     * @param contractDTO
     * @return
     */
    public static ContractDO toDO(ContractDTO contractDTO) {
        ContractDO contractDO = new ContractDO();
        BeanUtils.copyProperties(contractDTO, contractDO);
        return contractDO;
    }

    /**
     * ContractForm 转换 ContractDTO
     * @param contractForm
     * @return
     */
    public static ContractDTO toDTO(ContractForm contractForm) {
        ContractDTO contractDTO = new ContractDTO();
        BeanUtils.copyProperties(contractForm, contractDTO);
        return contractDTO;
    }

    /**
     * ContractVO 转换 ContractDTO
     * @param contractVO
     * @return
     */
    public static ContractDTO toDTO(ContractVO contractVO) {
        ContractDTO contractDTO = new ContractDTO();
        BeanUtils.copyProperties(contractVO, contractDTO);
        return contractDTO;
    }

    /**
     * ContractDO 转换 ContractDTO
     * @param contractDO
     * @return
     */
    public static ContractDTO toDTO(ContractDO contractDO) {
        ContractDTO contractDTO = new ContractDTO();
        BeanUtils.copyProperties(contractDO, contractDTO);
        return contractDTO;
    }

    /**
     * List<ContractForm> 转换 List<ContractDTO>
     * @param contractFormList
     * @return
     */
    public static List<ContractDTO> toDTO(List<ContractForm> contractFormList) {
        if (CollectionUtils.isEmpty(contractFormList)) return null;
        List<ContractDTO> contractDTOList = new ArrayList<>();
        for (ContractForm contractForm : contractFormList) {
            contractDTOList.add(toDTO(contractForm));
        }
        return contractDTOList;
    }

    /**
     * ContractDTO 转换 ContractVO
     * @param contractDTO
     * @return
     */
    public static ContractVO toVO(ContractDTO contractDTO) {
        ContractVO contractVO = new ContractVO();
        BeanUtils.copyProperties(contractDTO, contractVO);
        return contractVO;
    }

    /**
     * List<ContractDTO> 转换 List<ContractVO>
     * @param contractDTOList
     * @return
     */
    public static List<ContractVO> toVO(List<ContractDTO> contractDTOList) {
        if (CollectionUtils.isEmpty(contractDTOList)) return null;
        List<ContractVO> contractVOList = new ArrayList<>();
        for (ContractDTO contractDTO : contractDTOList) {
            contractVOList.add(toVO(contractDTO));
        }
        return contractVOList;
    }

    /**
     * Page<ContractDTO> 转换 Page<ContractVO>
     * @param contractDTOPage
     * @return
     */
    public static Page<ContractVO> toVO(Page<ContractDTO> contractDTOPage) {
        List<ContractDTO> contractDTOList = contractDTOPage.getRecords();
        Page<ContractVO> contractVOPage = new Page<>();
        BeanUtils.copyProperties(contractDTOPage, contractVOPage);
        contractVOPage.setRecords(toVO(contractDTOList));
        return contractVOPage;
    }

}
