package com.bobo.service;

import java.util.List;

import com.bobo.dao.ShopDao;
import com.bobo.entity.Shop;

public class ShopServiceImpl implements ShopService{

  private ShopDao shopDao = new ShopDao();

  @Override
  public long selectCount() {
    String sql = "SELECT count(*) as total FROM shop";
    return (Long) shopDao.queryScalar(sql);
  }

  @Override
  public List<Shop> selectPage(long begin, long pageSize) {
    String sql = "SELECT * FROM	shop limit ?,?";
    return shopDao.queryMulti(sql, Shop.class, begin, pageSize);
  }
  
}
