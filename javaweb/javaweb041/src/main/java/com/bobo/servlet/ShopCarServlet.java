package com.bobo.servlet;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.bobo.common.Page;
import com.bobo.common.Result;
import com.bobo.entity.Good;
import com.bobo.entity.ShopCar;
import com.bobo.entity.ShopCarVO;
import com.bobo.service.GoodService;
import com.bobo.service.GoodServiceImpl;
import com.bobo.service.ShopCarService;
import com.bobo.service.ShopCarServiceImpl;

@WebServlet("/shopCar")
public class ShopCarServlet extends HttpServlet {

  private ShopCarService shopCarService = new ShopCarServiceImpl();
  private GoodService goodService = new GoodServiceImpl();

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
    }else if ("refreshPage".equals(method)) {
      this.listToSession(req, resp);
      return;
    }
  }

  protected void put(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ShopCar shopCar = this.selectOne(req, resp);
    if (shopCar == null) {
      this.add(req, resp);
    } else {
      req.setAttribute("num", shopCar.getNum() + 1);
      this.update(req, resp);
    }
  }

  protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    HttpSession session = req.getSession();

    String customer_no = (String) session.getAttribute("customerNo");
    String good_no = req.getParameter("good_no");
    String shop_no = req.getParameter("shop_no");

    Good good = goodService.selectById(good_no);

    Integer price = good.getPrice();
    ShopCar shopCar = new ShopCar(customer_no, good_no, 1, price, shop_no);
    
    int flag = shopCarService.add(shopCar);

    if (flag==0) {
      out.write(JSON.toJSONString(Result.error("-1","添加失败")));
      return;
    }
    this.listToSession(req, resp);
    out.write(JSON.toJSONString(Result.success()));
  }

  private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    HttpSession session = req.getSession();

    String customer_no = (String) session.getAttribute("customerNo");
    String good_no = req.getParameter("good_no");
    Object obj = req.getAttribute("num");

    int num = (obj != null ? ((int) obj) : Integer.valueOf(req.getParameter("num")));
    
    ShopCar shopCar = new ShopCar(customer_no, good_no, num);
    
    int flag = shopCarService.update(shopCar);

    if (flag == 0) {
      out.write(JSON.toJSONString(Result.error("-1", "添加失败")));
      return; 
    }
    this.listToSession(req, resp);
    out.write(JSON.toJSONString(Result.success()));
  }

  private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    HttpSession session = req.getSession();
    
    String customer_no = (String) session.getAttribute("customerNo");
    String good_no = req.getParameter("good_no");

    ShopCar shopCar = new ShopCar(customer_no, good_no);
    
    int flag = shopCarService.delete(shopCar);
    
    if (flag == 0) {
      out.write(JSON.toJSONString(Result.error("-1", "删除失败")));
      return;
    } 
    this.listToSession(req, resp);
    out.write(JSON.toJSONString(Result.success()));
  }

  protected ShopCar selectOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    
    String customer_no = (String)session.getAttribute("customerNo");
    String good_no = req.getParameter("good_no");
    
    ShopCar shopCar = new ShopCar(customer_no, good_no);
    
    return shopCarService.selectOne(shopCar);
  }

  protected void listToSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();

    String customer_no = (String) req.getSession().getAttribute("customerNo");
    
    List<ShopCarVO> shopCar = shopCarService.selectList(customer_no);
    session.setAttribute("shopCar", shopCar);
    
  }

  protected void selectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    HttpSession session = req.getSession();

    List<ShopCarVO> shopCars = (List<ShopCarVO>) session.getAttribute("shopCar");
    String currentPageStr = req.getParameter("currentPage");
    String pageSizeStr = req.getParameter("pageSize");

    if (shopCars == null) {
      this.listToSession(req, resp);
      shopCars = (List<ShopCarVO>) session.getAttribute("shopCar");
    }

    long total = shopCars.size();
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
    List<ShopCarVO> list = shopCars.stream().skip(begin).limit(begin + pageSize).collect(Collectors.toList());
    
    out.write(JSON.toJSONString(Result.success(new Page<ShopCarVO>(list,total,pageSize,currentPage))));
  }
}
