package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.SalaryConverter;
import com.hbjs.hrscommon.domain.hr.SalaryDO;
import com.hbjs.hrscommon.dto.*;
import com.hbjs.hrscommon.excel.SalaryExcel;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.DateUtils;
import com.hbjs.hrscommon.utils.EasyExcelUtils;
import com.hbjs.hrsrepo.mapper.SalaryMapper;
import com.hbjs.hrsservice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {

    private final SalaryMapper salaryMapper;

    private final StaffService staffService;

    private final SalarySettingService salarySettingService;

    private final PerformanceSettingService performanceSettingService;

    private final PerformanceService performanceService;

    private final AttendanceService attendanceService;

    @Override
    public Page<SalaryDTO> getSalaryPage(Page<SalaryDTO> page, String keyword, Long depId) {
        return salaryMapper.getSalaryPage(page, keyword, depId);
    }

    @Override
    public SalaryDTO getSalaryById(Long salaryId) {
        SalaryDTO salaryDTO = salaryMapper.getSalaryById(salaryId);
        Assert.notNull(salaryDTO, "获取失败：没有找到该薪资发放或已被删除");
        return salaryDTO;
    }

    @Override
    public Long addSalary(SalaryDTO salaryDTO) {
        SalaryDO salaryDO = SalaryConverter.toDO(salaryDTO);
        SalaryDO salaryInDB = new LambdaQueryChainWrapper<>(salaryMapper)
                .eq(SalaryDO::getStaffId, salaryDO.getStaffId())
                .one();
        Assert.isNull(salaryInDB, "新增失败：该员工的薪资发放已存在（存在相同的年份和季度）");
        salaryMapper.insert(salaryDO);
        return salaryDO.getId();
    }

    @Override
    public Long updateSalary(SalaryDTO salaryDTO) {
        Assert.notNull(salaryDTO.getId(), "薪资发放id不能为空");
        SalaryDO salaryDO = salaryMapper.selectById(salaryDTO.getId());
        Assert.notNull(salaryDO, "更新失败：没有找到该薪资发放或已被删除");
        Assert.isTrue(!salaryDTO.getFreeze(), "更新失败：当前薪资发放数据已被冻结，请解除冻结再操作");
        SalaryDO salaryInDB = new LambdaQueryChainWrapper<>(salaryMapper)
                .eq(SalaryDO::getStaffId, salaryDO.getStaffId())
                .one();
        Assert.isTrue(salaryInDB == null ||
                        salaryInDB.getId().equals(salaryDO.getId()),
                "更新失败：该员工薪资发放已存在（存在相同的年份和季度）");
        CustomBeanUtils.copyPropertiesExcludeMeta(salaryDTO, salaryDO);
        salaryMapper.updateById(salaryDO);
        return salaryDO.getId();
    }

    @Override
    public Long deleteSalary(Long salaryId) {
        SalaryDTO salaryDTO = this.getSalaryById(salaryId);
        Assert.notNull(salaryDTO, "删除失败：没有找到该薪资发放或已被删除");
        Assert.isTrue(!salaryDTO.getFreeze(), "删除失败：当前薪资发放数据已被冻结，请解除冻结再操作");
        salaryMapper.deleteById(salaryId);
        return salaryId;
    }

    @Override
    public List<Long> batchDeleteSalary(@NotEmpty(message = "没有需要删除的薪资发放") List<Long> salaryIds) {
        for (Long salaryId : salaryIds) {
            this.deleteSalary(salaryId);
        }
        return salaryIds;
    }

    @Override
    public String generateSalaryData(@NotEmpty(message = "不是有效的日期") String month) {
        // 检验date是否正确
        Assert.isTrue(DateUtils.isValidDate(month, "yyyy-MM"), "不是有效的日期");
        List<StaffDTO> staffListOnJob = staffService.getStaffListOnJob(month);
        int freezeTimes = 0;
        StringBuffer freezeResult = new StringBuffer("存在冻结的薪资发放数据，员工编号有：");
        StringBuffer result = new StringBuffer();
        // 获取薪资配置
        SalarySettingDTO salarySettingNew = salarySettingService.getSalarySettingNew();
        Assert.notNull(salarySettingNew, "薪资数据暂未配置，请先配置好再生成薪资数据");

        // 获取绩效配置
        PerformanceSettingDTO performanceSetting = performanceSettingService.getPerformanceSettingByMonth(month);
        if (performanceSetting == null) {    // 如果找不到绩效配置，则直接取所在月份的默认季度
            String[] split = month.split("-");
            Integer year = Integer.parseInt(split[0]);
            Integer quarter = Integer.parseInt(split[1]) / 3 + 1;
            performanceSetting = new PerformanceSettingDTO();
            performanceSetting.setYear(year).setQuarter(quarter);
            result.append("本次生成数据没有找到绩效配置，默认取年度").append(year).append("月度").append(quarter).append("的员工绩效数据\n");
        }

        for (StaffDTO staffDTO : staffListOnJob) {
            // 先获取该员工本月是否已经计算过薪资
            SalaryDTO salary = salaryMapper.getSalaryByStaffIdAndMonth(staffDTO.getId(), month);
            if (salary == null) {
                salary = new SalaryDTO();
            } else {
                if (salary.getFreeze() != null && salary.getFreeze()) {
                    // 冻结的数据直接跳过
                    ++freezeTimes;
                    freezeResult.append(salary.getStaffCode());
                    continue;
                }
            }

            StringBuffer remarks = new StringBuffer();
            // 获取绩效数据
            PerformanceDTO performance = performanceService.getPerformanceByStaffIdAndYearAndQuarter(staffDTO.getId(), performanceSetting.getYear(), performanceSetting.getQuarter());
            if (performance == null) {
                remarks.append("没有获取到该员工的绩效信息，本次不计算绩效数据");
            }

            // 获取考勤数据
            AttendanceDTO attendanceByStaffIdAndMonth = attendanceService.getAttendanceByStaffIdAndMonth(staffDTO.getId(), month);
            if (attendanceByStaffIdAndMonth == null) {
                remarks.append("没有获取到该员工的考勤数据，本次不计算绩效数据");
            }

            // 津贴数据


        }

        return result.toString();
    }

    @Override
    public String freezeSalaryDate(@NotEmpty(message = "不是有效的日期") String month, @NotNull(message = "请选择是否冻结") Boolean freeze) {
        // 检验date是否正确
        Assert.isTrue(DateUtils.isValidDate(month, "yyyy-MM"), "不是有效的日期");
        List<SalaryDO> salaryDOList = salaryMapper.getSalaryByMonth(month);
        for (SalaryDO salaryDO : salaryDOList) {
            salaryDO.setFreeze(freeze != null && freeze);
            salaryMapper.updateById(salaryDO);
        }
        return String.format("一共%s了%s条数据", freeze != null && freeze ? "冻结" : "解冻", salaryDOList.size());
    }

    @Override
    public void generateSalaryTemplate() {
        EasyExcelUtils.exportExcel(SalaryExcel.class, new ArrayList<>(), "模板", "薪资发放导入模板");
    }

    @Override
    public String importSalary(MultipartFile file) {
        List<SalaryExcel> list;
        try {
            list = EasyExcelUtils.readExcel(file.getInputStream(), SalaryExcel.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("文件读取失败");
        }

        Assert.isTrue(!CollectionUtils.isEmpty(list), "导入的文件不存在记录，请填写好再导入");

        StringBuffer stringBuffer = new StringBuffer();
        int errorTimes = 0;

        // 先检查是否存在部分缺少参数的，缺少参数则跳过
        List<SalaryExcel> errorItems = list.stream().filter(s -> !StringUtils.hasText(s.getStaffCode())).collect(Collectors.toList());
        Assert.isTrue(CollectionUtils.isEmpty(errorItems), "存在未填写的参数（员工编号、考核年份或考核季度），本次导入失败");

        for (SalaryExcel salaryExcel : list) {
            // 根据查找员工编号查找staffId
            StaffDTO staffDTO = staffService.getStaffByStaffCode(salaryExcel.getStaffCode());
            if (staffDTO == null) {
                stringBuffer.append("员工编号：").append(salaryExcel.getStaffCode()).append("未录入本系统;\n");
                ++errorTimes;
                continue;
            }

            SalaryDTO salaryDTO = null;
            if (salaryDTO == null) {
                salaryDTO = new SalaryDTO();
                BeanUtils.copyProperties(salaryExcel, salaryDTO);
                salaryDTO.setStaffId(staffDTO.getId());
                this.addSalary(salaryDTO);
            } else {
                BeanUtils.copyProperties(salaryExcel, salaryDTO);
                salaryDTO.setStaffId(staffDTO.getId());
                this.updateSalary(salaryDTO);
            }
        }

        if (StringUtils.hasText(stringBuffer)) {
            Assert.isTrue(list.size() != errorTimes, "导入失败，情况如下：\n" + stringBuffer);
            return String.format("成功导入%s条记录, %s条数据导入失败。\n详细如下：\n%s", list.size() - errorTimes, errorTimes, stringBuffer);
        }

        return String.format("成功导入%s条记录", list.size());
    }

    @Override
    public void exportSalary(String keyword, Long depId) {
        List<SalaryExcel> salaryList = salaryMapper.getSalaryExcelList(keyword, depId);
        EasyExcelUtils.exportExcel(SalaryExcel.class, salaryList, "薪资发放记录");
    }

}
