package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.FamilyDO;
import com.hbjs.hrscommon.dto.FamilyDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FamilyMapper extends BaseMapper<FamilyDO> {

    Page<FamilyDTO> getFamilyPage(Page<FamilyDTO> page, @Param("keyword") String keyword);

    List<FamilyDTO> getFamilyListByPid(@Param("pid") Long pid);

    long deleteFamilyByStaffId(@Param("staffId") Long staffId);

}
