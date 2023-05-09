package com.bobo.service;

import java.util.List;

import com.bobo.common.Page;
import com.bobo.entity.ShopCar;
import com.bobo.entity.ShopCarVO;

public interface ShopCarService {

  long add(ShopCar shopCar);

  long update(ShopCar shopCar);

  ShopCar selectOne(ShopCar shopCar);

  long selectCount(String customer_no);

  Page selectPage(int currentPage, int pageSize, String customer_no);

  List<ShopCarVO> selectList(String customer_no);

  long delete(ShopCar shopCar);
  
}
