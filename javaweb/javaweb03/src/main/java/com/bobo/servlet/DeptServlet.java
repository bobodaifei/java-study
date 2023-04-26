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

@WebServlet("/dept")
public class DeptServlet extends BaseServlet {
  private DeptService deptService = new DeptServiceImpl();

  protected void getDeptList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    List<Dept> res = deptService.getDeptList();
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
