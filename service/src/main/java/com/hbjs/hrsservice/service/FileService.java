package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    /**
     * 获取文件分页列表
     * @param page
     * @param keyword
     * @return
     */
    Page<FileDTO> getFilePage(Page<FileDTO> page, String keyword);

    /**
     * 通过文件id查找文件信息
     * @param fileId
     * @return
     */
    FileDTO getFileById(Long fileId);

    /**
     * 通过文件md5查找文件信息
     * @param md5
     * @return
     */
    FileDTO getFileByMd5(String md5);

    /**
     * 批量上传文件
     * @param multipartFiles
     * @return
     */
    List<FileDTO> saveFile(MultipartFile[] multipartFiles);

    /**
     * 上传文件
     * @param multipartFile
     * @return
     */
    FileDTO saveFile(MultipartFile multipartFile);

    /**
     * 上传文件保存文件信息到数据库
     * @param fileInfo
     * @return
     */
    FileDTO saveFile(FileDTO fileInfo);

    /**
     * 上传文件保存文件内容到服务器
     * @param multipartFile
     * @param md5
     * @return
     */
    FileDTO uploadFile(MultipartFile multipartFile, String md5);

}
