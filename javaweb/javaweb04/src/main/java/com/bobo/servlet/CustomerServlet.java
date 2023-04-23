package com.bobo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.bobo.common.Result;
import com.bobo.entity.Customer;
import com.bobo.service.CustomerService;
import com.bobo.service.CustomerServiceImpl;
// import com.bobo.utils.JwtUtil;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {

  private CustomerService customerService = new CustomerServiceImpl();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getParameter("method");
    if ("login".equals(method)) {
      this.login(req, resp);
      return;
    }
  }

  protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    Customer customer = new Customer();
    customer.setCustomer_no(req.getParameter("customer_no"));
    customer.setPassword(req.getParameter("password"));
    Customer res = customerService.login(customer);
    if (res == null) {
      out.write(JSON.toJSONString(Result.error("-1", "登录失败")));
      return;
    }
    
    // String token = JwtUtil.getToken(res);
    // res.setToken(token);
    HttpSession session = req.getSession();
    session.setAttribute("customerNo", res.getCustomer_no());
    
    Cookie cookie = new Cookie("customer_no", res.getCustomer_no());
    resp.addCookie(cookie);
    
    out.write(JSON.toJSONString(Result.success(res)));
  }
}
