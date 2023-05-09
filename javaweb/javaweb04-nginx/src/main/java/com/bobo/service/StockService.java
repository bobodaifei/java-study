package com.bobo.service;

import com.bobo.entity.ShopCar;
import com.bobo.entity.Stock;

public interface StockService {

  Stock selectById(String good_no);

  //结算减少库存
  int update(ShopCar shopCar);
  
  //获取该商店商品数量
  long selectCount(String shop_no);
  
}
