package com.bobo.service;


import com.bobo.dao.OrderDetailDao;
import com.bobo.entity.OrderDetail;

public class OrderDetailServiceImpl implements OrderDetailService {

  private OrderDetailDao orderDetailDao = new OrderDetailDao();

  @Override
  public int add(OrderDetail orderDetail) {
    return orderDetailDao.add(orderDetail);
  }

}
