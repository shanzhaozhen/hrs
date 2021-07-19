package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.AttendanceConverter;
import com.hbjs.hrscommon.domain.hr.AttendanceDO;
import com.hbjs.hrscommon.dto.AttendanceDTO;
import com.hbjs.hrscommon.dto.StaffDTO;
import com.hbjs.hrscommon.excel.AttendanceExcel;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.DateUtils;
import com.hbjs.hrscommon.utils.EasyExcelUtils;
import com.hbjs.hrsrepo.mapper.AttendanceMapper;
import com.hbjs.hrsservice.service.AttendanceService;
import com.hbjs.hrsservice.service.StaffService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceMapper attendanceMapper;

    private final StaffService staffService;

    @Override
    public Page<AttendanceDTO> getAttendancePage(Page<AttendanceDTO> page, String keyword, Long depId, String month) {
        return attendanceMapper.getAttendancePage(page, keyword, depId, month);
    }

    @Override
    public AttendanceDTO getAttendanceById(Long attendanceId) {
        AttendanceDTO attendanceDTO = attendanceMapper.getAttendanceById(attendanceId);
        Assert.notNull(attendanceDTO, "获取失败：没有找到该考勤数据或已被删除");
        return attendanceDTO;
    }

    @Override
    @Transactional
    public Long addAttendance(AttendanceDTO attendanceDTO) {
        AttendanceDO attendanceDO = AttendanceConverter.toDO(attendanceDTO);
        AttendanceDO attendanceInDB = new LambdaQueryChainWrapper<>(attendanceMapper)
                .eq(AttendanceDO::getStaffId, attendanceDO.getStaffId())
                .eq(AttendanceDO::getMonth, attendanceDO.getMonth())
                .one();
        Assert.isNull(attendanceInDB, "新增失败：该员工的考勤数据已存在（存在相同的年份和季度）");
        attendanceMapper.insert(attendanceDO);
        return attendanceDO.getId();
    }

    @Override
    @Transactional
    public Long updateAttendance(AttendanceDTO attendanceDTO) {
        Assert.notNull(attendanceDTO.getId(), "考勤数据id不能为空");
        AttendanceDO attendanceDO = attendanceMapper.selectById(attendanceDTO.getId());
        Assert.notNull(attendanceDO, "更新失败：没有找到该考勤数据或已被删除");
        AttendanceDO attendanceInDB = new LambdaQueryChainWrapper<>(attendanceMapper)
                .eq(AttendanceDO::getStaffId, attendanceDO.getStaffId())
                .eq(AttendanceDO::getMonth, attendanceDO.getMonth())
                .one();
        Assert.isTrue(attendanceInDB == null ||
                        attendanceInDB.getId().equals(attendanceDO.getId()),
                "更新失败：该员工考勤数据已存在（存在相同的年份和季度）");
        CustomBeanUtils.copyPropertiesExcludeMeta(attendanceDTO, attendanceDO);
        attendanceMapper.updateById(attendanceDO);
        return attendanceDO.getId();
    }

    @Override
    @Transactional
    public Long deleteAttendance(Long attendanceId) {
        AttendanceDTO attendanceDTO = this.getAttendanceById(attendanceId);
        Assert.notNull(attendanceDTO, "删除失败：没有找到该考勤数据或已被删除");
        attendanceMapper.deleteById(attendanceId);
        return attendanceId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteAttendance(@NotEmpty(message = "没有需要删除的考勤数据") List<Long> attendanceIds) {
        for (Long attendanceId : attendanceIds) {
            this.deleteAttendance(attendanceId);
        }
        return attendanceIds;
    }

    @Override
    public void generateAttendanceTemplate() {
        EasyExcelUtils.exportExcel(AttendanceExcel.class, new ArrayList<>(), "模板", "考勤数据导入模板");
    }

    @Override
    @Transactional
    public String importAttendance(MultipartFile file) {
        List<AttendanceExcel> list;
        try {
            list = EasyExcelUtils.readExcel(file.getInputStream(), AttendanceExcel.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("文件读取失败");
        }

        Assert.isTrue(!CollectionUtils.isEmpty(list), "导入的文件不存在记录，请填写好再导入");

        StringBuilder errorResult = new StringBuilder();
        int errorTimes = 0;

        // 先检查是否存在部分缺少参数的，缺少参数则跳过
        List<AttendanceExcel> errorItems = list.stream().filter(s -> !StringUtils.hasText(s.getStaffCode()) || s.getMonth() == null).collect(Collectors.toList());
        Assert.isTrue(CollectionUtils.isEmpty(errorItems), "存在未填写的参数（员工编号、考核年份或考核季度），本次导入失败");

        for (AttendanceExcel attendanceExcel : list) {
            // 根据查找员工编号查找staffId
            StaffDTO staffDTO = staffService.getStaffByStaffCode(attendanceExcel.getStaffCode());
            if (staffDTO == null) {
                errorResult.append("员工编号：").append(attendanceExcel.getStaffCode()).append("未录入本系统;\n");
                ++errorTimes;
                continue;
            }

            AttendanceDTO attendanceDTO = attendanceMapper.getAttendanceByStaffCodeAndMonth(attendanceExcel.getStaffCode(), DateUtils.format(attendanceExcel.getMonth(), "yyyy-MM"));
            if (attendanceDTO == null) {
                attendanceDTO = new AttendanceDTO();
                BeanUtils.copyProperties(attendanceExcel, attendanceDTO);
                attendanceDTO.setStaffId(staffDTO.getId());
                this.addAttendance(attendanceDTO);
            } else {
                BeanUtils.copyProperties(attendanceExcel, attendanceDTO);
                attendanceDTO.setStaffId(staffDTO.getId());
                this.updateAttendance(attendanceDTO);
            }
        }

        if (StringUtils.hasText(errorResult)) {
            Assert.isTrue(list.size() != errorTimes, "导入失败，情况如下：\n" + errorResult);
            return String.format("成功导入%s条记录, %s条数据导入失败。\n详细如下：\n%s", list.size() - errorTimes, errorTimes, errorResult);
        }

        return String.format("成功导入%s条记录", list.size());
    }

    @Override
    public void exportAttendance(String keyword, Long depId, String month) {
        List<AttendanceExcel> attendanceList = attendanceMapper.getAttendanceExcelList(keyword, depId, month);
        EasyExcelUtils.exportExcel(AttendanceExcel.class, attendanceList, "考勤数据记录");
    }

    @Override
    public AttendanceDTO getAttendanceByStaffIdAndMonth(Long staffId, String month) {
        return attendanceMapper.getAttendanceByStaffIdAndMonth(staffId, month);
    }

}
