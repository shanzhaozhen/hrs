package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.WorkRecordDO;
import com.hbjs.hrscommon.dto.WorkRecordDTO;
import com.hbjs.hrscommon.form.WorkRecordForm;
import com.hbjs.hrscommon.vo.WorkRecordVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class WorkRecordConverter {
    
    /**
     * WorkRecordDTO 转换 WorkRecordDO
     * @param workRecordDTO
     * @return
     */
    public static WorkRecordDO toDO(WorkRecordDTO workRecordDTO) {
        WorkRecordDO workRecordDO = new WorkRecordDO();
        BeanUtils.copyProperties(workRecordDTO, workRecordDO);
        return workRecordDO;
    }

    /**
     * WorkRecordForm 转换 WorkRecordDTO
     * @param workRecordForm
     * @return
     */
    public static WorkRecordDTO toDTO(WorkRecordForm workRecordForm) {
        WorkRecordDTO workRecordDTO = new WorkRecordDTO();
        BeanUtils.copyProperties(workRecordForm, workRecordDTO);
        return workRecordDTO;
    }

    /**
     * WorkRecordVO 转换 WorkRecordDTO
     * @param workRecordVO
     * @return
     */
    public static WorkRecordDTO toDTO(WorkRecordVO workRecordVO) {
        WorkRecordDTO workRecordDTO = new WorkRecordDTO();
        BeanUtils.copyProperties(workRecordVO, workRecordDTO);
        return workRecordDTO;
    }

    /**
     * WorkRecordDO 转换 WorkRecordDTO
     * @param workRecordDO
     * @return
     */
    public static WorkRecordDTO toDTO(WorkRecordDO workRecordDO) {
        WorkRecordDTO workRecordDTO = new WorkRecordDTO();
        BeanUtils.copyProperties(workRecordDO, workRecordDTO);
        return workRecordDTO;
    }

    /**
     * List<WorkRecordForm> 转换 List<WorkRecordDTO>
     * @param workRecordFormList
     * @return
     */
    public static List<WorkRecordDTO> toDTO(List<WorkRecordForm> workRecordFormList) {
        if (CollectionUtils.isEmpty(workRecordFormList)) return null;
        List<WorkRecordDTO> workRecordDTOList = new ArrayList<>();
        for (WorkRecordForm workRecordForm : workRecordFormList) {
            workRecordDTOList.add(toDTO(workRecordForm));
        }
        return workRecordDTOList;
    }

    /**
     * WorkRecordDTO 转换 WorkRecordVO
     * @param workRecordDTO
     * @return
     */
    public static WorkRecordVO toVO(WorkRecordDTO workRecordDTO) {
        WorkRecordVO workRecordVO = new WorkRecordVO();
        BeanUtils.copyProperties(workRecordDTO, workRecordVO);
        return workRecordVO;
    }

    /**
     * List<WorkRecordDTO> 转换 List<WorkRecordVO>
     * @param workRecordDTOList
     * @return
     */
    public static List<WorkRecordVO> toVO(List<WorkRecordDTO> workRecordDTOList) {
        if (CollectionUtils.isEmpty(workRecordDTOList)) return null;
        List<WorkRecordVO> workRecordVOList = new ArrayList<>();
        for (WorkRecordDTO workRecordDTO : workRecordDTOList) {
            workRecordVOList.add(toVO(workRecordDTO));
        }
        return workRecordVOList;
    }

    /**
     * Page<WorkRecordDTO> 转换 Page<WorkRecordVO>
     * @param workRecordDTOPage
     * @return
     */
    public static Page<WorkRecordVO> toVO(Page<WorkRecordDTO> workRecordDTOPage) {
        List<WorkRecordDTO> workRecordDTOList = workRecordDTOPage.getRecords();
        Page<WorkRecordVO> workRecordVOPage = new Page<>();
        BeanUtils.copyProperties(workRecordDTOPage, workRecordVOPage);
        workRecordVOPage.setRecords(toVO(workRecordDTOList));
        return workRecordVOPage;
    }

}
