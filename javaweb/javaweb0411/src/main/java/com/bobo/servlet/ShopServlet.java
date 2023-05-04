package com.bobo.servlet;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.bobo.common.Page;
import com.bobo.common.Result;
import com.bobo.entity.Shop;
import com.bobo.service.ShopService;
import com.bobo.service.ShopServiceImpl;
import com.bobo.utils.aop.MyAdvice;
import com.bobo.utils.aop.ProxyFactory;
import com.bobo.utils.aop.TestHandler;

@WebServlet("/shop")
public class ShopServlet extends HttpServlet {

  // private ShopService shopService = new ShopServiceImpl();

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
    String currentPageStr = req.getParameter("currentPage");
    String pageSizeStr = req.getParameter("pageSize");

    ShopService shopService = (ShopService) ProxyFactory.getInstance(ShopServiceImpl.class,
        new TestHandler(new MyAdvice(customer_no)));

    int currentPage = 0;
    int pageSize = 10;
    if (!(currentPageStr == null || pageSizeStr == null)) {
      currentPage = Integer.valueOf(currentPageStr);
      pageSize = Integer.valueOf(pageSizeStr);
    }
    long total = shopService.selectCount();
    int begin = (currentPage - 1) * pageSize;

    if (begin >= total) {
      currentPage--;
      begin = begin - pageSize;
    }
    if (currentPage < 1) {
      currentPage = 1;
      begin = 0;
    }

    List<Shop> list = shopService.selectPage(begin, pageSize);

    out.write(JSON.toJSONString(Result.success(new Page<Shop>(list, total, pageSize, currentPage))));
  }
}
