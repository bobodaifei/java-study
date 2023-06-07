package com.bobo.servlet;

import com.alibaba.fastjson.JSON;
import com.bobo.common.Page;
import com.bobo.common.Result;
import com.bobo.entity.OrderDetail;
import com.bobo.entity.OrderDetailVO;
import com.bobo.entity.OrderVO;
import com.bobo.service.OrderDetailService;
import com.bobo.service.OrderService;
import com.bobo.utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/orderDetail")
public class OrderDetailServlet extends BaseServlet {

  private OrderDetailService orderDetailService = BeanFactory.getBean(OrderDetailService.class);

  private OrderService orderService = BeanFactory.getBean(OrderService.class);

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
    
    Page<OrderDetail> res = orderDetailService.selectPage(currentPage, pageSize, order_no);

    out.write(JSON.toJSONString(Result.success(res)));
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

    Page<OrderDetailVO> res = orderDetailService.selectPageByGoodNo(currentPage, pageSize, good_no);
    out.write(JSON.toJSONString(Result.success(res)));
  }

}
