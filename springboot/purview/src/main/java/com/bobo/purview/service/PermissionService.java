package com.bobo.purview.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bobo.purview.entity.Permission;
import com.bobo.purview.pojo.dto.PermissionDTO;

import java.util.List;

public interface PermissionService extends IService<Permission> {

  public boolean save(PermissionDTO permissionDTO);

  public boolean update(Integer permissionId, PermissionDTO permissionDTO);

  public List<Integer> selectedTreeList(Integer roleId);

  public List<String> selectPermissionListByRoleId(Integer roleId);
}
