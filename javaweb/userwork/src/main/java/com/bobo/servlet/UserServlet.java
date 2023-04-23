package com.bobo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    User user = new User();
    user.setUser_no(req.getParameter("user_no"));
    user.setPassword(req.getParameter("password"));
    User res = userService.login(user);
    if (res == null) {
      resp.sendRedirect("/userwork/login.jsp");
      return;
    }
    Shop shop = shopService.selectOne(res.getShop_no());
    HttpSession session = req.getSession();
    session.setAttribute("userNo", res.getUser_no());
    session.setAttribute("userName", res.getUser_name());
    session.setAttribute("shopNo", res.getShop_no());
    session.setAttribute("shopName", shop.getShop_name());

    Cookie cookie = new Cookie("user_no", res.getUser_no());
    resp.addCookie(cookie);
    resp.sendRedirect("/userwork/stock?method=selectPage");
    // req.getRequestDispatcher("/good?method=selectPage").forward(req, resp);
  }

  protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    session.invalidate();
    resp.sendRedirect("/userwork/login.jsp");
  }
}
