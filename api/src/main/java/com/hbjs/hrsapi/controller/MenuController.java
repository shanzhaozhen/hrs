package com.hbjs.hrsapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import com.hbjs.hrscommon.converter.MenuConverter;
import com.hbjs.hrscommon.form.MenuForm;
import com.hbjs.hrscommon.vo.ResultBody;
import com.hbjs.hrscommon.vo.MenuVO;
import com.hbjs.hrsservice.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "menu", description = "用户菜单接口")
@RestController
@RequiredArgsConstructor
public class MenuController {

    private static final String GET_ALL_MENU = "/menu/all";
    private static final String GET_ALL_MENU_TREE = "/menu/tree";
    private static final String GET_MENU_BY_ID = "/menu/{menuId}";
    private static final String ADD_MENU = "/menu";
    private static final String UPDATE_MENU = "/menu";
    private static final String DELETE_MENU = "/menu/{menuId}";
    private static final String BATCH_DELETE_MENU = "/menu";

    private final MenuService menuService;

    @Operation(summary = "获取所有菜单信息")
    @GetMapping(GET_ALL_MENU)
    public ResultBody<List<MenuVO>> getAllMenus() {
        return ResultBody.build(() -> MenuConverter.toVO(menuService.getAllMenus()));
    }

    @Operation(summary = "获取所有菜单信息（树状结构）")
    @GetMapping(GET_ALL_MENU_TREE)
    public ResultBody<List<MenuVO>> getMenuTree() {
        return ResultBody.build(() -> MenuConverter.toVO(menuService.getMenuTree()));
    }

    @Operation(summary = "获取菜单信息（通过菜单id）")
    @GetMapping(GET_MENU_BY_ID)
    public ResultBody<MenuVO> getMenuByMenuId(@PathVariable("menuId") @Parameter(description = "菜单id", example = "1") Long menuId) {
        return ResultBody.build(() -> MenuConverter.toVO(menuService.getMenuById(menuId)));
    }

    @Operation(summary = "添加菜单接口")
    @PostMapping(ADD_MENU)
    public ResultBody<Long> addMenu(@RequestBody @Validated MenuForm menuForm) {
        return ResultBody.build(() -> menuService.addMenu(MenuConverter.toDTO(menuForm)));
    }

    @Operation(summary = "更新菜单接口")
    @PutMapping(UPDATE_MENU)
    public ResultBody<Long> updateMenu(@RequestBody @Validated MenuForm menuForm) {
        return ResultBody.build(() -> menuService.updateMenu(MenuConverter.toDTO(menuForm)));
    }

    @Operation(summary = "删除菜单接口")
    @DeleteMapping(DELETE_MENU)
    public ResultBody<Long> deleteMenu(@PathVariable("menuId") @Parameter(description = "菜单id", example = "1") Long menuId) {
        return ResultBody.build(() -> menuService.deleteMenu(menuId));
    }

    @Operation(summary = "批量删除菜单接口")
    @DeleteMapping(BATCH_DELETE_MENU)
    public ResultBody<List<Long>> batchDeleteMenu(@Parameter(description = "菜单id", example = "[1, 2]") @RequestBody List<Long> menuIds) {
        return ResultBody.build(() -> menuService.batchDeleteMenu(menuIds));
    }

}
