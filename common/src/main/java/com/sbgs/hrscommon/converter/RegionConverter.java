package com.sbgs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbgs.hrscommon.domain.sys.RegionDO;
import com.sbgs.hrscommon.dto.RegionDTO;
import com.sbgs.hrscommon.form.RegionForm;
import com.sbgs.hrscommon.vo.RegionVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class RegionConverter {

    /**
     * RegionDTO 转换 RegionDO
     * @param regionDTO
     * @return
     */
    public static RegionDO toDO(RegionDTO regionDTO) {
        RegionDO regionDO = new RegionDO();
        BeanUtils.copyProperties(regionDTO, regionDO);
        return regionDO;
    }

    /**
     * RegionForm 转换 RegionDTO
     * @param regionForm
     * @return
     */
    public static RegionDTO toDTO(RegionForm regionForm) {
        RegionDTO regionDTO = new RegionDTO();
        BeanUtils.copyProperties(regionForm, regionDTO);
        return regionDTO;
    }
    
    /**
     * RegionVO 转换 RegionDTO
     * @param regionVO
     * @return
     */
    public static RegionDTO toDTO(RegionVO regionVO) {
        RegionDTO regionDTO = new RegionDTO();
        BeanUtils.copyProperties(regionVO, regionDTO);
        if (regionVO.getChildren() != null && regionVO.getChildren().size() > 0) {
            regionDTO.setChildren(toDTO(regionVO.getChildren()));
        }
        return regionDTO;
    }

    /**
     * List<RegionDTO> 转换 List<RegionVO>
     * @param regionVOList
     * @return
     */
    public static List<RegionDTO> toDTO(List<RegionVO> regionVOList) {
        List<RegionDTO> regionDTOList = new ArrayList<>();
        for (RegionVO regionVO : regionVOList) {
            regionDTOList.add(toDTO(regionVO));
        }
        return regionDTOList;
    }

    /**
     * RegionDO 转换 RegionDTO
     * @param regionDO
     * @return
     */
    public static RegionDTO toDTO(RegionDO regionDO) {
        RegionDTO regionDTO = new RegionDTO();
        BeanUtils.copyProperties(regionDO, regionDTO);
        return regionDTO;
    }

    /**
     * RegionDTO 转换 RegionVO
     * @param regionDTO
     * @return
     */
    public static RegionVO toVO(RegionDTO regionDTO) {
        RegionVO regionVO = new RegionVO();
        BeanUtils.copyProperties(regionDTO, regionVO);
        if (regionDTO.getChildren() != null && regionDTO.getChildren().size() > 0) {
            regionVO.setChildren(toVO(regionDTO.getChildren()));
        }
        return regionVO;
    }

    /**
     * List<RegionDTO> 转换 List<RegionVO>
     * @param regionDTOList
     * @return
     */
    public static List<RegionVO> toVO(List<RegionDTO> regionDTOList) {
        List<RegionVO> regionVOList = new ArrayList<>();
        for (RegionDTO regionDTO : regionDTOList) {
            regionVOList.add(toVO(regionDTO));
        }
        return regionVOList;
    }

    /**
     * Page<RegionDTO> 转换 Page<RegionVO>
     * @param regionDTOPage
     * @return
     */
    public static Page<RegionVO> toVO(Page<RegionDTO> regionDTOPage) {
        List<RegionDTO> regionDTOList = regionDTOPage.getRecords();
        Page<RegionVO> regionVOPage = new Page<>();
        BeanUtils.copyProperties(regionDTOPage, regionVOPage);
        regionVOPage.setRecords(toVO(regionDTOList));
        return regionVOPage;
    }

}
