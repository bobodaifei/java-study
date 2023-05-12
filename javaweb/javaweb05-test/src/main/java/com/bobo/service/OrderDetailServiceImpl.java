package com.bobo.service;

import java.util.List;

import com.bobo.dao.OrderDetailDao;
import com.bobo.entity.OrderDetail;
import com.bobo.entity.OrderDetailVO;

public class OrderDetailServiceImpl implements OrderDetailService {

  private OrderDetailDao orderDetailDao = new OrderDetailDao();

  @Override
  public long selectCount(String order_no) {
    return orderDetailDao.selectCount(order_no);
  }

  @Override
  public List<OrderDetail> selectPage(int begin, int pageSize, String order_no) {
    return orderDetailDao.selectPage(begin, pageSize, order_no);
  }

  @Override
  public long selectCountByGoodNo(String good_no) {
    return orderDetailDao.selectCountByGoodNo(good_no);
  }

  @Override
  public List<OrderDetailVO> selectPageByGoodNo(int begin, int pageSize, String good_no) {
    return orderDetailDao.selectPageByGoodNo(begin, pageSize, good_no);
  }

}
