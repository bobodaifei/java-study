package com.bobo.servlet;

import com.bobo.entity.Customer;
import com.bobo.service.CustomerService;
import com.bobo.service.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

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
    customer.setCustomer_no(req.getParameter("customer_no"));
    customer.setPassword(req.getParameter("password"));
    Customer res = customerService.login(customer);
    if (res == null) {
      resp.sendRedirect("/shopwork/login.jsp");
      return;
    }
    HttpSession session = req.getSession();
    session.setAttribute("customerNo", res.getCustomer_no());

    Cookie cookie = new Cookie("customer_no", res.getCustomer_no());
    resp.addCookie(cookie);
    req.getRequestDispatcher("/shop?method=selectPage").forward(req, resp);
  }
}