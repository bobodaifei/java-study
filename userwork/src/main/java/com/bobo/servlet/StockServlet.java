package com.bobo.servlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
      req.getRequestDispatcher("/updateStock.jsp").forward(req, resp);
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

  protected void listToSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    String shop_no = (String) session.getAttribute("shopNo");
    List<StockVO> list = stockService.selectList(shop_no);
    session.setAttribute("stockList", list);
  }

  protected void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    String shop_no = (String) session.getAttribute("shopNo");
    List<Good> list = stockService.absentList(shop_no);
    req.setAttribute("list", list);
    req.getRequestDispatcher("/insertStock.jsp").forward(req, resp);
  }

  protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    String shop_no = (String) session.getAttribute("shopNo");
    String good_no = req.getParameter("good_no");
    int is_online = Integer.valueOf(req.getParameter("is_online"));
    int stock = Integer.valueOf(req.getParameter("stock"));
    Stock stock_ = new Stock(good_no, shop_no, is_online, stock);
    stockService.insert(stock_);
    this.listToSession(req, resp);
    resp.sendRedirect("/userwork/stock?method=selectPage");
  }

  protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String good_no = req.getParameter("good_no");
    stockService.delete(good_no);
    this.listToSession(req, resp);
    resp.sendRedirect("/userwork/stock?method=selectPage");
  }

  protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String good_no = req.getParameter("good_no");
    int is_online = Integer.valueOf(req.getParameter("is_online"));
    int stock = Integer.valueOf(req.getParameter("stock"));
    Stock stock_ = new Stock(good_no, is_online, stock);
    stockService.update(stock_);
    this.listToSession(req, resp);
    resp.sendRedirect("/userwork/stock?method=selectPage");
  }

  protected void selectOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String good_no = req.getParameter("good_no");
    StockVO stockVO = stockService.selectOne(good_no);
    req.setAttribute("stock", stockVO);
  }

  protected void selectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String currentPageStr = req.getParameter("currentPage");
    String pageSizeStr = req.getParameter("pageSize");
    HttpSession session = req.getSession();
    String shop_no = (String) session.getAttribute("shopNo");
    Object stockListObj = session.getAttribute("stockList");
    if (stockListObj==null) {
      this.listToSession(req, resp);
    }
    List<StockVO> stockList = (List<StockVO>) session.getAttribute("stockList");

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
    List<StockVO> stocks = stockList.stream().skip(begin).limit(begin + pageSize).collect(Collectors.toList());
    // List<StockVO> list = stockService.selectPage(begin, pageSize, shop_no);

    req.setAttribute("stocks", stocks);
    req.setAttribute("total", total);
    req.setAttribute("currentPage", currentPage);
    req.setAttribute("pageSize", pageSize);

    req.getRequestDispatcher("/stockList.jsp").forward(req, resp);
  }

}
