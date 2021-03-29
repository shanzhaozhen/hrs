package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.FileConverter;
import com.hbjs.hrscommon.dto.FileDTO;
import com.hbjs.hrscommon.vo.FileVO;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrsservice.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FileController {

    private static final String UPLOAD_FILE = "/upload";
    private static final String GET_FILE_PAGE = "/file/page";
    private static final String GET_FILE_BY_ID = "/file/{fileId}";
    private static final String DELETE_FILE = "/file/{fileId}";
    private static final String BATCH_DELETE_TASK = "/file";

    private final FileService fileService;

    @RequestMapping(UPLOAD_FILE)
    @ResponseBody
    public ResultBody<List<FileVO>> upload(@RequestParam("files") MultipartFile[] multipartFiles) {
        return ResultBody.build(res -> FileConverter.toVO(fileService.saveFile(multipartFiles)));
    }

    @Operation(summary = "获取文件信息（分页）")
    @GetMapping(GET_FILE_PAGE)
    public ResultBody<Page<FileVO>> getFilePage(Page<FileDTO> page, String keyword) {
        return ResultBody.build(() -> FileConverter.toVO(fileService.getFilePage(page, keyword)));
    }


    @Operation(summary = "获取文件信息（通过文件id）")
    @GetMapping(GET_FILE_BY_ID)
    public ResultBody<FileVO> getFileById(@Parameter(description = "文件id", example = "1") @PathVariable("fileId") Long fileId) {
        return ResultBody.build(() -> FileConverter.toVO(fileService.getFileById(fileId)));
    }
    
}
