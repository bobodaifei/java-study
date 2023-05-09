package com.bobo.service;

import java.util.List;

import com.bobo.dao.ShopDao;
import com.bobo.entity.Shop;

public class ShopServiceImpl implements ShopService{

  private ShopDao shopDao = new ShopDao();

  @Override
  public long selectCount() {
    return shopDao.selectCount();
  }

  @Override
  public List<Shop> selectPage(long begin, long pageSize) {
    return shopDao.selectPage(begin, pageSize);
  }
  
}
