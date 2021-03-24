package com.sbgs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbgs.hrscommon.domain.sys.CustomTaskDO;
import com.sbgs.hrscommon.dto.CustomTaskDTO;
import com.sbgs.hrscommon.form.CustomTaskForm;
import com.sbgs.hrscommon.vo.CustomTaskVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomTaskConverter {
    
    /**
     * CustomTaskDTO 转换 CustomTaskDO
     * @param customTaskDTO
     * @return
     */
    public static CustomTaskDO toDO(CustomTaskDTO customTaskDTO) {
        CustomTaskDO customTaskDO = new CustomTaskDO();
        BeanUtils.copyProperties(customTaskDTO, customTaskDO);
        return customTaskDO;
    }

    /**
     * CustomTaskForm 转换 CustomTaskDTO
     * @param customTaskForm
     * @return
     */
    public static CustomTaskDTO toDTO(CustomTaskForm customTaskForm) {
        CustomTaskDTO customTaskDTO = new CustomTaskDTO();
        BeanUtils.copyProperties(customTaskForm, customTaskDTO);
        return customTaskDTO;
    }

    /**
     * CustomTaskVO 转换 CustomTaskDTO
     * @param customTaskVO
     * @return
     */
    public static CustomTaskDTO toDTO(CustomTaskVO customTaskVO) {
        CustomTaskDTO customTaskDTO = new CustomTaskDTO();
        BeanUtils.copyProperties(customTaskVO, customTaskDTO);
        return customTaskDTO;
    }

    /**
     * CustomTaskDO 转换 CustomTaskDTO
     * @param customTaskDO
     * @return
     */
    public static CustomTaskDTO toDTO(CustomTaskDO customTaskDO) {
        CustomTaskDTO customTaskDTO = new CustomTaskDTO();
        BeanUtils.copyProperties(customTaskDO, customTaskDTO);
        return customTaskDTO;
    }

    /**
     * CustomTaskDTO 转换 CustomTaskVO
     * @param customTaskDTO
     * @return
     */
    public static CustomTaskVO toVO(CustomTaskDTO customTaskDTO) {
        CustomTaskVO customTaskVO = new CustomTaskVO();
        BeanUtils.copyProperties(customTaskDTO, customTaskVO);
        return customTaskVO;
    }

    /**
     * List<CustomTaskDTO> 转换 List<CustomTaskVO>
     * @param customTaskDTOList
     * @return
     */
    public static List<CustomTaskVO> toVO(List<CustomTaskDTO> customTaskDTOList) {
        List<CustomTaskVO> customTaskVOList = new ArrayList<>();
        for (CustomTaskDTO customTaskDTO : customTaskDTOList) {
            customTaskVOList.add(toVO(customTaskDTO));
        }
        return customTaskVOList;
    }

    /**
     * Page<CustomTaskDTO> 转换 Page<CustomTaskVO>
     * @param customTaskDTOPage
     * @return
     */
    public static Page<CustomTaskVO> toVO(Page<CustomTaskDTO> customTaskDTOPage) {
        List<CustomTaskDTO> customTaskDTOList = customTaskDTOPage.getRecords();
        Page<CustomTaskVO> customTaskVOPage = new Page<>();
        BeanUtils.copyProperties(customTaskDTOPage, customTaskVOPage);
        customTaskVOPage.setRecords(toVO(customTaskDTOList));
        return customTaskVOPage;
    }

}
