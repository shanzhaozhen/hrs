package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.AllowanceConverter;
import com.hbjs.hrscommon.domain.hr.AllowanceDO;
import com.hbjs.hrscommon.dto.AllowanceDTO;
import com.hbjs.hrscommon.dto.StaffDTO;
import com.hbjs.hrscommon.excel.AllowanceExcel;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
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

    private final AllowanceMapper performanceMapper;

    private final StaffService staffService;

    @Override
    public Page<AllowanceDTO> getAllowancePage(Page<AllowanceDTO> page, String keyword, Long depId, Integer year, Integer quarter) {
        return performanceMapper.getAllowancePage(page, keyword, depId, year, quarter);
    }

    @Override
    public AllowanceDTO getAllowanceById(Long performanceId) {
        AllowanceDTO performanceDTO = performanceMapper.getAllowanceById(performanceId);
        Assert.notNull(performanceDTO, "获取失败：没有找到该绩效评价或已被删除");
        return performanceDTO;
    }

    @Override
    public Long addAllowance(AllowanceDTO performanceDTO) {
        AllowanceDO performanceDO = AllowanceConverter.toDO(performanceDTO);
        AllowanceDO performanceInDB = new LambdaQueryChainWrapper<>(performanceMapper)
                .eq(AllowanceDO::getStaffId, performanceDO.getStaffId())
                .eq(AllowanceDO::getYear, performanceDO.getYear())
                .eq(AllowanceDO::getQuarter, performanceDO.getQuarter())
                .one();
        Assert.isNull(performanceInDB, "新增失败：该员工的绩效评价已存在（存在相同的年份和季度）");
        performanceMapper.insert(performanceDO);
        return performanceDO.getId();
    }

    @Override
    public Long updateAllowance(AllowanceDTO performanceDTO) {
        Assert.notNull(performanceDTO.getId(), "绩效评价id不能为空");
        AllowanceDO performanceDO = performanceMapper.selectById(performanceDTO.getId());
        Assert.notNull(performanceDO, "更新失败：没有找到该绩效评价或已被删除");
        AllowanceDO performanceInDB = new LambdaQueryChainWrapper<>(performanceMapper)
                .eq(AllowanceDO::getStaffId, performanceDO.getStaffId())
                .eq(AllowanceDO::getYear, performanceDO.getYear())
                .eq(AllowanceDO::getQuarter, performanceDO.getQuarter())
                .one();
        Assert.isTrue(performanceInDB == null ||
                        performanceInDB.getId().equals(performanceDO.getId()),
                "更新失败：该员工绩效评价已存在（存在相同的年份和季度）");
        CustomBeanUtils.copyPropertiesExcludeMeta(performanceDTO, performanceDO);
        performanceMapper.updateById(performanceDO);
        return performanceDO.getId();
    }

    @Override
    public Long deleteAllowance(Long performanceId) {
        AllowanceDTO performanceDTO = this.getAllowanceById(performanceId);
        Assert.notNull(performanceDTO, "删除失败：没有找到该绩效评价或已被删除");
        performanceMapper.deleteById(performanceId);
        return performanceId;
    }

    @Override
    public List<Long> batchDeleteAllowance(@NotEmpty(message = "没有需要删除的绩效评价") List<Long> performanceIds) {
        for (Long performanceId : performanceIds) {
            this.deleteAllowance(performanceId);
        }
        return performanceIds;
    }

    @Override
    public void generateAllowanceTemplate() {
        EasyExcelUtils.exportExcel(AllowanceExcel.class, new ArrayList<>(), "模板", "绩效评价导入模板");
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

        StringBuffer stringBuffer = new StringBuffer();
        int errorTimes = 0;

        // 先检查是否存在部分缺少参数的，缺少参数则跳过
        List<AllowanceExcel> errorItems = list.stream().filter(s -> !StringUtils.hasText(s.getStaffCode()) || s.getYear() == null || s.getQuarter() == null).collect(Collectors.toList());
        Assert.isTrue(CollectionUtils.isEmpty(errorItems), "存在未填写的参数（员工编号、考核年份或考核季度），本次导入失败");

        for (AllowanceExcel performanceExcel : list) {
            // 根据查找员工编号查找staffId
            StaffDTO staffDTO = staffService.getStaffByStaffCode(performanceExcel.getStaffCode());
            if (staffDTO == null) {
                stringBuffer.append("员工编号：").append(performanceExcel.getStaffCode()).append("未录入本系统;\n");
                ++errorTimes;
                continue;
            }

            AllowanceDTO performanceDTO = performanceMapper.getAllowanceByStaffCodeAndYearAndQuarter(performanceExcel.getStaffCode(), performanceExcel.getYear(), performanceExcel.getQuarter());
            if (performanceDTO == null) {
                performanceDTO = new AllowanceDTO();
                BeanUtils.copyProperties(performanceExcel, performanceDTO);
                performanceDTO.setStaffId(staffDTO.getId());
                this.addAllowance(performanceDTO);
            } else {
                BeanUtils.copyProperties(performanceExcel, performanceDTO);
                performanceDTO.setStaffId(staffDTO.getId());
                this.updateAllowance(performanceDTO);
            }
        }

        if (StringUtils.hasText(stringBuffer)) {
            Assert.isTrue(list.size() != errorTimes, "导入失败，情况如下：\n" + stringBuffer);
            return String.format("成功导入%s条记录, %s条数据导入失败。\n详细如下：\n%s", list.size() - errorTimes, errorTimes, stringBuffer);
        }

        return String.format("成功导入%s条记录", list.size());
    }

    @Override
    public void exportAllowance(String keyword, Long depId, Integer year, Integer quarter) {
        List<AllowanceExcel> performanceList = performanceMapper.getAllowanceExcelList(keyword, depId, year, quarter);
        EasyExcelUtils.exportExcel(AllowanceExcel.class, performanceList, "绩效评价记录");
    }

    @Override
    public AllowanceDTO getAllowanceByStaffIdAndYearAndQuarter(Long staffId, Integer year, Integer quarter) {
        return performanceMapper.getAllowanceByStaffIdAndYearAndQuarter(staffId, year, quarter);
    }

}
