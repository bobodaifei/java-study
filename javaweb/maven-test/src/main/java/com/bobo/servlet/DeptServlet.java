package com.bobo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.bobo.common.Result;
import com.bobo.entity.Dept;
import com.bobo.service.DeptService;
import com.bobo.service.DeptServiceImpl;
// import com.bobo.utils.AffairsHandler;
// import com.bobo.utils.ProxyFactory;
import com.bobo.utils.aop.MyAdvice;
import com.bobo.utils.aop.ProxyFactory;
import com.bobo.utils.aop.TestHandler;

@WebServlet("/dept")
public class DeptServlet extends BaseServlet {
  // private DeptService deptService = new DeptServiceImpl();
  // private DeptService deptService = (DeptService) ProxyFactory.getInstance(DeptServiceImpl.class, new AffairsHandler());

  protected void getDeptList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();

    DeptService deptService = (DeptService) ProxyFactory.getInstance(DeptServiceImpl.class, 
        new TestHandler(new MyAdvice(getEmp(req, resp).getEmpno())));
    List<Dept> res = deptService.getDeptList();
    // List<Dept> res = (List<Dept>) new Proxy_<DeptServiceImpl>(DeptServiceImpl.class, "getDeptList").newProxyInstance();
    out.write(JSON.toJSONString(Result.success(res)));
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getParameter("method");
    if ("getDeptList".equals(method)) {
      this.getDeptList(req, resp);
      return;
    }
  }

  
}
