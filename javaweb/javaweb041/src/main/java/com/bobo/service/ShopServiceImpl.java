package com.bobo.service;

import java.util.List;

import com.bobo.dao.ShopDao;
import com.bobo.entity.Shop;
import com.bobo.utils.JdbcUtil;

public class ShopServiceImpl implements ShopService{

  private ShopDao shopDao = new ShopDao();

  @Override
  public long selectCount() {
    long res = shopDao.selectCount();
    JdbcUtil.close(); 
    return res;
  }

  @Override
  public List<Shop> selectPage(long begin, long pageSize) {
    List<Shop> res = shopDao.selectPage(begin, pageSize);
    JdbcUtil.close();
    return res;
  }
  
}
