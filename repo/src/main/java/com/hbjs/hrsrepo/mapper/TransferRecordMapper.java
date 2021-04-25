package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.TransferRecordDO;
import com.hbjs.hrscommon.dto.TransferRecordDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransferRecordMapper extends BaseMapper<TransferRecordDO> {

    Page<TransferRecordDTO> getTransferRecordPage(Page<TransferRecordDTO> page, @Param("staffId") Long staffId, @Param("keyword") String keyword);

    TransferRecordDTO getTransferRecordById(@Param("transferRecordId") Long transferRecordId);

    List<TransferRecordDTO> getTransferRecordInDays(@Param("days") int days);

}
