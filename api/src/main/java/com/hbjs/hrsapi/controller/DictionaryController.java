package com.hbjs.hrsapi.controller;

import com.hbjs.hrscommon.converter.DictionaryConverter;
import com.hbjs.hrscommon.enums.DictionaryType;
import com.hbjs.hrscommon.form.DictionaryForm;
import com.hbjs.hrscommon.vo.DictionaryVO;
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

    private static final String GET_ALL_RESOURCE_TREE = "/dictionary/tree";
    private static final String GET_ALL_RESOURCE_ROOT_TREE = "/dictionary/root-tree";
    private static final String GET_RESOURCE_BY_ID = "/dictionary/{dictionaryId}";
    private static final String ADD_RESOURCE = "/dictionary";
    private static final String UPDATE_RESOURCE = "/dictionary";
    private static final String DELETE_RESOURCE = "/dictionary/{dictionaryId}";
    private static final String BATCH_DELETE_RESOURCE = "/dictionary";

    private final DictionaryService dictionaryService;

    @Operation(summary = "获取所有字典（树状结构）")
    @GetMapping(GET_ALL_RESOURCE_TREE)
    public ResultBody<List<DictionaryVO>> getDictionaryTree() {
        return ResultBody.build(() -> DictionaryConverter.toVO(dictionaryService.getDictionaryTreeByType(null)));
    }

    @Operation(summary = "获取所有根部字典")
    @GetMapping(GET_ALL_RESOURCE_ROOT_TREE)
    public ResultBody<List<DictionaryVO>> getDictionaryRoot() {
        return ResultBody.build(() -> DictionaryConverter.toVO(dictionaryService.getDictionaryRoot()));
    }


    @Operation(summary = "获取字典（通过字典id）")
    @GetMapping(GET_RESOURCE_BY_ID)
    public ResultBody<DictionaryVO> getDictionaryByDictionaryId(@PathVariable("dictionaryId") @Parameter(description = "字典id", example = "1") Long dictionaryId) {
        return ResultBody.build(() -> DictionaryConverter.toVO(dictionaryService.getDictionaryById(dictionaryId)));
    }

    @Operation(summary = "字典添加接口")
    @PostMapping(ADD_RESOURCE)
    public ResultBody<Long> addDictionary(@RequestBody @Validated DictionaryForm dictionaryForm) {
        return ResultBody.build(() -> dictionaryService.addDictionary(DictionaryConverter.toDTO(dictionaryForm)));
    }

    @Operation(summary = "字典更新接口")
    @PutMapping(UPDATE_RESOURCE)
    public ResultBody<Long> updateDictionary(@RequestBody @Validated DictionaryForm dictionaryForm) {
        return ResultBody.build(() -> dictionaryService.updateDictionary(DictionaryConverter.toDTO(dictionaryForm)));
    }

    @Operation(summary = "字典删除接口")
    @DeleteMapping(DELETE_RESOURCE)
    public ResultBody<Long> deleteDictionary(@PathVariable("dictionaryId") @Parameter(description = "字典id", example = "1") Long dictionaryId) {
        return ResultBody.build(() -> dictionaryService.deleteDictionary(dictionaryId));
    }

    @Operation(summary = "批量字典删除接口")
    @DeleteMapping(BATCH_DELETE_RESOURCE)
    public ResultBody<List<Long>> batchDeleteDictionary(@Parameter(description = "字典id", example = "1") @RequestBody List<Long> dictionaryIds) {
        return ResultBody.build(() -> dictionaryService.batchDeleteDictionary(dictionaryIds));
    }

}
