package com.bobo.service;

import com.bobo.dao.StockDao;
import com.bobo.entity.ShopCar;
import com.bobo.entity.Stock;

public class StockServiceImpl implements StockService {
  private StockDao stockDao = new StockDao();

  @Override
  public Stock selectById(String good_no) {
    return stockDao.selectOne(good_no);
  }

  @Override
  public int update(ShopCar shopCar) {
    return stockDao.update(shopCar);
  }

  @Override
  public long selectCount(String shop_no) {
    return stockDao.selectCount(shop_no);
  }

}
