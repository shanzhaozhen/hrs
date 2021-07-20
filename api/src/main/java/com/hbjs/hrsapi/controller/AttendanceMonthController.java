package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.AttendanceMonthConverter;
import com.hbjs.hrscommon.dto.AttendanceMonthDTO;
import com.hbjs.hrscommon.form.AttendanceMonthForm;
import com.hbjs.hrscommon.vo.AttendanceMonthVO;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrsservice.service.AttendanceMonthService;
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

@Tag(name = "attendance-month", description = "月度考勤接口")
@RestController
@RequiredArgsConstructor
public class AttendanceMonthController {

    private static final String GET_ATTENDANCE_MONTH_PAGE = "/attendance-month/page";
    private static final String GET_ATTENDANCE_MONTH_BY_ID = "/attendance-month/{attendanceMonthId}";
    private static final String ADD_ATTENDANCE_MONTH = "/attendance-month";
    private static final String UPDATE_ATTENDANCE_MONTH = "/attendance-month";
    private static final String DELETE_ATTENDANCE_MONTH = "/attendance-month/{attendanceMonthId}";
    private static final String BATCH_DELETE_ATTENDANCE_MONTH = "/attendance-month";
    private static final String GENERATE_ATTENDANCE_MONTH_TEMPLATE = "/attendance-month/template";
    private static final String IMPORT_ATTENDANCE_MONTH = "/attendance-month/import";
    private static final String EXPORT_ATTENDANCE_MONTH = "/attendance-month/export";

    private final AttendanceMonthService attendanceMonthService;

    @Operation(summary = "获取月度考勤（分页）")
    @GetMapping(GET_ATTENDANCE_MONTH_PAGE)
    public ResultBody<Page<AttendanceMonthVO>> getAttendanceMonthPage(Page<AttendanceMonthDTO> page, String keyword, Long depId, String month) {
        return ResultBody.build(() -> AttendanceMonthConverter.toVO(attendanceMonthService.getAttendanceMonthPage(page, keyword, depId, month)));
    }

    @Operation(summary = "获取月度考勤（通过月度考勤id）")
    @GetMapping(GET_ATTENDANCE_MONTH_BY_ID)
    public ResultBody<AttendanceMonthVO> getAttendanceMonthById(@Parameter(description = "月度考勤id", example = "1") @PathVariable("attendanceMonthId") Long attendanceMonthId) {
        return ResultBody.build(() -> AttendanceMonthConverter.toVO(attendanceMonthService.getAttendanceMonthById(attendanceMonthId)));
    }

    @Operation(summary = "添加月度考勤接口")
    @PostMapping(ADD_ATTENDANCE_MONTH)
    public ResultBody<Long> addAttendanceMonth(@RequestBody @Validated({Insert.class}) AttendanceMonthForm attendanceMonthForm) {
        return ResultBody.build(() -> attendanceMonthService.addAttendanceMonth(AttendanceMonthConverter.toDTO(attendanceMonthForm)));
    }

    @Operation(summary = "更新月度考勤接口")
    @PutMapping(UPDATE_ATTENDANCE_MONTH)
    public ResultBody<Long> updateAttendanceMonth(@RequestBody @Validated({Update.class}) AttendanceMonthForm attendanceMonthForm) {
        return ResultBody.build(() -> attendanceMonthService.updateAttendanceMonth(AttendanceMonthConverter.toDTO(attendanceMonthForm)));
    }

    @Operation(summary = "删除月度考勤接口")
    @DeleteMapping(DELETE_ATTENDANCE_MONTH)
    public ResultBody<Long> deleteAttendanceMonth(@Parameter(description = "月度考勤id", example = "1") @PathVariable("attendanceMonthId") Long attendanceMonthId) {
        return ResultBody.build(() -> attendanceMonthService.deleteAttendanceMonth(attendanceMonthId));
    }

    @Operation(summary = "批量删除月度考勤接口")
    @DeleteMapping(BATCH_DELETE_ATTENDANCE_MONTH)
    public ResultBody<List<Long>> batchDeleteAttendanceMonth(@Parameter(description = "月度考勤id", example = "[1, 2]") @RequestBody List<Long> attendanceMonthIds) {
        return ResultBody.build(() -> attendanceMonthService.batchDeleteAttendanceMonth(attendanceMonthIds));
    }

    @Operation(summary = "生成月度考勤导入模板")
    @GetMapping(GENERATE_ATTENDANCE_MONTH_TEMPLATE)
    public void generateAttendanceMonthTemplate() {
        attendanceMonthService.generateAttendanceMonthTemplate();
    }

    @Operation(summary = "导入月度考勤")
    @PostMapping(IMPORT_ATTENDANCE_MONTH)
    public ResultBody<String> importAttendanceMonth(MultipartFile file) {
        return ResultBody.build(() -> attendanceMonthService.importAttendanceMonth(file));
    }

    @Operation(summary = "导出月度考勤")
    @GetMapping(EXPORT_ATTENDANCE_MONTH)
    public void exportAttendanceMonth(String keyword, Long depId, String month) {
        attendanceMonthService.exportAttendanceMonth(keyword, depId, month);
    }

}
