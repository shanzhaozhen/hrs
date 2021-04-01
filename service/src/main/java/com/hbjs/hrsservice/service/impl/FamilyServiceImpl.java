package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.FamilyConverter;
import com.hbjs.hrscommon.domain.hr.FamilyDO;
import com.hbjs.hrscommon.dto.FamilyDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrsrepo.mapper.FamilyMapper;
import com.hbjs.hrsservice.service.FamilyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FamilyServiceImpl implements FamilyService {

    private final FamilyMapper familyMapper;

    @Override
    public Page<FamilyDTO> getFamilyPage(Page<FamilyDTO> page, String keyword) {
        return familyMapper.getFamilyPage(page, keyword);
    }

    @Override
    public FamilyDTO getFamilyById(Long familyId) {
        FamilyDO familyDO = familyMapper.selectById(familyId);
        Assert.notNull(familyDO, "获取失败：没有找到该家庭成员或已被删除");
        return FamilyConverter.toDTO(familyDO);
    }

    @Override
    public List<FamilyDTO> getFamilyListByPid(Long pid) {
        return familyMapper.getFamilyListByPid(pid);
    }

    @Override
    @Transactional
    public Long addFamily(FamilyDTO familyDTO) {
        FamilyDO familyDO = FamilyConverter.toDO(familyDTO);
        familyMapper.insert(familyDO);
        return familyDO.getId();
    }

    @Override
    @Transactional
    public Long updateFamily(FamilyDTO familyDTO) {
        Assert.notNull(familyDTO.getId(), "家庭成员id不能为空");
        FamilyDO familyDO = familyMapper.selectById(familyDTO.getId());
        Assert.notNull(familyDO, "更新失败：没有找到该家庭成员或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(familyDTO, familyDO);
        familyMapper.updateById(familyDO);
        return familyDO.getId();
    }

    @Override
    @Transactional
    public Long deleteFamily(Long familyId) {
        FamilyDTO familyDTO = this.getFamilyById(familyId);
        Assert.notNull(familyDTO, "删除失败：没有找到该家庭成员或已被删除");
        familyMapper.deleteById(familyId);
        return familyId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteFamily(@NotEmpty(message = "没有需要删除的家庭成员") List<Long> familyIds) {
        for (Long familyId : familyIds) {
            this.deleteFamily(familyId);
        }
        return familyIds;
    }

}
