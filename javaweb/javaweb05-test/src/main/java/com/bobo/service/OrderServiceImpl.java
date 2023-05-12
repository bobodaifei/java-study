package com.bobo.service;

import java.util.List;

import com.bobo.dao.OrderDao;
import com.bobo.entity.OrderVO;

public class OrderServiceImpl implements OrderService {

  private OrderDao orderDao = new OrderDao();

  @Override
  public long selectCount(String shop_no) {
    return orderDao.selectCount(shop_no);
  }

  @Override
  public long selectCount(String shop_no, String status) {
    return orderDao.selectCount(shop_no, status);
  }

  @Override
  public List<OrderVO> selectPage(int begin, int pageSize, String shop_no) {
    return orderDao.selectPage(begin, pageSize, shop_no);
  }

  @Override
  public OrderVO selectOne(String order_no) {
    return orderDao.selectOne(order_no);
  }

}
