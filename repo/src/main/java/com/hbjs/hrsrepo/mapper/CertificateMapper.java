package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.CertificateDO;
import com.hbjs.hrscommon.dto.CertificateDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CertificateMapper extends BaseMapper<CertificateDO> {

    Page<CertificateDTO> getCertificatePage(Page<CertificateDTO> page, @Param("keyword") String keyword);

    List<CertificateDTO> getCertificateListByPid(@Param("pid") Long pid);


}
