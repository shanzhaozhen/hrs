package com.sbgs.hrsservice.service.impl;

import com.sbgs.hrscommon.converter.DepartmentConverter;
import com.sbgs.hrscommon.domain.sys.DepartmentDO;
import com.sbgs.hrscommon.dto.DepartmentDTO;
import com.sbgs.hrscommon.utils.CustomBeanUtils;
import com.sbgs.hrscommon.utils.TreeUtils;
import com.sbgs.hrsrepo.mapper.DepartmentMapper;
import com.sbgs.hrsservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return departmentMapper.getAllDepartments();
    }

    @Override
    public List<DepartmentDTO> getDepartmentTree() {
        List<DepartmentDTO> allDepartments = this.getAllDepartments();
        return TreeUtils.builtTree(allDepartments, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long departmentId) {
        DepartmentDO departmentDO = departmentMapper.selectById(departmentId);
        Assert.notNull(departmentDO, "获取失败：没有找到该部门或已被删除");
        return DepartmentConverter.toDTO(departmentDO);
    }

    @Override
    @Transactional
    public Long addDepartment(DepartmentDTO departmentDTO) {
        DepartmentDO departmentDO = DepartmentConverter.toDO(departmentDTO);
        departmentMapper.insert(departmentDO);
        return departmentDO.getId();
    }

    @Override
    @Transactional
    public Long updateDepartment(DepartmentDTO departmentDTO) {
        Assert.notNull(departmentDTO.getId(), "更新失败：部门id不能为空");
        Assert.isTrue(!departmentDTO.getId().equals(departmentDTO.getPid()), "更新失败：上级部门不能选择自己");
        DepartmentDO departmentPNode = departmentMapper.selectById(departmentDTO.getPid());
        Assert.notNull(departmentPNode, "更新失败：没有找到该部门的上级部门或已被删除");
        Assert.isTrue(!departmentDTO.getId().equals(departmentPNode.getPid()), "更新失败：部门之间不能互相引用");
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
    public Long deleteDepartment(Long departmentId) {
        departmentMapper.deleteById(departmentId);
        return departmentId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteDepartment(@NotEmpty(message = "没有需要删除的部门") List<Long> departmentIds) {
        for (Long departmentId : departmentIds) {
            this.deleteDepartment(departmentId);
        }
        return departmentIds;
    }

}
