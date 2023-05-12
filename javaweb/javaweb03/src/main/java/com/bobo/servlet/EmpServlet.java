package com.bobo.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.bobo.common.Result;
import com.bobo.entity.Emp;
import com.bobo.service.EmpService;
import com.bobo.service.EmpServiceImpl;
// import com.bobo.utils.AffairsHandler;
import com.bobo.utils.JwtUtil;
import com.bobo.utils.aop.MyAdvice;
import com.bobo.utils.aop.ProxyFactory;
import com.bobo.utils.aop.TestHandler;

import cn.hutool.json.JSONUtil;

// import com.bobo.utils.ProxyFactory;
import com.bobo.common.Page;

@WebServlet("/emp")
public class EmpServlet extends BaseServlet {

  // private EmpService empService =
  // (EmpService)ProxyFactory.getInstance(EmpServiceImpl.class, new
  // AffairsHandler());
  private EmpService empService = (EmpService) ProxyFactory.getInstance(EmpServiceImpl.class,
      new TestHandler(new MyAdvice()));

  protected void delete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    int empno = Integer.valueOf(req.getParameter("empno"));

    EmpService empService = (EmpService) ProxyFactory.getInstance(EmpServiceImpl.class,
        new TestHandler(new MyAdvice(getEmp(req, resp).getEmpno())));
    int res = empService.delete(empno);

    if (res == 0) {
      out.write(JSON.toJSONString(Result.error("-1", "修改失败")));
      return;
    }
    out.write(JSON.toJSONString(Result.success()));
  }

  public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    PrintWriter out = resp.getWriter();
    String str = req.getReader().readLine();
    Emp emp = JSONUtil.toBean(str, Emp.class);

    EmpService empService = (EmpService) ProxyFactory.getInstance(EmpServiceImpl.class,
        new TestHandler(new MyAdvice(getEmp(req, resp).getEmpno())));
    int res = empService.update(emp);

    if (res == 0) {
      out.write(JSON.toJSONString(Result.error("-1", "修改失败")));
      return;
    }
    out.write(JSON.toJSONString(Result.success()));
  }

  protected void selectOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    String empno = req.getParameter("empno");

    EmpService empService = (EmpService) ProxyFactory.getInstance(EmpServiceImpl.class,
        new TestHandler(new MyAdvice(getEmp(req, resp).getEmpno())));
    Emp res = empService.selectById(Integer.valueOf(empno));
    out.write(JSON.toJSONString(Result.success(res)));
  }

  protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    String str = req.getReader().readLine();
    Emp emp = JSONUtil.toBean(str, Emp.class);

    EmpService empService = (EmpService) ProxyFactory.getInstance(EmpServiceImpl.class,
        new TestHandler(new MyAdvice(getEmp(req, resp).getEmpno())));
    int res = empService.add(emp);

    if (res == 0) {
      out.write(JSON.toJSONString(Result.error("-1", "插入失败")));
      return;
    }
    out.write(JSON.toJSONString(Result.success()));
  }

  protected void getJobList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();

    EmpService empService = (EmpService) ProxyFactory.getInstance(EmpServiceImpl.class,
        new TestHandler(new MyAdvice(getEmp(req, resp).getEmpno())));
    List<String> res = empService.getJobList();
    out.write(JSON.toJSONString(Result.success(res)));
  }

  protected void getMgrList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();

    EmpService empService = (EmpService) ProxyFactory.getInstance(EmpServiceImpl.class,
        new TestHandler(new MyAdvice(getEmp(req, resp).getEmpno())));
    List<Emp> res = empService.getMgrList();
    out.write(JSON.toJSONString(Result.success(res)));
  }

  protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    String str = req.getReader().readLine();
    Emp emp = JSONUtil.toBean(str, Emp.class);

    EmpService empService = (EmpService) ProxyFactory.getInstance(EmpServiceImpl.class,
        new TestHandler(new MyAdvice(emp.getEmpno())));
    Emp res = empService.selectOne(emp);

    if (res == null) {
      out.write(JSON.toJSONString(Result.error("-1", "登录失败")));
      return;
    }

    String token = JwtUtil.getToken(res);
    res.setToken(token);
    out.write(JSON.toJSONString(Result.success(res)));
  }

  protected void selectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    String currentPageStr = req.getParameter("currentPage");
    String pageSizeStr = req.getParameter("pageSize");
    int currentPage = 0;
    int pageSize = 5;
    if (!(currentPageStr == null || pageSizeStr == null)) {
      currentPage = Integer.valueOf(currentPageStr);
      pageSize = Integer.valueOf(pageSizeStr);
    }

    EmpService empService = new EmpServiceImpl();
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

    List<Emp> list = empService.selectPage(begin, pageSize);

    out.write(JSON.toJSONString(Result.success(new Page<Emp>(list, total, pageSize, currentPage))));
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getParameter("method");
    if ("login".equals(method)) {
      this.login(req, resp);
      return;
    } else if ("selectPage".equals(method)) {
      this.selectPage(req, resp);
      return;
    } else if ("getMgrList".equals(method)) {
      this.getMgrList(req, resp);
      return;
    } else if ("getJobList".equals(method)) {
      this.getJobList(req, resp);
      return;
    } else if ("add".equals(method)) {
      this.add(req, resp);
      return;
    } else if ("selectOne".equals(method)) {
      this.selectOne(req, resp);
      return;
    } else if ("update".equals(method)) {
      this.update(req, resp);
      return;
    } else if ("delete".equals(method)) {
      this.delete(req, resp);
      return;
    }
  }

}
