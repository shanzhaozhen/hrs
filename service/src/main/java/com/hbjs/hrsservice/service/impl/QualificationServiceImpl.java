package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.QualificationConverter;
import com.hbjs.hrscommon.domain.hr.QualificationDO;
import com.hbjs.hrscommon.dto.QualificationDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrsrepo.mapper.QualificationMapper;
import com.hbjs.hrsservice.service.QualificationService;
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
public class QualificationServiceImpl implements QualificationService {

    private final QualificationMapper qualificationMapper;

    @Override
    public Page<QualificationDTO> getQualificationPage(Page<QualificationDTO> page, String keyword) {
        return qualificationMapper.getQualificationPage(page, keyword);
    }

    @Override
    public QualificationDTO getQualificationById(Long qualificationId) {
        QualificationDO qualificationDO = qualificationMapper.selectById(qualificationId);
        Assert.notNull(qualificationDO, "获取失败：没有找到该职业资格或已被删除");
        return QualificationConverter.toDTO(qualificationDO);
    }

    @Override
    public List<QualificationDTO> getQualificationListByStaffId(Long pid) {
        return qualificationMapper.getQualificationListByStaffId(pid);
    }

    @Override
    @Transactional
    public Long addQualification(QualificationDTO qualificationDTO) {
        QualificationDO qualificationDO = QualificationConverter.toDO(qualificationDTO);
        qualificationMapper.insert(qualificationDO);
        return qualificationDO.getId();
    }

    @Override
    @Transactional
    public Long updateQualification(QualificationDTO qualificationDTO) {
        Assert.notNull(qualificationDTO.getId(), "职业资格id不能为空");
        QualificationDO qualificationDO = qualificationMapper.selectById(qualificationDTO.getId());
        Assert.notNull(qualificationDO, "更新失败：没有找到该职业资格或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(qualificationDTO, qualificationDO);
        qualificationMapper.updateById(qualificationDO);
        return qualificationDO.getId();
    }

    @Override
    @Transactional
    public Long deleteQualification(Long qualificationId) {
        QualificationDTO qualificationDTO = this.getQualificationById(qualificationId);
        Assert.notNull(qualificationDTO, "删除失败：没有找到该职业资格或已被删除");
        qualificationMapper.deleteById(qualificationId);
        return qualificationId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteQualification(@NotEmpty(message = "没有需要删除的职业资格") List<Long> qualificationIds) {
        for (Long qualificationId : qualificationIds) {
            this.deleteQualification(qualificationId);
        }
        return qualificationIds;
    }

    @Override
    public long deleteQualificationByStaffId(Long staffId) {
        return qualificationMapper.deleteQualificationByStaffId(staffId);
    }

    @Override
    public void batchAddQualification(List<QualificationDTO> qualificationDTOList, Long staffId) {
        this.deleteQualificationByStaffId(staffId);
        if (!CollectionUtils.isEmpty(qualificationDTOList)) {
            for (QualificationDTO qualificationDTO : qualificationDTOList) {
                qualificationDTO.setId(null).setStaffId(staffId);
                this.addQualification(qualificationDTO);
            }
        }
    }

}
