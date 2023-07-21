package com.bobo.purview.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bobo.purview.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {

  @Select("SELECT -permission_id FROM role_permission WHERE role_id = #{roleId}")
  public List<Integer> selectedTreeList(@Param("roleId") Integer roleId);

  @Select("SELECT func FROM permission WHERE permission_id IN (SELECT permission_id FROM role_permission WHERE role_id = #{roleId})")
  public List<String> selectPermissionListByRoleId(@Param("roleId") Integer roleId);
}
