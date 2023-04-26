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
    }else if ("logout".equals(method)) {
      this.logout(req, resp);
      return;
    }
  }

  protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    User user = new User();
    user.setUser_no(req.getParameter("user_no"));
    user.setPassword(req.getParameter("password"));
    User res = userService.login(user);
    if (res == null) {
      out.write(JSON.toJSONString(Result.error("-1", "登录失败")));
      return;
    }
    Shop shop = shopService.selectOne(res.getShop_no());
    HttpSession session = req.getSession();
    session.setAttribute("userNo", res.getUser_no());
    session.setAttribute("userName", res.getUser_name());
    session.setAttribute("shopNo", res.getShop_no());
    session.setAttribute("shopName", shop.getShop_name());

    Cookie cookie1 = new Cookie("user_no", res.getUser_no());
    Cookie cookie2 = new Cookie("userName", res.getUser_name());
    Cookie cookie3 = new Cookie("shopNo", res.getShop_no());
    Cookie cookie4 = new Cookie("shopName", shop.getShop_name());
    resp.addCookie(cookie1);
    resp.addCookie(cookie2);
    resp.addCookie(cookie3);
    resp.addCookie(cookie4);
    out.write(JSON.toJSONString(Result.success()));
  }


  protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    HttpSession session = req.getSession();
    session.invalidate();
    out.write(JSON.toJSONString(Result.success()));
  }
}
