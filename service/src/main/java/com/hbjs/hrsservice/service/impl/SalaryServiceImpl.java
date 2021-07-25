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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {

    private final SalaryMapper salaryMapper;
    private final StaffService staffService;
    private final SalaryStaffService salaryStaffService;
    private final SalarySettingService salarySettingService;
    private final PerformanceService performanceService;
    private final AttendanceMonthService attendanceMonthService;
    private final AttendanceQuarterService attendanceQuarterService;
    private final AllowanceService allowanceService;
    private final DictionaryService dictionaryService;

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
    @Transactional
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
    @Transactional
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
    @Transactional
    public Long deleteSalary(Long salaryId) {
        SalaryDTO salaryDTO = this.getSalaryById(salaryId);
        Assert.notNull(salaryDTO, "删除失败：没有找到该薪资发放或已被删除");
        Assert.isTrue(!salaryDTO.getFreeze(), "删除失败：当前薪资发放数据已被冻结，请解除冻结再操作");
        salaryMapper.deleteById(salaryId);
        return salaryId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteSalary(@NotEmpty(message = "没有需要删除的薪资发放") List<Long> salaryIds) {
        for (Long salaryId : salaryIds) {
            this.deleteSalary(salaryId);
        }
        return salaryIds;
    }

    @Override
    @Transactional
    public String generateSalaryData(@NotEmpty(message = "不是有效的日期") String month) {
        // 检验month是否正确
        Assert.isTrue(DateUtils.isValidDate(month, "yyyy-MM"), "不是有效的日期");
        List<StaffDTO> staffListOnJob = staffService.getStaffListOnJob(month);
        int freezeTimes = 0;
        int noSalaryTimes = 0;
        StringBuilder freezeResult = new StringBuilder("员工编号有：");
        StringBuilder noSalaryResult = new StringBuilder("员工编号有：");
        StringBuilder result = new StringBuilder();
        // 获取薪资配置
        SalarySettingDTO salarySetting = salarySettingService.getSalarySettingNew();
        Assert.notNull(salarySetting, "薪资数据暂未配置，请先配置好再生成薪资数据");

        // 计算当前月份取哪一期绩效数据
        String[] split = month.split("-");
        int year = Integer.parseInt(split[0]);
        int monthNo = Integer.parseInt(split[1]);
        int quarter = Integer.parseInt(split[1]) / 3 + 1;
        if (quarter == 1) {
            --year;
            quarter = 4;
        }

        for (StaffDTO staffDTO : staffListOnJob) {
            // 获取定薪数据
            SalaryStaffDTO salaryStaff = salaryStaffService.getSalaryStaffByStaffId(staffDTO.getId());
            if (salaryStaff == null) {
                noSalaryResult.append(freezeTimes == 0 ? "" : ",").append(staffDTO.getStaffCode());
                ++noSalaryTimes;
                continue;
            }

            // 先获取该员工本月是否已经计算过薪资
            SalaryDTO salary = salaryMapper.getSalaryByStaffIdAndMonth(staffDTO.getId(), month);
            if (salary == null) {
                salary = new SalaryDTO();
            } else {
                if (salary.getFreeze() != null && salary.getFreeze()) {
                    // 冻结的数据直接跳过
                    freezeResult.append(freezeTimes == 0 ? "" : ",").append(staffDTO.getStaffCode());
                    ++freezeTimes;
                    continue;
                }
            }

            // 取基础工资、岗位工资
            salary
                    .setBasicSalary(salaryStaff.getBasicSalary())
                    .setPostSalary(salaryStaff.getPostSalary());

            // 基础工资总计（础工资 + 岗位工资）
            BigDecimal basicSalaryTotal = salaryStaff.getBasicSalary().add(salaryStaff.getPostSalary());

            StringBuilder remarks = new StringBuilder();
            // 获取绩效数据
            PerformanceDTO performance = performanceService.getPerformanceByStaffIdAndYearAndQuarter(staffDTO.getId(), year, quarter);
            BigDecimal meritSalary = BigDecimal.ZERO;
            if (performance == null || !StringUtils.hasText(performance.getAppraise())) {
                remarks.append("没有获取到该员工的绩效信息，本次不计算绩效数据\n");
            } else {
                if (StringUtils.hasText(staffDTO.getPostLevel())) {
                    // 取字典中的岗位等级系数
                    DictionaryDTO postLevel = dictionaryService.getDictionaryByCode(staffDTO.getPostLevel());
                    if (postLevel == null) {
                        remarks.append("没有获取到该员工的岗位等级配置的岗位系数，本次不计算绩效数据\n");
                    } else {
                        BigDecimal meritCoefficient = new BigDecimal(postLevel.getExpress());
                        Integer appraiseCoefficient;
                        switch (performance.getAppraise()) {
                            case "A":
                                appraiseCoefficient = salarySetting.getMeritA();
                                break;
                            case "B":
                                appraiseCoefficient = salarySetting.getMeritB();
                                break;
                            case "C":
                                appraiseCoefficient = salarySetting.getMeritC();
                                break;
                            case "D":
                                appraiseCoefficient = salarySetting.getMeritD();
                                break;
                            case "E":
                                appraiseCoefficient = salarySetting.getMeritE();
                                break;
                            case "F":
                                appraiseCoefficient = salarySetting.getMeritF();
                                break;
                            default:
                                appraiseCoefficient = 100;
                                remarks.append("没有获取到该员工的绩效评价或绩效评价的值不为ABCDEF，本次绩效发放比例以100%计算\n");
                        }

                        // 出勤系数 = 季度实出勤天数 ÷ 季度应出勤天数
                        // 取上季度三个月的月度考勤
                        AttendanceQuarterDTO attendanceQuarter = attendanceQuarterService.calculateAttendanceQuarter(staffDTO.getId(), year, quarter);

                        BigDecimal attendanceCoefficient;
                        if (attendanceQuarter.getShouldAttendanceDays() == 0) {
                            // 如果获取季度不到季度考勤默认出勤系数为1
                            attendanceCoefficient = BigDecimal.valueOf(1);
                            remarks.append("没有获取到该员工的季度考勤数据，本次绩效出勤系数以1计算\n");
                        } else {
                            attendanceCoefficient = BigDecimal.valueOf(attendanceQuarter.getActualAttendanceDays()).divide(BigDecimal.valueOf(attendanceQuarter.getShouldAttendanceDays()), 3);
                        }

                        // 绩效工资 = 绩效工资基数 × 职位系数 × 发放比例 × 出勤系数
                        meritSalary = salarySetting.getMeritSalary()
                                .multiply(meritCoefficient)
                                .multiply(BigDecimal.valueOf(appraiseCoefficient))
                                .multiply(attendanceCoefficient);
                    }
                } else {
                    remarks.append("没有获取到该员工的岗位等级信息，本次不计算绩效数据\n");
                }

            }
            salary.setMeritSalary(meritSalary);


            // 获取月度考勤
            AttendanceMonthDTO attendance = attendanceMonthService.getAttendanceMonthByStaffIdAndMonth(staffDTO.getId(), month);
            if (attendance == null) {
                remarks.append("没有获取到该员工的月度考勤，本次不计算与考勤相关工资\n");

                // 加班费设置为0
                salary
                        .setFullAttendanceAllowance(BigDecimal.ZERO)
                        .setFullAttendanceDeduct(BigDecimal.ZERO)
                        .setOvertimeSalary(BigDecimal.ZERO)
                        .setHotWeatherAllowance(BigDecimal.ZERO)
                        .setOnDutyAllowance(BigDecimal.ZERO)
                        .setFullAttendanceDeduct(BigDecimal.ZERO)
                        .setSickSalary(BigDecimal.ZERO)
                        .setSickLeaveDeduct(BigDecimal.ZERO)
                ;


            } else if (attendance.getShouldAttendanceDays().equals(0)) {      // 应出勤出现0
                remarks.append("获取到该员工的月度考勤中应出勤为0，本次不计算与考勤相关工资\n");

            } else {
                // 实际出勤率
                BigDecimal actualAttendanceRate = BigDecimal.valueOf(attendance.getActualAttendanceDays()).divide(BigDecimal.valueOf(attendance.getShouldAttendanceDays()), 3);

                // 全勤奖：只要出现病假、事假、迟到、签卡超过3次，全勤奖归零。
                if (!(attendance.getSickLeaveDays() > 0 ||
                        attendance.getAbsenceLeaveDays() > 0 ||
                        attendance.getLateMinutes() > 0 ||
                        attendance.getLeaveEarlyMinutes() > 0 ||
                        attendance.getSignCardTimes() > 3)) {
                    salary.setFullAttendanceAllowance(salarySetting.getFullAttendanceAllowance());
                } else {
                    salary.setFullAttendanceAllowance(BigDecimal.ZERO);
                }

                // 计算加班费
                BigDecimal overTimeSalary = BigDecimal.ZERO;
                BigDecimal dayBasicSalary = salaryStaff.getBasicSalary().divide(BigDecimal.valueOf(21.75), 3);
                if (attendance.getOvertimeWeekHours() > 0) {
                    overTimeSalary = overTimeSalary.add(
                            dayBasicSalary
                                    .multiply(BigDecimal.valueOf(1.5))
                                    .multiply(BigDecimal.valueOf(attendance.getOvertimeWeekHours())));
                }
                if (attendance.getOvertimeWeekendHours() > 0) {
                    overTimeSalary = overTimeSalary.add(
                            dayBasicSalary
                                    .multiply(BigDecimal.valueOf(2))
                                    .multiply(BigDecimal.valueOf(attendance.getOvertimeWeekHours())));
                }
                if (attendance.getOvertimeFestivalHours() > 0) {
                    overTimeSalary = overTimeSalary.add(
                            dayBasicSalary
                                    .multiply(BigDecimal.valueOf(3))
                                    .multiply(BigDecimal.valueOf(attendance.getOvertimeWeekHours())));
                }
                salary.setOvertimeSalary(overTimeSalary);

                // 高温津贴
                String hotWeatherAllowanceGrade = salaryStaff.getHotWeatherGrade();
                if ("ABC".contains(hotWeatherAllowanceGrade) && (monthNo >= salarySetting.getHotWeatherStartMonth() && monthNo <= salarySetting.getHotWeatherEndMonth())) {
                    BigDecimal hotWeatherAllowance = BigDecimal.ZERO;

                    switch (hotWeatherAllowanceGrade) {
                        case "A":
                            hotWeatherAllowance = salarySetting.getHotWeatherAllowanceA().multiply(actualAttendanceRate);
                            break;
                        case "B":
                            hotWeatherAllowance = salarySetting.getHotWeatherAllowanceB().multiply(actualAttendanceRate);
                            break;
                        case "C":
                            hotWeatherAllowance = salarySetting.getHotWeatherAllowanceC().multiply(actualAttendanceRate);
                            break;
                    }

                    salary.setHotWeatherAllowance(hotWeatherAllowance);
                }

                // 值班费用
                BigDecimal onDutyAllowance = BigDecimal.ZERO;
                if (attendance.getDutyWeek() > 0) {             // 值班（工作日）天数
                    onDutyAllowance = onDutyAllowance.add(salarySetting.getDutyWeekFee().multiply(BigDecimal.valueOf(attendance.getDutyWeek())));
                }
                if (attendance.getDutyBeforeWeek() > 0) {       // 值班（休息日前一天）天数
                    onDutyAllowance = onDutyAllowance.add(salarySetting.getDutyBeforeWeekFee().multiply(BigDecimal.valueOf(attendance.getDutyWeek())));
                }
                if (attendance.getDutyBeforeFestival() > 0) {   // 值班（法定节假日前一天）天数
                    onDutyAllowance = onDutyAllowance.add(salarySetting.getDutyBeforeFestivalFee().multiply(BigDecimal.valueOf(attendance.getDutyWeek())));
                }
                if (attendance.getDutyWeekend() > 0) {          // 值班（休息日）天数
                    onDutyAllowance = onDutyAllowance.add(salarySetting.getDutyWeekendFee().multiply(BigDecimal.valueOf(attendance.getDutyWeek())));
                }
                if (attendance.getDutyFestival() > 0) {         // 值班（法定节假日（春节假期除外））天数
                    onDutyAllowance = onDutyAllowance.add(salarySetting.getDutyFestivalFee().multiply(BigDecimal.valueOf(attendance.getDutyWeek())));
                }
                if (attendance.getDutyOutSpring() > 0) {        // 值班（春节假期（不含除夕、初一、初二））天数
                    onDutyAllowance = onDutyAllowance.add(salarySetting.getDutyOutSpringFee().multiply(BigDecimal.valueOf(attendance.getDutyWeek())));
                }
                if (attendance.getDutyInSpring() > 0) {         //值班（春节假期（除夕、初一、初二））天数
                    onDutyAllowance = onDutyAllowance.add(salarySetting.getDutyInSpringFee().multiply(BigDecimal.valueOf(attendance.getDutyWeek())));
                }
                salary.setOnDutyAllowance(onDutyAllowance);

                // 扣工资
                BigDecimal avgHoursSalary = basicSalaryTotal.divide(BigDecimal.valueOf(174), 3);

                // 迟到
                BigDecimal late = BigDecimal.ZERO;
                if (attendance.getLateMinutes() + attendance.getLeaveEarlyMinutes() >= 15) {  // 迟到早退总时长超过15分钟
                    late = avgHoursSalary.multiply(BigDecimal.valueOf(attendance.getLateMinutes() + attendance.getLeaveEarlyMinutes()));
                }

                // 事假
                BigDecimal absenceLeave = avgHoursSalary.multiply(BigDecimal.valueOf(attendance.getAbsenceLeaveDays()));

                // 扣全勤（迟到+事假）
                salary.setFullAttendanceDeduct(late.add(absenceLeave));

                // 计算病假相关
                if (attendance.getSickLeaveDays() > 0) {
                    // 扣病假
                    BigDecimal sickLeave = avgHoursSalary.multiply(BigDecimal.valueOf(8)).multiply(BigDecimal.valueOf(attendance.getSickLeaveDays()));
                    salary.setSickLeaveDeduct(sickLeave);

                    // 病假工资
                    // 计算工龄
                    String sickLeaveGrade = "A";
                    if (staffDTO.getWorkDate() == null) {
                        remarks.append("没有取到该员工的开始工作时间，没办法计算工龄，本次计算病假工资以不满5年计算");
                    } else {
                        LocalDate nowDate = LocalDate.now();
                        LocalDate workDate = staffDTO.getWorkDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        long workYear = ChronoUnit.YEARS.between(nowDate, workDate);
                        if (workYear < 5) {
                            sickLeaveGrade = "A";
                        } else if (workYear < 10) {
                            sickLeaveGrade = "B";
                        } else if (workYear < 20) {
                            sickLeaveGrade = "C";
                        } else if (workYear < 30) {
                            sickLeaveGrade = "D";
                        } else {
                            sickLeaveGrade = "E";
                        }
                    }
                    BigDecimal sickSalary = BigDecimal.ZERO;
                    
                    switch (sickLeaveGrade) {
                        case "A":
                            sickSalary = dayBasicSalary.multiply(BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(attendance.getSickLeaveDays()));
                            break;
                        case "B":
                            sickSalary = dayBasicSalary.multiply(BigDecimal.valueOf(0.6)).multiply(BigDecimal.valueOf(attendance.getSickLeaveDays()));
                            break;
                        case "C":
                            if (attendance.getSickLeaveDays() < 180) {
                                sickSalary = dayBasicSalary.multiply(BigDecimal.valueOf(0.8)).multiply(BigDecimal.valueOf(attendance.getSickLeaveDays()));
                            } else {
                                sickSalary = dayBasicSalary.multiply(BigDecimal.valueOf(0.7)).multiply(BigDecimal.valueOf(attendance.getSickLeaveDays()));
                            }
                            break;
                        case "D":
                            if (attendance.getSickLeaveDays() < 180) {
                                sickSalary = dayBasicSalary.multiply(BigDecimal.valueOf(0.9)).multiply(BigDecimal.valueOf(attendance.getSickLeaveDays()));
                            } else {
                                sickSalary = dayBasicSalary.multiply(BigDecimal.valueOf(0.8)).multiply(BigDecimal.valueOf(attendance.getSickLeaveDays()));
                            }
                            break;
                        case "E":
                            if (attendance.getSickLeaveDays() < 180) {
                                sickSalary = dayBasicSalary.multiply(BigDecimal.valueOf(0.95)).multiply(BigDecimal.valueOf(attendance.getSickLeaveDays()));
                            } else {
                                sickSalary = dayBasicSalary.multiply(BigDecimal.valueOf(0.9)).multiply(BigDecimal.valueOf(attendance.getSickLeaveDays()));
                            }
                            break;
                    }
                    
                    salary.setSickSalary(sickSalary);
                } else {
                    salary
                            .setSickSalary(BigDecimal.ZERO)
                            .setSickLeaveDeduct(BigDecimal.ZERO);
                }

                // todo: 计算交通补贴

            }


            // 津贴数据（含奖金）
            AllowanceDTO allowance = allowanceService.getAllowanceByStaffIdAndMonth(staffDTO.getId(), month);
            if (allowance == null) {
                salary
                        .setBackSalary(BigDecimal.ZERO)
                        .setAnnualBonus(BigDecimal.ZERO)
                        .setSafetyBonus(BigDecimal.ZERO)
                        .setStabilityBonus(BigDecimal.ZERO)
                        .setFamilyPlanningBonus(BigDecimal.ZERO)
                        .setExcellenceBonus(BigDecimal.ZERO)
                        .setSpecialBonus(BigDecimal.ZERO)
                        .setFestivalAllowance(BigDecimal.ZERO)
                        .setCommunicationAllowance(BigDecimal.ZERO)
                        .setOtherAllowance(BigDecimal.ZERO)
                        .setBirthdayCard(BigDecimal.ZERO)
                        .setCoolDrink(BigDecimal.ZERO)
                        .setCondolenceGoods(BigDecimal.ZERO)
                        .setRent(BigDecimal.ZERO)
                        .setPhoneBill(BigDecimal.ZERO);
                remarks.append("没有获取到该员工的津贴数据，本次不计算与津贴相关工资");
            } else {
                salary
                        .setBackSalary(allowance.getBackSalary())
                        .setAnnualBonus(allowance.getAnnualBonus())
                        .setSafetyBonus(allowance.getSafetyBonus())
                        .setStabilityBonus(allowance.getStabilityBonus())
                        .setFamilyPlanningBonus(allowance.getFamilyPlanningBonus())
                        .setExcellenceBonus(allowance.getExcellenceBonus())
                        .setSpecialBonus(allowance.getSpecialBonus())
                        .setFestivalAllowance(allowance.getFestivalAllowance())
                        .setCommunicationAllowance(allowance.getCommunicationAllowance())
                        .setOtherAllowance(allowance.getOtherAllowance())
                        .setBirthdayCard(allowance.getBirthdayCard())
                        .setCoolDrink(allowance.getCoolDrink())
                        .setCondolenceGoods(allowance.getCondolenceGoods())
                        .setRent(allowance.getRent())
                        .setPhoneBill(allowance.getPhoneBill());
            }

            // 计算安全津贴
            switch (salaryStaff.getSafetyGrade()) {
                case "A":
                    salary.setSafetyAllowance(salarySetting.getSafetyAllowanceA());
                    break;
                case "B":
                    salary.setSafetyAllowance(salarySetting.getSafetyAllowanceB());
                    break;
                case "C":
                    salary.setSafetyAllowance(salarySetting.getSafetyAllowanceC());
                    break;
                default:
                    salary.setSafetyAllowance(BigDecimal.ZERO);
            }

            // 更新薪资数据
            salary.setRemarks(remarks.toString());
            if (salary.getId() == null) {
                this.addSalary(salary);
            } else {
                this.updateSalary(salary);
            }

        }

        if (freezeTimes > 0) {
            result.append("存在").append(freezeTimes).append("条薪资发放的冻结数据，有：").append(freezeResult).append("\n");
        }

        if (noSalaryTimes > 0) {
            result.append("存在").append(noSalaryTimes).append("位员工没有定薪数据的，有：").append(noSalaryResult).append("\n");
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

        StringBuilder errorResult = new StringBuilder();
        int errorTimes = 0;

        // 先检查是否存在部分缺少参数的，缺少参数则跳过
        List<SalaryExcel> errorItems = list.stream().filter(s -> !StringUtils.hasText(s.getStaffCode())).collect(Collectors.toList());
        Assert.isTrue(CollectionUtils.isEmpty(errorItems), "存在未填写的参数（员工编号、考核年份或考核季度），本次导入失败");

        for (SalaryExcel salaryExcel : list) {
            // 根据查找员工编号查找staffId
            StaffDTO staffDTO = staffService.getStaffByStaffCode(salaryExcel.getStaffCode());
            if (staffDTO == null) {
                errorResult.append("员工编号：").append(salaryExcel.getStaffCode()).append("未录入本系统;\n");
                ++errorTimes;
                continue;
            }

            SalaryDTO salaryDTO = salaryMapper.getSalaryByStaffIdAndMonth(staffDTO.getId(), DateUtils.format(salaryExcel.getMonth(), "yyyy-MM"));
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

        if (StringUtils.hasText(errorResult)) {
            Assert.isTrue(list.size() != errorTimes, "导入失败，情况如下：\n" + errorResult);
            return String.format("成功导入%s条记录, %s条数据导入失败。\n详细如下：\n%s", list.size() - errorTimes, errorTimes, errorResult);
        }

        return String.format("成功导入%s条记录", list.size());
    }

    @Override
    public void exportSalary(String keyword, Long depId) {
        List<SalaryExcel> salaryList = salaryMapper.getSalaryExcelList(keyword, depId);
        EasyExcelUtils.exportExcel(SalaryExcel.class, salaryList, "薪资发放记录");
    }

}
