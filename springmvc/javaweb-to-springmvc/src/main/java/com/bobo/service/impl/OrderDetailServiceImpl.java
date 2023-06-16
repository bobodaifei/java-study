package com.bobo.service.impl;

import com.bobo.common.Page;
import com.bobo.dao.OrderDetailDao;
import com.bobo.entity.OrderDetail;
import com.bobo.entity.OrderDetailVO;
import com.bobo.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

  @Autowired
  private OrderDetailDao orderDetailDao;

  @Override
  public long selectCount(String order_no) {
    return orderDetailDao.selectCount(order_no);
  }

  @Override
  public Page<OrderDetail> selectPage(int currentPage, int pageSize, String order_no) {
    long total = selectCount(order_no);
    int begin = (currentPage - 1) * pageSize;

    if (begin >= total) {
      currentPage--;
      begin = begin - pageSize;
    }
    if (currentPage < 1) {
      currentPage = 1;
      begin = 0;
    }
    List<OrderDetail> res = orderDetailDao.selectPage(begin, pageSize, order_no);
    return new Page<OrderDetail>(res, total, pageSize, currentPage);
  }

  @Override
  public long selectCountByGoodNo(String good_no) {
    return orderDetailDao.selectCountByGoodNo(good_no);
  }

  @Override
  public Page<OrderDetailVO> selectPageByGoodNo(int currentPage, int pageSize, String good_no) {
    long total = selectCountByGoodNo(good_no);
    int begin = (currentPage - 1) * pageSize;

    if (begin >= total) {
      currentPage--;
      begin = begin - pageSize;
    }
    if (currentPage < 1) {
      currentPage = 1;
      begin = 0;
    }
    List<OrderDetailVO> res = orderDetailDao.selectPageByGoodNo(begin, pageSize, good_no);
    return new Page<OrderDetailVO>(res, total, pageSize, currentPage);
  }

}
