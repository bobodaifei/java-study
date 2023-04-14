package com.bobo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bobo.entity.Customer;
import com.bobo.service.CustomerService;
import com.bobo.service.CustomerServiceImpl;

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
    Customer customer = new Customer();
    customer.setCustomer_no(req.getParameter("username"));
    customer.setPassword(req.getParameter("password"));
    Customer res = customerService.login(customer);
    if (res == null) {
      resp.sendRedirect("/shopwork/login.jsp");
      return;
    }
    HttpSession session = req.getSession();
    session.setAttribute("username", res.getCustomer_no());

    Cookie cookie = new Cookie("username", res.getCustomer_no());
    resp.addCookie(cookie);
    req.getRequestDispatcher("/shop?method=selectPage").forward(req, resp);
  }
}
