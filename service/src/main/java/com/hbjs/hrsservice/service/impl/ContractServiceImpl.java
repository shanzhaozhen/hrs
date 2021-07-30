package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.ContractConverter;
import com.hbjs.hrscommon.domain.hr.ContractDO;
import com.hbjs.hrscommon.dto.ContractDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrsrepo.mapper.ContractMapper;
import com.hbjs.hrsservice.service.ContractService;
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
public class ContractServiceImpl implements ContractService {

    private final ContractMapper contractMapper;

    @Override
    public Page<ContractDTO> getContractPage(Page<ContractDTO> page, String keyword) {
        return contractMapper.getContractPage(page, keyword);
    }

    @Override
    public ContractDTO getContractById(Long contractId) {
        ContractDO contractDO = contractMapper.selectById(contractId);
        Assert.notNull(contractDO, "获取失败：没有找到该合同信息或已被删除");
        return ContractConverter.toDTO(contractDO);
    }

    @Override
    public List<ContractDTO> getContractListByStaffId(Long pid) {
        return contractMapper.getContractListByStaffId(pid);
    }

    @Override
    @Transactional
    public Long addContract(ContractDTO contractDTO) {
        ContractDO contractDO = ContractConverter.toDO(contractDTO);
        contractMapper.insert(contractDO);
        return contractDO.getId();
    }

    @Override
    @Transactional
    public Long updateContract(ContractDTO contractDTO) {
        Assert.notNull(contractDTO.getId(), "合同信息id不能为空");
        ContractDO contractDO = contractMapper.selectById(contractDTO.getId());
        Assert.notNull(contractDO, "更新失败：没有找到该合同信息或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(contractDTO, contractDO);
        contractMapper.updateById(contractDO);
        return contractDO.getId();
    }

    @Override
    @Transactional
    public Long deleteContract(Long contractId) {
        ContractDTO contractDTO = this.getContractById(contractId);
        Assert.notNull(contractDTO, "删除失败：没有找到该合同信息或已被删除");
        contractMapper.deleteById(contractId);
        return contractId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteContract(@NotEmpty(message = "没有需要删除的合同信息") List<Long> contractIds) {
        for (Long contractId : contractIds) {
            this.deleteContract(contractId);
        }
        return contractIds;
    }

    @Override
    public long deleteContractByStaffId(Long staffId) {
        return contractMapper.deleteContractByStaffId(staffId);
    }

    @Override
    public void batchAddContract(List<ContractDTO> contractDTOList, Long staffId) {
        this.deleteContractByStaffId(staffId);
        if (!CollectionUtils.isEmpty(contractDTOList)) {
            for (ContractDTO contractDTO : contractDTOList) {
                contractDTO.setId(null).setStaffId(staffId);
                this.addContract(contractDTO);
            }
        }
    }

}
