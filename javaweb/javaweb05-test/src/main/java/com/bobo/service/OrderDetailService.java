package com.bobo.service;

import com.bobo.common.Page;
import com.bobo.entity.OrderDetail;
import com.bobo.entity.OrderDetailVO;

public interface OrderDetailService {

  long selectCount(String order_no);

  Page<OrderDetail> selectPage(int begin, int pageSize, String order_no);

  long selectCountByGoodNo(String good_no);

  Page<OrderDetailVO> selectPageByGoodNo(int begin, int pageSize, String good_no);
  
}
