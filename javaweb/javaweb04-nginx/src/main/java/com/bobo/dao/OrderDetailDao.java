package com.bobo.dao;

import com.bobo.entity.OrderDetail;

public class OrderDetailDao extends BasicDao<OrderDetail>{
  public int add(OrderDetail orderDetail) {
    String sql = "insert into order_detail values ( ?, ?, ?, ?)";
    return update(sql, orderDetail.getOrder_no(), orderDetail.getGood_no(), orderDetail.getNum(),
        orderDetail.getPrice());
  }
}
