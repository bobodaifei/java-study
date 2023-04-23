package com.bobo.service;

import java.util.List;

import com.bobo.entity.OrderDetail;
import com.bobo.entity.OrderDetailVO;

public interface OrderDetailService {

  long selectCount(String order_no);

  List<OrderDetail> selectPage(int begin, int pageSize, String order_no);

  long selectCountByGoodNo(String good_no);

  List<OrderDetailVO> selectPageByGoodNo(int begin, int pageSize, String good_no);
  
}
