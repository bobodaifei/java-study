package com.bobo.service;

import java.util.List;

import com.bobo.entity.ShopCar;
import com.bobo.entity.ShopCarVO;

public interface ShopCarService {

  int add(ShopCar shopCar);

  int update(ShopCar shopCar);

  ShopCar selectOne(ShopCar shopCar);

  long selectCount();

  long selectCount(String username);

  List<ShopCarVO> selectPage(int begin, int pageSize);

  List<ShopCarVO> selectPage(int begin, int pageSize, String username);

  List<ShopCarVO> selectList(String username);
  
}
