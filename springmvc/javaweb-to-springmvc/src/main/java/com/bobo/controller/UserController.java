package com.bobo.controller;

import com.bobo.common.Result;
import com.bobo.entity.Shop;
import com.bobo.entity.User;
import com.bobo.service.ShopService;
import com.bobo.service.UserService;
import com.bobo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

  @Autowired
  UserService userService;

  @Autowired
  ShopService shopService;

  @PostMapping("/login")
  public Result<?> login(@RequestBody User user) {
    user = userService.login(user);
    if (user == null) {
      return Result.error("-1", "登录失败");
    }
    Shop shop = shopService.selectById(user.getShop_no());
    user.setShop(shop);
    user.setToken(JwtUtil.getToken(user));

    return Result.success(user);
  }


}
