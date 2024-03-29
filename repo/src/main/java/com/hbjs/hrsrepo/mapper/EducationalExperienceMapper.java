package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.EducationalExperienceDO;
import com.hbjs.hrscommon.dto.EducationalExperienceDTO;
import com.hbjs.hrscommon.excel.EducationalExperienceExcel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EducationalExperienceMapper extends BaseMapper<EducationalExperienceDO> {

    Page<EducationalExperienceDTO> getEducationalExperiencePage(Page<EducationalExperienceDTO> page, @Param("keyword") String keyword);

    List<EducationalExperienceDTO> getEducationalExperienceListByPid(@Param("pid") Long pid);

    long deleteEducationalExperienceByStaffId(@Param("staffId") Long staffId);

    List<EducationalExperienceExcel> getEducationalExperienceExcelList(@Param("keyword") String keyword, @Param("depId") Long depId, @Param("companyState") String companyState, @Param("postLevel") String postLevel);

}
