package com.bobo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bobo.entity.Good;
import com.bobo.service.GoodService;
import com.bobo.service.GoodServiceImpl;
import com.bobo.service.StockService;
import com.bobo.service.StockServiceImpl;

@WebServlet("/good")
public class GoodServlet extends HttpServlet {

  private GoodService goodService = new GoodServiceImpl();
  private StockService stockService = new StockServiceImpl();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getParameter("method");
    if ("selectPage".equals(method)) {
      this.selectPage(req, resp);
      return;
    }
  }

  protected void selectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String currentPageStr = req.getParameter("currentPage");
    String pageSizeStr = req.getParameter("pageSize");
    String shop_no = req.getParameter("shop_no");
    int currentPage = 0;
    int pageSize = 10;
    if (!(currentPageStr == null || pageSizeStr == null)) {
      currentPage = Integer.valueOf(currentPageStr);
      pageSize = Integer.valueOf(pageSizeStr);
    }
    long total = stockService.selectCount(shop_no);
    
    int begin = (currentPage - 1) * pageSize;

    if (begin >= total) {
      currentPage--;
      begin = begin - pageSize;
    }
    if (currentPage < 1) {
      currentPage = 1;
      begin = 0;
    }
    List<Good> list = null;
    if (shop_no!=null) {
      list = goodService.selectPage(begin, pageSize, shop_no);
    }else{
      list = goodService.selectPage(begin, pageSize);
    }

    // List<Good> list = goodService.selectPage(begin, pageSize, shopNo);

    req.setAttribute("list", list);
    req.setAttribute("total", total);
    req.setAttribute("currentPage", currentPage);
    req.setAttribute("pageSize", pageSize);
    req.setAttribute("shop_no", shop_no);
  
    req.getRequestDispatcher("/goodList.jsp").forward(req, resp);
  }
}
