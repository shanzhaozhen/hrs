package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.TransferRecordConverter;
import com.hbjs.hrscommon.domain.hr.TransferRecordDO;
import com.hbjs.hrscommon.dto.StaffDTO;
import com.hbjs.hrscommon.dto.TransferRecordDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrsrepo.mapper.TransferRecordMapper;
import com.hbjs.hrsservice.service.StaffService;
import com.hbjs.hrsservice.service.TransferRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TransferRecordServiceImpl implements TransferRecordService {

    private final TransferRecordMapper transferRecordMapper;
    private final StaffService staffService;

    @Override
    public Page<TransferRecordDTO> getTransferRecordPage(Page<TransferRecordDTO> page, Long staffId, String keyword) {
        return transferRecordMapper.getTransferRecordPage(page, staffId, keyword);
    }

    @Override
    public TransferRecordDTO getTransferRecordById(Long transferRecordId) {
        return transferRecordMapper.getTransferRecordById(transferRecordId);
    }

    @Override
    public Long addTransferRecord(TransferRecordDTO transferRecordDTO) {
        TransferRecordDO transferRecordDO = TransferRecordConverter.toDO(transferRecordDTO);
        transferRecordMapper.insert(transferRecordDO);
        return transferRecordDO.getId();
    }

    @Override
    public Long updateTransferRecord(TransferRecordDTO transferRecordDTO) {
        Assert.notNull(transferRecordDTO.getId(), "调动记录id不能为空");
        TransferRecordDO transferRecordDO = transferRecordMapper.selectById(transferRecordDTO.getId());
        Assert.notNull(transferRecordDO, "更新失败：没有找到该调动记录或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(transferRecordDTO, transferRecordDO);
        transferRecordMapper.updateById(transferRecordDO);
        return transferRecordDO.getId();
    }

    @Override
    public Long deleteTransferRecord(Long transferRecordId) {
        transferRecordMapper.deleteById(transferRecordId);
        return transferRecordId;
    }

    @Override
    public List<Long> batchDeleteTransferRecorde(List<Long> transferRecordIds) {
        for (Long transferRecordId : transferRecordIds) {
            this.deleteTransferRecord(transferRecordId);
        }
        return transferRecordIds;
    }

    @Override
    public Long runTransfer(Long transferRecordId) {
        Assert.notNull(transferRecordId, "调动记录id不能为空");
        TransferRecordDTO transferRecordDTO = this.getTransferRecordById(transferRecordId);
        Assert.notNull(transferRecordDTO, "执行失败：没有找到该调动记录或已被删除");
        return this.runTransfer(transferRecordDTO);
    }

    @Override
    public Long runTransfer(TransferRecordDTO transferRecordDTO) {
        StaffDTO staffDTO = staffService.getStaffById(transferRecordDTO.getStaffId());
        staffDTO
                .setDepId(transferRecordDTO.getPostDepId())
                .setDuty(transferRecordDTO.getPostDuty())
                .setPost(transferRecordDTO.getPostPost())
                .setPostType(transferRecordDTO.getPostPostType())
                .setPostLevel(transferRecordDTO.getPostPostLevel());
        staffService.updateStaff(staffDTO);
        return transferRecordDTO.getId();
    }

    @Override
    public void runTransfer(int days) {
        List<TransferRecordDTO> transferRecordDTOS = transferRecordMapper.getTransferRecordInDays(days);
        for (TransferRecordDTO transferRecordDTO : transferRecordDTOS) {
            this.runTransfer(transferRecordDTO);
        }
    }

}
