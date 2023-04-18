package com.bobo.service;

import java.util.List;

import com.bobo.dao.OrderDao;
import com.bobo.entity.Order;
import com.bobo.entity.OrderVO;

public class OrderServiceImpl implements OrderService{

  private OrderDao orderDao = new OrderDao();

  @Override
  public int add(Order order) {
    String sql = "insert into order1 values ( ?, ?, ?, ?, ?, ? );";
    return orderDao.update(sql, order.getOrder_no(), order.getShop_no(), order.getCustomer_no(), order.getCreate_date(),
        order.getStatus(),order.getPrice());
  }

  @Override
  public long selectCount() {
    String sql = "SELECT count(*) as total FROM order1";
    return (Long) orderDao.queryScalar(sql);
  }

  @Override
  public List<Order> selectPage(int begin, int pageSize) {
    String sql = "SELECT * FROM	order1 limit ?,?";
    return orderDao.queryMulti(sql, Order.class, begin, pageSize);
  }

  @Override
  public long selectCount(String customer_no) {
    String sql = "SELECT count(*) as total FROM order1 where customer_no = ?";
    return (Long) orderDao.queryScalar(sql, customer_no);
  }

  @Override
  public List<OrderVO> selectPage(int begin, int pageSize, String customer_no) {
    String sql = "SELECT o.*,s.shop_name FROM	order1 o LEFT JOIN shop s ON o.shop_no=s.shop_no  where customer_no = ? limit ?,?";
    return orderDao.queryMulti(sql, OrderVO.class,customer_no, begin, pageSize);
  }

  @Override
  public List<OrderVO> selectPage(int begin, int pageSize, String customer_no, String status) {
    String sql = "SELECT o.*,s.shop_name FROM	order1 o LEFT JOIN shop s ON o.shop_no=s.shop_no  where customer_no = ? and status = ? limit ?,?";
    return orderDao.queryMulti(sql, OrderVO.class, customer_no,status, begin, pageSize);
  }

  @Override
  public long selectCount(String customer_no, String status) {
    String sql = "SELECT count(*) as total FROM order1 where customer_no = ? and status =?";
    return (Long) orderDao.queryScalar(sql, customer_no, status);
  }
  
}
