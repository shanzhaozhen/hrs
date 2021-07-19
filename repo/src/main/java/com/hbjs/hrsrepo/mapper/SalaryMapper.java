package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.SalaryDO;
import com.hbjs.hrscommon.dto.SalaryDTO;
import com.hbjs.hrscommon.excel.SalaryExcel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SalaryMapper extends BaseMapper<SalaryDO> {

    Page<SalaryDTO> getSalaryPage(Page<SalaryDTO> page, @Param("keyword") String keyword, @Param("depId") Long depId);

    SalaryDTO getSalaryById(@Param("salaryId") Long salaryId);

    List<SalaryExcel> getSalaryExcelList(@Param("keyword") String keyword, @Param("depId") Long depId);

    List<SalaryDO> getSalaryByMonth(@Param("month") String month);

    SalaryDTO getSalaryByStaffIdAndMonth(@Param("staffId") Long staffId, @Param("month") String month);

}
