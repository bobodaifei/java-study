package com.xxx.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String uname = req.getParameter("uname");
    String upwd = req.getParameter("upwd");

    if (!("zhangsan".equals(uname)&&"123456".equals(upwd))) {
      req.setAttribute("msg", "error");
      req.getRequestDispatcher("login.jsp").forward(req, resp);
      return;
    }
    req.getSession().setAttribute("uname", uname);
    resp.sendRedirect("body.jsp");

  }
  
}
