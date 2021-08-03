package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.ContractDTO;
import com.hbjs.hrscommon.excel.ContractExcel;

import java.util.List;

public interface ContractService {

    /**
     * 获取合同信息的分页列表
     * @param page
     * @param keyword
     * @return
     */
    Page<ContractDTO> getContractPage(Page<ContractDTO> page, String keyword);

    /**
     * 通过合同信息id获取
     * @param contractId
     * @return
     */
    ContractDTO getContractById(Long contractId);

    /**
     * 通过pid获取合同信息信息
     * @param pid
     * @return
     */
    List<ContractDTO> getContractListByStaffId(Long pid);

    /**
     * 新增合同信息
     * @param contractDTO
     * @return
     */
    Long addContract(ContractDTO contractDTO);

    /**
     * 修改合同信息
     * @param contractDTO
     * @return
     */
    Long updateContract(ContractDTO contractDTO);

    /**
     * 删除合同信息(通过合同信息id删除)
     * @param contractId
     */
    Long deleteContract(Long contractId);

    /**
     * 批量删除合同信息(通过合同信息id删除)
     * @param contractIds
     * @return
     */
    List<Long> batchDeleteContract(List<Long> contractIds);

    /**
     * 通过员工id删除工作履历
     * @param staffId
     * @return
     */
    long deleteContractByStaffId(Long staffId);

    /**
     * 批量添加工作履历
     * @param contractDTOList
     * @param staffId
     */
    void batchAddContract(List<ContractDTO> contractDTOList, Long staffId);

    /**
     * 获取导出Excel的内容
     * @param keyword
     * @param depId
     * @param companyState
     * @param postLevel
     * @return
     */
    List<ContractExcel> getContractExcelList(String keyword, Long depId, String companyState, String postLevel);
}
