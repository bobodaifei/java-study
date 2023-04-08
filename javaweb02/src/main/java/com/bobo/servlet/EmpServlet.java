package com.bobo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bobo.entity.Emp;
import com.bobo.service.EmpService;
import com.bobo.service.EmpServiceImpl;

@WebServlet("/emp")
public class EmpServlet extends HttpServlet{

  private EmpService empService = new EmpServiceImpl();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

  }

  protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Emp emp = new Emp();
    emp.setEname(req.getParameter("ename"));
    emp.setEmpno(Integer.valueOf(req.getParameter("empno")));
    Emp res = empService.login(emp);
    if (res == null) {
      resp.sendRedirect("/javaweb02/login.jsp");
      return;
    }
    Cookie cookie = new Cookie("ename", res.getEname());
    resp.addCookie(cookie);
    resp.sendRedirect("/javaweb02/emp?method=selectAll");
  }

  protected void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<Emp> list = empService.selectAll();
    req.setAttribute("empList", list);
    req.getRequestDispatcher("/empList.jsp").forward(req, resp);
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getParameter("method");
    if ("login".equals(method)) {
      this.login(req, resp);
      return;
    }else if ("selectAll".equals(method)) {
      this.selectAll(req, resp);
      return;
    }
  }

  
  
}
