package com.bobo.service;


import com.bobo.dao.OrderDetailDao;
import com.bobo.entity.OrderDetail;

public class OrderDetailServiceImpl implements OrderDetailService {

  private OrderDetailDao orderDetailDao = new OrderDetailDao();

  @Override
  public int add(OrderDetail orderDetail) {
    String sql = "insert into order_detail values ( ?, ?, ?, ?)";
    return orderDetailDao.update(sql, orderDetail.getOrder_no(), orderDetail.getGood_no(), orderDetail.getNum(),
        orderDetail.getPrice());
  }

}
