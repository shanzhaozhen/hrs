package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.sys.FileDO;
import com.hbjs.hrscommon.dto.FileDTO;
import org.apache.ibatis.annotations.Param;

public interface FileMapper extends BaseMapper<FileDO> {

    Page<FileDTO> getFilePage(Page<FileDTO> page, @Param("keyword") String keyword);

    FileDTO selectFileByMd5(String md5);

}
