package com.bobo.controller;

import com.auth0.jwt.JWT;
import com.bobo.entity.Shop;
import com.bobo.entity.User;
import com.bobo.service.ShopService;
import com.bobo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BaseController {

  @Autowired
  private UserService userService;

  @Autowired
  private ShopService shopService;

  @Autowired
  private HttpServletRequest request;

  public User getUser() {
    String token = request.getHeader("Access-Token");
    String user_no = JWT.decode(token).getAudience().get(0);
    User user = userService.selectById(user_no);
    Shop shop = shopService.selectById(user.getShop_no());
    user.setShop(shop);
    return user;
  }
}
