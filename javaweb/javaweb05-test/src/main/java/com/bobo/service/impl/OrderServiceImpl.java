package com.bobo.service.impl;

import com.bobo.common.Page;
import com.bobo.dao.OrderDao;
import com.bobo.entity.OrderVO;
import com.bobo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderDao orderDao;

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
