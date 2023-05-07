package com.bobo.servlet;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.bobo.common.Page;
import com.bobo.common.Result;
import com.bobo.entity.ShopCar;
import com.bobo.service.ShopCarService;
import com.bobo.service.ShopCarServiceImpl;
import com.bobo.utils.aop.MyAdvice;
import com.bobo.utils.aop.ProxyFactory;
import com.bobo.utils.aop.TestHandler;
import com.bobo.utils.jedis.JedisUtil;

@WebServlet("/shopCar")
public class ShopCarServlet extends BaseServlet {

  private ShopCarService shopCarService = new ShopCarServiceImpl();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getParameter("method");
    if ("selectPage".equals(method)) {
      this.selectPage(req, resp);
      return;
    } else if ("put".equals(method)) {
      this.put(req, resp);
      return;
    } else if ("update".equals(method)) {
      this.update(req, resp);
      return;
    } else if ("delete".equals(method)) {
      this.delete(req, resp);
      return;
    }
  }

  protected void put(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String customer_no = getCustomer(req, resp).getCustomer_no();
    String good_no = req.getParameter("good_no");
    String shop_no = req.getParameter("shop_no");

    JedisUtil jedisUtil = JedisUtil.getInstance();
    String redis_customer_no = "shopCar:" + customer_no;
    if (!jedisUtil.hexists(redis_customer_no, good_no + ":" + shop_no)) {
      this.add(req, resp);
      return;
    } else {
      this.update(req, resp);
      return;
    }
  }

  protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();

    String customer_no = getCustomer(req, resp).getCustomer_no();
    String good_no = req.getParameter("good_no");
    String shop_no = req.getParameter("shop_no");
    int num = Integer.valueOf(req.getParameter("num"));

    ShopCarService shopCarService = (ShopCarService) ProxyFactory.getInstance(ShopCarServiceImpl.class,
        new TestHandler(new MyAdvice(customer_no)));

    ShopCar shopCar = new ShopCar(customer_no, good_no, num, shop_no);

    long flag = shopCarService.add(shopCar);

    if (flag == 0) {
      out.write(JSON.toJSONString(Result.error("-1", "添加失败")));
      return;
    }
    out.write(JSON.toJSONString(Result.success()));
  }

  private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();

    String customer_no = getCustomer(req, resp).getCustomer_no();
    String good_no = req.getParameter("good_no");
    String shop_no = req.getParameter("shop_no");
    int num = Integer.valueOf(req.getParameter("num"));

    ShopCarService shopCarService = (ShopCarService) ProxyFactory.getInstance(ShopCarServiceImpl.class,
        new TestHandler(new MyAdvice(customer_no)));

    ShopCar shopCar = new ShopCar(customer_no, good_no, shop_no);
    shopCar = shopCarService.selectOne(shopCar);

    shopCar.setNum(shopCar.getNum() + num);

    long flag = shopCarService.update(shopCar);

    if (flag == 0) {
      out.write(JSON.toJSONString(Result.error("-1", "添加失败")));
      return;
    }
    out.write(JSON.toJSONString(Result.success()));
  }

  private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();

    String customer_no = getCustomer(req, resp).getCustomer_no();
    String good_no = req.getParameter("good_no");
    String shop_no = req.getParameter("shop_no");
 
    ShopCar shopCar = new ShopCar(customer_no, good_no, shop_no);

    ShopCarService shopCarService = (ShopCarService) ProxyFactory.getInstance(ShopCarServiceImpl.class,
        new TestHandler(new MyAdvice(customer_no)));

    long flag = shopCarService.delete(shopCar);

    if (flag == 0) {
      out.write(JSON.toJSONString(Result.error("-1", "删除失败")));
      return;
    }
    out.write(JSON.toJSONString(Result.success()));
  }

  protected void selectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();

    String currentPageStr = req.getParameter("currentPage");
    String pageSizeStr = req.getParameter("pageSize");

    int currentPage = 0;
    int pageSize = 10;

    if (!(currentPageStr == null || pageSizeStr == null)) {
      currentPage = Integer.valueOf(currentPageStr);
      pageSize = Integer.valueOf(pageSizeStr);
    }

    Page page = shopCarService.selectPage(currentPage, pageSize, getCustomer(req, resp).getCustomer_no());

    out.write(JSON.toJSONString(Result.success(page)));
  }
}
