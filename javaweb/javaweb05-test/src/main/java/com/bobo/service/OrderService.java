package com.bobo.service;

import java.util.List;

import com.bobo.common.Page;
import com.bobo.entity.OrderVO;

public interface OrderService {

  long selectCount(String shop_no);

  long selectCount(String shop_no, String status);

  Page<OrderVO> selectPage(int begin, int pageSize, String shop_no);

  OrderVO selectOne(String order_no);
  
}
