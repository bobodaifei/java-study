package com.bobo.purview.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bobo.purview.component.convert.MenuConvert;
import com.bobo.purview.component.convert.PermissionConvert;
import com.bobo.purview.component.convert.RoleConvert;
import com.bobo.purview.entity.Menu;
import com.bobo.purview.entity.Permission;
import com.bobo.purview.entity.Role;
import com.bobo.purview.exception.CustomException;
import com.bobo.purview.mapper.RoleMapper;
import com.bobo.purview.pojo.dto.RoleDTO;
import com.bobo.purview.pojo.vo.MenuVO;
import com.bobo.purview.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

  private final RoleConvert INSTANCE = RoleConvert.INSTANCE;

  private final MenuConvert INSTANCE1 = MenuConvert.INSTANCE;
  private final PermissionConvert INSTANCE2 = PermissionConvert.INSTANCE;

  @Autowired
  RoleMapper roleMapper;

  @Override
  public List<MenuVO> getMenuTreeByRoleId(Integer roleId) {

    //获取全部菜单
    List<Menu> menus = roleMapper.selectMenusByRoleId(roleId);

    //全部菜单是否为空
    List<MenuVO> menuVOS = INSTANCE1.toVOS(menus);
    if (menuVOS == null || CollUtil.isEmpty(menuVOS)) {
      return null;
    }

    //获取一级标题
    List<MenuVO> menuVOS1 = menuVOS.stream()
            .filter(menuVO -> menuVO.getPid() == null)
            .collect(Collectors.toList());

    //以及标题是否为空
    if (menuVOS1.size() == 0) {
      return null;
    }

    //按照pid进行分组
    Map<Integer, List<MenuVO>> groupedData = menuVOS.stream()
            .collect(Collectors.groupingBy(menu -> menu.getPid() != null ? menu.getPid() : 0));

    //1.获取二级标题
    //2.获取三级标题，并设置给二级标题
    //3.为三级标题内设置权限
    List<MenuVO> menuList = groupedData.getOrDefault(0, Collections.emptyList())
            .stream()
            .peek(data1 -> {
              if (groupedData.get(data1.getMenuId()).size() > 0) data1.setHasMenuChildren(true);
              data1.setChildren(groupedData.getOrDefault(data1.getMenuId(), Collections.emptyList())
                      .stream()
                      .peek(data2 -> {
                        if (groupedData.get(data2.getMenuId()).size() > 0) data2.setHasMenuChildren(true);
                        data2.setChildren(groupedData.getOrDefault(data2.getMenuId(), Collections.emptyList())
                                .stream()
                                .peek(data3 -> {
                                  List<Permission> permissions = roleMapper.selectPermissionsByRoleIdAndMenuId(roleId, data3.getMenuId());
                                  //if (permissions.size() > 0) childData.setHasChildren(false);
                                  data3.setChildren(INSTANCE2.toVOS(permissions));
                                })
                                .collect(Collectors.toList()));
                      })
                      .collect(Collectors.toList()));
            })
            .collect(Collectors.toList());
    return menuList;
  }

  @Override
  public boolean update(Integer roleId, RoleDTO roleDTO) {
    Role role = INSTANCE.toEntity(roleDTO);
    role.setRoleId(roleId);
    int i = roleMapper.updateById(role);
    return i != 0;
  }

  @Override
  public boolean save(RoleDTO roleDTO) {
    int i = roleMapper.insert(INSTANCE.toEntity(roleDTO));
    return i != 0;
  }

  @Override
  public boolean updateMenuAndPermission(Integer roleId, List<Integer> ids) {
    roleMapper.deletetMenuForRole(roleId);

    roleMapper.deletetPermissionForRole(roleId);

    List<Integer> menuList = ids.stream()
            .filter(id -> id > 0)
            .collect(Collectors.toList());

    List<Integer> permissionList = ids.stream()
            .filter(id -> id < 0)
            .map(id -> -id)
            .collect(Collectors.toList());

    menuList.forEach(menuId -> {
      int i = roleMapper.insertMenuForRole(roleId, menuId);
      if (i == 0) {
        throw new CustomException("-1", "修改权限异常");
      }
    });

    permissionList.forEach(permissionId -> {
      int i = roleMapper.insertPermissionForRole(roleId, permissionId);
      if (i == 0) {
        throw new CustomException("-1", "修改权限异常");
      }
    });

    return true;
  }

}
