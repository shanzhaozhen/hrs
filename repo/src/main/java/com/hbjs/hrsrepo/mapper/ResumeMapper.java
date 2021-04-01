package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.ResumeDO;
import com.hbjs.hrscommon.dto.ResumeDTO;
import org.apache.ibatis.annotations.Param;

public interface ResumeMapper extends BaseMapper<ResumeDO> {

    Page<ResumeDTO> getResumePage(Page<ResumeDTO> page, @Param("keyword") String keyword);

}