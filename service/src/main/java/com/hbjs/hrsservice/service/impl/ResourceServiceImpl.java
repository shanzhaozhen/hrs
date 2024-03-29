package com.hbjs.hrsservice.service.impl;

import com.hbjs.hrscommon.converter.ResourceConverter;
import com.hbjs.hrscommon.domain.sys.ResourceDO;
import com.hbjs.hrscommon.dto.ResourceDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.TreeUtils;
import com.hbjs.hrsrepo.mapper.ResourceMapper;
import com.hbjs.hrsservice.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceMapper resourceMapper;

    @Override
    public List<ResourceDTO> getResourceRoleListByType(Integer type) {
        return resourceMapper.getResourceRoleListByTypeAndUserId(type, null);
    }

    @Override
    public List<ResourceDTO> getResourceTreeByType(Integer type) {
        List<ResourceDTO> resourceDTOList = this.getResourceRoleListByType(type);
        return TreeUtils.builtTree(resourceDTOList, ResourceDTO.class);
    }

    @Override
    public ResourceDTO getResourceById(Long resourceId) {
        ResourceDO resourceDO = resourceMapper.selectById(resourceId);
        Assert.notNull(resourceDO, "获取失败：没有找到该资源或已被删除");
        return ResourceConverter.toDTO(resourceDO);
    }

    @Override
    @Transactional
    public Long addResource(ResourceDTO resourceDTO) {
        ResourceDO resourceDO = ResourceConverter.toDO(resourceDTO);
        resourceMapper.insert(resourceDO);
        return resourceDO.getId();
    }

    @Override
    @Transactional
    public Long updateResource(ResourceDTO resourceDTO) {
        Assert.notNull(resourceDTO.getId(), "更新失败：资源id不能为空");
        Assert.isTrue(!resourceDTO.getId().equals(resourceDTO.getPid()), "更新失败：上级节点不能选择自己");
        if (resourceDTO.getPid() != null) {
            ResourceDO resourcePNode = resourceMapper.selectById(resourceDTO.getPid());
            Assert.notNull(resourcePNode, "更新失败：没有找到该资源的上级节点或已被删除");
            Assert.isTrue(!resourceDTO.getId().equals(resourcePNode.getPid()), "更新失败：节点之间不能互相引用");
        }
        ResourceDO resourceDO = resourceMapper.selectById(resourceDTO.getId());
        Assert.notNull(resourceDO, "更新失败：没有找到该资源或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(resourceDTO, resourceDO);
        resourceMapper.updateById(resourceDO);
        try {
            this.getResourceTreeByType(null);
        } catch (StackOverflowError e) {
            throw new IllegalArgumentException("更新失败：请检查资源的节点设置是否有问题");
        }
        return resourceDO.getId();
    }

    @Override
    @Transactional
    public Long deleteResource(Long resourceId) {
        resourceMapper.deleteById(resourceId);
        return resourceId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteResource(@NotEmpty(message = "没有需要删除的资源") List<Long> resourceIds) {
        for (Long resourceId : resourceIds) {
            this.deleteResource(resourceId);
        }
        return resourceIds;
    }

}
