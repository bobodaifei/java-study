package com.bobo.service;


import com.bobo.dao.OrderDao;
import com.bobo.entity.Order;

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
  public long selectCount(String customer_no) {
    String sql = "SELECT count(*) as total FROM order1 where customer_no = ?";
    return (Long) orderDao.queryScalar(sql, customer_no);
  }

  @Override
  public long selectCount(String customer_no, String status) {
    String sql = "SELECT count(*) as total FROM order1 where customer_no = ? and status =?";
    return (Long) orderDao.queryScalar(sql, customer_no, status);
  }
  
}
