package com.bobo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/downServlet")
public class DownServlet01 extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 设置请求编码格式
    request.setCharacterEncoding("UTF-8");

    // 文件下载位置
    String realPath = request.getServletContext().getRealPath("/");
    String parameter = request.getParameter("fileName");
    File file = new File(realPath + parameter);
    System.out.println(realPath + parameter);
    // 判断是否存在，且是一个标准文件
    if (file.exists() && file.isFile()) {
      // 设置响应类型，为浏览器无法使用某种方式或激活某个程序来处理的MIME类型
      response.setContentType("application/x-msdownload");
      //设置信息头
      response.setHeader("content-disposition", "attachment;filename=" + parameter);
      FileInputStream fileInputStream = new FileInputStream(file);
      ServletOutputStream outputStream = response.getOutputStream();
      byte[] b = new byte[1024];
      int len = 0;
      while ((len = fileInputStream.read(b)) != -1) {
        outputStream.write(b, 0, len);
      }
      outputStream.close();
      fileInputStream.close();
    }
  }

}
