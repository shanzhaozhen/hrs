package com.sbgs.hrsapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import com.sbgs.hrscommon.converter.ResourceConverter;
import com.sbgs.hrscommon.enums.ResourceType;
import com.sbgs.hrscommon.form.ResourceForm;
import com.sbgs.hrscommon.vo.ResourceVO;
import com.sbgs.hrscommon.vo.ResultBody;
import com.sbgs.hrsservice.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "resource", description = "后台资源接口")
@RestController
@RequiredArgsConstructor
public class ResourceController {

    private static final String GET_ALL_RESOURCE_TREE = "/resource/tree";
    private static final String GET_ALL_RESOURCE_ROOT_TREE = "/resource/root-tree";
    private static final String GET_RESOURCE_BY_ID = "/resource/{resourceId}";
    private static final String ADD_RESOURCE = "/resource";
    private static final String UPDATE_RESOURCE = "/resource";
    private static final String DELETE_RESOURCE = "/resource/{resourceId}";

    private final ResourceService resourceService;

    @Operation(summary = "获取所有资源（树状结构）")
    @GetMapping(GET_ALL_RESOURCE_TREE)
    public ResultBody<List<ResourceVO>> getAllResourceTree() {
        return ResultBody.build(() -> ResourceConverter.toVO(resourceService.getAllResourceTreeByType(null)));
    }

    @Operation(summary = "获取所有根部资源（树状结构）")
    @GetMapping(GET_ALL_RESOURCE_ROOT_TREE)
    public ResultBody<List<ResourceVO>> getAllResourceRootTree() {
        return ResultBody.build(() -> ResourceConverter.toVO(resourceService.getAllResourceTreeByType(ResourceType.KID.getValue())));
    }


    @Operation(summary = "获取资源（通过资源id）")
    @GetMapping(GET_RESOURCE_BY_ID)
    public ResultBody<ResourceVO> getResourceByResourceId(@PathVariable("resourceId") @Parameter(description = "资源id", example = "1") Long resourceId) {
        return ResultBody.build(() -> ResourceConverter.toVO(resourceService.getResourceById(resourceId)));
    }

    @Operation(summary = "资源添加接口")
    @PostMapping(ADD_RESOURCE)
    public ResultBody<Long> addResource(@RequestBody @Validated ResourceForm resourceForm) {
        return ResultBody.build(() -> resourceService.addResource(ResourceConverter.toDTO(resourceForm)));
    }

    @Operation(summary = "资源更新接口")
    @PutMapping(UPDATE_RESOURCE)
    public ResultBody<Long> updateResource(@RequestBody @Validated ResourceForm resourceForm) {
        return ResultBody.build(() -> resourceService.updateResource(ResourceConverter.toDTO(resourceForm)));
    }

    @Operation(summary = "资源删除接口")
    @DeleteMapping(DELETE_RESOURCE)
    public ResultBody<Long> deleteResource(@PathVariable("resourceId") @Parameter(description = "资源id", example = "1") Long resourceId) {
        return ResultBody.build(() -> resourceService.deleteResource(resourceId));
    }

}
