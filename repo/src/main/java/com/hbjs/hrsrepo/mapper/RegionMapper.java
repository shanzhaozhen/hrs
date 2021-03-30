package com.hbjs.hrsrepo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.sys.RegionDO;
import com.hbjs.hrscommon.dto.RegionDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RegionMapper extends BaseMapper<RegionDO> {

    List<RegionDTO> getRegionByPid(@Param("pid") Long pid, @Param("keyword") String keyword);

    Page<RegionDTO> getRegionByPid(Page<RegionDTO> page, @Param("pid") Long pid, @Param("keyword") String keyword);

    @Select("select id, pid, name, code, level, " +
            "created_by, created_date, last_modified_by, last_modified_date " +
            "from sys_region")
    List<RegionDTO> getAllRegions();

    List<RegionDTO> getRegionByLevel(@Param("level") Integer level, @Param("type") Integer type);

    @Select("select id, pid, name, code, level, " +
            "created_by, created_date, last_modified_by, last_modified_date " +
            "from sys_region where code = #{code} ")
    RegionDTO getRegionByCode(@Param("code") String code);

    @Select("select id, pid, name, code, level, " +
            "created_by, created_date, last_modified_by, last_modified_date " +
            "from sys_region where id != #{regionId} and code = #{code} ")
    RegionDTO getRegionByIdNotEqualAndCodeEqual(@Param("regionId") Long regionId, @Param("code") String code);

    @Delete("delete from sys_region")
    void clearRegionTable();

}
