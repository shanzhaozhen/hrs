package com.sbgs.hrscommon.converter;

import com.sbgs.hrscommon.domain.sys.MenuDO;
import com.sbgs.hrscommon.dto.RoleDTO;
import com.sbgs.hrscommon.dto.MenuDTO;
import com.sbgs.hrscommon.form.MenuForm;
import com.sbgs.hrscommon.vo.MenuVO;
import com.sbgs.hrscommon.vo.Meta;
import com.sbgs.hrscommon.vo.MenuVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MenuConverter {

    /**
     * MenuDTO 转换 MenuDO
     * @param menuDTO
     * @return
     */
    public static MenuDO toDO(MenuDTO menuDTO) {
        MenuDO menuDO = new MenuDO();
        BeanUtils.copyProperties(menuDTO, menuDO);
        return menuDO;
    }

    /**
     * MenuDO 转换 MenuDTO
     * @param menuDO
     * @return
     */
    public static MenuDTO toDTO(MenuDO menuDO) {
        MenuDTO menuDTO = new MenuDTO();
        BeanUtils.copyProperties(menuDO, menuDTO);
        return menuDTO;
    }

    /**
     * MenuForm 转换 MenuDTO
     * @param menuForm
     * @return
     */
    public static MenuDTO toDTO(MenuForm menuForm) {
        MenuDTO menuDTO = new MenuDTO();
        BeanUtils.copyProperties(menuForm, menuDTO);
        return menuDTO;
    }

    /**
     * MenuVO 转换 MenuDTO
     * @param menuVO
     * @return
     */
    public static MenuDTO toDTO(MenuVO menuVO) {
        MenuDTO menuDTO = new MenuDTO();
        BeanUtils.copyProperties(menuVO, menuDTO);
        return menuDTO;
    }

    /**
     * MenuDTO 转换 MenuVO
     * @param menuDTO
     * @return
     */
    public static MenuVO toVO(MenuDTO menuDTO) {
        MenuVO menuVO = new MenuVO();
        BeanUtils.copyProperties(menuDTO, menuVO);
        if (menuDTO.getChildren() != null && menuDTO.getChildren().size() > 0) {
            menuVO.setChildren(toVO(menuDTO.getChildren()));
        }
        return menuVO;
    }

    /**
     * List<MenuDTO> 转换 List<MenuVO>
     * @param menuDTOList
     * @return
     */
    public static List<MenuVO> toVO(List<MenuDTO> menuDTOList) {
        List<MenuVO> menuVOList = new ArrayList<>();
        for (MenuDTO menuDTO : menuDTOList) {
            menuVOList.add(toVO(menuDTO));
        }
        return menuVOList;
    }

    /**
     * 将MenuDTO转换成MenuVO供给前端渲染使用
     * @param menuDTO
     * @return
     */
    public static MenuVO toMenuVO(MenuDTO menuDTO) {
        MenuVO menuVO = new MenuVO();
        List<RoleDTO> roleDTOList = menuDTO.getRoles();
        BeanUtils.copyProperties(menuDTO, menuVO);
        List<String> roles = new ArrayList<>();
        for (RoleDTO roleDTO : roleDTOList) {
            roles.add(roleDTO.getIdentification());
        }
        menuVO.setAccess(roles);
        if (menuDTO.getChildren() != null && menuDTO.getChildren().size() > 0) {
            menuVO.setChildren(toMenuVO(menuDTO.getChildren()));
        }
        return menuVO;
    }

    /**
     * 批量将MenuDTOList转换成MenuVO供给前端渲染使用
     * @param menuDTOList
     * @return
     */
    public static List<MenuVO> toMenuVO(List<MenuDTO> menuDTOList) {
        List<MenuVO> menus = new ArrayList<>();
        for (MenuDTO menuDTO : menuDTOList) {
            menus.add(toMenuVO(menuDTO));
        }
        return menus;
    }

    /**
     * 将asyncMenuList生成树状结构
     * @param asyncMenuList
     * @return
     */
    public static List<MenuVO> builtMenuVOTree(List<MenuVO> asyncMenuList) {
        List<MenuVO> rootList = new ArrayList<>();
        List<MenuVO> noRootList = new ArrayList<>();

        for (MenuVO asyncMenu : asyncMenuList) {
            if (asyncMenu.getPid() == null || asyncMenu.getPid() <= 0) {
                rootList.add(asyncMenu);
            } else {
                noRootList.add(asyncMenu);
            }
        }

        getMenuVOChildren(noRootList, asyncMenuList);

        rootList.sort((Comparator.comparing(MenuVO::getPriority, Comparator.nullsLast(Comparator.naturalOrder()))));

        return rootList;
    }

    /**
     * 对动态菜单子节点进行递归查找
     * @param noRootList
     * @param children
     * @return
     */
    public static List<MenuVO> getMenuVOChildren(List<MenuVO> noRootList, List<MenuVO> children) {
        for (MenuVO child : children) {
            List<MenuVO> grandsons = new ArrayList<>();
            for (MenuVO noRoot : noRootList) {
                if (child.getId().equals(noRoot.getPid())) {
                    grandsons.add(noRoot);
                }
            }
            if (grandsons.size() > 0) {
                child.setChildren(getMenuVOChildren(noRootList, grandsons));
            }
        }
        children.sort((Comparator.comparing(MenuVO::getPriority, Comparator.nullsLast(Comparator.naturalOrder()))));

        return children;
    }

    /**
     * 将menuList生成MenuVO的树状结构
     * @param menuDTOList
     * @return
     */
    public static List<MenuVO> builtMenuVOTreeByMenuList(List<MenuDTO> menuDTOList) {
        List<MenuVO> menuList = toMenuVO(menuDTOList);
        return builtMenuVOTree(menuList);
    }

    /**
     * 装资源list转成树状结构
     * @param menuDTOList
     * @return
     */
    public static List<MenuDTO> builtMenuTree(List<MenuDTO> menuDTOList) {
        List<MenuDTO> rootList = new ArrayList<>();
        List<MenuDTO> noRootList = new ArrayList<>();

        for (MenuDTO menuDTO : menuDTOList) {
            if (menuDTO.getPid() == null || menuDTO.getPid() <= 0) {
                rootList.add(menuDTO);
            } else {
                noRootList.add(menuDTO);
            }
        }

        getMenuDTOChildren(noRootList, menuDTOList);
        rootList.sort((Comparator.comparing(MenuDTO::getPriority, Comparator.nullsLast(Comparator.naturalOrder()))));

        return rootList;
    }

    /**
     * 对动态菜单子节点进行递归查找
     * @param noRootList
     * @param children
     * @return
     */
    public static List<MenuDTO> getMenuDTOChildren(List<MenuDTO> noRootList, List<MenuDTO> children) {
        for (MenuDTO child : children) {
            List<MenuDTO> grandsons = new ArrayList<>();
            for (MenuDTO noRoot : noRootList) {
                if (child.getId().equals(noRoot.getPid())) {
                    grandsons.add(noRoot);
                }
            }
            if (grandsons.size() > 0) {
                child.setChildren(getMenuDTOChildren(noRootList, grandsons));
            }
        }
        children.sort((Comparator.comparing(MenuDTO::getPriority, Comparator.nullsLast(Comparator.naturalOrder()))));

        return children;
    }

}
