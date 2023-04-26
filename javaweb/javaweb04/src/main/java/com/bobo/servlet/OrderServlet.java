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
import com.bobo.common.Result;
import com.bobo.entity.ShopCarVO;
import com.bobo.service.OrderDetailService;
import com.bobo.service.OrderDetailServiceImpl;
import com.bobo.service.OrderService;
import com.bobo.service.OrderServiceImpl;
import com.bobo.service.ShopCarService;
import com.bobo.service.ShopCarServiceImpl;
import com.bobo.service.StockService;
import com.bobo.service.StockServiceImpl;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

  private OrderService orderService = new OrderServiceImpl();
  private ShopCarService shopCarService = new ShopCarServiceImpl();
  private OrderDetailService orderDetailService = new OrderDetailServiceImpl();
  private StockService stockService = new StockServiceImpl();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getParameter("method");
    if ("add".equals(method)) {
      this.add(req, resp);
      return;
    }
  }

  protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    HttpSession session = req.getSession();
    String customer_no = (String) session.getAttribute("customerNo");

    // 获取需要购买的商品NO数组
    String[] goodsArr = req.getParameter("good").split(",");

    int flag = orderService.addArr(customer_no, goodsArr);

    List<ShopCarVO> shopCar = shopCarService.selectList(customer_no);
    session.setAttribute("shopCar", shopCar);

    out.write(JSON.toJSONString(Result.success(flag)));
  }

}
 