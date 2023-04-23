package com.bobo.service;

import com.bobo.entity.Order;

public interface OrderService {

  int add(Order order);

  long selectCount();

  long selectCount(String customer_no);

  long selectCount(String customer_no, String status);
  
}
