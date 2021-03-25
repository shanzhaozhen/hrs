package com.hbjs.hrsservice.service;

import com.hbjs.hrscommon.dto.DictionaryDTO;

import java.util.List;

public interface DictionaryService {

    /**
     * 获取所有字典的树形结构
     * @return
     */
    List<DictionaryDTO> getDictionaryTreeByType(Integer type);

    /**
     * 获取所有字典的根节点
     * @return
     */
    List<DictionaryDTO>  getDictionaryRoot();

    /**
     * 通过字典id获取字典实体
     * @param dictionaryId
     * @return
     */
    DictionaryDTO getDictionaryById(Long dictionaryId);

    /**
     * 增加字典
     * @param dictionaryDTO
     * @return
     */
    Long addDictionary(DictionaryDTO dictionaryDTO);

    /**
     * 修改字典
     * @param dictionaryDTO
     * @return
     */
    Long updateDictionary(DictionaryDTO dictionaryDTO);

    /**
     * 删除字典(通过字典id删除)
     * @param dictionaryId
     * @return
     */
    Long deleteDictionary(Long dictionaryId);

    /**
     * 批量删除字典(通过字典id删除)
     * @param dictionaryIds
     * @return
     */
    List<Long> batchDeleteDictionary(List<Long> dictionaryIds);
    
}
