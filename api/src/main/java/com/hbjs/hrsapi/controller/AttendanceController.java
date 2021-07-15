package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.AttendanceConverter;
import com.hbjs.hrscommon.dto.AttendanceDTO;
import com.hbjs.hrscommon.form.AttendanceForm;
import com.hbjs.hrscommon.vo.AttendanceVO;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrsservice.service.AttendanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "attendance", description = "考勤数据接口")
@RestController
@RequiredArgsConstructor
public class AttendanceController {

    private static final String GET_ATTENDANCE_PAGE = "/attendance/page";
    private static final String GET_ATTENDANCE_BY_ID = "/attendance/{attendanceId}";
    private static final String ADD_ATTENDANCE = "/attendance";
    private static final String UPDATE_ATTENDANCE = "/attendance";
    private static final String DELETE_ATTENDANCE = "/attendance/{attendanceId}";
    private static final String BATCH_DELETE_ATTENDANCE = "/attendance";
    private static final String GENERATE_ATTENDANCE_TEMPLATE = "/attendance/template";
    private static final String IMPORT_ATTENDANCE = "/attendance/import";
    private static final String EXPORT_ATTENDANCE = "/attendance/export";

    private final AttendanceService attendanceService;

    @Operation(summary = "获取考勤数据（分页）")
    @GetMapping(GET_ATTENDANCE_PAGE)
    public ResultBody<Page<AttendanceVO>> getAttendancePage(Page<AttendanceDTO> page, String keyword, Long depId, String month) {
        return ResultBody.build(() -> AttendanceConverter.toVO(attendanceService.getAttendancePage(page, keyword, depId, month)));
    }

    @Operation(summary = "获取考勤数据（通过考勤数据id）")
    @GetMapping(GET_ATTENDANCE_BY_ID)
    public ResultBody<AttendanceVO> getAttendanceById(@Parameter(description = "考勤数据id", example = "1") @PathVariable("attendanceId") Long attendanceId) {
        return ResultBody.build(() -> AttendanceConverter.toVO(attendanceService.getAttendanceById(attendanceId)));
    }

    @Operation(summary = "添加考勤数据接口")
    @PostMapping(ADD_ATTENDANCE)
    public ResultBody<Long> addAttendance(@RequestBody @Validated({Insert.class}) AttendanceForm attendanceForm) {
        return ResultBody.build(() -> attendanceService.addAttendance(AttendanceConverter.toDTO(attendanceForm)));
    }

    @Operation(summary = "更新考勤数据接口")
    @PutMapping(UPDATE_ATTENDANCE)
    public ResultBody<Long> updateAttendance(@RequestBody @Validated({Update.class}) AttendanceForm attendanceForm) {
        return ResultBody.build(() -> attendanceService.updateAttendance(AttendanceConverter.toDTO(attendanceForm)));
    }

    @Operation(summary = "删除考勤数据接口")
    @DeleteMapping(DELETE_ATTENDANCE)
    public ResultBody<Long> deleteAttendance(@Parameter(description = "考勤数据id", example = "1") @PathVariable("attendanceId") Long attendanceId) {
        return ResultBody.build(() -> attendanceService.deleteAttendance(attendanceId));
    }

    @Operation(summary = "批量删除考勤数据接口")
    @DeleteMapping(BATCH_DELETE_ATTENDANCE)
    public ResultBody<List<Long>> batchDeleteAttendance(@Parameter(description = "考勤数据id", example = "[1, 2]") @RequestBody List<Long> attendanceIds) {
        return ResultBody.build(() -> attendanceService.batchDeleteAttendance(attendanceIds));
    }

    @Operation(summary = "生成考勤数据导入模板")
    @GetMapping(GENERATE_ATTENDANCE_TEMPLATE)
    public void generateAttendanceTemplate() {
        attendanceService.generateAttendanceTemplate();
    }

    @Operation(summary = "导入考勤数据")
    @PostMapping(IMPORT_ATTENDANCE)
    public ResultBody<String> importAttendance(MultipartFile file) {
        return ResultBody.build(() -> attendanceService.importAttendance(file));
    }

    @Operation(summary = "导出考勤数据")
    @GetMapping(EXPORT_ATTENDANCE)
    public void exportAttendance(String keyword, Long depId, String month) {
        attendanceService.exportAttendance(keyword, depId, month);
    }

}
