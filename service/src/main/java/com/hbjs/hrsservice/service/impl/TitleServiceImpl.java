package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.TitleConverter;
import com.hbjs.hrscommon.domain.hr.TitleDO;
import com.hbjs.hrscommon.dto.TitleDTO;
import com.hbjs.hrscommon.excel.TitleExcel;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrsrepo.mapper.TitleMapper;
import com.hbjs.hrsservice.service.TitleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TitleServiceImpl implements TitleService {

    private final TitleMapper titleMapper;

    @Override
    public Page<TitleDTO> getTitlePage(Page<TitleDTO> page, String keyword) {
        return titleMapper.getTitlePage(page, keyword);
    }

    @Override
    public TitleDTO getTitleById(Long titleId) {
        TitleDO titleDO = titleMapper.selectById(titleId);
        Assert.notNull(titleDO, "获取失败：没有找到该职称信息或已被删除");
        return TitleConverter.toDTO(titleDO);
    }

    @Override
    public List<TitleDTO> getTitleListByStaffId(Long pid) {
        return titleMapper.getTitleListByStaffId(pid);
    }

    @Override
    @Transactional
    public Long addTitle(TitleDTO titleDTO) {
        TitleDO titleDO = TitleConverter.toDO(titleDTO);
        titleMapper.insert(titleDO);
        return titleDO.getId();
    }

    @Override
    @Transactional
    public Long updateTitle(TitleDTO titleDTO) {
        Assert.notNull(titleDTO.getId(), "职称信息id不能为空");
        TitleDO titleDO = titleMapper.selectById(titleDTO.getId());
        Assert.notNull(titleDO, "更新失败：没有找到该职称信息或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(titleDTO, titleDO);
        titleMapper.updateById(titleDO);
        return titleDO.getId();
    }

    @Override
    @Transactional
    public Long deleteTitle(Long titleId) {
        TitleDTO titleDTO = this.getTitleById(titleId);
        Assert.notNull(titleDTO, "删除失败：没有找到该职称信息或已被删除");
        titleMapper.deleteById(titleId);
        return titleId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteTitle(@NotEmpty(message = "没有需要删除的职称信息") List<Long> titleIds) {
        for (Long titleId : titleIds) {
            this.deleteTitle(titleId);
        }
        return titleIds;
    }

    @Override
    public long deleteTitleByStaffId(Long staffId) {
        return titleMapper.deleteTitleByStaffId(staffId);
    }

    @Override
    public void batchAddTitle(List<TitleDTO> titleDTOList, Long staffId) {
        this.deleteTitleByStaffId(staffId);
        if (!CollectionUtils.isEmpty(titleDTOList)) {
            for (TitleDTO titleDTO : titleDTOList) {
                titleDTO.setId(null).setStaffId(staffId);
                this.addTitle(titleDTO);
            }
        }
    }

    @Override
    public List<TitleExcel> getTitleExcelList(String keyword, Long depId, String companyState, String postLevel) {
        return titleMapper.getTitleExcelList(keyword, depId, companyState, postLevel);
    }

}
