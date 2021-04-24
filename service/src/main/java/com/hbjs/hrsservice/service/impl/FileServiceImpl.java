package com.hbjs.hrsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.FileConverter;
import com.hbjs.hrscommon.domain.sys.FileDO;
import com.hbjs.hrscommon.dto.FileDTO;
import com.hbjs.hrscommon.utils.CustomBeanUtils;
import com.hbjs.hrscommon.utils.DateUtils;
import com.hbjs.hrsrepo.mapper.FileMapper;
import com.hbjs.hrsservice.config.FileConfig;
import com.hbjs.hrsservice.service.FileService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileConfig fileConfig;

    private final FileMapper fileMapper;

    @Override
    public Page<FileDTO> getFilePage(Page<FileDTO> page, String keyword) {
        return fileMapper.getFilePage(page, keyword);
    }

    @Override
    public FileDTO getFileById(Long fileId) {
        FileDO fileDO = fileMapper.selectById(fileId);
        Assert.notNull(fileDO, "获取失败：没有找到该文件或已被删除");
        return FileConverter.toDTO(fileDO);
    }

    @Override
    public List<FileDTO> getFileByMd5(String md5) {
        return fileMapper.selectFileByMd5(md5);
    }

    @Override
    @Transactional
    public List<FileDTO> saveFile(MultipartFile[] multipartFiles) {
        Assert.isTrue(multipartFiles != null && multipartFiles.length > 0, "上传失败，请选择文件");

        List<FileDTO> fileInfoDTOList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            fileInfoDTOList.add(this.saveFile(multipartFile));
        }
        return fileInfoDTOList;
    }

    @Override
    @Transactional
    public FileDTO saveFile(MultipartFile multipartFile) {
        Assert.isTrue(!multipartFile.isEmpty(), "上传失败，文件为空");
        FileDTO fileInfoDTO = this.uploadFile(multipartFile);
        return this.saveFile(fileInfoDTO);
    }

    @Override
    @Transactional
    public FileDTO saveFile(FileDTO fileInfoDTO) {
        FileDO fileInfoDO = FileConverter.toDO(fileInfoDTO);
        fileMapper.insert(fileInfoDO);
        return FileConverter.toDTO(fileInfoDO);
    }

    @Override
    public FileDTO uploadFile(MultipartFile multipartFile) {
        Assert.isTrue(!multipartFile.isEmpty(), "上传失败，文件为空");
        try {
            // 获取原来的文件名
            String fileName = multipartFile.getOriginalFilename();

            // 文件的后缀名
            String suffix = "";
            if (StringUtils.hasText(fileName)) {
                suffix = fileName.substring(fileName.lastIndexOf("."));
            }

            // 检查是否重复文件
            String md5 = DigestUtils.md5DigestAsHex(multipartFile.getInputStream());
            List<FileDTO> fileDTOS = this.getFileByMd5(md5);
            if (!CollectionUtils.isEmpty(fileDTOS)) {
                FileDTO fileDTO = fileDTOS.get(0);
                FileDTO newFile = new FileDTO();
                CustomBeanUtils.copyPropertiesExcludeMeta(fileDTO, newFile, "id");
                newFile.setName(fileName).setSuffix(suffix);
                return newFile;
            } else {
                // 生成新的文件名
                String newFileName = UUID.randomUUID() + suffix;

                // 保存文件的路径
                String path = String.format("/%s/%s", DateUtils.format(new Date(), "yyyy-MM-dd"), newFileName);

                // 访问文件的相对路径
//        String newRelativePath = fileConfig.getRelativePath() + path;

                String realPath = fileConfig.getRealPath() + path;
                File file = new File(realPath);

                // 创建文件夹
                if (!file.getParentFile().exists()) {
                    Assert.isTrue(file.getParentFile().mkdirs(), "文件保存失败，请检查服务器");
                }

                multipartFile.transferTo(file);

                return FileDTO.builder()
                        .name(fileName)
                        .suffix(suffix)
//                    .type(FileType.COMMON.getValue())
                        .path(realPath)
                        .urlPath(fileConfig.getUrlPath() + path)
                        .md5(md5)
                        .build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("文件上传失败！");
        }
    }

    @Override
    public void downloadFile(Long fileId, HttpServletResponse httpServletResponse) {
        FileDTO fileInfo = this.getFileById(fileId);
        File file = new File(fileInfo.getPath());
        Assert.isTrue(file.exists(), "文件不存在");
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            // 设置以流的形式下载文件，这样可以实现任意格式的文件下载
            httpServletResponse.setContentType("application/octet-stream");
            httpServletResponse.addHeader("Content-Disposition", " attachment;filename=" + fileInfo.getName());
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();            //请求完成servlet会自动关闭
            IOUtils.copy(fileInputStream, servletOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}