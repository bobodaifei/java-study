package com.bobo.purview.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bobo.purview.component.convert.MenuConvert;
import com.bobo.purview.entity.Menu;
import com.bobo.purview.mapper.MenuMapper;
import com.bobo.purview.pojo.dto.MenuDTO;
import com.bobo.purview.pojo.vo.MenuVO;
import com.bobo.purview.service.MenuService;
import com.bobo.purview.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

  private final MenuConvert INSTANCE = MenuConvert.INSTANCE;

  @Autowired
  MenuMapper menuMapper;

  @Autowired
  RoleService roleService;

  @Override
  public boolean update(Integer menuId, MenuDTO menuDTO) {
    Menu menu = INSTANCE.toEntity(menuDTO);
    menu.setMenuId(menuId);
    int i = menuMapper.updateById(menu);
    return i != 0;
  }

  @Override
  public boolean save(MenuDTO menuDTO) {
    int i = menuMapper.insert(INSTANCE.toEntity(menuDTO));
    return i != 0;
  }

  @Override
  public List<MenuVO> tree(Integer roleId) {
    return roleService.getMenuTreeByRoleId(roleId);
  }

  @Override
  public List<MenuVO> getByPid(Integer pid) {
    LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
    if (pid == null) {
      wrapper.isNull(Menu::getPid);
    } else {
      wrapper.eq(Menu::getPid, pid);
    }
    List<Menu> menus = menuMapper.selectList(wrapper);

    List<MenuVO> menuVOS = INSTANCE.toVOS(menus);
    menuVOS.forEach(menuVO -> {
      LambdaQueryWrapper<Menu> wrapper1 = new LambdaQueryWrapper<>();
      wrapper1.eq(Menu::getPid, menuVO.getId());
      if (menuMapper.selectCount(wrapper1) > 0) {
        menuVO.setHasMenuChildren(true);
      }
    });
    return menuVOS;
  }


}
