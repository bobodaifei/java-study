package com.bobo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.bobo.common.Result;
import com.bobo.entity.Shop;
import com.bobo.entity.User;
import com.bobo.service.ShopService;
import com.bobo.service.ShopServiceImpl;
import com.bobo.service.UserService;
import com.bobo.service.UserServiceImpl;
import com.bobo.utils.JwtUtil;
import com.bobo.utils.aop.MyAdvice;
import com.bobo.utils.aop.ProxyFactory;
import com.bobo.utils.aop.TestHandler;

import cn.hutool.json.JSONUtil;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

  private UserService userService = new UserServiceImpl();
  ShopService shopService = new ShopServiceImpl();
  
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getParameter("method");
    if ("login".equals(method)) {
      this.login(req, resp);
      return;
    }
  }

  protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    String str = req.getReader().readLine();
    User user = JSONUtil.toBean(str, User.class);

    UserService userService = (UserService) ProxyFactory.getInstance(
        UserServiceImpl.class, new TestHandler(new MyAdvice(user.getUser_no())));
    user = userService.login(user);
    if (user == null) {
      out.write(JSON.toJSONString(Result.error("-1", "登录失败")));
      return;
    }
    Shop shop = shopService.selectById(user.getShop_no());
    user.setShop(shop);
    user.setToken(JwtUtil.getToken(user));

    out.write(JSON.toJSONString(Result.success(user)));
  }

}
