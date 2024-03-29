package com.hbjs.hrscommon.converter;

import com.hbjs.hrscommon.domain.sys.DepartmentDO;
import com.hbjs.hrscommon.dto.DepartmentDTO;
import com.hbjs.hrscommon.form.DepartmentForm;
import com.hbjs.hrscommon.vo.DepartmentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class DepartmentConverter {

    /**
     * DepartmentDTO 转换 DepartmentDO
     * @param departmentDTO
     * @return
     */
    public static DepartmentDO toDO(DepartmentDTO departmentDTO) {
        DepartmentDO departmentDO = new DepartmentDO();
        BeanUtils.copyProperties(departmentDTO, departmentDO);
        return departmentDO;
    }

    /**
     * departmentForm 转换 DepartmentDTO
     * @param departmentForm
     * @return
     */
    public static DepartmentDTO toDTO(DepartmentForm departmentForm) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        BeanUtils.copyProperties(departmentForm, departmentDTO);
        return departmentDTO;
    }

    /**
     * DepartmentDO 转换 DepartmentDTO
     * @param departmentDO
     * @return
     */
    public static DepartmentDTO toDTO(DepartmentDO departmentDO) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        BeanUtils.copyProperties(departmentDO, departmentDTO);
        return departmentDTO;
    }

    /**
     * DepartmentDTO 转换 DepartmentVO
     * @param departmentDTO
     * @return
     */
    public static DepartmentVO toVO(DepartmentDTO departmentDTO) {
        DepartmentVO departmentVO = new DepartmentVO();
        BeanUtils.copyProperties(departmentDTO, departmentVO);
        if (!CollectionUtils.isEmpty(departmentDTO.getChildren())) {
            departmentVO.setChildren(toVO(departmentDTO.getChildren()));
        }
        return departmentVO;
    }

    /**
     * List<DepartmentDTO> 转换 List<DepartmentVO>
     * @param departmentDTOList
     * @return
     */
    public static List<DepartmentVO> toVO(List<DepartmentDTO> departmentDTOList) {
        List<DepartmentVO> departmentVOList = new ArrayList<>();
        for (DepartmentDTO departmentDTO : departmentDTOList) {
            departmentVOList.add(toVO(departmentDTO));
        }
        return departmentVOList;
    }

}
