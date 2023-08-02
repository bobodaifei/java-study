package com.bobo.purview.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bobo.purview.entity.Role;
import com.bobo.purview.pojo.dto.RoleDTO;
import com.bobo.purview.pojo.vo.MenuVO;

import java.util.List;

public interface RoleService extends IService<Role> {

  public List<MenuVO> getMenuTreeByRoleId(Integer roleId);

  public boolean update(Integer roleId, RoleDTO roleDTO);

  public boolean save(RoleDTO roleDTO);

  public boolean updateMenuAndPermission(Integer roleId, List<Integer> ids);
}
