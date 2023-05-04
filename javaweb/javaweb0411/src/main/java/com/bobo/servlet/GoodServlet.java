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
import com.bobo.entity.Good;
import com.bobo.service.GoodService;
import com.bobo.service.GoodServiceImpl;
import com.bobo.service.StockService;
import com.bobo.service.StockServiceImpl;
import com.bobo.utils.aop.MyAdvice;
import com.bobo.utils.aop.ProxyFactory;
import com.bobo.utils.aop.TestHandler;

@WebServlet("/good")
public class GoodServlet extends HttpServlet {

  // private GoodService goodService = new GoodServiceImpl();
  // private StockService stockService = new StockServiceImpl();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getParameter("method");
    if ("selectPage".equals(method)) {
      this.selectPage(req, resp);
      return;
    }
  }

  protected void selectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    String customer_no = (String) req.getSession().getAttribute("customerNo");
    StockService stockService = (StockService) ProxyFactory.getInstance(StockServiceImpl.class,
        new TestHandler(new MyAdvice(customer_no)));
    GoodService goodService = (GoodService) ProxyFactory.getInstance(GoodServiceImpl.class,
        new TestHandler(new MyAdvice(customer_no)));

    String currentPageStr = req.getParameter("currentPage");
    String pageSizeStr = req.getParameter("pageSize");
    String shop_no = req.getParameter("shop_no");
    long currentPage = 0;
    long pageSize = 10;
    if (!(currentPageStr == null || pageSizeStr == null)) {
      currentPage = Integer.valueOf(currentPageStr);
      pageSize = Integer.valueOf(pageSizeStr);
    }
    long total = stockService.selectCount(shop_no);

    long begin = (currentPage - 1) * pageSize;

    if (begin >= total) {
      currentPage--;
      begin = begin - pageSize;
    }
    if (currentPage < 1) {
      currentPage = 1;
      begin = 0;
    }
    List<Good> list = goodService.selectPage(begin, pageSize, shop_no);

    out.write(JSON.toJSONString(Result.success(new Page<Good>(list, total, pageSize, currentPage))));
  }
}
