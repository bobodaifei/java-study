package com.bobo.service;

import java.util.List;

import com.bobo.common.Page;
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
  public Page<OrderVO> selectPage(int currentPage, int pageSize, String shop_no) {
    long total = selectCount(shop_no);

    int begin = (currentPage - 1) * pageSize;
    if (begin >= total) {
      currentPage--;
      begin = begin - pageSize;
    }
    if (currentPage < 1) {
      currentPage = 1;
      begin = 0;
    }
    List<OrderVO> res = orderDao.selectPage(begin, pageSize, shop_no);
    
    return new Page<OrderVO>(res, total, pageSize, currentPage);
  }

  @Override
  public OrderVO selectOne(String order_no) {
    return orderDao.selectOne(order_no);
  }

}
