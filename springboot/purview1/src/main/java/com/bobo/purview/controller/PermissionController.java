package com.bobo.purview.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bobo.purview.component.annotate.RequiresPermissions;
import com.bobo.purview.component.base.Result;
import com.bobo.purview.entity.Permission;
import com.bobo.purview.pojo.dto.PermissionDTO;
import com.bobo.purview.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {

  @Autowired
  PermissionService permissionService;

  @RequiresPermissions("permission:select")
  @PostMapping("/getByMenuId")
  public Result<?> selectByMenuId(@RequestBody PermissionDTO permissionDTO) {
    LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(Permission::getMenuId, permissionDTO.getMenuId());
    if (permissionDTO.getType() != null) {
      wrapper.eq(Permission::getType, permissionDTO.getType());
    }
    List<Permission> list = permissionService.list(wrapper);
    return Result.success(list);
  }

  @RequiresPermissions("permission:update")
  @PutMapping("/{permissionId}")
  public Result<?> update(@PathVariable Integer permissionId, @RequestBody PermissionDTO permissionDTO) {
    boolean res = permissionService.update(permissionId, permissionDTO);
    if (!res) {
      return Result.error("-1", "修改失败");
    }
    return Result.success();
  }

  @RequiresPermissions("permission:delete")
  @DeleteMapping("/{permissionId}")
  public Result<?> delete(@PathVariable Integer permissionId) {
    boolean res = permissionService.removeById(permissionId);
    if (!res) {
      return Result.error("-1", "删除失败");
    }
    return Result.success();
  }

  @RequiresPermissions("permission:add")
  @PostMapping
  public Result<?> add(@RequestBody PermissionDTO permissionDTO) {
    boolean res = permissionService.save(permissionDTO);
    if (!res) {
      return Result.error("-1", "添加失败");
    }
    return Result.success();
  }

  @RequiresPermissions("permission:select")
  @GetMapping("/selectedTreeList/{roleId}")
  public Result<?> selectedTreeList(@PathVariable Integer roleId) {
    List<Integer> list = permissionService.selectedTreeList(roleId);
    return Result.success(list);
  }


}
