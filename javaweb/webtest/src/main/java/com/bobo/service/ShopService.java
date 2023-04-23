package com.bobo.service;

import java.util.List;

import com.bobo.entity.Shop;



public interface ShopService {

  long selectCount();

  List<Shop> selectPage(int begin, int pageSize);
  
}
