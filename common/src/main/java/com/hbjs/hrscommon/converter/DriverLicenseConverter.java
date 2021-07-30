package com.hbjs.hrscommon.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.domain.hr.DriverLicenseDO;
import com.hbjs.hrscommon.dto.DriverLicenseDTO;
import com.hbjs.hrscommon.form.DriverLicenseForm;
import com.hbjs.hrscommon.vo.DriverLicenseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class DriverLicenseConverter {
    
    /**
     * DriverLicenseDTO 转换 DriverLicenseDO
     * @param driverLicenseDTO
     * @return
     */
    public static DriverLicenseDO toDO(DriverLicenseDTO driverLicenseDTO) {
        DriverLicenseDO driverLicenseDO = new DriverLicenseDO();
        BeanUtils.copyProperties(driverLicenseDTO, driverLicenseDO);
        return driverLicenseDO;
    }

    /**
     * DriverLicenseForm 转换 DriverLicenseDTO
     * @param driverLicenseForm
     * @return
     */
    public static DriverLicenseDTO toDTO(DriverLicenseForm driverLicenseForm) {
        DriverLicenseDTO driverLicenseDTO = new DriverLicenseDTO();
        BeanUtils.copyProperties(driverLicenseForm, driverLicenseDTO);
        return driverLicenseDTO;
    }

    /**
     * DriverLicenseVO 转换 DriverLicenseDTO
     * @param driverLicenseVO
     * @return
     */
    public static DriverLicenseDTO toDTO(DriverLicenseVO driverLicenseVO) {
        DriverLicenseDTO driverLicenseDTO = new DriverLicenseDTO();
        BeanUtils.copyProperties(driverLicenseVO, driverLicenseDTO);
        return driverLicenseDTO;
    }

    /**
     * DriverLicenseDO 转换 DriverLicenseDTO
     * @param driverLicenseDO
     * @return
     */
    public static DriverLicenseDTO toDTO(DriverLicenseDO driverLicenseDO) {
        DriverLicenseDTO driverLicenseDTO = new DriverLicenseDTO();
        BeanUtils.copyProperties(driverLicenseDO, driverLicenseDTO);
        return driverLicenseDTO;
    }

    /**
     * List<DriverLicenseForm> 转换 List<DriverLicenseDTO>
     * @param driverLicenseFormList
     * @return
     */
    public static List<DriverLicenseDTO> toDTO(List<DriverLicenseForm> driverLicenseFormList) {
        if (CollectionUtils.isEmpty(driverLicenseFormList)) return null;
        List<DriverLicenseDTO> driverLicenseDTOList = new ArrayList<>();
        for (DriverLicenseForm driverLicenseForm : driverLicenseFormList) {
            driverLicenseDTOList.add(toDTO(driverLicenseForm));
        }
        return driverLicenseDTOList;
    }

    /**
     * DriverLicenseDTO 转换 DriverLicenseVO
     * @param driverLicenseDTO
     * @return
     */
    public static DriverLicenseVO toVO(DriverLicenseDTO driverLicenseDTO) {
        DriverLicenseVO driverLicenseVO = new DriverLicenseVO();
        BeanUtils.copyProperties(driverLicenseDTO, driverLicenseVO);
        return driverLicenseVO;
    }

    /**
     * List<DriverLicenseDTO> 转换 List<DriverLicenseVO>
     * @param driverLicenseDTOList
     * @return
     */
    public static List<DriverLicenseVO> toVO(List<DriverLicenseDTO> driverLicenseDTOList) {
        if (CollectionUtils.isEmpty(driverLicenseDTOList)) return null;
        List<DriverLicenseVO> driverLicenseVOList = new ArrayList<>();
        for (DriverLicenseDTO driverLicenseDTO : driverLicenseDTOList) {
            driverLicenseVOList.add(toVO(driverLicenseDTO));
        }
        return driverLicenseVOList;
    }

    /**
     * Page<DriverLicenseDTO> 转换 Page<DriverLicenseVO>
     * @param driverLicenseDTOPage
     * @return
     */
    public static Page<DriverLicenseVO> toVO(Page<DriverLicenseDTO> driverLicenseDTOPage) {
        List<DriverLicenseDTO> driverLicenseDTOList = driverLicenseDTOPage.getRecords();
        Page<DriverLicenseVO> driverLicenseVOPage = new Page<>();
        BeanUtils.copyProperties(driverLicenseDTOPage, driverLicenseVOPage);
        driverLicenseVOPage.setRecords(toVO(driverLicenseDTOList));
        return driverLicenseVOPage;
    }

}
