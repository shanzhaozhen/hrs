package com.sbgs.hrsservice.service;

import com.sbgs.hrscommon.dto.MenuDTO;
import com.sbgs.hrscommon.vo.MenuVO;

import java.util.List;

public interface MenuService {

    /**
     * 通过 MenuType 类型获取所有的Menu（多对多含有角色信息）
     * @param type
     * @return
     */
    List<MenuDTO> getMenuRoleListByType(Integer type);

    /**
     * 通过当前用户的信息生成对应的前端菜单
     * @return
     */
    List<MenuVO> getMenusByCurrentUser();

    /**
     * 获取所有菜单的树形结构
     * @return
     */
    List<MenuDTO> getAllMenu();
    
    /**
     * 获取所有菜单的树形结构
     * @return
     */
    List<MenuDTO> getAllMenuTree();

    /**
     * 通过菜单id获取菜单实体
     * @param menuId
     * @return
     */
    MenuDTO getMenuById(Long menuId);

    /**
     * 增加菜单
     * @param menuDTO
     * @return
     */
    Long addMenu(MenuDTO menuDTO);

    /**
     * 修改菜单
     * @param menuDTO
     * @return
     */
    Long updateMenu(MenuDTO menuDTO);

    /**
     * 删除菜单(通过菜单id删除)
     * @param menuId
     * @return
     */
    Long deleteMenu(Long menuId);

}
