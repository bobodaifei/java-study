package com.bobo.purview.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bobo.purview.entity.Menu;
import com.bobo.purview.pojo.dto.MenuDTO;
import com.bobo.purview.pojo.vo.MenuVO;

import java.util.List;

public interface MenuService extends IService<Menu> {

  public boolean update(Integer menuId, MenuDTO menuDTO);

  public boolean save(MenuDTO menuDTO);

  public List<MenuVO> tree(Integer roleId);

  public List<MenuVO> getByPid(Integer pid);

}
