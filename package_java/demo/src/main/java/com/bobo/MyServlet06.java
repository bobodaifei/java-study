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
import javax.servlet.http.HttpSession;

@WebServlet("/ser06")
// name 起个名字做区分，value才是路径
// @WebServlet(name ="/demo",value = "ser01")
// @WebServlet(name ="/demo",value = {"ser01","ser001"})
// @WebServlet(name = "/demo", urlPatterns = { "ser01", "ser001" })

public class MyServlet06 extends HttpServlet {

 
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //如果存在则获取session对象，不存在就创建session对象
    HttpSession session = req.getSession();
    
    //获取session的会话标识符
    String id = session.getId();
    //获取session的创建时间
    long creationTime = session.getCreationTime();
    //获取最后一次访问时间
    long lastAccessedTime = session.getLastAccessedTime();
    //判断是否是新的session对象
    boolean new1 = session.isNew();

    //设置session域对象
    session.setAttribute("userName", "admin");
    //获取域对象
    String userName = (String)session.getAttribute("userName");
    //移除指定名称的session域对象
    session.removeAttribute("userName");
    //session 销毁时间为15秒
    session.setMaxInactiveInterval(15);
    //获取最大不活动时间
    int maxInactiveInterval = session.getMaxInactiveInterval();
    //销毁session对象
    session.invalidate();

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
