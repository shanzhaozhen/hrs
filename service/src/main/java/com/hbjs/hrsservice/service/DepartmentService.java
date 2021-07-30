package com.hbjs.hrsservice.service;

import com.hbjs.hrscommon.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {


    /**
     * 获取所有部门
     * @return
     */
    List<DepartmentDTO> getAllDepartments();

    /**
     * 获取所有部门（树状）
     * @return
     */
    List<DepartmentDTO> getDepartmentTree();

    /**
     * 通过部门id获取
     * @param departmentId
     * @return
     */
    DepartmentDTO getDepartmentById(Long departmentId);

    /**
     * 新增部门
     * @param departmentDTO
     * @return
     */
    Long addDepartment(DepartmentDTO departmentDTO);

    /**
     * 修改部门
     * @param departmentDTO
     * @return
     */
    Long updateDepartment(DepartmentDTO departmentDTO);

    /**
     * 删除部门(通过部门id删除)
     * @param departmentId
     * @return
     */
    Long deleteDepartment(Long departmentId);

    /**
     * 批量删除部门(通过部门id删除)
     * @param departmentIds
     * @return
     */
    List<Long> batchDeleteDepartment(List<Long> departmentIds);

    /**
     * 通过部门名称查询
     * @param depName
     * @return
     */
    DepartmentDTO getDepartmentByName(String depName);

}
