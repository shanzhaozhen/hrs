package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.sys.FileDO;
import com.hbjs.hrscommon.dto.FileDTO;
import com.hbjs.hrscommon.vo.FileVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class FileConverter {

    /**
     * FileDTO 转换 FileDO
     * @param fileInfoDTO
     * @return
     */
    public static FileDO toDO(FileDTO fileInfoDTO) {
        FileDO fileInfoDO = new FileDO();
        BeanUtils.copyProperties(fileInfoDTO, fileInfoDO);
        return fileInfoDO;
    }

    /**
     * FileDO 转换 FileDTO
     * @param fileInfoDO
     * @return
     */
    public static FileDTO toDTO(FileDO fileInfoDO) {
        FileDTO fileInfoDTO = new FileDTO();
        BeanUtils.copyProperties(fileInfoDO, fileInfoDTO);
        return fileInfoDTO;
    }

    /**
     * FileDTO 转换 FileVO
     * @param fileInfoDTO
     * @return
     */
    public static FileVO toVO(FileDTO fileInfoDTO) {
        FileVO fileInfoVO = new FileVO();
        BeanUtils.copyProperties(fileInfoDTO, fileInfoVO);
        return fileInfoVO;
    }

    /**
     * List<FileDTO> 转换 List<FileVO>
     * @param fileInfoDTOList
     * @return
     */
    public static List<FileVO> toVO(List<FileDTO> fileInfoDTOList) {
        List<FileVO> fileInfoVOList = new ArrayList<>();
        for (FileDTO fileInfoDTO : fileInfoDTOList) {
            fileInfoVOList.add(toVO(fileInfoDTO));
        }
        return fileInfoVOList;
    }

    /**
     * Page<FileDTO> 转换 Page<FileVO>
     * @param fileDTOPage
     * @return
     */
    public static Page<FileVO> toVO(Page<FileDTO> fileDTOPage) {
        List<FileDTO> fileDTOList = fileDTOPage.getRecords();
        Page<FileVO> fileVOPage = new Page<>();
        BeanUtils.copyProperties(fileDTOPage, fileVOPage);
        fileVOPage.setRecords(toVO(fileDTOList));
        return fileVOPage;
    }

}
