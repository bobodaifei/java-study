package com.bobo.service;


import com.bobo.dao.ShopDao;
import com.bobo.entity.Shop;
import com.bobo.utils.JdbcUtil;

public class ShopServiceImpl implements ShopService{

  private ShopDao shopDao = new ShopDao();

  @Override
  public Shop selectOne(String shop_no) {
    Shop res = shopDao.selectOne(shop_no);
    JdbcUtil.close();
    return res;
  }
  
}
