package com.bobo.servlet;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bobo.entity.ShopCar;
import com.bobo.entity.ShopCarVO;
import com.bobo.service.ShopCarService;
import com.bobo.service.ShopCarServiceImpl;

@WebServlet("/shopCar")
public class ShopCarServlet extends HttpServlet {

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
      resp.sendRedirect("/shopwork/shopCar?method=selectPage");
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
    String shop_no = req.getParameter("shop_no");
    String url = "/shopwork/good?method=selectPage&shop_no=" + shop_no;
    resp.sendRedirect(url);
    // req.getRequestDispatcher(url).forward(req, resp);
  }

  protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String customer_no = req.getParameter("customer_no");
    String good_no = req.getParameter("good_no");
    String shop_no = req.getParameter("shop_no");
    Integer price = Integer.parseInt(req.getParameter("price"));
    ShopCar shopCar = new ShopCar(customer_no, good_no, 1, price, shop_no);
    shopCarService.add(shopCar);
    this.listToSession(req, resp);
  }

  private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String customer_no = req.getParameter("customer_no");
    String good_no = req.getParameter("good_no");
    Object obj = req.getAttribute("num");
    int num = (obj != null ? ((int) obj) : Integer.valueOf(req.getParameter("num")));
    ShopCar shopCar = new ShopCar(customer_no, good_no, num);
    shopCarService.update(shopCar);
    this.listToSession(req, resp);
  }

  protected ShopCar selectOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String customer_no = req.getParameter("customer_no");
    String good_no = req.getParameter("good_no");
    ShopCar shopCar = new ShopCar(customer_no, good_no);
    return shopCarService.selectOne(shopCar);
  }

  // protected long count(HttpServletRequest req, HttpServletResponse resp) throws
  // ServletException, IOException {
  // String customer_no = req.getParameter("customer_no");
  // String good_no = req.getParameter("good_no");
  // String shop_no = req.getParameter("shop_no");
  // Integer price = Integer.parseInt(req.getParameter("price"));
  // //
  // return 0;
  // }

  protected void listToSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = (String) req.getSession().getAttribute("username");
    List<ShopCarVO> shopCars = shopCarService.selectList(username);
    HttpSession session = req.getSession();
    session.setAttribute("shopCar", shopCars);
  }

  protected void selectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String currentPageStr = req.getParameter("currentPage");
    String pageSizeStr = req.getParameter("pageSize");
    int currentPage = 0;
    int pageSize = 10;
    if (!(currentPageStr == null || pageSizeStr == null)) {
      currentPage = Integer.valueOf(currentPageStr);
      pageSize = Integer.valueOf(pageSizeStr);
    }
    HttpSession session = req.getSession();
    Object obj = session.getAttribute("shopCar");
    if (obj == null) {
      this.listToSession(req, resp);
    }

    List<ShopCarVO> shopCars = (List<ShopCarVO>) session.getAttribute("shopCar");

    long total = shopCars.size();
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
    req.setAttribute("list", list);
    req.setAttribute("total", total);
    req.setAttribute("currentPage", currentPage);
    req.setAttribute("pageSize", pageSize);
    req.getRequestDispatcher("/shopCarList.jsp").forward(req, resp);
  }
}
