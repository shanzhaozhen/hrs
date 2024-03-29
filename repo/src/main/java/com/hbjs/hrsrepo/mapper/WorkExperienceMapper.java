package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.WorkExperienceDO;
import com.hbjs.hrscommon.dto.WorkExperienceDTO;
import com.hbjs.hrscommon.excel.WorkExperienceExcel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkExperienceMapper extends BaseMapper<WorkExperienceDO> {

    Page<WorkExperienceDTO> getWorkExperiencePage(Page<WorkExperienceDTO> page, @Param("keyword") String keyword);

    List<WorkExperienceDTO> getWorkExperienceListByPid(@Param("pid") Long pid);

    long deleteWorkExperienceByStaffId(@Param("staffId") Long staffId);

    List<WorkExperienceExcel> getWorkExperienceExcelList(@Param("keyword") String keyword, @Param("depId") Long depId, @Param("companyState") String companyState, @Param("postLevel") String postLevel);

}
