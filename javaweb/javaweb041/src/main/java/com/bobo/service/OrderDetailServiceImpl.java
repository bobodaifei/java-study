package com.bobo.service;


import com.bobo.dao.OrderDetailDao;
import com.bobo.entity.OrderDetail;
import com.bobo.utils.JdbcUtil;

public class OrderDetailServiceImpl implements OrderDetailService {

  private OrderDetailDao orderDetailDao = new OrderDetailDao();

  @Override
  public int add(OrderDetail orderDetail) {
    int res = orderDetailDao.add(orderDetail);
    JdbcUtil.close();
    return res;
  }

}
