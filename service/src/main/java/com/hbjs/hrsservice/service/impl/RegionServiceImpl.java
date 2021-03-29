package com.hbjs.hrsservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.RegionConverter;
import com.hbjs.hrscommon.domain.sys.RegionDO;
import com.hbjs.hrscommon.dto.RegionDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.FIleUtils;
import com.hbjs.hrscommon.utils.TreeUtils;
import com.hbjs.hrsrepo.mapper.RegionMapper;
import com.hbjs.hrsservice.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
        RegionDO regionDO = regionMapper.selectById(regionId);
        Assert.notNull(regionDO, "获取失败：没有找到该区域信息或已被删除");
        return RegionConverter.toDTO(regionDO);
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
        Assert.isNull(regionInDB, "更新失败：区域编号已被占用");
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

    @Override
    @Transactional
    public List<Long> batchDeleteRegion(List<Long> regionIds) {
        for (Long regionId : regionIds) {
            this.deleteRegion(regionId);
        }
        return regionIds;
    }

    @Override
    @Transactional
    public boolean refreshRegion() {
        try {
            File provincesJsonFile = ResourceUtils.getFile("classpath:region/provinces.json");
            File citiesJsonFile = ResourceUtils.getFile("classpath:region/cities.json");
            File areasJsonFile = ResourceUtils.getFile("classpath:region/areas.json");
            File streetsJsonFile = ResourceUtils.getFile("classpath:region/streets.json");
//        File hkJsonFile = ResourceUtils.getFile("classpath:region/HK-MO-TW.json");
            String provincesJson = FIleUtils.readFileText(new FileInputStream(provincesJsonFile));
            String citiesJson = FIleUtils.readFileText(new FileInputStream(citiesJsonFile));
            String areasJson = FIleUtils.readFileText(new FileInputStream(areasJsonFile));
            String streetsJson = FIleUtils.readFileText(new FileInputStream(streetsJsonFile));
//        String hkJson = FIleUtils.readFileText(new FileInputStream(hkJsonFile));
            List<RegionDO> provinces = JSON.parseArray(provincesJson, RegionDO.class);
            List<RegionDO> cities = JSON.parseArray(citiesJson, RegionDO.class);
            List<RegionDO> areas = JSON.parseArray(areasJson, RegionDO.class);
            List<RegionDO> streets = JSON.parseArray(streetsJson, RegionDO.class);
//        List<RegionDO> hk = JSON.parseArray(hkJson);

            regionMapper.clearRegionTable();

            for (RegionDO province : provinces) {
                province.setLevel(1);
                regionMapper.insert(province);
            }

            for (RegionDO city : cities) {
                city.setLevel(2);
                this.findRegionPid(city, provinces);
                regionMapper.insert(city);
            }

            for (RegionDO area : areas) {
                area.setLevel(3);
                this.findRegionPid(area, cities);
                regionMapper.insert(area);
            }

            for (RegionDO street : streets) {
                street.setLevel(4);
                this.findRegionPid(street, areas);
                regionMapper.insert(street);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("刷新区域信息失败");
        }
    }

    void findRegionPid(RegionDO child, List<RegionDO> parentList) {
        List<RegionDO> collect = parentList.stream().filter(area -> child.getCode().startsWith(area.getCode())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(collect)) {
            RegionDO parent = collect.get(0);
            child.setPid(parent.getId());
        }
    }

}
