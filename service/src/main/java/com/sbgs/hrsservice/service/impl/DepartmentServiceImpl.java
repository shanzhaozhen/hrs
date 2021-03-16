package com.sbgs.hrsservice.service.impl;

import com.sbgs.hrscommon.converter.DepartmentConverter;
import com.sbgs.hrscommon.domain.sys.DepartmentDO;
import com.sbgs.hrscommon.dto.DepartmentDTO;
import com.sbgs.hrscommon.utils.CustomBeanUtils;
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
        return this.getAllDepartments();
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
        Assert.notNull(departmentDTO.getId(), "部门id不能为空");
        DepartmentDTO departmentInDB = departmentMapper.getDepartmentByCode(departmentDTO.getCode());
        Assert.isNull(departmentInDB, "更新失败：部门编号已被占用");
        DepartmentDO departmentDO = departmentMapper.selectById(departmentDTO.getId());
        Assert.notNull(departmentDO, "更新失败：没有找到该部门或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(departmentDTO, departmentDO);
        departmentMapper.updateById(departmentDO);
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
    public List<Long> deleteDepartments(@NotEmpty(message = "没有需要删除的部门") List<Long> departmentIds) {
        for (Long departmentId : departmentIds) {
            this.deleteDepartment(departmentId);
        }
        return departmentIds;
    }

}
