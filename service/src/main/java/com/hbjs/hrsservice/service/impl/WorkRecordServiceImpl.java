package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.WorkRecordConverter;
import com.hbjs.hrscommon.domain.hr.WorkRecordDO;
import com.hbjs.hrscommon.dto.WorkRecordDTO;
import com.hbjs.hrscommon.excel.WorkRecordExcel;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrsrepo.mapper.WorkRecordMapper;
import com.hbjs.hrsservice.service.WorkRecordService;
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
public class WorkRecordServiceImpl implements WorkRecordService {

    private final WorkRecordMapper workRecordMapper;

    @Override
    public Page<WorkRecordDTO> getWorkRecordPage(Page<WorkRecordDTO> page, String keyword) {
        return workRecordMapper.getWorkRecordPage(page, keyword);
    }

    @Override
    public WorkRecordDTO getWorkRecordById(Long workRecordId) {
        WorkRecordDO workRecordDO = workRecordMapper.selectById(workRecordId);
        Assert.notNull(workRecordDO, "获取失败：没有找到该工作记录或已被删除");
        return WorkRecordConverter.toDTO(workRecordDO);
    }

    @Override
    public List<WorkRecordDTO> getWorkRecordListByStaffId(Long pid) {
        return workRecordMapper.getWorkRecordListByStaffId(pid);
    }

    @Override
    @Transactional
    public Long addWorkRecord(WorkRecordDTO workRecordDTO) {
        WorkRecordDO workRecordDO = WorkRecordConverter.toDO(workRecordDTO);
        workRecordMapper.insert(workRecordDO);
        return workRecordDO.getId();
    }

    @Override
    @Transactional
    public Long updateWorkRecord(WorkRecordDTO workRecordDTO) {
        Assert.notNull(workRecordDTO.getId(), "工作记录id不能为空");
        WorkRecordDO workRecordDO = workRecordMapper.selectById(workRecordDTO.getId());
        Assert.notNull(workRecordDO, "更新失败：没有找到该工作记录或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(workRecordDTO, workRecordDO);
        workRecordMapper.updateById(workRecordDO);
        return workRecordDO.getId();
    }

    @Override
    @Transactional
    public Long deleteWorkRecord(Long workRecordId) {
        WorkRecordDTO workRecordDTO = this.getWorkRecordById(workRecordId);
        Assert.notNull(workRecordDTO, "删除失败：没有找到该工作记录或已被删除");
        workRecordMapper.deleteById(workRecordId);
        return workRecordId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteWorkRecord(@NotEmpty(message = "没有需要删除的工作记录") List<Long> workRecordIds) {
        for (Long workRecordId : workRecordIds) {
            this.deleteWorkRecord(workRecordId);
        }
        return workRecordIds;
    }

    @Override
    public long deleteWorkRecordByStaffId(Long staffId) {
        return workRecordMapper.deleteWorkRecordByStaffId(staffId);
    }

    @Override
    public void batchAddWorkRecord(List<WorkRecordDTO> workRecordDTOList, Long staffId) {
        this.deleteWorkRecordByStaffId(staffId);
        if (!CollectionUtils.isEmpty(workRecordDTOList)) {
            for (WorkRecordDTO workRecordDTO : workRecordDTOList) {
                workRecordDTO.setId(null).setStaffId(staffId);
                this.addWorkRecord(workRecordDTO);
            }
        }
    }

    @Override
    public List<WorkRecordExcel> getWorkRecordExcelList(String keyword, Long depId, String companyState, String postLevel) {
        return workRecordMapper.getWorkRecordExcelList(keyword, depId, companyState, postLevel);
    }

}
