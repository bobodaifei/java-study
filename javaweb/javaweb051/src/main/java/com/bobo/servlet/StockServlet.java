package com.bobo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.bobo.common.Page;
import com.bobo.common.Result;
import com.bobo.entity.Good;
import com.bobo.entity.Stock;
import com.bobo.entity.StockVO;
import com.bobo.service.StockService;
import com.bobo.service.StockServiceImpl;

@WebServlet("/stock")
public class StockServlet extends HttpServlet {

  private StockService stockService = new StockServiceImpl();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getParameter("method");
    if ("selectPage".equals(method)) {
      this.selectPage(req, resp);
      return;
    } else if ("selectOne".equals(method)) {
      this.selectOne(req, resp);
      return;
    } else if ("update".equals(method)) {
      this.update(req, resp);
      return;
    } else if ("delete".equals(method)) {
      this.delete(req, resp);
      return;
    } else if ("add".equals(method)) {
      this.add(req, resp);
      return;
    } else if ("toAdd".equals(method)) {
      this.toAdd(req, resp);
      return;
    }
  }

  protected void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    HttpSession session = req.getSession();
    String shop_no = (String) session.getAttribute("shopNo");
    List<Good> res = stockService.absentList(shop_no);

    out.write(JSON.toJSONString(Result.success(res)));
  }

  protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    HttpSession session = req.getSession();

    String shop_no = (String) session.getAttribute("shopNo");
    String good_no = req.getParameter("good_no");
    int is_online = Integer.valueOf(req.getParameter("is_online"));
    int stock = Integer.valueOf(req.getParameter("stock"));

    Stock stock_ = new Stock(good_no, shop_no, is_online, stock);
    int res = stockService.insert(stock_);

    if (res == 0) {
      out.write(JSON.toJSONString(Result.error("-1", "删除失败")));
      return;
    }
    this.listToSession(req, resp);
    out.write(JSON.toJSONString(Result.success()));
  }

  protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    String good_no = req.getParameter("good_no");
    int res = stockService.delete(good_no);
    if (res == 0) {
      out.write(JSON.toJSONString(Result.error("-1", "删除失败")));
      return;
    }
    this.listToSession(req, resp);
    out.write(JSON.toJSONString(Result.success()));
  }

  protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();

    String good_no = req.getParameter("good_no");
    int is_online = Integer.valueOf(req.getParameter("is_online"));
    int stock = Integer.valueOf(req.getParameter("stock"));

    Stock stock_ = new Stock(good_no, is_online, stock);
    int res = stockService.update(stock_);

    if (res == 0) {
      out.write(JSON.toJSONString(Result.error("-1", "删除失败")));
      return;
    }
    this.listToSession(req, resp);
    out.write(JSON.toJSONString(Result.success()));
  }

  protected void selectOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    String good_no = req.getParameter("good_no");
    StockVO res = stockService.selectOne(good_no);
    out.write(JSON.toJSONString(Result.success(res)));
  }

  protected void listToSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    String shop_no = (String) session.getAttribute("shopNo");

    List<StockVO> list = stockService.selectList(shop_no);
    session.setAttribute("stockList", list);
    
  }

  protected void selectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    HttpSession session = req.getSession();
    
    List<StockVO> stockList = (List<StockVO>) session.getAttribute("stockList");
    if (stockList == null) {
      listToSession(req, resp);
      stockList = (List<StockVO>) session.getAttribute("stockList");
    }

    

    String currentPageStr = req.getParameter("currentPage");
    String pageSizeStr = req.getParameter("pageSize");

    long total = stockList.size();
    int currentPage = 0;
    int pageSize = 10;

    if (!(currentPageStr == null || pageSizeStr == null)) {
      currentPage = Integer.valueOf(currentPageStr);
      pageSize = Integer.valueOf(pageSizeStr);
    }

    int begin = (currentPage - 1) * pageSize;
    if (begin >= total) {
      currentPage--;
      begin = begin - pageSize;
    }
    if (currentPage < 1) {
      currentPage = 1;
      begin = 0;
    }

    List<StockVO> res = stockList.stream().skip(begin).limit(begin + pageSize).collect(Collectors.toList());

    out.write(JSON.toJSONString(Result.success(new Page<StockVO>(res, total, pageSize, currentPage))));
  }

}
