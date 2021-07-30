package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.ContractDO;
import com.hbjs.hrscommon.dto.ContractDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContractMapper extends BaseMapper<ContractDO> {

    Page<ContractDTO> getContractPage(Page<ContractDTO> page, @Param("keyword") String keyword);

    List<ContractDTO> getContractListByStaffId(@Param("staffId") Long staffId);

    long deleteContractByStaffId(@Param("staffId") Long staffId);

}
