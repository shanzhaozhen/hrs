package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.TransferRecordDO;
import com.hbjs.hrscommon.dto.TransferRecordDTO;
import com.hbjs.hrscommon.form.TransferRecordForm;
import com.hbjs.hrscommon.vo.TransferRecordVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class TransferRecordConverter {
    
    /**
     * TransferRecordDTO 转换 TransferRecordDO
     * @param transferRecordDTO
     * @return
     */
    public static TransferRecordDO toDO(TransferRecordDTO transferRecordDTO) {
        TransferRecordDO transferRecordDO = new TransferRecordDO();
        BeanUtils.copyProperties(transferRecordDTO, transferRecordDO);
        return transferRecordDO;
    }

    /**
     * TransferRecordForm 转换 TransferRecordDTO
     * @param transferRecordForm
     * @return
     */
    public static TransferRecordDTO toDTO(TransferRecordForm transferRecordForm) {
        TransferRecordDTO transferRecordDTO = new TransferRecordDTO();
        BeanUtils.copyProperties(transferRecordForm, transferRecordDTO);
        return transferRecordDTO;
    }

    /**
     * TransferRecordVO 转换 TransferRecordDTO
     * @param transferRecordVO
     * @return
     */
    public static TransferRecordDTO toDTO(TransferRecordVO transferRecordVO) {
        TransferRecordDTO transferRecordDTO = new TransferRecordDTO();
        BeanUtils.copyProperties(transferRecordVO, transferRecordDTO);
        return transferRecordDTO;
    }

    /**
     * TransferRecordDO 转换 TransferRecordDTO
     * @param transferRecordDO
     * @return
     */
    public static TransferRecordDTO toDTO(TransferRecordDO transferRecordDO) {
        TransferRecordDTO transferRecordDTO = new TransferRecordDTO();
        BeanUtils.copyProperties(transferRecordDO, transferRecordDTO);
        return transferRecordDTO;
    }

    /**
     * TransferRecordDTO 转换 TransferRecordVO
     * @param transferRecordDTO
     * @return
     */
    public static TransferRecordVO toVO(TransferRecordDTO transferRecordDTO) {
        TransferRecordVO transferRecordVO = new TransferRecordVO();
        BeanUtils.copyProperties(transferRecordDTO, transferRecordVO);
        return transferRecordVO;
    }

    /**
     * List<TransferRecordDTO> 转换 List<TransferRecordVO>
     * @param transferRecordDTOList
     * @return
     */
    public static List<TransferRecordVO> toVO(List<TransferRecordDTO> transferRecordDTOList) {
        List<TransferRecordVO> transferRecordVOList = new ArrayList<>();
        for (TransferRecordDTO transferRecordDTO : transferRecordDTOList) {
            transferRecordVOList.add(toVO(transferRecordDTO));
        }
        return transferRecordVOList;
    }

    /**
     * Page<TransferRecordDTO> 转换 Page<TransferRecordVO>
     * @param transferRecordDTOPage
     * @return
     */
    public static Page<TransferRecordVO> toVO(Page<TransferRecordDTO> transferRecordDTOPage) {
        List<TransferRecordDTO> transferRecordDTOList = transferRecordDTOPage.getRecords();
        Page<TransferRecordVO> transferRecordVOPage = new Page<>();
        BeanUtils.copyProperties(transferRecordDTOPage, transferRecordVOPage);
        transferRecordVOPage.setRecords(toVO(transferRecordDTOList));
        return transferRecordVOPage;
    }

}
