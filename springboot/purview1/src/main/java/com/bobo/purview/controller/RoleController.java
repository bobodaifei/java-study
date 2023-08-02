package com.bobo.purview.controller;

import com.bobo.purview.component.annotate.RequiresPermissions;
import com.bobo.purview.component.base.Result;
import com.bobo.purview.entity.Role;
import com.bobo.purview.pojo.dto.RoleDTO;
import com.bobo.purview.pojo.query.RolePage;
import com.bobo.purview.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

  @Autowired
  RoleService roleService;

  @RequiresPermissions("role:select")
  @GetMapping("/list")
  public Result<?> list(){
    List<Role> list = roleService.list();
    return Result.success(list);
  }

  @RequiresPermissions("role:select")
  @PostMapping("/page")
  public Result<?> page(@RequestBody RolePage page){
     roleService.page(page,null);
     return Result.success(page);
  }

  @RequiresPermissions("role:update")
  @PutMapping("/{roleId}")
  public Result<?> update(@PathVariable Integer roleId, @RequestBody RoleDTO roleDTO) {
    boolean res = roleService.update(roleId, roleDTO);
    if (!res) {
      return Result.error("-1", "修改失败");
    }
    return Result.success();
  }

  @RequiresPermissions("role:update")
//  @RequiresRoles()
  @PutMapping("/updateMenuAndPermission/{roleId}")
  public Result<?> updateRoleAndPermission(@PathVariable Integer roleId, @RequestBody List<Integer> ids) {
    boolean res = roleService.updateMenuAndPermission(roleId,ids);
    if (!res){
      return Result.error("-1","修改失败");
    }
    return Result.success();
  }

  @RequiresPermissions("role:delete")
  @DeleteMapping("/{roleId}")
  public Result<?> delete(@PathVariable Integer roleId) {
    boolean res = roleService.removeById(roleId);
    if (!res) {
      return Result.error("-1", "删除失败");
    }
    return Result.success();
  }

  @RequiresPermissions("role:add")
  @PostMapping
  public Result<?> add(@RequestBody RoleDTO roleDTO) {
    boolean res = roleService.save(roleDTO);
    if (!res) {
      return Result.error("-1", "删除失败");
    }
    return Result.success();
  }



}
