package com.sbgs.hrsservice.service.impl;

import com.sbgs.hrscommon.converter.MenuConverter;
import com.sbgs.hrscommon.domain.sys.MenuDO;
import com.sbgs.hrscommon.dto.MenuDTO;
import com.sbgs.hrscommon.utils.CustomBeanUtils;
import com.sbgs.hrscommon.utils.UserDetailsUtils;
import com.sbgs.hrscommon.vo.MenuVO;
import com.sbgs.hrsrepo.mapper.MenuMapper;
import com.sbgs.hrsservice.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;

    @Override
    public List<MenuDTO> getMenuRoleListByType(Integer type) {
        return menuMapper.getMenuRoleListByUserId(null);
    }

    @Override
    public List<MenuVO> getMenusByCurrentUser() {
        Long userId = UserDetailsUtils.getUserId();
        Assert.notNull(userId, "没有获取到当前的登录状态或为匿名用户");
        List<MenuDTO> menuDTOList = menuMapper.getMenuRoleListByUserId(userId);
        return MenuConverter.builtMenuVOTreeByMenuList(menuDTOList);
    }

    @Override
    public List<MenuDTO> getAllMenus() {
        return menuMapper.getAllMenus();
    }


    @Override
    public List<MenuDTO> getAllMenuTree() {
        List<MenuDTO> menuDTOList = this.getAllMenus();
        return MenuConverter.builtMenuTree(menuDTOList);
    }

    @Override
    public MenuDTO getMenuById(Long menuId) {
        MenuDO menuDO = menuMapper.selectById(menuId);
        Assert.notNull(menuDO, "获取失败：没有找到该菜单或已被删除");
        return MenuConverter.toDTO(menuDO);
    }

    @Override
    @Transactional
    public Long addMenu(MenuDTO menuDTO) {
        MenuDO menuDO = MenuConverter.toDO(menuDTO);
        menuMapper.insert(menuDO);
        return menuDO.getId();
    }

    @Override
    @Transactional
    public Long updateMenu(MenuDTO menuDTO) {
        Assert.notNull(menuDTO.getId(), "更新失败：菜单id不能为空");
        Assert.isTrue(!menuDTO.getId().equals(menuDTO.getPid()), "更新失败：上级菜单不能选择自己");
        if (menuDTO.getPid() != null) {
            MenuDO menuPNode = menuMapper.selectById(menuDTO.getPid());
            Assert.notNull(menuPNode, "更新失败：没有找到该菜单的上级菜单或已被删除");
            Assert.isTrue(!menuDTO.getId().equals(menuPNode.getPid()), "更新失败：菜单之间不能互相引用");
        }
        MenuDO menuDO = menuMapper.selectById(menuDTO.getId());
        Assert.notNull(menuDO, "更新失败：没有找到该菜单或已被删除");
        CustomBeanUtils.copyPropertiesExcludeMeta(menuDTO, menuDO);
        menuMapper.updateById(menuDO);
        try {
            this.getAllMenuTree();
        } catch (StackOverflowError e) {
            throw new IllegalArgumentException("更新失败：请检查菜单节点间设置是否有问题");
        }
        return menuDO.getId();
    }

    @Override
    @Transactional
    public Long deleteMenu(Long menuId) {
        menuMapper.deleteById(menuId);
        return menuId;
    }

}
