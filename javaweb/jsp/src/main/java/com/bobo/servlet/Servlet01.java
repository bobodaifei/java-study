package com.bobo.servlet;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginServlet")
public class Servlet01 extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ServletContext context = getServletContext();
    String uname = context.getInitParameter("uname");
    String password = context.getInitParameter("password");

    String name = req.getParameter("name");
    String pwd = req.getParameter("pwd");
    if (!(name.equals(uname) && pwd.equals(password))) {
      System.out.println("nook");
      resp.sendRedirect("/SDDZYY/loginServlet");
    } else {
      System.out.println("ok");
      req.setAttribute("name", uname);
      req.getRequestDispatcher("/menu").forward(req, resp);
      // resp.sendRedirect("/SDDZYY/loginServlet");
    }

  }

}
