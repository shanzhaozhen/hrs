package com.hbjs.hrsservice.service.impl;

import com.hbjs.hrscommon.converter.DepartmentConverter;
import com.hbjs.hrscommon.domain.sys.DepartmentDO;
import com.hbjs.hrscommon.dto.DepartmentDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.TreeUtils;
import com.hbjs.hrsrepo.mapper.DepartmentMapper;
import com.hbjs.hrsservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "department")
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;

    @Override
    @Cacheable(key = "#root.methodName")
    public List<DepartmentDTO> getAllDepartments() {
        return departmentMapper.getAllDepartments();
    }

    @Override
    @Cacheable(key = "#root.methodName")
    public List<DepartmentDTO> getDepartmentTree() {
        List<DepartmentDTO> allDepartments = this.getAllDepartments();
        return TreeUtils.builtTree(allDepartments, DepartmentDTO.class);
    }

    @Override
    @Cacheable(key = "#root.methodName + ':' + #departmentId")
    public DepartmentDTO getDepartmentById(Long departmentId) {
        DepartmentDO departmentDO = departmentMapper.selectById(departmentId);
        Assert.notNull(departmentDO, "获取失败：没有找到该部门或已被删除");
        return DepartmentConverter.toDTO(departmentDO);
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public Long addDepartment(DepartmentDTO departmentDTO) {
        DepartmentDO departmentDO = DepartmentConverter.toDO(departmentDTO);
        departmentMapper.insert(departmentDO);
        return departmentDO.getId();
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public Long updateDepartment(DepartmentDTO departmentDTO) {
        Assert.notNull(departmentDTO.getId(), "更新失败：部门id不能为空");
        Assert.isTrue(!departmentDTO.getId().equals(departmentDTO.getPid()), "更新失败：上级部门不能选择自己");
        if (departmentDTO.getPid() != null) {
            DepartmentDO departmentPNode = departmentMapper.selectById(departmentDTO.getPid());
            Assert.notNull(departmentPNode, "更新失败：没有找到该部门的上级部门或已被删除");
            Assert.isTrue(!departmentDTO.getId().equals(departmentPNode.getPid()), "更新失败：部门之间不能互相引用");
        }
        DepartmentDO departmentDO = departmentMapper.selectById(departmentDTO.getId());
        Assert.notNull(departmentDO, "更新失败：没有找到该部门或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(departmentDTO, departmentDO);
        departmentMapper.updateById(departmentDO);
        try {
            this.getDepartmentTree();
        } catch (StackOverflowError e) {
            throw new IllegalArgumentException("更新失败：请检查部门节点间设置是否有问题");
        }
        return departmentDO.getId();

    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public Long deleteDepartment(Long departmentId) {
        departmentMapper.deleteById(departmentId);
        return departmentId;
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public List<Long> batchDeleteDepartment(@NotEmpty(message = "没有需要删除的部门") List<Long> departmentIds) {
        for (Long departmentId : departmentIds) {
            this.deleteDepartment(departmentId);
        }
        return departmentIds;
    }

}
