package com.bobo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bobo.entity.OrderDetail;
import com.bobo.entity.OrderDetailVO;
import com.bobo.entity.OrderVO;
import com.bobo.service.OrderDetailService;
import com.bobo.service.OrderDetailServiceImpl;
import com.bobo.service.OrderService;
import com.bobo.service.OrderServiceImpl;

@WebServlet("/orderDetail")
public class OrderDetailServlet extends HttpServlet {

  private OrderDetailService orderDetailService = new OrderDetailServiceImpl();

  private OrderService orderService = new OrderServiceImpl();

  private StockServlet stockServlet = new StockServlet();

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
      this.stockServlet.selectOne(req, resp);
      this.selectPageByGoodNo(req, resp);
      return;
    }
  }

  protected void selectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    req.setAttribute("list", list);
    req.setAttribute("total", total);
    req.setAttribute("currentPage", currentPage);
    req.setAttribute("pageSize", pageSize);
    req.setAttribute("order_no", order_no);
    req.getRequestDispatcher("/orderDetailList.jsp").forward(req, resp);
  }


  protected void selectPageByGoodNo(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
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

    req.setAttribute("list", list);
    req.setAttribute("total", total);
    req.setAttribute("currentPage", currentPage);
    req.setAttribute("pageSize", pageSize);
    req.setAttribute("good_no", good_no);
    req.getRequestDispatcher("/goodOrderDetail.jsp").forward(req, resp);
  }

}
