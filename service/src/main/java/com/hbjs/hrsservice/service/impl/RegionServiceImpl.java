package com.hbjs.hrsservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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

    private final ThreadPoolTaskExecutor customThreadPoolTaskExecutor;

    @Override
    public List<RegionDTO> getRegionRootList() {
        return regionMapper.getRegionByPid( null, null);
    }

    @Override
    public Page<RegionDTO> getRegionRootPage(Page<RegionDTO> page, String keyword) {
        return regionMapper.getRegionByPid(page, null, keyword);
    }

    @Override
    public List<RegionDTO> getAllRegions() {
        return regionMapper.getAllRegions();
    }

    @Override
    @Cacheable(cacheNames = "region", key = "#root.methodName")
    public List<RegionDTO> getRegionTree() {
        List<RegionDTO> allRegions = this.getAllRegions();
        return TreeUtils.builtTree(allRegions, RegionDTO.class);
    }

    @Override
    @Cacheable(cacheNames = "region", key = "#root.methodName + ':' + #level + ':' + #type")
    public List<RegionDTO> getRegionTreeByLevel(Integer level, Integer type) {
        List<RegionDTO> list = regionMapper.getRegionByLevel(level, type);
        return TreeUtils.builtTree(list, RegionDTO.class);
    }

    @Override
    @Cacheable(cacheNames = "region", key = "#root.methodName + ':' + #pid")
    public List<RegionDTO> getRegionChildrenById(Long pid) {
        return regionMapper.getRegionByPid(pid, null);
    }

    @Override
    @Cacheable(cacheNames = "region", key = "#root.methodName + ':' + #regionId")
    public RegionDTO getRegionById(Long regionId) {
        RegionDO regionDO = regionMapper.selectById(regionId);
        Assert.notNull(regionDO, "获取失败：没有找到该区域信息或已被删除");
        return RegionConverter.toDTO(regionDO);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "region", allEntries = true)
    public Long addRegion(RegionDTO regionDTO) {
        RegionDTO regionInDB = regionMapper.getRegionByCode(regionDTO.getCode());
        Assert.isNull(regionInDB, "创建失败：区域编号已被占用");
        RegionDO regionDO = RegionConverter.toDO(regionDTO);
        regionMapper.insert(regionDO);
        return regionDO.getId();
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "region", allEntries = true)
    public Long updateRegion(RegionDTO regionDTO) {
        Assert.notNull(regionDTO.getId(), "区域信息id不能为空");
        RegionDTO regionInDB = regionMapper.getRegionByCode(regionDTO.getCode());
        Assert.isTrue(regionInDB == null || regionInDB.getId().equals(regionDTO.getId()), "更新失败：区域编号已被占用");
        RegionDO regionDO = regionMapper.selectById(regionDTO.getId());
        Assert.notNull(regionDO, "更新失败：没有找到该区域信息或已被删除");
        Assert.isTrue(!regionDO.getId().equals(regionDTO.getPid()), "父级区域不能选择自己");
        CustomBeanUtils.copyPropertiesExcludeMeta(regionDTO, regionDO);
        regionMapper.updateById(regionDO);
        return regionDO.getId();
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "region", allEntries = true)
    public Long deleteRegion(Long regionId) {
        regionMapper.deleteById(regionId);
        return regionId;
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "region", allEntries = true)
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
            File villagesJsonFile = ResourceUtils.getFile("classpath:region/villages.json");
            File hmtJsonFile = ResourceUtils.getFile("classpath:region/HK-MO-TW.json");
            String provincesJson = FIleUtils.readFileText(new FileInputStream(provincesJsonFile));
            String citiesJson = FIleUtils.readFileText(new FileInputStream(citiesJsonFile));
            String areasJson = FIleUtils.readFileText(new FileInputStream(areasJsonFile));
            String streetsJson = FIleUtils.readFileText(new FileInputStream(streetsJsonFile));
            String villagesJson = FIleUtils.readFileText(new FileInputStream(villagesJsonFile));
            String hmtJson = FIleUtils.readFileText(new FileInputStream(hmtJsonFile));
            List<RegionDO> provinces = JSON.parseArray(provincesJson, RegionDO.class);
            List<RegionDO> cities = JSON.parseArray(citiesJson, RegionDO.class);
            List<RegionDO> areas = JSON.parseArray(areasJson, RegionDO.class);
            List<RegionDO> streets = JSON.parseArray(streetsJson, RegionDO.class);
            List<RegionDO> villages = JSON.parseArray(villagesJson, RegionDO.class);
            JSONObject hmt = JSON.parseObject(hmtJson);

            regionMapper.clearRegionTable();

            this.bathInsertRegion(provinces, null, 1);
            this.bathInsertRegion(cities, provinces, 2);
            this.bathInsertRegion(areas, cities, 3);
//            this.bathInsertRegion(streets, areas, 4);
//            this.bathInsertRegion(villages, streets, 5);

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

    void bathInsertRegion(List<RegionDO> list, List<RegionDO> parentList, Integer level) {
        for (RegionDO regionDO : list) {
            regionDO.setLevel(level);
            if (!CollectionUtils.isEmpty(parentList)) {
                this.findRegionPid(regionDO, parentList);
            }
            regionMapper.insert(regionDO);
        }
    }

}
