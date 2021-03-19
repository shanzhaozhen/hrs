package com.sbgs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbgs.hrscommon.converter.RegionConverter;
import com.sbgs.hrscommon.domain.sys.RegionDO;
import com.sbgs.hrscommon.dto.RegionDTO;
import com.sbgs.hrscommon.utils.CustomBeanUtils;
import com.sbgs.hrscommon.utils.TreeUtils;
import com.sbgs.hrsrepo.mapper.RegionMapper;
import com.sbgs.hrsservice.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionMapper regionMapper;

    @Override
    public Page<RegionDTO> getRegionPage(Page<RegionDTO> page, String keyword) {
        return regionMapper.getRegionPage(page, keyword);
    }

    @Override
    public List<RegionDTO> getAllRegions() {
        return regionMapper.getAllRegions();
    }

    @Override
    public List<RegionDTO> getRegionTree() {
        List<RegionDTO> allRegions = this.getAllRegions();
        return TreeUtils.builtTree(allRegions, RegionDTO.class);
    }

    @Override
    public RegionDTO getRegionById(Long regionId) {
        RegionDTO regionDTO = regionMapper.getRegionByRegionId(regionId);
        Assert.notNull(regionDTO, "获取失败：没有找到该区域信息或已被删除");
        return regionDTO;
    }

    @Override
    @Transactional
    public Long addRegion(RegionDTO regionDTO) {
        RegionDTO regionInDB = regionMapper.getRegionByCode(regionDTO.getCode());
        Assert.isNull(regionInDB, "创建失败：区域编号已被占用");
        RegionDO regionDO = RegionConverter.toDO(regionDTO);
        regionMapper.insert(regionDO);
        return regionDO.getId();
    }

    @Override
    @Transactional
    public Long updateRegion(RegionDTO regionDTO) {
        Assert.notNull(regionDTO.getId(), "区域信息id不能为空");
        RegionDTO regionInDB = regionMapper.getRegionByIdNotEqualAndCodeEqual(regionDTO.getId(), regionDTO.getCode());
        Assert.isNull(regionInDB, "更新失败：角色代码已被占用");
        RegionDO regionDO = regionMapper.selectById(regionDTO.getId());
        Assert.notNull(regionDO, "更新失败：没有找到该区域信息或已被删除");
        Assert.isTrue(!regionDO.getId().equals(regionDTO.getPid()), "父级区域不能选择自己");
        CustomBeanUtils.copyPropertiesExcludeMeta(regionDTO, regionDO);
        regionMapper.updateById(regionDO);
        return regionDO.getId();
    }

    @Override
    @Transactional
    public Long deleteRegion(Long regionId) {
        regionMapper.deleteById(regionId);
        return regionId;
    }
}
