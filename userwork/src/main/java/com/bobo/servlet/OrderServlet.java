package com.bobo.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    }else if ("selectOne".equals(method)) {
      this.selectOne(req, resp);
      return;
    }
  }

  protected void selectOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String order_no = req.getParameter("order_no");
    OrderVO orderVO = orderService.selectOne(order_no);
    req.setAttribute("order", orderVO);
  }

  protected void selectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String currentPageStr = req.getParameter("currentPage");
    String pageSizeStr = req.getParameter("pageSize");
    int currentPage = 0;
    int pageSize = 10;
    if (!(currentPageStr == null || pageSizeStr == null)) {
      currentPage = Integer.valueOf(currentPageStr);
      pageSize = Integer.valueOf(pageSizeStr);
    }
    HttpSession session = req.getSession();
    String shop_no = (String) session.getAttribute("shopNo");
    String status = req.getParameter("status");
    long total = 0;
    if (status == null) {
      total = orderService.selectCount(shop_no);
    } else {
      total = orderService.selectCount(shop_no, status);
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
    List<OrderVO> list = null;

    if (status == null) {
      list = orderService.selectPage(begin, pageSize, shop_no);
    } else {
      list = orderService.selectPage(begin, pageSize, shop_no, status);
    }

    req.setAttribute("list", list);
    req.setAttribute("total", total);
    req.setAttribute("currentPage", currentPage);
    req.setAttribute("pageSize", pageSize);
    req.setAttribute("status", status);
    req.getRequestDispatcher("/orderList.jsp").forward(req, resp);
  }

  

}
