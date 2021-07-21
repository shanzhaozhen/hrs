package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.AttendanceMonthConverter;
import com.hbjs.hrscommon.domain.hr.AttendanceMonthDO;
import com.hbjs.hrscommon.dto.AttendanceMonthDTO;
import com.hbjs.hrscommon.dto.StaffDTO;
import com.hbjs.hrscommon.excel.AttendanceMonthExcel;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.DateUtils;
import com.hbjs.hrscommon.utils.EasyExcelUtils;
import com.hbjs.hrsrepo.mapper.AttendanceMonthMapper;
import com.hbjs.hrsservice.service.AttendanceMonthService;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttendanceMonthServiceImpl implements AttendanceMonthService {

    private final AttendanceMonthMapper attendanceMonthMapper;

    private final StaffService staffService;

    @Override
    public Page<AttendanceMonthDTO> getAttendanceMonthPage(Page<AttendanceMonthDTO> page, String keyword, Long depId, String month) {
        return attendanceMonthMapper.getAttendanceMonthPage(page, keyword, depId, month);
    }

    @Override
    public AttendanceMonthDTO getAttendanceMonthById(Long attendanceMonthId) {
        AttendanceMonthDTO attendanceMonthDTO = attendanceMonthMapper.getAttendanceMonthById(attendanceMonthId);
        Assert.notNull(attendanceMonthDTO, "获取失败：没有找到该月度考勤或已被删除");
        return attendanceMonthDTO;
    }

    @Override
    public AttendanceMonthDTO getAttendanceMonthByStaffIdAndMonth(Long staffId, String month) {
        return attendanceMonthMapper.getAttendanceMonthByStaffIdAndMonth(staffId, month);
    }

    @Override
    @Transactional
    public Long addAttendanceMonth(AttendanceMonthDTO attendanceMonthDTO) {
        AttendanceMonthDO attendanceMonthDO = AttendanceMonthConverter.toDO(attendanceMonthDTO);
        AttendanceMonthDO attendanceMonthInDB = new LambdaQueryChainWrapper<>(attendanceMonthMapper)
                .eq(AttendanceMonthDO::getStaffId, attendanceMonthDO.getStaffId())
                .eq(AttendanceMonthDO::getMonth, attendanceMonthDO.getMonth())
                .one();
        Assert.isNull(attendanceMonthInDB, "新增失败：该员工的月度考勤已存在（存在相同的年份和季度）");
        attendanceMonthMapper.insert(attendanceMonthDO);
        return attendanceMonthDO.getId();
    }

    @Override
    @Transactional
    public Long updateAttendanceMonth(AttendanceMonthDTO attendanceMonthDTO) {
        Assert.notNull(attendanceMonthDTO.getId(), "月度考勤id不能为空");
        AttendanceMonthDO attendanceMonthDO = attendanceMonthMapper.selectById(attendanceMonthDTO.getId());
        Assert.notNull(attendanceMonthDO, "更新失败：没有找到该月度考勤或已被删除");
        AttendanceMonthDO attendanceMonthInDB = new LambdaQueryChainWrapper<>(attendanceMonthMapper)
                .eq(AttendanceMonthDO::getStaffId, attendanceMonthDO.getStaffId())
                .eq(AttendanceMonthDO::getMonth, attendanceMonthDO.getMonth())
                .one();
        Assert.isTrue(attendanceMonthInDB == null ||
                        attendanceMonthInDB.getId().equals(attendanceMonthDO.getId()),
                "更新失败：该员工月度考勤已存在（存在相同的年份和季度）");
        CustomBeanUtils.copyPropertiesExcludeMeta(attendanceMonthDTO, attendanceMonthDO);
        attendanceMonthMapper.updateById(attendanceMonthDO);
        return attendanceMonthDO.getId();
    }

    @Override
    @Transactional
    public Long deleteAttendanceMonth(Long attendanceMonthId) {
        AttendanceMonthDTO attendanceMonthDTO = this.getAttendanceMonthById(attendanceMonthId);
        Assert.notNull(attendanceMonthDTO, "删除失败：没有找到该月度考勤或已被删除");
        attendanceMonthMapper.deleteById(attendanceMonthId);
        return attendanceMonthId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteAttendanceMonth(@NotEmpty(message = "没有需要删除的月度考勤") List<Long> attendanceMonthIds) {
        for (Long attendanceMonthId : attendanceMonthIds) {
            this.deleteAttendanceMonth(attendanceMonthId);
        }
        return attendanceMonthIds;
    }

    @Override
    public void generateAttendanceMonthTemplate() {
        EasyExcelUtils.exportExcel(AttendanceMonthExcel.class, new ArrayList<>(), "模板", "月度考勤导入模板");
    }

    @Override
    @Transactional
    public String importAttendanceMonth(MultipartFile file) {
        List<AttendanceMonthExcel> list;
        try {
            list = EasyExcelUtils.readExcel(file.getInputStream(), AttendanceMonthExcel.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("文件读取失败");
        }

        Assert.isTrue(!CollectionUtils.isEmpty(list), "导入的文件不存在记录，请填写好再导入");

        StringBuilder errorResult = new StringBuilder();
        int errorTimes = 0;

        // 先检查是否存在部分缺少参数的，缺少参数则跳过
        List<AttendanceMonthExcel> errorItems = list.stream().filter(s -> !StringUtils.hasText(s.getStaffCode()) || s.getMonth() == null).collect(Collectors.toList());
        Assert.isTrue(CollectionUtils.isEmpty(errorItems), "存在未填写的参数（员工编号、考核年份或考核季度），本次导入失败");

        for (AttendanceMonthExcel attendanceMonthExcel : list) {
            // 根据查找员工编号查找staffId
            StaffDTO staffDTO = staffService.getStaffByStaffCode(attendanceMonthExcel.getStaffCode());
            if (staffDTO == null) {
                errorResult.append("员工编号：").append(attendanceMonthExcel.getStaffCode()).append("未录入本系统;\n");
                ++errorTimes;
                continue;
            }

            AttendanceMonthDTO attendanceMonthDTO = attendanceMonthMapper.getAttendanceMonthByStaffCodeAndMonth(attendanceMonthExcel.getStaffCode(), DateUtils.format(attendanceMonthExcel.getMonth(), "yyyy-MM"));
            if (attendanceMonthDTO == null) {
                attendanceMonthDTO = new AttendanceMonthDTO();
                BeanUtils.copyProperties(attendanceMonthExcel, attendanceMonthDTO);
                attendanceMonthDTO.setStaffId(staffDTO.getId());
                this.addAttendanceMonth(attendanceMonthDTO);
            } else {
                BeanUtils.copyProperties(attendanceMonthExcel, attendanceMonthDTO);
                attendanceMonthDTO.setStaffId(staffDTO.getId());
                this.updateAttendanceMonth(attendanceMonthDTO);
            }
        }

        if (StringUtils.hasText(errorResult)) {
            Assert.isTrue(list.size() != errorTimes, "导入失败，情况如下：\n" + errorResult);
            return String.format("成功导入%s条记录, %s条数据导入失败。\n详细如下：\n%s", list.size() - errorTimes, errorTimes, errorResult);
        }

        return String.format("成功导入%s条记录", list.size());
    }

    @Override
    public void exportAttendanceMonth(String keyword, Long depId, String month) {
        List<AttendanceMonthExcel> attendanceMonthList = attendanceMonthMapper.getAttendanceMonthExcelList(keyword, depId, month);
        EasyExcelUtils.exportExcel(AttendanceMonthExcel.class, attendanceMonthList, "月度考勤记录");
    }

    @Override
    public List<AttendanceMonthDTO> getAttendanceMonthByStaffIdAndStartMonthAndEndMonth(Long staffId, String startMonth, String endMonth) {
        return attendanceMonthMapper.getAttendanceMonthByStaffIdAndStartMonthAndEndMonth(staffId, startMonth, endMonth);
    }

}
