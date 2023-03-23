package com.bobo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ser04")
// name 起个名字做区分，value才是路径
// @WebServlet(name ="/demo",value = "ser01")
// @WebServlet(name ="/demo",value = {"ser01","ser001"})
// @WebServlet(name = "/demo", urlPatterns = { "ser01", "ser001" })

public class MyServlet04 extends HttpServlet {

  // 除了实现service 还可以实现doGet和doPost
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // new 一个cookie
    Cookie cookie = new Cookie("userName", "zhangsan");

    //将不存储cookie，浏览器过期后失效
    cookie.setMaxAge(-1);
    //设置cookie的存活时间
    cookie.setMaxAge(10);
    //删除该cookie
    cookie.setMaxAge(0);
    // 将cookie对象添加到给response
    resp.addCookie(cookie);


    // //重定向可以为页面 也可以为servlet
    // resp.sendRedirect("index.jsp");

    Cookie[] cookies = req.getCookies();
    if (cookies != null && cookies.length > 0) {
      for (Cookie cookie2 : cookies) {
        System.out.println(cookie2.getName());
        System.out.println(cookie2.getValue());
      }
    }

  }

  @Override
  public void init(ServletConfig config) throws ServletException {
    System.out.println("创建实例");
  }

  @Override
  public void destroy() {
    System.out.println("实例销毁");
  }

}
