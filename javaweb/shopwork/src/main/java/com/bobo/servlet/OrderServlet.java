package com.bobo.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bobo.entity.Order;
import com.bobo.entity.OrderDetail;
import com.bobo.entity.OrderVO;
import com.bobo.entity.ShopCar;
import com.bobo.entity.Stock;
import com.bobo.service.OrderDetailService;
import com.bobo.service.OrderDetailServiceImpl;
import com.bobo.service.OrderService;
import com.bobo.service.OrderServiceImpl;
import com.bobo.service.ShopCarService;
import com.bobo.service.ShopCarServiceImpl;
import com.bobo.service.StockService;
import com.bobo.service.StockServiceImpl;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

  private OrderService orderService = new OrderServiceImpl();
  private ShopCarService shopCarService = new ShopCarServiceImpl();
  private OrderDetailService orderDetailService = new OrderDetailServiceImpl();
  private StockService stockService = new StockServiceImpl();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getParameter("method");
    if ("add".equals(method)) {
      this.add(req, resp);
      resp.sendRedirect("/shopwork/shopCar?method=refreshPage");
      return;
    } else if ("selectPage".equals(method)) {
      this.selectPage(req, resp);
      return;
    }
  }

  protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    String customer_no = (String) session.getAttribute("customerNo");
    //获取需要购买的商品NO数组
    String[] goodsArr = req.getParameterValues("good");
    //最终可购买的购物车商品
    List<ShopCar> goodList = new ArrayList<>();
    //遍历商品NO数组
    for (String goodStr : goodsArr) {
      // 通过商品NO数组获取用户购物车的完整信息
      ShopCar shopCar = shopCarService.selectOne(new ShopCar(customer_no, goodStr));
      // 通过商品NO数组获取库存的完整信息
      Stock stock = stockService.selectOne(shopCar.getGood_no());
      //如果允许购买并且库存大于购买量
      if (stock.getIs_online() == 1 && stock.getStock() > shopCar.getNum()) {
        goodList.add(shopCar);
      }
    }

    //转换成一个商店对应多个商品的形式
    Map<String, List<ShopCar>> goodMap = goodList.stream().filter(shopCar -> shopCar.getNum() > 0)
        .collect(Collectors.groupingBy(ShopCar::getShop_no));

    //遍历
    Iterator<Entry<String, List<ShopCar>>> iterator = goodMap.entrySet().iterator();
    while (iterator.hasNext()) {
      Entry<String, List<ShopCar>> entry = iterator.next();
      String shop_no = entry.getKey();
      List<ShopCar> shopCarList = entry.getValue();
      //获取一个商店对应多个商品的总金额
      int totalPrice = shopCarList.stream().mapToInt(shopCar -> shopCar.getNum() * shopCar.getPrice()).sum();
      //生成订单编号
      String order_no = "NO" + new Date().getTime();
      Order order = new Order(order_no, shop_no, customer_no, new java.sql.Date(new Date().getTime()), 1, totalPrice);
      //添加订单
      orderService.add(order);
      //遍历添加订单详情
      for (ShopCar shopCar : shopCarList) {
        OrderDetail orderDetail = new OrderDetail(order_no, shopCar.getGood_no(), shopCar.getNum(), shopCar.getPrice());
        // 添加订单详情
        orderDetailService.add(orderDetail);
        //清楚购物车的商品
        shopCarService.delete(shopCar);
        //修改库存
        stockService.update(shopCar);
      }
    }

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
    String customer_no = (String) session.getAttribute("customerNo");
    String status = req.getParameter("status");
    long total = 0;
    if (status == null) {
      total = orderService.selectCount(customer_no);
    } else {
      total = orderService.selectCount(customer_no, status);
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
    List<OrderVO> list = null;

    if (status == null) {
      list = orderService.selectPage(begin, pageSize, customer_no);
    } else {
      list = orderService.selectPage(begin, pageSize, customer_no, status);
    }

    req.setAttribute("list", list);
    req.setAttribute("total", total);
    req.setAttribute("currentPage", currentPage);
    req.setAttribute("pageSize", pageSize);
    req.setAttribute("status", status);
    req.getRequestDispatcher("/orderList.jsp").forward(req, resp);
  }

}
