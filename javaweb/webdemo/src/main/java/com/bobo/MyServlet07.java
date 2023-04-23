package com.bobo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ser07")
// name 起个名字做区分，value才是路径
// @WebServlet(name ="/demo",value = "ser01")
// @WebServlet(name ="/demo",value = {"ser01","ser001"})
// @WebServlet(name = "/demo", urlPatterns = { "ser01", "ser001" })

public class MyServlet07 extends HttpServlet {

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 通过request对象获取(个人推荐地址中，只要能获取request对象就能用)
    ServletContext servletContext = req.getServletContext();

    // 通过session对象获取
    ServletContext servletContext2 = req.getSession().getServletContext();

    // 通过servletConfig对象获取
    ServletContext servletContext3 = getServletConfig().getServletContext();

    // 直接获取（只能在Servlet中用）
    ServletContext servletContext4 = getServletContext();

    // 常用方法
    // 1.获取当前服务器的版本信息
    String serverInfo = req.getServletContext().getServerInfo();
    System.out.println(serverInfo);
    // 获取项目根路径（真实路径）
    String realPath = req.getServletContext().getRealPath("/");
    System.out.println(realPath);

    // 通过request对象获取(个人推荐地址中，只要能获取request对象就能用)
    ServletContext servletContext5 = req.getServletContext();
    //设置域对象
    servletContext5.setAttribute("name", "zhangsan");
    //获取域对象
    String attribute = (String) servletContext5.getAttribute("name");
    //移除域对象
    servletContext5.removeAttribute("name");
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
