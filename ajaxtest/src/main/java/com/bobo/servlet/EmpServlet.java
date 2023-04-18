package com.bobo.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bobo.entity.Dept;
import com.bobo.entity.Emp;
import com.bobo.service.EmpService;
import com.bobo.service.EmpServiceImpl;

@WebServlet("/emp")
public class EmpServlet extends HttpServlet {

  private EmpService empService = new EmpServiceImpl();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

  }

  protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String empno = req.getParameter("empno");
    int res = empService.delete(empno);
    if (res == 1) {
      resp.sendRedirect("/javaweb02/emp?method=selectAll");
    }
  }

  public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    String ename = req.getParameter("ename");
    String empno = req.getParameter("empno");
    String job = req.getParameter("job");
    int mgrno = Integer.valueOf(req.getParameter("mgr"));
    Date hiredate = Date.valueOf(req.getParameter("hiredate"));
    BigDecimal sal = new BigDecimal(req.getParameter("sal"));
    BigDecimal COMM = new BigDecimal(req.getParameter("COMM"));
    int deptno = Integer.valueOf(req.getParameter("deptno"));

    Emp emp = new Emp();
    emp.setEmpno(Integer.valueOf(empno));
    emp.setCOMM(COMM);
    emp.setDeptno(deptno);
    emp.setEname(ename);
    emp.setSal(sal);
    emp.setHiredate(hiredate);
    emp.setJob(job);
    emp.setMgr(mgrno);
    empService.update(emp);

    req.getRequestDispatcher("/emp?method=selectAll").forward(req, resp);
  }

  protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String ename = req.getParameter("ename");
    String job = req.getParameter("job");
    Integer mgr = Integer.parseInt(req.getParameter("mgr"));
    Date hiredate = Date.valueOf(req.getParameter("hiredate"));
    BigDecimal sal = new BigDecimal(req.getParameter("sal"));
    BigDecimal COMM = new BigDecimal(req.getParameter("COMM"));
    Integer deptno = Integer.parseInt(req.getParameter("deptno"));
    Emp emp = new Emp(ename, job, mgr, hiredate, sal, COMM, deptno);
    emp.setMgr(mgr);
    int res = empService.add(emp);
    if (res == 1) {
      resp.sendRedirect("/javaweb02/emp?method=selectAll");
    }
  }

  protected void toUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    prepare(req, resp);
    String empno = req.getParameter("empno");
    Emp emp = empService.selectById(empno);
    req.setAttribute("emp", emp);
    req.getRequestDispatcher("/updateEmp.jsp").forward(req, resp);
  }

  protected void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    prepare(req, resp);
    req.getRequestDispatcher("/addEmp.jsp").forward(req, resp);
  }

  protected static void prepare(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    EmpService empService = new EmpServiceImpl();
    // 偷懒
    List<String> jobList = empService.getJobList();
    List<Emp> mgrList = empService.getMgrList();
    List<Dept> deptList = empService.getDeptList();

    HttpSession session = req.getSession();
    session.setAttribute("jobList", jobList);
    session.setAttribute("mgrList", mgrList);
    session.setAttribute("deptList", deptList);
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
    req.getRequestDispatcher("/emp?method=selectAll").forward(req, resp);
  }

  protected void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String currentPageStr = req.getParameter("currentPage");
    String pageSizeStr = req.getParameter("pageSize");
    int currentPage = 0;
    int pageSize = 10;
    if (!(currentPageStr == null || pageSizeStr == null)) {
      currentPage = Integer.valueOf(currentPageStr);
      pageSize = Integer.valueOf(pageSizeStr);
    }
    long total = empService.selectCount();
    int begin = (currentPage - 1) * pageSize;

    if (begin >= total) {
      currentPage--;
      begin = begin - pageSize;
    }
    if (currentPage < 1) {
      currentPage = 1;
      begin = 0;
    }

    List<Emp> list = empService.selectAll(begin, pageSize);

    req.setAttribute("empList", list);
    req.setAttribute("total", total);
    req.setAttribute("currentPage", currentPage);
    req.setAttribute("pageSize", pageSize);
    req.getRequestDispatcher("/empList.jsp").forward(req, resp);
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getParameter("method");
    if ("test".equals(method)) {
      resp.getWriter().write("Success Data");
      return;
    }
    // if ("login".equals(method)) {
    //   this.login(req, resp);
    //   return;
    // } else if ("selectAll".equals(method)) {
    //   this.selectAll(req, resp);
    //   return;
    // } else if ("add".equals(method)) {
    //   this.add(req, resp);
    //   return;
    // } else if ("toAdd".equals(method)) {
    //   this.toAdd(req, resp);
    //   return;
    // }else if ("delete".equals(method)) {
    //   this.delete(req, resp);
    //   return;
    // } else if ("update".equals(method)) {
    //   this.update(req, resp);
    //   return;
    // } else if ("toUpdate".equals(method)) {
    //   this.toUpdate(req, resp);
    //   return;
    // } else if ("test".equals(method)) {
    //   this.toUpdate(req, resp);
    //   return;
    // }
  }

}
