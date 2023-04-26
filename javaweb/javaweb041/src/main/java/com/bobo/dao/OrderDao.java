package com.bobo.dao;

import com.bobo.entity.Order;

public class OrderDao extends BasicDao<Order>{
  
  public long selectCount() {
    String sql = "SELECT count(*) as total FROM order1";
    return (Long) queryScalar(sql);
  }

  public long selectCount(String customer_no) {
    String sql = "SELECT count(*) as total FROM order1 where customer_no = ?";
    return (Long) queryScalar(sql, customer_no);
  }

  public long selectCount(String customer_no, String status) {
    String sql = "SELECT count(*) as total FROM order1 where customer_no = ? and status =?";
    return (Long) queryScalar(sql, customer_no, status);
  }

  public int add(Order order) {
    String sql = "insert into order1 values ( ?, ?, ?, ?, ?, ? );";
    return update(sql, order.getOrder_no(), order.getShop_no(), order.getCustomer_no(), order.getCreate_date(),
        order.getStatus(), order.getPrice());
  }
}
