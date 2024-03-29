package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.QualificationDO;
import com.hbjs.hrscommon.dto.QualificationDTO;
import com.hbjs.hrscommon.excel.QualificationExcel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QualificationMapper extends BaseMapper<QualificationDO> {

    Page<QualificationDTO> getQualificationPage(Page<QualificationDTO> page, @Param("keyword") String keyword);

    List<QualificationDTO> getQualificationListByStaffId(@Param("staffId") Long staffId);

    long deleteQualificationByStaffId(@Param("staffId") Long staffId);

    List<QualificationExcel> getQualificationExcelList(@Param("keyword") String keyword, @Param("depId") Long depId, @Param("companyState") String companyState, @Param("postLevel") String postLevel);

}
