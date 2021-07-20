package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.DictionaryConverter;
import com.hbjs.hrscommon.domain.sys.DictionaryDO;
import com.hbjs.hrscommon.dto.DictionaryDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrsrepo.mapper.DictionaryMapper;
import com.hbjs.hrsservice.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "dictionary")
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryMapper dictionaryMapper;

    @Override
    public List<DictionaryDTO> getDictionaryRootList() {
        return dictionaryMapper.getDictionaryByPid(null, null);
    }

    @Override
    public Page<DictionaryDTO> getDictionaryRootPage(Page<DictionaryDTO> page, String keyword) {
        return dictionaryMapper.getDictionaryChildrenByPid(page, null, keyword);
    }

    @Override
    @Cacheable(key = "#root.methodName + ':' + #dictionaryId")
    public DictionaryDTO getDictionaryById(Long dictionaryId) {
        DictionaryDO dictionaryDO = dictionaryMapper.selectById(dictionaryId);
        Assert.notNull(dictionaryDO, "获取失败：没有找到该字典或已被删除");
        return DictionaryConverter.toDTO(dictionaryDO);
    }

    @Override
    public DictionaryDTO getDictionaryByCode(String code) {
        return dictionaryMapper.getDictionaryByCode(code);
    }

    @Override
    @Cacheable(key = "#root.methodName + ':' + #dictionaryId")
    public DictionaryDTO getDictionaryTreeById(Long dictionaryId) {
        DictionaryDTO dictionary = this.getDictionaryById(dictionaryId);
        this.builtDictionaryTree(dictionary);
        return dictionary;
    }

    @Override
    public void builtDictionaryTree(DictionaryDTO dictionaryDTO) {
        List<DictionaryDTO> children = this.getDictionaryChildrenByPid(dictionaryDTO.getId());
        if (!CollectionUtils.isEmpty(children)) {
            dictionaryDTO.setChildren(children);
            for (DictionaryDTO dictionary : children) {
                this.builtDictionaryTree(dictionary);
            }
        }
    }

    @Override
    @Cacheable(key = "#root.methodName + ':' + #dictionaryId")
    public DictionaryDTO getDictionaryParentTreeById(Long dictionaryId) {
        DictionaryDO dictionaryDO = dictionaryMapper.selectById(dictionaryId);
        Assert.notNull(dictionaryDO, "该字典节点不存在");

        if (dictionaryDO.getPid() == null) {    // 为根节点不返回
            return null;
        }

        DictionaryDO parent = dictionaryDO;
        while (parent.getPid() != null) {
            parent = dictionaryMapper.selectById(dictionaryDO.getPid());
        }

        return getDictionaryTreeById(parent.getId());
    }

    @Override
    @Cacheable(key = "#root.methodName + ':' + #pid")
    public List<DictionaryDTO> getDictionaryChildrenByPid(Long pid) {
        return dictionaryMapper.getDictionaryChildrenByPid(pid, null);
    }

    @Override
    @Cacheable(key = "#root.methodName + ':' + #code + ':' + #keyword")
    public List<DictionaryDTO> getDictionaryChildrenByCode(String code, String keyword) {
        DictionaryDTO dictionaryByCode = dictionaryMapper.getDictionaryByCode(code);

        if (dictionaryByCode == null) {
            return new ArrayList<>();
        }

        return dictionaryMapper.getDictionaryByPid(dictionaryByCode.getId(), keyword);
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public Long addDictionary(DictionaryDTO dictionaryDTO) {
        DictionaryDTO dictionaryInDB = dictionaryMapper.getDictionaryByCode(dictionaryDTO.getCode());
        Assert.isNull(dictionaryInDB, "创建失败：字典代码已被占用");
        DictionaryDO dictionaryDO = DictionaryConverter.toDO(dictionaryDTO);
        dictionaryMapper.insert(dictionaryDO);
        return dictionaryDO.getId();
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public Long updateDictionary(DictionaryDTO dictionaryDTO) {
        Assert.notNull(dictionaryDTO.getId(), "更新失败：字典id不能为空");
        Assert.isTrue(!dictionaryDTO.getId().equals(dictionaryDTO.getPid()), "更新失败：上级节点不能选择自己");
        if (dictionaryDTO.getPid() != null) {
            DictionaryDO dictionaryPNode = dictionaryMapper.selectById(dictionaryDTO.getPid());
            Assert.notNull(dictionaryPNode, "更新失败：没有找到该字典的上级节点或已被删除");
            Assert.isTrue(!dictionaryDTO.getId().equals(dictionaryPNode.getPid()), "更新失败：节点之间不能互相引用");
        }
        DictionaryDTO dictionaryInDB = dictionaryMapper.getDictionaryByCode(dictionaryDTO.getCode());
        Assert.isTrue(dictionaryInDB == null || dictionaryInDB.getId().equals(dictionaryDTO.getId()), "创建失败：字典代码已被占用");
        DictionaryDO dictionaryDO = dictionaryMapper.selectById(dictionaryDTO.getId());
        Assert.notNull(dictionaryDO, "更新失败：没有找到该字典或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(dictionaryDTO, dictionaryDO);
        dictionaryMapper.updateById(dictionaryDO);
        return dictionaryDO.getId();
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public Long deleteDictionary(Long dictionaryId) {
        dictionaryMapper.deleteById(dictionaryId);
        return dictionaryId;
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public List<Long> batchDeleteDictionary(@NotEmpty(message = "没有需要删除的字典") List<Long> dictionaryIds) {
        for (Long dictionaryId : dictionaryIds) {
            this.deleteDictionary(dictionaryId);
        }
        return dictionaryIds;
    }
    
}
