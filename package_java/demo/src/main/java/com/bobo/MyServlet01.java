package com.bobo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ser01")
// name 起个名字做区分，value才是路径
// @WebServlet(name ="/demo",value = "ser01")
// @WebServlet(name ="/demo",value = {"ser01","ser001"})
// @WebServlet(name = "/demo", urlPatterns = { "ser01", "ser001" })

public class MyServlet01 extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("1515");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("1515");
  }

  // 除了实现service 还可以实现doGet和doPost
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");

    // 获取完整路径 (从http开始到？前结束)
    req.getRequestURL();
    // 获取部分路径 (从项目站点名开始到？前结束)
    req.getRequestURI();
    // 获取参数字符串，(从？后面开始到最后的字符串)
    req.getQueryString();
    // 获取请求方式
    req.getMethod();
    // 获取协议版本
    req.getProtocol();
    // 获取项目站点名 (项目对外访问路径)
    req.getContextPath();// 上下文路径
    // 获取指定名称的参数 常用重点！
    req.getParameter("name");
    // 获取指定名称参数的所有值(比如复选框)
    String[] hobbys = req.getParameterValues("hobby");
    if (hobbys != null && hobbys.length > 0) {
      for (String string : hobbys) {

      }
    }

    // request作用域
    // 设置作用域对象内容
    req.setAttribute("name", "张三");
    // 获取作用域对象内容
    req.getAttribute("name");
    // 删除作用域对象内容
    req.removeAttribute("name");
    // 从始至终只有一个请求
    // 数据可以共享
    // 从服务端跳转到另一个Servlet或客户端
    // req.getRequestDispatcher("/ser02").forward(req, resp);
    req.getRequestDispatcher("index.jsp").forward(req, resp);

    

    
    // 字符流乱码解决
    resp.setHeader("content-type", "text/html;charset=UTF-8");
    // 第一步: 使用字节输入流读取那张图片
    // 使用ServletContext获取资源的真实路径
    String realPath = getServletContext().getRealPath("filePath");
    InputStream is = new FileInputStream(realPath);
    // 第二步: 使用字节输出流，将图片输出到浏览器
    OutputStream os = resp.getOutputStream();
    // 边读编写
    int len = 0;
    byte[] buffer = new byte[1024];
    while ((len = is.read(buffer)) != -1) {
      os.write(buffer, 0, len);
    }
    is.close();

    
    // 字节流乱码解决
    // //设置输出流编码
    // resp.setCharacterEncoding("UTF-8");
    // //设置客户端的解码方式
    // resp.setHeader("content-type","text/html;charset=UTF-8");
    // 同时设置编码格式，上面可忽略
    resp.setContentType("text/html;charset=UTF-8");
    // 字符输出流
    PrintWriter writer = resp.getWriter();
    writer.write("realPath");

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
