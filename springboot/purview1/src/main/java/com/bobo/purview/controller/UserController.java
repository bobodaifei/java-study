package com.bobo.purview.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobo.purview.component.annotate.RequiresPermissions;
import com.bobo.purview.component.base.Result;
import com.bobo.purview.entity.User;
import com.bobo.purview.pojo.dto.UserDTO;
import com.bobo.purview.pojo.query.UserPage;
import com.bobo.purview.pojo.vo.UserVO;
import com.bobo.purview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/login")
  public Result<?> login(@RequestBody UserDTO userDTO) {
    UserVO userVO = userService.login(userDTO);
    if (userVO == null) {
      return Result.error("-1", "登陆失败");
    }
    return Result.success(userVO);
  }

  @RequiresPermissions("user:select")
  @PostMapping("/page")
  public Result<?> page(@RequestBody UserPage page){
    Page<User> userPage = userService.pageWithRoleName(page, null);
    return Result.success(userPage);
  }

  @RequiresPermissions("user:update")
  @PutMapping("/{userId}")
  public Result<?> update(@PathVariable Integer userId, @RequestBody UserDTO userDTO) {
    boolean res = userService.update(userId, userDTO);

    if (!res) {
      return Result.error("-1", "修改失败");
    }
    return Result.success();
  }

  @RequiresPermissions("user:delete")
  @DeleteMapping("/{userId}")
  public Result<?> delete(@PathVariable Integer userId) {
    boolean res = userService.removeById(userId);
    if (!res) {
      return Result.error("-1", "删除失败");
    }
    return Result.success();
  }

  @RequiresPermissions("user:add")
  @PostMapping
  public Result<?> add(@RequestBody UserDTO userDTO) {
        boolean res = userService.save(userDTO);
    if (!res) {
      return Result.error("-1", "删除失败");
    }
    return Result.success();
  }

}
