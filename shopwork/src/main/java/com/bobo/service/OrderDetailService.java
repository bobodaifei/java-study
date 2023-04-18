package com.bobo.service;

import java.util.List;

import com.bobo.entity.OrderDetail;
import com.bobo.entity.OrderDetailVO;

public interface OrderDetailService {

  int add(OrderDetail orderDetail);

  long selectCount(String order_no);

  List<OrderDetailVO> selectPage(int begin, int pageSize, String order_no);
  
}
