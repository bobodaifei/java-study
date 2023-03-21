package com.bobo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ser05")
// name 起个名字做区分，value才是路径
// @WebServlet(name ="/demo",value = "ser01")
// @WebServlet(name ="/demo",value = {"ser01","ser001"})
// @WebServlet(name = "/demo", urlPatterns = { "ser01", "ser001" })

public class MyServlet05 extends HttpServlet {

  // 除了实现service 还可以实现doGet和doPost
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    String name = "姓名";
    String value = "张三";
    //设置编码
    name = URLEncoder.encode(name);
    value = URLEncoder.encode(value);

    //当前项目路径为/ser05
    Cookie cookie = new Cookie(name, value);

    //该服务器任意项目下的资源可以访问
    cookie.setPath("/");
    //该服务器当前项目下的资源可以访问，默认就是当前站点
    cookie.setPath("/demo");
    //该服务器指定项目下的资源可以访问
    cookie.setPath("/demo01");
    //该服务器指定项目下的某个资源可以访问
    cookie.setPath("/demo/ser05");

    resp.addCookie(cookie);

    Cookie[] cookies = req.getCookies();
    if (cookies != null && cookies.length > 0) {
      for (Cookie cookie2 : cookies) {
        //进行解码
        URLDecoder.decode(cookie2.getName());
        URLDecoder.decode(cookie2.getValue());
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
