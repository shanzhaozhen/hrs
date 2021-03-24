package com.hbjs.hrscommon.converter;

import com.hbjs.hrscommon.domain.sys.ResourceDO;
import com.hbjs.hrscommon.dto.ResourceDTO;
import com.hbjs.hrscommon.form.ResourceForm;
import com.hbjs.hrscommon.vo.ResourceVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ResourceConverter {

    /**
     * ResourceDTO 转换 ResourceDO
     * @param resourceDTO
     * @return
     */
    public static ResourceDO toDO(ResourceDTO resourceDTO) {
        ResourceDO resourceDO = new ResourceDO();
        BeanUtils.copyProperties(resourceDTO, resourceDO);
        return resourceDO;
    }

    /**
     * resourceForm 转换 ResourceDTO
     * @param resourceForm
     * @return
     */
    public static ResourceDTO toDTO(ResourceForm resourceForm) {
        ResourceDTO resourceDTO = new ResourceDTO();
        BeanUtils.copyProperties(resourceForm, resourceDTO);
        return resourceDTO;
    }

    /**
     * ResourceDO 转换 ResourceDTO
     * @param resourceDO
     * @return
     */
    public static ResourceDTO toDTO(ResourceDO resourceDO) {
        ResourceDTO resourceDTO = new ResourceDTO();
        BeanUtils.copyProperties(resourceDO, resourceDTO);
        return resourceDTO;
    }

    /**
     * ResourceDTO 转换 ResourceVO
     * @param resourceDTO
     * @return
     */
    public static ResourceVO toVO(ResourceDTO resourceDTO) {
        ResourceVO resourceVO = new ResourceVO();
        BeanUtils.copyProperties(resourceDTO, resourceVO);
        if (resourceDTO.getChildren() != null && resourceDTO.getChildren().size() > 0) {
            resourceVO.setChildren(toVO(resourceDTO.getChildren()));
        }
        return resourceVO;
    }

    /**
     * List<ResourceDTO> 转换 List<ResourceVO>
     * @param resourceDTOList
     * @return
     */
    public static List<ResourceVO> toVO(List<ResourceDTO> resourceDTOList) {
        List<ResourceVO> resourceVOList = new ArrayList<>();
        for (ResourceDTO resourceDTO : resourceDTOList) {
            resourceVOList.add(toVO(resourceDTO));
        }
        return resourceVOList;
    }

}
