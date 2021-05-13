package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.ResumeDO;
import com.hbjs.hrscommon.dto.ResumeDTO;
import com.hbjs.hrscommon.vo.ResumeExcel;
import com.hbjs.hrscommon.dto.ResumePrint;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResumeMapper extends BaseMapper<ResumeDO> {

    Page<ResumeDTO> getResumePage(Page<ResumeDTO> page, @Param("keyword") String keyword);

    List<ResumeExcel> getResumeExcelList(@Param("keyword") String keyword);

    ResumePrint getResumePrintByResumeId(@Param("resumeId") Long resumeId);
}
