package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.AttendanceQuarterConverter;
import com.hbjs.hrscommon.domain.hr.AttendanceQuarterDO;
import com.hbjs.hrscommon.dto.AttendanceQuarterDTO;
import com.hbjs.hrscommon.dto.StaffDTO;
import com.hbjs.hrscommon.excel.AttendanceQuarterExcel;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.EasyExcelUtils;
import com.hbjs.hrsrepo.mapper.AttendanceQuarterMapper;
import com.hbjs.hrsservice.service.AttendanceQuarterService;
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
public class AttendanceQuarterServiceImpl implements AttendanceQuarterService {

    private final AttendanceQuarterMapper attendanceQuarterMapper;

    private final StaffService staffService;

    @Override
    public Page<AttendanceQuarterDTO> getAttendanceQuarterPage(Page<AttendanceQuarterDTO> page, String keyword, Long depId, Integer year, Integer quarter) {
        return attendanceQuarterMapper.getAttendanceQuarterPage(page, keyword, depId, year, quarter);
    }

    @Override
    public AttendanceQuarterDTO getAttendanceQuarterById(Long attendanceQuarterId) {
        AttendanceQuarterDTO attendanceQuarterDTO = attendanceQuarterMapper.getAttendanceQuarterById(attendanceQuarterId);
        Assert.notNull(attendanceQuarterDTO, "获取失败：没有找到该季度考勤或已被删除");
        return attendanceQuarterDTO;
    }


    @Override
    public AttendanceQuarterDTO getAttendanceQuarterByStaffIdAndYearQuarter(Long staffId, Integer year, Integer quarter) {
        return attendanceQuarterMapper.getAttendanceQuarterByStaffIdAndYearAndQuarter(staffId, year, quarter);
    }

    @Override
    @Transactional
    public Long addAttendanceQuarter(AttendanceQuarterDTO attendanceQuarterDTO) {
        AttendanceQuarterDO attendanceQuarterDO = AttendanceQuarterConverter.toDO(attendanceQuarterDTO);
        AttendanceQuarterDTO attendanceQuarterInDB = attendanceQuarterMapper.getAttendanceQuarterByStaffIdAndYearAndQuarter(attendanceQuarterDTO.getStaffId(), attendanceQuarterDTO.getYear(), attendanceQuarterDTO.getQuarter());
        Assert.isNull(attendanceQuarterInDB, "新增失败：该员工的季度考勤已存在（存在相同的年份和季度）");
        attendanceQuarterMapper.insert(attendanceQuarterDO);
        return attendanceQuarterDO.getId();
    }

    @Override
    @Transactional
    public Long updateAttendanceQuarter(AttendanceQuarterDTO attendanceQuarterDTO) {
        Assert.notNull(attendanceQuarterDTO.getId(), "季度考勤id不能为空");
        AttendanceQuarterDO attendanceQuarterDO = attendanceQuarterMapper.selectById(attendanceQuarterDTO.getId());
        Assert.notNull(attendanceQuarterDO, "更新失败：没有找到该季度考勤或已被删除");
        AttendanceQuarterDTO attendanceQuarterInDB = attendanceQuarterMapper.getAttendanceQuarterByStaffIdAndYearAndQuarter(attendanceQuarterDTO.getStaffId(), attendanceQuarterDTO.getYear(), attendanceQuarterDTO.getQuarter());
        Assert.isTrue(attendanceQuarterInDB == null || attendanceQuarterInDB.getId().equals(attendanceQuarterDO.getId()),
                "更新失败：该员工季度考勤已存在（存在相同的年份和季度）");
        CustomBeanUtils.copyPropertiesExcludeMeta(attendanceQuarterDTO, attendanceQuarterDO);
        attendanceQuarterMapper.updateById(attendanceQuarterDO);
        return attendanceQuarterDO.getId();
    }

