package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.SalaryChangeDO;
import com.hbjs.hrscommon.dto.SalaryChangeDTO;
import com.hbjs.hrscommon.excel.SalaryChangeExportExcel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SalaryChangeMapper extends BaseMapper<SalaryChangeDO> {

    Page<SalaryChangeDTO> getSalaryChangePage(Page<SalaryChangeDTO> page, @Param("staffId") Long staffId, @Param("keyword") String keyword, @Param("depId") Long depId);

    SalaryChangeDTO getSalaryChangeById(@Param("salaryChangeId") Long salaryChangeId);

    List<SalaryChangeDTO> getSalaryChangeInDays(@Param("days") int days);

    List<SalaryChangeExportExcel> getSalaryChangeExcelList(@Param("keyword") String keyword, @Param("depId") Long depId);
}
