package com.bobo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/uploadServlet")
// 文件上传表单，的注解
@MultipartConfig
public class UploadServlet01 extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 设置请求编码格式
    request.setCharacterEncoding("UTF-8");
    // 获取uname表单元素
    String parameter = request.getParameter("uname");
    System.out.println(parameter);
    // 通过getPart(name)方法获取Part对象,name代表的是页面中file文件域的name属性值
    Part part = request.getPart("myFile");
    // 获取文件名
    String submittedFileName = part.getSubmittedFileName();
    // 文件上传位置
    String realPath = request.getServletContext().getRealPath("/");
    // 上传到指定位置
    part.write(realPath + submittedFileName);
    System.out.println(realPath + submittedFileName);
  }

}
