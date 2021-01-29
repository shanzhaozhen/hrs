package com.sbgs.hrsservice.service;

import com.sbgs.hrscommon.dto.BeanInfo;

import java.util.List;

public interface BeanService {

    /**
     * 获取注入 Spring 容器的Bean
     * @return
     */
    List<BeanInfo> getBeanInfoList();

    /**
     * 根据BeanName获取对应的Bean信息
     * @param beanName
     * @return
     */
    BeanInfo getBeanInfoByName(String beanName);
}
