package com.bobo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.bobo.common.Page;
import com.bobo.common.Result;
import com.bobo.entity.OrderVO;
import com.bobo.service.OrderService;
import com.bobo.service.OrderServiceImpl;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

  private OrderService orderService = new OrderServiceImpl();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getParameter("method");
    if ("selectPage".equals(method)) {
      this.selectPage(req, resp);
      return;
    } else if ("selectOne".equals(method)) {
      this.selectOne(req, resp);
      return;
    }
  }

  protected void selectOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    String order_no = req.getParameter("order_no");
    OrderVO orderVO = orderService.selectOne(order_no);
    out.write(JSON.toJSONString(Result.success(orderVO)));
  }

  protected void selectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    PrintWriter out = resp.getWriter();

    String currentPageStr = req.getParameter("currentPage");
    String pageSizeStr = req.getParameter("pageSize");
    String shop_no = (String) session.getAttribute("shopNo");

    long total = orderService.selectCount(shop_no);
    int currentPage = 0;
    int pageSize = 10;

    if (!(currentPageStr == null || pageSizeStr == null)) {
      currentPage = Integer.valueOf(currentPageStr);
      pageSize = Integer.valueOf(pageSizeStr);
    }

    int begin = (currentPage - 1) * pageSize;

    if (begin >= total) {
      currentPage--;
      begin = begin - pageSize;
    }
    if (currentPage < 1) {
      currentPage = 1;
      begin = 0;
    }
    List<OrderVO> list = orderService.selectPage(begin, pageSize, shop_no);

    out.write(JSON.toJSONString(Result.success(new Page<OrderVO>(list, total, pageSize, currentPage))));
  }

}
