package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.FamilyDTO;

import java.util.List;

public interface FamilyService {

    /**
     * 获取家庭成员的分页列表
     * @param page
     * @param keyword
     * @return
     */
    Page<FamilyDTO> getFamilyPage(Page<FamilyDTO> page, String keyword);

    /**
     * 通过家庭成员id获取
     * @param familyId
     * @return
     */
    FamilyDTO getFamilyById(Long familyId);

    /**
     * 通过pid获取家庭成员信息
     * @param pid
     * @return
     */
    List<FamilyDTO> getFamilyListByPid(Long pid);

    /**
     * 新增家庭成员
     * @param familyDTO
     * @return
     */
    Long addFamily(FamilyDTO familyDTO);

    /**
     * 修改家庭成员
     * @param familyDTO
     * @return
     */
    Long updateFamily(FamilyDTO familyDTO);

    /**
     * 删除家庭成员(通过家庭成员id删除)
     * @param familyId
     */
    Long deleteFamily(Long familyId);

    /**
     * 批量删除家庭成员(通过家庭成员id删除)
     * @param familyIds
     * @return
     */
    List<Long> batchDeleteFamily(List<Long> familyIds);

}
