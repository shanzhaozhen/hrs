package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.AttendanceQuarterConverter;
import com.hbjs.hrscommon.dto.AttendanceQuarterDTO;
import com.hbjs.hrscommon.form.AttendanceQuarterForm;
import com.hbjs.hrscommon.vo.AttendanceQuarterVO;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrsservice.service.AttendanceQuarterService;
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

@Tag(name = "attendance-quarter", description = "季度考勤接口")
@RestController
@RequiredArgsConstructor
public class AttendanceQuarterController {

    private static final String GET_ATTENDANCE_QUARTER_PAGE = "/attendance-quarter/page";
    private static final String GET_ATTENDANCE_QUARTER_BY_ID = "/attendance-quarter/{attendanceQuarterId}";
    private static final String ADD_ATTENDANCE_QUARTER = "/attendance-quarter";
    private static final String UPDATE_ATTENDANCE_QUARTER = "/attendance-quarter";
    private static final String DELETE_ATTENDANCE_QUARTER = "/attendance-quarter/{attendanceQuarterId}";
    private static final String BATCH_DELETE_ATTENDANCE_QUARTER = "/attendance-quarter";
    private static final String GENERATE_ATTENDANCE_QUARTER_TEMPLATE = "/attendance-quarter/template";
    private static final String IMPORT_ATTENDANCE_QUARTER = "/attendance-quarter/import";
    private static final String EXPORT_ATTENDANCE_QUARTER = "/attendance-quarter/export";

    private final AttendanceQuarterService attendanceQuarterService;

    @Operation(summary = "获取季度考勤（分页）")
    @GetMapping(GET_ATTENDANCE_QUARTER_PAGE)
    public ResultBody<Page<AttendanceQuarterVO>> getAttendanceQuarterPage(Page<AttendanceQuarterDTO> page, String keyword, Long depId, Integer year, Integer quarter) {
        return ResultBody.build(() -> AttendanceQuarterConverter.toVO(attendanceQuarterService.getAttendanceQuarterPage(page, keyword, depId, year, quarter)));
    }

    @Operation(summary = "获取季度考勤（通过季度考勤id）")
    @GetMapping(GET_ATTENDANCE_QUARTER_BY_ID)
    public ResultBody<AttendanceQuarterVO> getAttendanceQuarterById(@Parameter(description = "季度考勤id", example = "1") @PathVariable("attendanceQuarterId") Long attendanceQuarterId) {
        return ResultBody.build(() -> AttendanceQuarterConverter.toVO(attendanceQuarterService.getAttendanceQuarterById(attendanceQuarterId)));
    }

    @Operation(summary = "添加季度考勤接口")
    @PostMapping(ADD_ATTENDANCE_QUARTER)
    public ResultBody<Long> addAttendanceQuarter(@RequestBody @Validated({Insert.class}) AttendanceQuarterForm attendanceQuarterForm) {
        return ResultBody.build(() -> attendanceQuarterService.addAttendanceQuarter(AttendanceQuarterConverter.toDTO(attendanceQuarterForm)));
    }

    @Operation(summary = "更新季度考勤接口")
    @PutMapping(UPDATE_ATTENDANCE_QUARTER)
    public ResultBody<Long> updateAttendanceQuarter(@RequestBody @Validated({Update.class}) AttendanceQuarterForm attendanceQuarterForm) {
        return ResultBody.build(() -> attendanceQuarterService.updateAttendanceQuarter(AttendanceQuarterConverter.toDTO(attendanceQuarterForm)));
    }

    @Operation(summary = "删除季度考勤接口")
    @DeleteMapping(DELETE_ATTENDANCE_QUARTER)
    public ResultBody<Long> deleteAttendanceQuarter(@Parameter(description = "季度考勤id", example = "1") @PathVariable("attendanceQuarterId") Long attendanceQuarterId) {
        return ResultBody.build(() -> attendanceQuarterService.deleteAttendanceQuarter(attendanceQuarterId));
    }

    @Operation(summary = "批量删除季度考勤接口")
    @DeleteMapping(BATCH_DELETE_ATTENDANCE_QUARTER)
    public ResultBody<List<Long>> batchDeleteAttendanceQuarter(@Parameter(description = "季度考勤id", example = "[1, 2]") @RequestBody List<Long> attendanceQuarterIds) {
        return ResultBody.build(() -> attendanceQuarterService.batchDeleteAttendanceQuarter(attendanceQuarterIds));
    }

    @Operation(summary = "生成季度考勤导入模板")
    @GetMapping(GENERATE_ATTENDANCE_QUARTER_TEMPLATE)
    public void generateAttendanceQuarterTemplate() {
        attendanceQuarterService.generateAttendanceQuarterTemplate();
    }

    @Operation(summary = "导入季度考勤")
    @PostMapping(IMPORT_ATTENDANCE_QUARTER)
    public ResultBody<String> importAttendanceQuarter(MultipartFile file) {
        return ResultBody.build(() -> attendanceQuarterService.importAttendanceQuarter(file));
    }

    @Operation(summary = "导出季度考勤")
    @GetMapping(EXPORT_ATTENDANCE_QUARTER)
    public void exportAttendanceQuarter(String keyword, Long depId, Integer year, Integer quarter) {
        attendanceQuarterService.exportAttendanceQuarter(keyword, depId, year, quarter);
    }

}
