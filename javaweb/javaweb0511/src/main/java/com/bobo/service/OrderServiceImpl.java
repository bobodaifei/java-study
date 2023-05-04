package com.bobo.service;

import java.util.List;

import com.bobo.dao.OrderDao;
import com.bobo.entity.OrderVO;
import com.bobo.utils.JdbcUtil;

public class OrderServiceImpl implements OrderService {

  private OrderDao orderDao = new OrderDao();

  @Override
  public long selectCount(String shop_no) {
    long res = (Long) orderDao.selectCount(shop_no);
    JdbcUtil.close();
    return res;
  }

  @Override
  public long selectCount(String shop_no, String status) {
    long res = (Long) orderDao.selectCount(shop_no, status);
    JdbcUtil.close();
    return res;
  }

  @Override
  public List<OrderVO> selectPage(int begin, int pageSize, String shop_no) {
    List<OrderVO> res = orderDao.selectPage(begin, pageSize, shop_no);
    JdbcUtil.close();
    return res;
  }

  @Override
  public OrderVO selectOne(String order_no) {
    OrderVO res = orderDao.selectOne(order_no);
    JdbcUtil.close();
    return res;
  }

}
