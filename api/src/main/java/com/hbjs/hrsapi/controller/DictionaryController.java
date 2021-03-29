package com.hbjs.hrsapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbjs.hrscommon.converter.DictionaryConverter;
import com.hbjs.hrscommon.dto.DictionaryDTO;
import com.hbjs.hrscommon.form.DictionaryForm;
import com.hbjs.hrscommon.vo.DictionaryVO;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrsservice.service.DictionaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "dictionary", description = "字典接口")
@RestController
@RequiredArgsConstructor
public class DictionaryController {

    private static final String GET_ALL_DICTIONARY_ROOT = "/dictionary/root";
    private static final String GET_PAGE_DICTIONARY_ROOT = "/dictionary/page/root";
    private static final String GET_DICTIONARY_BY_ID = "/dictionary/{dictionaryId}";
    private static final String GET_DICTIONARY_TREE_BY_ID = "/dictionary/{dictionaryId}/tree";
    private static final String GET_DICTIONARY_PARENT_TREE_BY_ID = "/dictionary/{dictionaryId}/tree/parent";
    private static final String GET_DICTIONARY_CHILDREN_BY_ID = "/dictionary/{dictionaryId}/children";
    private static final String ADD_DICTIONARY = "/dictionary";
    private static final String UPDATE_DICTIONARY = "/dictionary";
    private static final String DELETE_DICTIONARY = "/dictionary/{dictionaryId}";
    private static final String BATCH_DELETE_DICTIONARY = "/dictionary";

    private final DictionaryService dictionaryService;

    @Operation(summary = "获取所有根部字典")
    @GetMapping(GET_ALL_DICTIONARY_ROOT)
    public ResultBody<List<DictionaryVO>> getDictionaryRootList() {
        return ResultBody.build(() -> DictionaryConverter.toVO(dictionaryService.getDictionaryRootList()));
    }

    @Operation(summary = "获取根部字典（分页）")
    @GetMapping(GET_PAGE_DICTIONARY_ROOT)
    public ResultBody<Page<DictionaryVO>> getDictionaryRootPage(Page<DictionaryDTO> page) {
        return ResultBody.build(() -> DictionaryConverter.toVO(dictionaryService.getDictionaryRootPage(page)));
    }

    @Operation(summary = "获取字典（通过字典id）")
    @GetMapping(GET_DICTIONARY_BY_ID)
    public ResultBody<DictionaryVO> getDictionaryById(@PathVariable("dictionaryId") @Parameter(description = "字典id", example = "1") Long dictionaryId) {
        return ResultBody.build(() -> DictionaryConverter.toVO(dictionaryService.getDictionaryById(dictionaryId)));
    }

    @Operation(summary = "获取字典树（通过父级字典id）")
    @GetMapping(GET_DICTIONARY_TREE_BY_ID)
    public ResultBody<DictionaryVO> getDictionaryTreeById(@PathVariable("dictionaryId") @Parameter(description = "字典id", example = "1") Long dictionaryId) {
        return ResultBody.build(() -> DictionaryConverter.toVO(dictionaryService.getDictionaryTreeById(dictionaryId)));
    }

    @Operation(summary = "通过ID获取所在的树")
    @GetMapping(GET_DICTIONARY_PARENT_TREE_BY_ID)
    public ResultBody<DictionaryVO> getDictionaryParentTreeById(@PathVariable("dictionaryId") @Parameter(description = "字典id", example = "1") Long dictionaryId) {
        return ResultBody.build(() -> DictionaryConverter.toVO(dictionaryService.getDictionaryParentTreeById(dictionaryId)));
    }

    @Operation(summary = "通过父级ID获取字典子节点")
    @GetMapping(GET_DICTIONARY_CHILDREN_BY_ID)
    public ResultBody<List<DictionaryVO>> getDictionaryChildrenById(@PathVariable("dictionaryId") @Parameter(description = "字典id", example = "1") Long dictionaryId) {
        return ResultBody.build(() -> DictionaryConverter.toVO(dictionaryService.getDictionaryChildrenByPid(dictionaryId)));
    }

    @Operation(summary = "字典添加接口")
    @PostMapping(ADD_DICTIONARY)
    public ResultBody<Long> addDictionary(@RequestBody @Validated DictionaryForm dictionaryForm) {
        return ResultBody.build(() -> dictionaryService.addDictionary(DictionaryConverter.toDTO(dictionaryForm)));
    }

    @Operation(summary = "字典更新接口")
    @PutMapping(UPDATE_DICTIONARY)
    public ResultBody<Long> updateDictionary(@RequestBody @Validated DictionaryForm dictionaryForm) {
        return ResultBody.build(() -> dictionaryService.updateDictionary(DictionaryConverter.toDTO(dictionaryForm)));
    }

    @Operation(summary = "字典删除接口")
    @DeleteMapping(DELETE_DICTIONARY)
    public ResultBody<Long> deleteDictionary(@PathVariable("dictionaryId") @Parameter(description = "字典id", example = "1") Long dictionaryId) {
        return ResultBody.build(() -> dictionaryService.deleteDictionary(dictionaryId));
    }

    @Operation(summary = "批量字典删除接口")
    @DeleteMapping(BATCH_DELETE_DICTIONARY)
    public ResultBody<List<Long>> batchDeleteDictionary(@Parameter(description = "字典id", example = "1") @RequestBody List<Long> dictionaryIds) {
        return ResultBody.build(() -> dictionaryService.batchDeleteDictionary(dictionaryIds));
    }

}
