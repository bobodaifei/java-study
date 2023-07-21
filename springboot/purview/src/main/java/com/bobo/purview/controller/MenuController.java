package com.bobo.purview.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bobo.purview.component.annotate.RequiresPermissions;
import com.bobo.purview.component.base.Result;
import com.bobo.purview.component.convert.MenuConvert;
import com.bobo.purview.entity.Menu;
import com.bobo.purview.pojo.dto.MenuDTO;
import com.bobo.purview.pojo.vo.MenuVO;
import com.bobo.purview.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

  private final MenuConvert INSTANCE = MenuConvert.INSTANCE;

  @Autowired
  MenuService menuService;

  @RequiresPermissions("menu:select")
  @GetMapping("/{menuId}")
  public Result<?> select(@PathVariable Integer menuId) {
    Menu menu = menuService.getById(menuId);
    if (menu == null) {
      return Result.error("-1", "查询失败");
    }
    MenuVO menuVO = INSTANCE.toVO(menu);
    return Result.success(menuVO);
  }

  @RequiresPermissions("menu:select")
  @GetMapping("/tree")
  public Result<?> allTree() {
    List<MenuVO> list = menuService.tree(null);
    return Result.success(list);
  }

  @GetMapping("/getListByLevel/{level}")
  public Result<?> selectListByLevel(@PathVariable Integer level) {
    LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(Menu::getLevel, level);
    List<Menu> list = menuService.list(wrapper);
    List<MenuVO> menuVOS = INSTANCE.toVOS(list);
    return Result.success(menuVOS);
  }

  @RequiresPermissions("menu:select")
  @PostMapping("/getTreeChild")
  public Result<?> selectTreeChild(@RequestParam(required = false) Integer pid) {
    List<MenuVO> list = menuService.getByPid(pid);
    return Result.success(list);
  }

  @RequiresPermissions("menu:update")
  @PutMapping("/{menuId}")
  public Result<?> update(@PathVariable Integer menuId, @RequestBody MenuDTO menuDTO) {
    boolean res = menuService.update(menuId, menuDTO);
    if (!res) {
      return Result.error("-1", "修改失败");
    }
    return Result.success();
  }

  @RequiresPermissions("menu:delete")
  @DeleteMapping("/{menuId}")
  public Result<?> delete(@PathVariable Integer menuId) {
    boolean res = menuService.removeById(menuId);
    if (!res) {
      return Result.error("-1", "删除失败");
    }
    return Result.success();
  }

  @RequiresPermissions("menu:add")
  @PostMapping
  public Result<?> add(@RequestBody MenuDTO menuDTO) {
    boolean res = menuService.save(menuDTO);
    if (!res) {
      return Result.error("-1", "新增失败");
    }
    return Result.success();
  }


}
