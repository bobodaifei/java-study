package com.bobo.purview.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bobo.purview.entity.Menu;
import com.bobo.purview.entity.Permission;
import com.bobo.purview.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

  public List<Menu> selectMenusByRoleId(@Param("roleId") Integer roleId);

  public List<Permission> selectPermissionsByRoleIdAndMenuId(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

  @Delete("DELETE FROM role_menu WHERE role_id = #{roleId}")
  public void deletetMenuForRole(@Param("roleId") Integer roleId);

  @Delete("DELETE FROM role_permission WHERE role_id = #{roleId}")
  public void deletetPermissionForRole(@Param("roleId") Integer roleId);

  @Insert("insert into role_menu (role_id,menu_id) VALUES(#{roleId},#{menuId})")
  public int insertMenuForRole(@Param("roleId") Integer roleId,@Param("menuId") Integer menuId);

  @Insert("insert into role_permission (role_id,permission_id) VALUES(#{roleId},#{permissionId})")
  public int insertPermissionForRole(@Param("roleId") Integer roleId,@Param("permissionId") Integer permissionId);
}
