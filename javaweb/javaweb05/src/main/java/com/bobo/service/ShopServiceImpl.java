package com.bobo.service;


import com.bobo.dao.ShopDao;
import com.bobo.entity.Shop;

public class ShopServiceImpl implements ShopService{

  private ShopDao shopDao = new ShopDao();

  @Override
  public Shop selectOne(String shop_no) {
    return shopDao.selectOne(shop_no);
  }
  
}
