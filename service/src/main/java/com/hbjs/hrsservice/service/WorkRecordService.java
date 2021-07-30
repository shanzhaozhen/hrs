package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.WorkRecordDTO;
import com.hbjs.hrscommon.excel.WorkRecordExcel;

import java.util.List;

public interface WorkRecordService {

    /**
     * 获取工作记录的分页列表
     * @param page
     * @param keyword
     * @return
     */
    Page<WorkRecordDTO> getWorkRecordPage(Page<WorkRecordDTO> page, String keyword);

    /**
     * 通过工作记录id获取
     * @param familyId
     * @return
     */
    WorkRecordDTO getWorkRecordById(Long familyId);

    /**
     * 通过pid获取工作记录信息
     * @param pid
     * @return
     */
    List<WorkRecordDTO> getWorkRecordListByStaffId(Long pid);

    /**
     * 新增工作记录
     * @param familyDTO
     * @return
     */
    Long addWorkRecord(WorkRecordDTO familyDTO);

    /**
     * 修改工作记录
     * @param familyDTO
     * @return
     */
    Long updateWorkRecord(WorkRecordDTO familyDTO);

    /**
     * 删除工作记录(通过工作记录id删除)
     * @param familyId
     */
    Long deleteWorkRecord(Long familyId);

    /**
     * 批量删除工作记录(通过工作记录id删除)
     * @param familyIds
     * @return
     */
    List<Long> batchDeleteWorkRecord(List<Long> familyIds);

    /**
     * 通过员工id删除工作记录
     * @param staffId
     * @return
     */
    long deleteWorkRecordByStaffId(Long staffId);

    /**
     * 批量添加工作记录
     * @param familyDTOList
     * @param staffId
     */
    void batchAddWorkRecord(List<WorkRecordDTO> familyDTOList, Long staffId);

}
