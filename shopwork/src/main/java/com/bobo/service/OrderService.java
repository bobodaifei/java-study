package com.bobo.service;

import java.util.List;

import com.bobo.entity.Order;
import com.bobo.entity.OrderVO;

public interface OrderService {

  int add(Order order);

  long selectCount();

  long selectCount(String customer_no);

  List<Order> selectPage(int begin, int pageSize);

  List<OrderVO> selectPage(int begin, int pageSize, String customer_no);

  List<OrderVO> selectPage(int begin, int pageSize, String customer_no, String status);

  long selectCount(String customer_no, String status);
  
}
