package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.RegionDTO;

import java.util.List;

public interface RegionService {

    /**
     * 获取区域信息的分页列表
     * @param page
     * @param keyword
     * @return
     */
    Page<RegionDTO> getRegionPage(Page<RegionDTO> page, String keyword);

    /**
     * 获取所有区域信息
     * @return
     */
    List<RegionDTO> getAllRegions();

    /**
     * 获取所有区域信息(树状)
     * @return
     */
    List<RegionDTO> getRegionTree();

    /**
     * 通过区域信息id获取
     * @param regionId
     * @return
     */
    RegionDTO getRegionById(Long regionId);

    /**
     * 新增区域信息
     * @param regionDTO
     * @return
     */
    Long addRegion(RegionDTO regionDTO);

    /**
     * 修改区域信息
     * @param regionDTO
     * @return
     */
    Long updateRegion(RegionDTO regionDTO);

    /**
     * 删除区域信息(通过区域信息id删除)
     * @param regionId
     * @return
     */
    Long deleteRegion(Long regionId);
    
}
