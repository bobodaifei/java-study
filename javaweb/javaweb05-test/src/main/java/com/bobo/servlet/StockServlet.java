package com.bobo.servlet;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.bobo.common.Page;
import com.bobo.common.Result;
import com.bobo.entity.Good;
import com.bobo.entity.Stock;
import com.bobo.entity.StockVO;
import com.bobo.entity.User;
import com.bobo.service.StockService;
import com.bobo.utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/stock")
public class StockServlet extends BaseServlet {


  private StockService stockService = BeanFactory.getBean(StockService.class);

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getParameter("method");
    if ("selectPage".equals(method)) {
      this.selectPage(req, resp);
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
    User user = getUser(req, resp);
    String shop_no = user.getShop().getShop_no();
    List<Good> res = stockService.absentList(shop_no);

    out.write(JSON.toJSONString(Result.success(res))); 
  }
 
  protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();

    User user = getUser(req, resp);

    String shop_no = user.getShop().getShop_no();
    String str = req.getReader().readLine();
    Stock stock_ = JSONUtil.toBean(str, Stock.class);
    stock_.setShop_no(shop_no);

    int res = stockService.insert(stock_);
    if (res == 0) {
      out.write(JSON.toJSONString(Result.error("-1", "删除失败")));
      return;
    }
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
    out.write(JSON.toJSONString(Result.success()));
  }

  protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();

    String str = req.getReader().readLine();
    Stock stock_ = JSONUtil.toBean(str, Stock.class);

    int res = stockService.update(stock_);

    if (res == 0) {
      out.write(JSON.toJSONString(Result.error("-1", "删除失败")));
      return;
    }
    out.write(JSON.toJSONString(Result.success()));
  }

  protected void selectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    User user = getUser(req, resp);
    String shop_no = user.getShop().getShop_no();
    String currentPageStr = req.getParameter("currentPage");
    String pageSizeStr = req.getParameter("pageSize");

    long currentPage = 0;
    long pageSize = 10;
    if (!(currentPageStr == null || pageSizeStr == null)) {
      currentPage = Long.valueOf(currentPageStr);
      pageSize = Long.valueOf(pageSizeStr);
    }

    Page<StockVO> res = stockService.selectPage(currentPage, pageSize, shop_no);

    out.write(JSON.toJSONString(Result.success(res)));
  }

}