    @Override
    @Transactional
    public Long deleteAttendanceQuarter(Long attendanceQuarterId) {
        AttendanceQuarterDTO attendanceQuarterDTO = this.getAttendanceQuarterById(attendanceQuarterId);
        Assert.notNull(attendanceQuarterDTO, "删除失败：没有找到该季度考勤或已被删除");
        attendanceQuarterMapper.deleteById(attendanceQuarterId);
        return attendanceQuarterId;
    }

    @Override
    @Transactional
    public List<Long> batchDeleteAttendanceQuarter(@NotEmpty(message = "没有需要删除的季度考勤") List<Long> attendanceQuarterIds) {
        for (Long attendanceQuarterId : attendanceQuarterIds) {
            this.deleteAttendanceQuarter(attendanceQuarterId);
        }
        return attendanceQuarterIds;
    }

    @Override
    public void generateAttendanceQuarterTemplate() {
        EasyExcelUtils.exportExcel(AttendanceQuarterExcel.class, new ArrayList<>(), "模板", "季度考勤导入模板");
    }

    @Override
    @Transactional
    public String importAttendanceQuarter(MultipartFile file) {
        List<AttendanceQuarterExcel> list;
        try {
            list = EasyExcelUtils.readExcel(file.getInputStream(), AttendanceQuarterExcel.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("文件读取失败");
        }

        Assert.isTrue(!CollectionUtils.isEmpty(list), "导入的文件不存在记录，请填写好再导入");

        StringBuilder errorResult = new StringBuilder();
        int errorTimes = 0;

        // 先检查是否存在部分缺少参数的，缺少参数则跳过
        List<AttendanceQuarterExcel> errorItems = list.stream().filter(s -> !StringUtils.hasText(s.getStaffCode()) || s.getYear() == null || s.getQuarter() == null).collect(Collectors.toList());
        Assert.isTrue(CollectionUtils.isEmpty(errorItems), "存在未填写的参数（员工编号、考核年份或考核季度），本次导入失败");

        for (AttendanceQuarterExcel attendanceQuarterExcel : list) {
            // 根据查找员工编号查找staffId
            StaffDTO staffDTO = staffService.getStaffByStaffCode(attendanceQuarterExcel.getStaffCode());
            if (staffDTO == null) {
                errorResult.append("员工编号：").append(attendanceQuarterExcel.getStaffCode()).append("未录入本系统;\n");
                ++errorTimes;
                continue;
            }

            AttendanceQuarterDTO attendanceQuarterDTO = attendanceQuarterMapper.getAttendanceQuarterByStaffIdAndYearAndQuarter(staffDTO.getId(), attendanceQuarterExcel.getYear(), attendanceQuarterExcel.getQuarter());
            if (attendanceQuarterDTO == null) {
                attendanceQuarterDTO = new AttendanceQuarterDTO();
                BeanUtils.copyProperties(attendanceQuarterExcel, attendanceQuarterDTO);
                attendanceQuarterDTO.setStaffId(staffDTO.getId());
                this.addAttendanceQuarter(attendanceQuarterDTO);
            } else {
                BeanUtils.copyProperties(attendanceQuarterExcel, attendanceQuarterDTO);
                attendanceQuarterDTO.setStaffId(staffDTO.getId());
                this.updateAttendanceQuarter(attendanceQuarterDTO);
            }
        }

        if (StringUtils.hasText(errorResult)) {
            Assert.isTrue(list.size() != errorTimes, "导入失败，情况如下：\n" + errorResult);
            return String.format("成功导入%s条记录, %s条数据导入失败。\n详细如下：\n%s", list.size() - errorTimes, errorTimes, errorResult);
        }

        return String.format("成功导入%s条记录", list.size());
    }

    @Override
    public void exportAttendanceQuarter(String keyword, Long depId, Integer year, Integer quarter) {
        List<AttendanceQuarterExcel> attendanceQuarterList = attendanceQuarterMapper.getAttendanceQuarterExcelList(keyword, depId, year, quarter);
        EasyExcelUtils.exportExcel(AttendanceQuarterExcel.class, attendanceQuarterList, "季度考勤记录");
    }

}
