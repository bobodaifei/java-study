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
import com.bobo.service.OrderService;
import com.bobo.service.OrderServiceImpl;
import com.bobo.service.ShopCarService;
import com.bobo.service.ShopCarServiceImpl;
import com.bobo.utils.aop.MyAdvice;
import com.bobo.utils.aop.ProxyFactory;
import com.bobo.utils.aop.TestHandler;


@WebServlet("/order")
public class OrderServlet extends BaseServlet {

  // private OrderService orderService = new OrderServiceImpl();
  // private ShopCarService shopCarService = new ShopCarServiceImpl();

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
    String customer_no = getCustomer(req, resp).getCustomer_no();

    OrderService orderService = (OrderService) ProxyFactory.getInstance(OrderServiceImpl.class,
        new TestHandler(new MyAdvice(customer_no)));

    // 获取需要购买的商品NO数组
    String[] goodsArr = req.getParameter("good").split(",");
    
    int flag = orderService.addArr(customer_no, goodsArr);

    out.write(JSON.toJSONString(Result.success(flag)));
  }

}
 