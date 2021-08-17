package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.SalaryConverter;
import com.hbjs.hrscommon.domain.hr.SalaryDO;
import com.hbjs.hrscommon.dto.*;
import com.hbjs.hrscommon.excel.SalaryExcel;
import com.hbjs.hrscommon.excel.SalaryTaxExcel;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.DateUtils;
import com.hbjs.hrscommon.utils.EasyExcelUtils;
import com.hbjs.hrscommon.utils.SalaryUtils;
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
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
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
    public Page<SalaryDTO> getSalaryPage(Page<SalaryDTO> page, String keyword, Long depId, String type, Boolean freeze) {
        return salaryMapper.getSalaryPage(page, keyword, depId, type, freeze);
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
        // 先检查薪资发放是否已存在，主要是工资同一个人一月只有一条
        if ("工资".equals(salaryDTO.getType())) {
            SalaryDTO salaryInDB = salaryMapper.getSalaryByStaffIdAndMonthAndType(salaryDTO.getId(), DateUtils.format(salaryDTO.getMonth(), "yyyy-MM"), "工资");
            Assert.isNull(salaryInDB, "新增失败：该员工的薪资发放已存在发薪类型为工资的记录");
        }

        SalaryDO salaryDO = SalaryConverter.toDO(salaryDTO);
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
        if ("工资".equals(salaryDTO.getType())) {
            SalaryDTO salaryInDB = salaryMapper.getSalaryByStaffIdAndMonthAndType(salaryDTO.getId(), DateUtils.format(salaryDTO.getMonth(), "yyyy-MM"), "工资");
            Assert.isTrue(salaryInDB == null || salaryInDB.getId().equals(salaryDO.getId()),
                    "更新失败：该员工的薪资发放已存在发薪类型为工资的记录");
        }

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
    public String generateSalaryData(@NotEmpty(message = "不是有效的日期") String month, String depId, String staffCode) {
        // 检验month是否正确
        Assert.isTrue(DateUtils.isValidDate(month, "yyyy-MM"), "不是有效的日期");
        List<StaffDTO> staffListOnJob = staffService.getStaffListOnJob(month, depId, staffCode);
        int freezeTimes = 0;
        int noSalaryTimes = 0;
        StringBuilder freezeResult = new StringBuilder("员工编号：");
        StringBuilder noSalaryResult = new StringBuilder("员工编号：");
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
            SalaryDTO salary = salaryMapper.getSalaryByStaffIdAndMonthAndType(staffDTO.getId(), month, "工资");
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

            // 先将所有数据预设为0
            salary
                    .setStaffId(staffDTO.getId())
                    .setMonth(DateUtils.format(month, "yyyy-MM"))
                    .setType("工资");
            SalaryUtils.clearSalaryValue(salary);

            StringBuilder remarks = new StringBuilder();


            // 取基础工资、岗位工资
            salary
                    .setBasicSalary(salaryStaff.getBasicSalary())
                    .setPostSalary(salaryStaff.getPostSalary());

            // 基础工资总计（础工资 + 岗位工资）
            BigDecimal basicSalaryTotal = salaryStaff.getBasicSalary().add(salaryStaff.getPostSalary());

            // 取公积金基数
            BigDecimal accumulationFund = salaryStaff.getAccumulationFund();

            // 公积金
            salary.setAccumulationFund(accumulationFund.multiply(salarySetting.getAccumulationFundRate()).divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_UP));

            // 判断养老保险基数上限
            BigDecimal endowmentInsurance;
            if (accumulationFund.compareTo(salarySetting.getEndowmentInsuranceLower()) < 0) {               // 小于基数
                endowmentInsurance = salarySetting.getEndowmentInsuranceLower().multiply(salarySetting.getEndowmentInsuranceRate()).divide(BigDecimal.valueOf(100), 3, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
            } else if (accumulationFund.compareTo(salarySetting.getEndowmentInsuranceUpper()) > 0) {        // 小于基数
                endowmentInsurance = salarySetting.getEndowmentInsuranceUpper().multiply(salarySetting.getEndowmentInsuranceRate()).divide(BigDecimal.valueOf(100), 3, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
            } else {
                endowmentInsurance = salaryStaff.getAccumulationFund().multiply(salarySetting.getEndowmentInsuranceRate()).divide(BigDecimal.valueOf(100), 3, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
            }
            salary.setEndowmentInsurance(endowmentInsurance);

            // 判断失业保险基数上限
            BigDecimal unemploymentInsurance;
            if (accumulationFund.compareTo(salarySetting.getUnemploymentInsuranceLower()) < 0) {               // 小于基数
                unemploymentInsurance = salarySetting.getUnemploymentInsuranceLower().multiply(salarySetting.getEndowmentInsuranceRate()).divide(BigDecimal.valueOf(100), 3, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
            } else if (accumulationFund.compareTo(salarySetting.getUnemploymentInsuranceUpper()) > 0) {        // 小于基数
                unemploymentInsurance = salarySetting.getUnemploymentInsuranceUpper().multiply(salarySetting.getEndowmentInsuranceRate()).divide(BigDecimal.valueOf(100), 3, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
            } else {
                unemploymentInsurance = salaryStaff.getAccumulationFund().multiply(salarySetting.getEndowmentInsuranceRate()).divide(BigDecimal.valueOf(100), 3, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
            }
            salary.setUnemploymentInsurance(unemploymentInsurance);

            // 判断失业保险基数上限
            BigDecimal medicalInsurance;
            if (accumulationFund.compareTo(salarySetting.getMedicalInsuranceLower()) < 0) {               // 小于基数
                medicalInsurance = salarySetting.getMedicalInsuranceLower().multiply(salarySetting.getEndowmentInsuranceRate()).divide(BigDecimal.valueOf(100), 3, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
            } else if (accumulationFund.compareTo(salarySetting.getMedicalInsuranceUpper()) > 0) {        // 小于基数
                medicalInsurance = salarySetting.getMedicalInsuranceUpper().multiply(salarySetting.getEndowmentInsuranceRate()).divide(BigDecimal.valueOf(100), 3, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
            } else {
                medicalInsurance = salaryStaff.getAccumulationFund().multiply(salarySetting.getEndowmentInsuranceRate()).divide(BigDecimal.valueOf(100), 3, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
            }
            salary.setMedicalInsurance(medicalInsurance);

            // 取字典中的岗位等级系数
            DictionaryDTO postLevel = dictionaryService.getDictionaryByCode(staffDTO.getPostLevel());
            if (postLevel != null) {
                salary.setPostLevel(postLevel.getCode());
            }

            // 获取绩效数据
            PerformanceDTO performance = performanceService.getPerformanceByStaffIdAndYearAndQuarter(staffDTO.getId(), year, quarter);
            BigDecimal meritSalary = BigDecimal.ZERO;
            if (performance == null || !StringUtils.hasText(performance.getAppraise())) {
                remarks.append("没有获取到该员工的绩效信息，本次不计算绩效数据；\n");
            } else {
                if (StringUtils.hasText(staffDTO.getPostLevel())) {
                    if (postLevel == null) {
                        remarks.append("没有获取到该员工的岗位等级配置的岗位系数，本次不计算绩效数据；\n");
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
                                remarks.append("没有获取到该员工的绩效评价或绩效评价的值不为ABCDEF，本次绩效发放比例以100%计算；\n");
                        }

                        // 出勤系数 = 季度实出勤天数 ÷ 季度应出勤天数
                        // 取上季度三个月的月度考勤
                        AttendanceQuarterDTO attendanceQuarter = attendanceQuarterService.calculateAttendanceQuarter(staffDTO.getId(), year, quarter);

                        BigDecimal attendanceCoefficient;
                        if (attendanceQuarter.getShouldAttendanceDays() == 0) {
                            // 如果获取季度不到季度考勤默认出勤系数为1
                            attendanceCoefficient = BigDecimal.valueOf(1);
                            remarks.append("没有获取到该员工的季度考勤数据，本次绩效出勤系数以1计算；\n");
                        } else {
                            attendanceCoefficient = BigDecimal.valueOf(attendanceQuarter.getActualAttendanceDays()).divide(BigDecimal.valueOf(attendanceQuarter.getShouldAttendanceDays()), 3, RoundingMode.HALF_UP);
                        }

                        // 绩效工资 = 绩效工资基数 × 职位系数 × 发放比例 × 出勤系数
                        meritSalary = salarySetting.getMeritSalary()
                                .multiply(meritCoefficient)
                                .multiply(BigDecimal.valueOf(appraiseCoefficient))
                                .multiply(attendanceCoefficient);
                    }
                } else {
                    remarks.append("没有获取到该员工的岗位等级信息，本次不计算绩效数据；\n");
                }

            }
            salary.setMeritSalary(meritSalary);


            // 获取月度考勤
            AttendanceMonthDTO attendance = attendanceMonthService.getAttendanceMonthByStaffIdAndMonth(staffDTO.getId(), month);
            if (attendance == null) {
                remarks.append("没有获取到该员工的月度考勤，本次不计算与考勤相关工资；\n");
            } else if (attendance.getShouldAttendanceDays().equals(0)) {      // 应出勤出现0
                remarks.append("获取到该员工的月度考勤中应出勤为0，本次不计算与考勤相关工资；\n");
            } else {
                if (attendance.getActualAttendanceDays() > attendance.getShouldAttendanceDays()) {     // 实出勤大于应出勤跳过计算
                    remarks.append("获取到该员工的月度考勤中实出勤大于应出勤跳过计算，本次不计算与考勤相关工资；\n");
                } else {
                    // 实际出勤率
                    BigDecimal actualAttendanceRate = BigDecimal.valueOf(attendance.getActualAttendanceDays()).divide(BigDecimal.valueOf(attendance.getShouldAttendanceDays()), 3, RoundingMode.HALF_UP);

                    // 缺勤天数
                    BigDecimal absenceDays = BigDecimal.valueOf(attendance.getShouldAttendanceDays() - attendance.getActualAttendanceDays());

                    // 缺勤率
//                    BigDecimal absenceRate = BigDecimal.valueOf(absenceDays).divide(BigDecimal.valueOf(attendance.getShouldAttendanceDays()), 3, RoundingMode.HALF_UP);


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

                    // 取加班费基数（1：全公司统一加班费基数，2：取员工基本工资，3：取员工基本工资+岗位工资）
                    BigDecimal overtimeBaseSalary;
                    switch (salarySetting.getOvertimeMode()) {
                        case "2":
                            overtimeBaseSalary = salaryStaff.getBasicSalary();
                            break;
                        case "3":
                            overtimeBaseSalary = salaryStaff.getBasicSalary().add(salaryStaff.getPostSalary());
                            break;
                        default:
                            overtimeBaseSalary = salarySetting.getOvertimeFees();
                            break;
                    }

                    BigDecimal dayBasicSalary = overtimeBaseSalary.divide(BigDecimal.valueOf(21.75), 3, RoundingMode.HALF_UP);
                    if (attendance.getOvertimeWeekHours() > 0) {
                        overTimeSalary = overTimeSalary.add(dayBasicSalary
                                .multiply(BigDecimal.valueOf(1.5))
                                .multiply(BigDecimal.valueOf(attendance.getOvertimeWeekHours())));
                    }
                    if (attendance.getOvertimeWeekendHours() > 0) {
                        overTimeSalary = overTimeSalary.add(dayBasicSalary
                                .multiply(BigDecimal.valueOf(2))
                                .multiply(BigDecimal.valueOf(attendance.getOvertimeWeekHours())));
                    }
                    if (attendance.getOvertimeFestivalHours() > 0) {
                        overTimeSalary = overTimeSalary.add(dayBasicSalary
                                .multiply(BigDecimal.valueOf(3))
                                .multiply(BigDecimal.valueOf(attendance.getOvertimeWeekHours())));
                    }
                    salary.setOvertimeSalary(overTimeSalary.setScale(2, RoundingMode.HALF_UP));


                    // 高温津贴
                    if (monthNo >= salarySetting.getHotWeatherStartMonth() && monthNo <= salarySetting.getHotWeatherEndMonth()) {
                        BigDecimal hotWeatherAllowanceStand = BigDecimal.ZERO;
                        BigDecimal hotWeatherAllowanceOneDay = BigDecimal.ZERO;

//                        // 按天发放高温津贴
//                        if (attendance.getHotWeatherDays() > 0) {
//                            hotWeatherAllowance = salarySetting.getHotWeatherAllowanceA()
//                                    .divide(BigDecimal.valueOf(21.75), 3, RoundingMode.HALF_UP)
//                                    .multiply(BigDecimal.valueOf(attendance.getHotWeatherDays()))
//                                    .setScale(2, RoundingMode.HALF_UP);
//                        }

                        switch (salaryStaff.getHotWeatherGrade()) {
                            case "A":
                                hotWeatherAllowanceStand = salarySetting.getHotWeatherAllowanceA();
                                hotWeatherAllowanceOneDay = hotWeatherAllowanceStand.divide(BigDecimal.valueOf(21.75), 3, RoundingMode.HALF_UP);
                                break;
                            case "B":
                                hotWeatherAllowanceStand = salarySetting.getHotWeatherAllowanceB().multiply(actualAttendanceRate);
                                hotWeatherAllowanceOneDay = hotWeatherAllowanceStand.divide(BigDecimal.valueOf(21.75), 3, RoundingMode.HALF_UP);
                                break;
                            case "C":
                                hotWeatherAllowanceStand = salarySetting.getHotWeatherAllowanceC().multiply(actualAttendanceRate);
                                hotWeatherAllowanceOneDay = hotWeatherAllowanceStand.divide(BigDecimal.valueOf(21.75), 3, RoundingMode.HALF_UP);
                                break;
                        }
                        BigDecimal hotWeatherAllowance = hotWeatherAllowanceStand.subtract(hotWeatherAllowanceOneDay.multiply(absenceDays));
                        salary.setHotWeatherAllowance(hotWeatherAllowance.setScale(2, RoundingMode.HALF_UP));
                    }

                    // 计算安全津贴
                    BigDecimal safetyAllowanceStand = BigDecimal.ZERO;
                    BigDecimal safetyAllowanceOneDay = BigDecimal.ZERO;

                    switch (salaryStaff.getSafetyGrade()) {
                        case "A":
                            safetyAllowanceStand = salarySetting.getSafetyAllowanceA();
                            safetyAllowanceOneDay = safetyAllowanceStand.divide(BigDecimal.valueOf(21.75), 3, RoundingMode.HALF_UP);
                            break;
                        case "B":
                            safetyAllowanceStand = salarySetting.getSafetyAllowanceB();
                            safetyAllowanceOneDay = safetyAllowanceStand.divide(BigDecimal.valueOf(21.75), 3, RoundingMode.HALF_UP);
                            break;
                        case "C":
                            safetyAllowanceStand = salarySetting.getSafetyAllowanceC();
                            safetyAllowanceOneDay = safetyAllowanceStand.divide(BigDecimal.valueOf(21.75), 3, RoundingMode.HALF_UP);
                            break;
                    }
                    BigDecimal safetyAllowance = safetyAllowanceStand.subtract(safetyAllowanceOneDay.multiply(absenceDays));
                    salary.setSafetyAllowance(safetyAllowance);


                    // 值班费用
                    BigDecimal onDutyAllowance = BigDecimal.ZERO;
                    if (attendance.getDutyWeek() > 0) {             // 值班（工作日）天数
                        onDutyAllowance = onDutyAllowance.add(salarySetting.getDutyWeekFee()
                                .multiply(BigDecimal.valueOf(attendance.getDutyWeek())));
                    }
                    if (attendance.getDutyBeforeWeek() > 0) {       // 值班（休息日前一天）天数
                        onDutyAllowance = onDutyAllowance.add(salarySetting.getDutyBeforeWeekFee()
                                .multiply(BigDecimal.valueOf(attendance.getDutyWeek())));
                    }
                    if (attendance.getDutyBeforeFestival() > 0) {   // 值班（法定节假日前一天）天数
                        onDutyAllowance = onDutyAllowance.add(salarySetting.getDutyBeforeFestivalFee()
                                .multiply(BigDecimal.valueOf(attendance.getDutyWeek())));
                    }
                    if (attendance.getDutyWeekend() > 0) {          // 值班（休息日）天数
                        onDutyAllowance = onDutyAllowance.add(salarySetting.getDutyWeekendFee()
                                .multiply(BigDecimal.valueOf(attendance.getDutyWeek())));
                    }
                    if (attendance.getDutyFestival() > 0) {         // 值班（法定节假日（春节假期除外））天数
                        onDutyAllowance = onDutyAllowance.add(salarySetting.getDutyFestivalFee()
                                .multiply(BigDecimal.valueOf(attendance.getDutyWeek())));
                    }
                    if (attendance.getDutyOutSpring() > 0) {        // 值班（春节假期（不含除夕、初一、初二））天数
                        onDutyAllowance = onDutyAllowance.add(salarySetting.getDutyOutSpringFee()
                                .multiply(BigDecimal.valueOf(attendance.getDutyWeek())));
                    }
                    if (attendance.getDutyInSpring() > 0) {         //值班（春节假期（除夕、初一、初二））天数
                        onDutyAllowance = onDutyAllowance.add(salarySetting.getDutyInSpringFee()
                                .multiply(BigDecimal.valueOf(attendance.getDutyWeek())));
                    }
                    salary.setOnDutyAllowance(onDutyAllowance);

                    // 扣工资

                    // 计算时薪
                    BigDecimal avgHoursSalary = basicSalaryTotal.divide(BigDecimal.valueOf(174), 3, RoundingMode.HALF_UP);

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
                            remarks.append("没有取到该员工的开始工作时间，没办法计算工龄，本次计算病假工资以不满5年计算；\n");
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

                        salary.setSickSalary(sickSalary.setScale(2, RoundingMode.HALF_UP));
                    } else {
                        salary
                                .setSickSalary(BigDecimal.ZERO)
                                .setSickLeaveDeduct(BigDecimal.ZERO);
                    }

                }
            }
            // 工会费
            salary.setUnionFees(salarySetting.getUnionFees());


            // 津贴数据（含奖金）
            AllowanceDTO allowance = allowanceService.getAllowanceByStaffIdAndMonth(staffDTO.getId(), month);
            if (allowance == null) {
                remarks.append("没有获取到该员工的津贴数据，本次不计算与津贴相关工资；");
            } else {
                salary
                        .setBackSalary(allowance.getBackSalary())
                        .setAnnualBonus(allowance.getAnnualBonus())
                        .setSafetyBonus(allowance.getSafetyBonus())
                        .setStabilityBonus(allowance.getStabilityBonus())
                        .setFamilyPlanningBonus(allowance.getFamilyPlanningBonus())
                        .setExcellenceBonus(allowance.getExcellenceBonus())
                        .setSpecialBonus(allowance.getSpecialBonus())
                        .setMealAllowance(allowance.getMealAllowance())
                        .setTrafficAllowance(allowance.getTrafficAllowance())
                        .setCommunicationAllowance(allowance.getCommunicationAllowance())
                        .setFestivalAllowance(allowance.getFestivalAllowance())
                        .setOtherAllowance(allowance.getOtherAllowance())
                        .setBirthdayCard(allowance.getBirthdayCard())
                        .setCoolDrink(allowance.getCoolDrink())
                        .setCondolenceGoods(allowance.getCondolenceGoods())
                        .setRent(allowance.getRent())
                        .setPhoneBill(allowance.getPhoneBill());
            }

            // 独生子女津贴
            if (salaryStaff.getHaveOneChildAllowance()) {
                salary.setOneChildAllowance(salarySetting.getOneChildAllowance());
            }

            // 计算小计、总计
            SalaryUtils.calculationSalaryTotal(salary);

            // 更新薪资数据
            salary.setRemarks(remarks.toString());
            if (salary.getId() == null) {
                this.addSalary(salary);
            } else {
                this.updateSalary(salary);
            }
        }

        if (freezeTimes > 0) {
            result.append("存在").append(freezeTimes).append("条薪资发放的冻结数据，").append(freezeResult).append("\n");
        }

        if (noSalaryTimes > 0) {
            result.append("存在").append(noSalaryTimes).append("位员工没有定薪数据的，").append(noSalaryResult).append("\n");
        }

        return String.format("一共处理了%s名员工薪资数据。\n%s", staffListOnJob.size(), result);
    }

    @Override
    @Transactional
    public String freezeSalaryByIds(List<Long> salaryIds, Boolean freeze) {
        for (Long salaryId : salaryIds) {
            SalaryDO salaryDO = salaryMapper.selectById(salaryId);
            if (salaryDO == null) {
                continue;
            }
            salaryDO.setFreeze(freeze != null && freeze);
            salaryMapper.updateById(salaryDO);
        }

        return String.format("一共%s了%s条数据。", freeze != null && freeze ? "冻结" : "解冻", salaryIds.size());
    }

    @Override
    @Transactional
    public String freezeSalaryByMonth(String month, Boolean freeze) {
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
    @Transactional
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
        List<SalaryExcel> loserItems = list.stream().filter(s -> !StringUtils.hasText(s.getStaffCode()) || s.getMonth() == null || !StringUtils.hasText(s.getType())).collect(Collectors.toList());
        Assert.isTrue(CollectionUtils.isEmpty(loserItems), "存在未填写的参数（员工编号、发放月份、薪资类型），本次导入失败");

        // 检查薪资类型是否只有工资、奖金两种
        List<SalaryExcel> noInItems = list.stream().filter(s -> (!"工资".equals(s.getType()) && !"奖金".equals(s.getType()))).collect(Collectors.toList());
        Assert.isTrue(CollectionUtils.isEmpty(noInItems), "薪资类型不能填写除工资、奖金这两种类型，本次导入失败");

        for (SalaryExcel salaryExcel : list) {
            // 根据查找员工编号查找staffId
            StaffDTO staffDTO = staffService.getStaffByStaffCode(salaryExcel.getStaffCode());
            if (staffDTO == null) {
                errorResult.append("员工编号：").append(salaryExcel.getStaffCode()).append(" 未录入本系统;\n");
                ++errorTimes;
                continue;
            }

            SalaryDTO salaryDTO = salaryMapper.getSalaryByStaffIdAndMonthAndType(staffDTO.getId(), DateUtils.format(salaryExcel.getMonth(), "yyyy-MM"), salaryExcel.getType());
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

    @Override
    public void generateSalaryTaxTemplate() {
        EasyExcelUtils.exportExcel(SalaryTaxExcel.class, new ArrayList<>(), "模板", "个税导入模板");
    }

    @Override
    public String importSalaryTax(MultipartFile file) {
        List<SalaryTaxExcel> list;
        try {
            list = EasyExcelUtils.readExcel(file.getInputStream(), SalaryTaxExcel.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("文件读取失败");
        }

        Assert.isTrue(!CollectionUtils.isEmpty(list), "导入的文件不存在记录，请填写好再导入");

        StringBuilder staffErrorResult = new StringBuilder("员工编号：");
        StringBuilder salaryErrorResult = new StringBuilder("员工编号：");
        int errorTimes = 0;
        int staffErrorTimes = 0;
        int salaryErrorTimes = 0;

        // 先检查是否存在部分缺少参数的，缺少参数则跳过
        List<SalaryTaxExcel> errorItems = list.stream().filter(s -> !StringUtils.hasText(s.getStaffCode()) || s.getMonth() == null || !StringUtils.hasText(s.getType()) || s.getIndividualIncomeTax() == null).collect(Collectors.toList());
        Assert.isTrue(CollectionUtils.isEmpty(errorItems), "存在未填写的参数（员工编号、发放月份），本次导入失败");

        for (SalaryTaxExcel salaryTaxExcel : list) {
            // 根据查找员工编号查找staffId
            StaffDTO staffDTO = staffService.getStaffByStaffCode(salaryTaxExcel.getStaffCode());
            if (staffDTO == null) {
                staffErrorResult.append(staffErrorTimes == 0 ? "" : "、").append(salaryTaxExcel.getStaffCode());
                ++errorTimes;
                ++staffErrorTimes;
                continue;
            }

            SalaryDTO salaryDTO = salaryMapper.getSalaryByStaffIdAndMonthAndType(staffDTO.getId(), DateUtils.format(salaryTaxExcel.getMonth(), "yyyy-MM"), salaryTaxExcel.getType());
            if (salaryDTO == null) {
                salaryErrorResult.append(staffErrorTimes == 0 ? "" : "、").append(salaryTaxExcel.getStaffCode());
                ++errorTimes;
                ++salaryErrorTimes;
            } else {
                salaryDTO.setIndividualIncomeTax(salaryTaxExcel.getIndividualIncomeTax());
                // 计算总计
                SalaryUtils.calculationSalaryTotal(salaryDTO);
                this.updateSalary(salaryDTO);
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("成功导入").append(list.size() - errorTimes).append("条记录，").append(errorTimes).append("条数据导入失败。\n");

        if (errorTimes > 0) {
            result.append("详细如下：\n");

            if (staffErrorTimes > 0) {
                result.append("存在没有录入系统的员工编号：").append(staffErrorResult).append("\n");
            }
            if (salaryErrorTimes > 0) {
                result.append("没有找到对应员工的薪资信息，员工编号：").append(salaryErrorResult).append("\n");
            }
        }

        return result.toString();
    }

}
