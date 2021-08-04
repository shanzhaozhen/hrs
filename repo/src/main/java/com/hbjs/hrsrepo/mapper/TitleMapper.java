package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.TitleDO;
import com.hbjs.hrscommon.dto.TitleDTO;
import com.hbjs.hrscommon.excel.TitleExcel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TitleMapper extends BaseMapper<TitleDO> {

    Page<TitleDTO> getTitlePage(Page<TitleDTO> page, @Param("keyword") String keyword);

    List<TitleDTO> getTitleListByStaffId(@Param("staffId") Long staffId);

    long deleteTitleByStaffId(@Param("staffId") Long staffId);

    List<TitleExcel> getTitleExcelList(@Param("keyword") String keyword, @Param("depId") Long depId, @Param("companyState") String companyState, @Param("postLevel") String postLevel);

}
