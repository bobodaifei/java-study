package com.bobo.service;

import java.util.List;

import com.bobo.dao.ShopDao;
import com.bobo.entity.Shop;

public class ShopServiceImpl implements ShopService{

  private ShopDao shopDao = new ShopDao();

  @Override
  public Shop selectOne(String shop_no) {
    String sql = "SELECT * FROM	shop where shop_no = ?";
    return shopDao.querySingle(sql, Shop.class, shop_no);
  }
  
}
