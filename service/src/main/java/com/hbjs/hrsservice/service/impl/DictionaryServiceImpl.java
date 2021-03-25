package com.hbjs.hrsservice.service.impl;

import com.hbjs.hrscommon.converter.DictionaryConverter;
import com.hbjs.hrscommon.domain.sys.DictionaryDO;
import com.hbjs.hrscommon.dto.DictionaryDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.TreeUtils;
import com.hbjs.hrsrepo.mapper.DictionaryMapper;
import com.hbjs.hrsservice.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryMapper dictionaryMapper;

    @Override
    public List<DictionaryDTO> getDictionaryTreeByType(Integer type) {
        return null;
    }

    @Override
    public List<DictionaryDTO> getDictionaryRoot() {
        return dictionaryMapper.getDictionaryByPid(null);
    }

    @Override
    public DictionaryDTO getDictionaryById(Long dictionaryId) {
        DictionaryDO dictionaryDO = dictionaryMapper.selectById(dictionaryId);
        Assert.notNull(dictionaryDO, "获取失败：没有找到该字典或已被删除");
        return DictionaryConverter.toDTO(dictionaryDO);
    }

    @Override
    @Transactional
    public Long addDictionary(DictionaryDTO dictionaryDTO) {
        DictionaryDO dictionaryDO = DictionaryConverter.toDO(dictionaryDTO);
        dictionaryMapper.insert(dictionaryDO);
        return dictionaryDO.getId();
    }

    @Override
    @Transactional
    public Long updateDictionary(DictionaryDTO dictionaryDTO) {
        Assert.notNull(dictionaryDTO.getId(), "更新失败：字典id不能为空");
        Assert.isTrue(!dictionaryDTO.getId().equals(dictionaryDTO.getPid()), "更新失败：上级节点不能选择自己");
        if (dictionaryDTO.getPid() != null) {
            DictionaryDO dictionaryPNode = dictionaryMapper.selectById(dictionaryDTO.getPid());
            Assert.notNull(dictionaryPNode, "更新失败：没有找到该字典的上级节点或已被删除");
            Assert.isTrue(!dictionaryDTO.getId().equals(dictionaryPNode.getPid()), "更新失败：节点之间不能互相引用");
        }
        DictionaryDO dictionaryDO = dictionaryMapper.selectById(dictionaryDTO.getId());
        Assert.notNull(dictionaryDO, "更新失败：没有找到该字典或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(dictionaryDTO, dictionaryDO);
        dictionaryMapper.updateById(dictionaryDO);
        try {
            this.getDictionaryTreeByType(null);
        } catch (StackOverflowError e) {
            throw new IllegalArgumentException("更新失败：请检查字典的节点设置是否有问题");
        }
        return dictionaryDO.getId();
    }

    @Override
    @Transactional
    public Long deleteDictionary(Long dictionaryId) {
        dictionaryMapper.deleteById(dictionaryId);
        return dictionaryId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteDictionary(@NotEmpty(message = "没有需要删除的字典") List<Long> dictionaryIds) {
        for (Long dictionaryId : dictionaryIds) {
            this.deleteDictionary(dictionaryId);
        }
        return dictionaryIds;
    }
    
}
