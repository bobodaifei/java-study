package com.bobo.purview.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bobo.purview.component.convert.PermissionConvert;
import com.bobo.purview.entity.Permission;
import com.bobo.purview.mapper.PermissionMapper;
import com.bobo.purview.pojo.dto.PermissionDTO;
import com.bobo.purview.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

  private final PermissionConvert INSTANCE = PermissionConvert.INSTANCE;

  @Autowired
  PermissionMapper permissionMapper;


  @Override
  public boolean save(PermissionDTO permissionDTO) {
    int i = permissionMapper.insert(INSTANCE.toEntity(permissionDTO));
    return i != 0;
  }

  @Override
  public boolean update(Integer permissionId, PermissionDTO permissionDTO) {
    Permission permission = INSTANCE.toEntity(permissionDTO);
    permission.setPermissionId(permissionId);
    int i = permissionMapper.updateById(permission);
    return i != 0;
  }

  @Override
  public List<Integer> selectedTreeList(Integer roleId) {
    return permissionMapper.selectedTreeList(roleId);
  }

  @Override
  public List<String> selectPermissionListByRoleId(Integer roleId) {
    return permissionMapper.selectPermissionListByRoleId(roleId);
  }
}
