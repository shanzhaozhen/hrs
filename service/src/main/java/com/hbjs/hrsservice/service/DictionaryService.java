package com.hbjs.hrsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.dto.DictionaryDTO;

import java.util.List;

public interface DictionaryService {

    /**
     * 获取所有字典的根节点
     * @return
     */
    List<DictionaryDTO> getDictionaryRootList();

    /**
     * 获取所有字典的根节点（分页）
     * @param page
     * @param keyword
     * @return
     */
    Page<DictionaryDTO> getDictionaryRootPage(Page<DictionaryDTO> page, String keyword);

    /**
     * 通过字典id获取字典实体
     * @param dictionaryId
     * @return
     */
    DictionaryDTO getDictionaryById(Long dictionaryId);

    /**
     * 通过父节点获取字典树
     * @param dictionaryId
     * @return
     */
    DictionaryDTO getDictionaryTreeById(Long dictionaryId);

    /**
     * 构建字典树
     * @param dictionaryDTO
     */
    void builtDictionaryTree(DictionaryDTO dictionaryDTO);

    /**
     * 通过ID获取所在的树
     * @param dictionaryId
     * @return
     */
    DictionaryDTO getDictionaryParentTreeById(Long dictionaryId);

    /**
     * 通过父级ID获取字典子节点
     * @param pid
     * @return
     */
    List<DictionaryDTO> getDictionaryChildrenById(Long pid);

    /**
     * 通过Code获取下级所有节点
     * @param code
     * @param keyword
     * @return
     */
    List<DictionaryDTO> getDictionaryChildrenByCode(String code, String keyword);

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
