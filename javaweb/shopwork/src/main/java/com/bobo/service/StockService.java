package com.bobo.service;

import com.bobo.entity.ShopCar;
import com.bobo.entity.Stock;

public interface StockService {

  Stock selectOne(String good_no);

  int update(ShopCar shopCar);
  
  long selectCount(String shop_no);
  
}
