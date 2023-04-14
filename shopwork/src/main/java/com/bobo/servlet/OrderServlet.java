package com.bobo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bobo.service.OrderService;
import com.bobo.service.OrderServiceImpl;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

  private OrderService orderService = new OrderServiceImpl();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getParameter("method");
    if ("submit".equals(method)) {
      this.submit(req, resp);
      return;
    }
  }

  protected void submit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String customer_no = req.getParameter("customer_no");
    String good_no = req.getParameter("good_no");
    
  }
}
