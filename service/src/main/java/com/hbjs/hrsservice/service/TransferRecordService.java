package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.TransferRecordDTO;

import java.util.List;

public interface TransferRecordService {

    /**
     * 获取调动记录
     * @param page
     * @param staffId
     * @param keyword
     * @return
     */
    Page<TransferRecordDTO> getTransferRecordPage(Page<TransferRecordDTO> page, Long staffId, String keyword);

    /**
     * 通过ID获取调动记录
     * @param transferRecordId
     * @return
     */
    TransferRecordDTO getTransferRecordById(Long transferRecordId);

    /**
     * 添加调动记录
     * @param transferRecordDTO
     * @return
     */
    Long addTransferRecord(TransferRecordDTO transferRecordDTO);

    /**
     * 更新调动记录
     * @param transferRecordDTO
     * @return
     */
    Long updateTransferRecord(TransferRecordDTO transferRecordDTO);

    /**
     * 删除调动记录
     * @param transferRecordId
     * @return
     */
    Long deleteTransferRecord(Long transferRecordId);

    /**
     * 批量删除调动记录
     * @param transferRecordIds
     * @return
     */
    List<Long> batchDeleteTransferRecorde(List<Long> transferRecordIds);

    /**
     * 执行调动记录
     * @param transferRecordId
     * @return
     */
    Long runTransfer(Long transferRecordId);

    /**
     * 执行调动记录
     * @param transferRecordDTO
     * @return
     */
    Long runTransfer(TransferRecordDTO transferRecordDTO);

    /**
     * 定期检查执行调动
     * @param days 多少天内
     * @return
     */
    void runTransfer(int days);

}
