package com.bobo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.bobo.dao.OrderDao;
import com.bobo.dao.OrderDetailDao;
import com.bobo.dao.ShopCarDao;
import com.bobo.dao.StockDao;
import com.bobo.entity.Order;
import com.bobo.entity.OrderDetail;
import com.bobo.entity.ShopCar;
import com.bobo.entity.Stock;
import com.bobo.utils.JdbcUtil;

public class OrderServiceImpl implements OrderService {

  private OrderDao orderDao = new OrderDao();
  private ShopCarDao shopCarDao = new ShopCarDao();
  private OrderDetailDao orderDetailDao = new OrderDetailDao();
  private StockDao stockDao = new StockDao();

  @Override
  public int addArr(String customer_no, String[] goodsArr) {
    int flag = 0;
    try {
      JdbcUtil.begin();
      // 最终可购买的购物车商品
      List<ShopCar> goodList = new ArrayList<>();
      // 遍历商品NO数组
      for (String goodStr : goodsArr) {
        // 通过商品NO数组获取用户购物车的完整信息
        ShopCar shopCar = shopCarDao.selectOne(new ShopCar(customer_no, goodStr));
        // 通过商品NO数组获取库存的完整信息
        Stock stock = stockDao.selectOne(shopCar.getGood_no());
        // 如果允许购买并且库存大于购买量
        if (stock != null && stock.getIs_online() == 1 && stock.getStock() > shopCar.getNum()) {
          goodList.add(shopCar);
        }
      }

      // 转换成一个商店对应多个商品的形式
      Map<String, List<ShopCar>> goodMap = goodList.stream().filter(shopCar -> shopCar.getNum() > 0)
          .collect(Collectors.groupingBy(ShopCar::getShop_no));

      // 遍历
      Iterator<Entry<String, List<ShopCar>>> iterator = goodMap.entrySet().iterator();
      while (iterator.hasNext()) {
        try {
          Entry<String, List<ShopCar>> entry = iterator.next();
          String shop_no = entry.getKey();
          List<ShopCar> shopCarList = entry.getValue();
          // 获取一个商店对应多个商品的总金额
          int totalPrice = shopCarList.stream().mapToInt(shopCar -> shopCar.getNum() * shopCar.getPrice()).sum();
          // 生成订单编号
          String order_no = "NO" + new Date().getTime();
          Order order = new Order(order_no, shop_no, customer_no, new java.sql.Date(new Date().getTime()), 1,
              totalPrice);
          // 添加订单
          int flag0 = add(order);
          if (flag0 == 0) {
            continue;
          }
          // 遍历添加订单详情
          for (ShopCar shopCar : shopCarList) {
            OrderDetail orderDetail = new OrderDetail(order_no, shopCar.getGood_no(), shopCar.getNum(),
                shopCar.getPrice());
            // 添加订单详情 
            int flag1 = orderDetailDao.add(orderDetail);
            // 清楚购物车的商品
            int flag2 = shopCarDao.delete(shopCar);
            // 修改库存
            int flag3 = stockDao.update(shopCar);
            if (flag1 == 0 || flag2 == 0 || flag3 == 0) {
              throw new RuntimeException();
            }
          }
          JdbcUtil.commit();
          ++flag;
        } catch (RuntimeException e) {
          JdbcUtil.rollback();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JdbcUtil.close();
    }
    return flag;
  }

  @Override
  public long selectCount() {
    long res = orderDao.selectCount();
    JdbcUtil.close();
    return res;
  }

  @Override
  public long selectCount(String customer_no) {
    long res = (Long) orderDao.selectCount(customer_no);
    JdbcUtil.close();
    return res;
  }

  @Override
  public long selectCount(String customer_no, String status) {
    long res = orderDao.selectCount(customer_no, status);
    JdbcUtil.close();
    return res;
  }

  @Override
  public int add(Order order) {
    int res = orderDao.add(order);
    JdbcUtil.close();
    return res;
  }

}
