package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.DriverLicenseConverter;
import com.hbjs.hrscommon.domain.hr.DriverLicenseDO;
import com.hbjs.hrscommon.dto.DriverLicenseDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrsrepo.mapper.DriverLicenseMapper;
import com.hbjs.hrsservice.service.DriverLicenseService;
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
public class DriverLicenseServiceImpl implements DriverLicenseService {

    private final DriverLicenseMapper driverLicenseMapper;

    @Override
    public Page<DriverLicenseDTO> getDriverLicensePage(Page<DriverLicenseDTO> page, String keyword) {
        return driverLicenseMapper.getDriverLicensePage(page, keyword);
    }

    @Override
    public DriverLicenseDTO getDriverLicenseById(Long driverLicenseId) {
        DriverLicenseDO driverLicenseDO = driverLicenseMapper.selectById(driverLicenseId);
        Assert.notNull(driverLicenseDO, "获取失败：没有找到该驾驶证信息或已被删除");
        return DriverLicenseConverter.toDTO(driverLicenseDO);
    }

    @Override
    public List<DriverLicenseDTO> getDriverLicenseListByPid(Long pid) {
        return driverLicenseMapper.getDriverLicenseListByPid(pid);
    }

    @Override
    @Transactional
    public Long addDriverLicense(DriverLicenseDTO driverLicenseDTO) {
        DriverLicenseDO driverLicenseDO = DriverLicenseConverter.toDO(driverLicenseDTO);
        driverLicenseMapper.insert(driverLicenseDO);
        return driverLicenseDO.getId();
    }

    @Override
    @Transactional
    public Long updateDriverLicense(DriverLicenseDTO driverLicenseDTO) {
        Assert.notNull(driverLicenseDTO.getId(), "驾驶证信息id不能为空");
        DriverLicenseDO driverLicenseDO = driverLicenseMapper.selectById(driverLicenseDTO.getId());
        Assert.notNull(driverLicenseDO, "更新失败：没有找到该驾驶证信息或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(driverLicenseDTO, driverLicenseDO);
        driverLicenseMapper.updateById(driverLicenseDO);
        return driverLicenseDO.getId();
    }

    @Override
    @Transactional
    public Long deleteDriverLicense(Long driverLicenseId) {
        DriverLicenseDTO driverLicenseDTO = this.getDriverLicenseById(driverLicenseId);
        Assert.notNull(driverLicenseDTO, "删除失败：没有找到该驾驶证信息或已被删除");
        driverLicenseMapper.deleteById(driverLicenseId);
        return driverLicenseId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteDriverLicense(@NotEmpty(message = "没有需要删除的驾驶证信息") List<Long> driverLicenseIds) {
        for (Long driverLicenseId : driverLicenseIds) {
            this.deleteDriverLicense(driverLicenseId);
        }
        return driverLicenseIds;
    }

    @Override
    public long deleteDriverLicenseByStaffId(Long staffId) {
        return driverLicenseMapper.deleteDriverLicenseByStaffId(staffId);
    }

    @Override
    public void batchAddDriverLicense(List<DriverLicenseDTO> driverLicenseDTOList, Long staffId) {
        this.deleteDriverLicenseByStaffId(staffId);
        if (!CollectionUtils.isEmpty(driverLicenseDTOList)) {
            for (DriverLicenseDTO driverLicenseDTO : driverLicenseDTOList) {
                driverLicenseDTO.setId(null).setPid(staffId);
                this.addDriverLicense(driverLicenseDTO);
            }
        }
    }

}
