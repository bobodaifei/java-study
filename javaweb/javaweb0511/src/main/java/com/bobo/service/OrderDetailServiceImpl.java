package com.bobo.service;

import java.util.List;

import com.bobo.dao.OrderDetailDao;
import com.bobo.entity.OrderDetail;
import com.bobo.entity.OrderDetailVO;
import com.bobo.utils.JdbcUtil;

public class OrderDetailServiceImpl implements OrderDetailService {

  private OrderDetailDao orderDetailDao = new OrderDetailDao();

  @Override
  public long selectCount(String order_no) {
    long res = orderDetailDao.selectCount(order_no);
    JdbcUtil.close();
    return res;
  }

  @Override
  public List<OrderDetail> selectPage(int begin, int pageSize, String order_no) {
    List<OrderDetail> res = orderDetailDao.selectPage(begin, pageSize, order_no);
    JdbcUtil.close();
    return res;
  }

  @Override
  public long selectCountByGoodNo(String good_no) {
    long res = orderDetailDao.selectCountByGoodNo(good_no);
    JdbcUtil.close();
    return res;
  }

  @Override
  public List<OrderDetailVO> selectPageByGoodNo(int begin, int pageSize, String good_no) {
    List<OrderDetailVO> res = orderDetailDao.selectPageByGoodNo(begin, pageSize, good_no);
    JdbcUtil.close();
    return res;
  }

}
