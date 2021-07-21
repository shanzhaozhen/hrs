package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.AllowanceConverter;
import com.hbjs.hrscommon.domain.hr.AllowanceDO;
import com.hbjs.hrscommon.dto.AllowanceDTO;
import com.hbjs.hrscommon.dto.StaffDTO;
import com.hbjs.hrscommon.excel.AllowanceExcel;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.DateUtils;
import com.hbjs.hrscommon.utils.EasyExcelUtils;
import com.hbjs.hrsrepo.mapper.AllowanceMapper;
import com.hbjs.hrsservice.service.AllowanceService;
import com.hbjs.hrsservice.service.StaffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AllowanceServiceImpl implements AllowanceService {

    private final AllowanceMapper allowanceMapper;

    private final StaffService staffService;

    @Override
    public Page<AllowanceDTO> getAllowancePage(Page<AllowanceDTO> page, String keyword, Long depId, String month) {
        return allowanceMapper.getAllowancePage(page, keyword, depId, month);
    }

    @Override
    public AllowanceDTO getAllowanceById(Long allowanceId) {
        AllowanceDTO allowanceDTO = allowanceMapper.getAllowanceById(allowanceId);
        Assert.notNull(allowanceDTO, "获取失败：没有找到该福利津贴或已被删除");
        return allowanceDTO;
    }

    @Override
    public Long addAllowance(AllowanceDTO allowanceDTO) {
        AllowanceDO allowanceDO = AllowanceConverter.toDO(allowanceDTO);
        AllowanceDTO allowanceInDB = allowanceMapper.getAllowanceByStaffIdAndMonth(allowanceDTO.getStaffId(), DateUtils.format(allowanceDTO.getMonth(), "yyyy-MM"));
        Assert.isNull(allowanceInDB, "新增失败：该员工的福利津贴已存在（存在相同的员工编号和津贴月份）");
        allowanceMapper.insert(allowanceDO);
        return allowanceDO.getId();
    }

    @Override
    public Long updateAllowance(AllowanceDTO allowanceDTO) {
        Assert.notNull(allowanceDTO.getId(), "福利津贴id不能为空");
        AllowanceDO allowanceDO = allowanceMapper.selectById(allowanceDTO.getId());
        Assert.notNull(allowanceDO, "更新失败：没有找到该福利津贴或已被删除");
        AllowanceDTO allowanceInDB = allowanceMapper.getAllowanceByStaffIdAndMonth(allowanceDTO.getStaffId(), DateUtils.format(allowanceDTO.getMonth(), "yyyy-MM"));
        Assert.isTrue(allowanceInDB == null || allowanceInDB.getId().equals(allowanceDO.getId()),
                "更新失败：该员工福利津贴已存在（存在相同的年份和季度）");
        CustomBeanUtils.copyPropertiesExcludeMeta(allowanceDTO, allowanceDO);
        allowanceMapper.updateById(allowanceDO);
        return allowanceDO.getId();
    }

    @Override
    public Long deleteAllowance(Long allowanceId) {
        AllowanceDTO allowanceDTO = this.getAllowanceById(allowanceId);
        Assert.notNull(allowanceDTO, "删除失败：没有找到该福利津贴或已被删除");
        allowanceMapper.deleteById(allowanceId);
        return allowanceId;
    }

    @Override
    public List<Long> batchDeleteAllowance(@NotEmpty(message = "没有需要删除的福利津贴") List<Long> allowanceIds) {
        for (Long allowanceId : allowanceIds) {
            this.deleteAllowance(allowanceId);
        }
        return allowanceIds;
    }

    @Override
    public void generateAllowanceTemplate() {
        EasyExcelUtils.exportExcel(AllowanceExcel.class, new ArrayList<>(), "模板", "福利津贴导入模板");
    }

    @Override
    public String importAllowance(MultipartFile file) {
        List<AllowanceExcel> list;
        try {
            list = EasyExcelUtils.readExcel(file.getInputStream(), AllowanceExcel.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("文件读取失败");
        }

        Assert.isTrue(!CollectionUtils.isEmpty(list), "导入的文件不存在记录，请填写好再导入");

        StringBuilder errorResult = new StringBuilder();
        int errorTimes = 0;

        // 先检查是否存在部分缺少参数的，缺少参数则跳过
        List<AllowanceExcel> errorItems = list.stream().filter(s -> !StringUtils.hasText(s.getStaffCode()) || s.getMonth() == null).collect(Collectors.toList());
        Assert.isTrue(CollectionUtils.isEmpty(errorItems), "存在未填写的参数（员工编号、考勤月份），本次导入失败");

        for (AllowanceExcel allowanceExcel : list) {
            // 根据查找员工编号查找staffId
            StaffDTO staffDTO = staffService.getStaffByStaffCode(allowanceExcel.getStaffCode());
            if (staffDTO == null) {
                errorResult.append("员工编号：").append(allowanceExcel.getStaffCode()).append("未录入本系统;\n");
                ++errorTimes;
                continue;
            }

            AllowanceDTO allowanceDTO = allowanceMapper.getAllowanceByStaffIdAndMonth(staffDTO.getId(), DateUtils.format(allowanceExcel.getMonth(), "yyyy-MM"));
            if (allowanceDTO == null) {
                allowanceDTO = new AllowanceDTO();
                BeanUtils.copyProperties(allowanceExcel, allowanceDTO);
                allowanceDTO.setStaffId(staffDTO.getId());
                this.addAllowance(allowanceDTO);
            } else {
                BeanUtils.copyProperties(allowanceExcel, allowanceDTO);
                allowanceDTO.setStaffId(staffDTO.getId());
                this.updateAllowance(allowanceDTO);
            }
        }

        if (StringUtils.hasText(errorResult)) {
            Assert.isTrue(list.size() != errorTimes, "导入失败，情况如下：\n" + errorResult);
            return String.format("成功导入%s条记录, %s条数据导入失败。\n详细如下：\n%s", list.size() - errorTimes, errorTimes, errorResult);
        }

        return String.format("成功导入%s条记录", list.size());
    }

    @Override
    public void exportAllowance(String keyword, Long depId, String month) {
        List<AllowanceExcel> allowanceList = allowanceMapper.getAllowanceExcelList(keyword, depId, month);
        EasyExcelUtils.exportExcel(AllowanceExcel.class, allowanceList, "福利津贴记录");
    }

    @Override
    public AllowanceDTO getAllowanceByStaffIdAndMonth(Long staffId, String month) {
        return allowanceMapper.getAllowanceByStaffIdAndMonth(staffId, month);
    }

}
