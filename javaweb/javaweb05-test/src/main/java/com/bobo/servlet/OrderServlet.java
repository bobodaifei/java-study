package com.bobo.servlet;

import com.alibaba.fastjson.JSON;
import com.bobo.common.Page;
import com.bobo.common.Result;
import com.bobo.entity.OrderVO;
import com.bobo.service.OrderService;
import com.bobo.utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/order")
public class OrderServlet extends BaseServlet {

  private OrderService orderService =  BeanFactory.getBean(OrderService.class);

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
    PrintWriter out = resp.getWriter();
    String currentPageStr = req.getParameter("currentPage");
    String pageSizeStr = req.getParameter("pageSize");
    // String status = req.getParameter("status");
    String shop_no = getUser(req, resp).getShop().getShop_no();

    int currentPage = 0;
    int pageSize = 10;
    if (!(currentPageStr == null || pageSizeStr == null)) {
      currentPage = Integer.valueOf(currentPageStr);
      pageSize = Integer.valueOf(pageSizeStr);
    }

    Page<OrderVO> res = orderService.selectPage(currentPage, pageSize, shop_no);

    out.write(JSON.toJSONString(Result.success(res)));
  }

}
