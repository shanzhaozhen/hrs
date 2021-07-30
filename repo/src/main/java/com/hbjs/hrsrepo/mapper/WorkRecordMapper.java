package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.WorkRecordDO;
import com.hbjs.hrscommon.dto.WorkRecordDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkRecordMapper extends BaseMapper<WorkRecordDO> {

    Page<WorkRecordDTO> getWorkRecordPage(Page<WorkRecordDTO> page, @Param("keyword") String keyword);

    List<WorkRecordDTO> getWorkRecordListByStaffId(@Param("staffId") Long staffId);

    long deleteWorkRecordByStaffId(@Param("staffId") Long staffId);

}
