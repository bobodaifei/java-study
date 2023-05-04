package com.bobo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.bobo.common.Page;
import com.bobo.common.Result;
import com.bobo.entity.OrderDetail;
import com.bobo.entity.OrderDetailVO;
import com.bobo.entity.OrderVO;
import com.bobo.entity.StockVO;
import com.bobo.service.OrderDetailService;
import com.bobo.service.OrderDetailServiceImpl;
import com.bobo.service.OrderService;
import com.bobo.service.OrderServiceImpl;
import com.bobo.service.StockService;
import com.bobo.service.StockServiceImpl;

@WebServlet("/orderDetail")
public class OrderDetailServlet extends HttpServlet {

  private OrderDetailService orderDetailService = new OrderDetailServiceImpl();

  private OrderService orderService = new OrderServiceImpl();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getParameter("method");
    if ("selectPage".equals(method)) {
      String order_no = req.getParameter("order_no");
      OrderVO orderVO = orderService.selectOne(order_no);
      req.setAttribute("order", orderVO);
      this.selectPage(req, resp);
      return;
    } else if ("selectPageByGoodNo".equals(method)) {
      this.selectPageByGoodNo(req, resp);
      return;
    }
  }

  protected void selectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    String currentPageStr = req.getParameter("currentPage");
    String pageSizeStr = req.getParameter("pageSize");
    String order_no = req.getParameter("order_no");

    int currentPage = 0;
    int pageSize = 10;

    if (!(currentPageStr == null || pageSizeStr == null)) {
      currentPage = Integer.valueOf(currentPageStr);
      pageSize = Integer.valueOf(pageSizeStr);
    }
    long total = orderDetailService.selectCount(order_no);
    int begin = (currentPage - 1) * pageSize;

    if (begin >= total) {
      currentPage--;
      begin = begin - pageSize;
    }
    if (currentPage < 1) {
      currentPage = 1;
      begin = 0;
    }

    List<OrderDetail> list = orderDetailService.selectPage(begin, pageSize, order_no);

    out.write(JSON.toJSONString(Result.success(new Page<OrderDetail>(list, total, pageSize, currentPage))));
  }


  protected void selectPageByGoodNo(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    PrintWriter out = resp.getWriter();
    
    String currentPageStr = req.getParameter("currentPage");
    String pageSizeStr = req.getParameter("pageSize");
    String good_no = req.getParameter("good_no");

    int currentPage = 0;
    int pageSize = 10;
    
    if (!(currentPageStr == null || pageSizeStr == null)) {
      currentPage = Integer.valueOf(currentPageStr);
      pageSize = Integer.valueOf(pageSizeStr);
    }

    long total = orderDetailService.selectCountByGoodNo(good_no);

    int begin = (currentPage - 1) * pageSize;

    if (begin >= total) {
      currentPage--;
      begin = begin - pageSize;
    }
    if (currentPage < 1) {
      currentPage = 1;
      begin = 0;
    }

    List<OrderDetailVO> list = orderDetailService.selectPageByGoodNo(begin, pageSize, good_no);
    out.write(JSON.toJSONString(Result.success(new Page<OrderDetailVO>(list,total,pageSize,currentPage))));
  }

}
