package com.bobo.service;

import com.bobo.dao.StockDao;
import com.bobo.entity.ShopCar;
import com.bobo.entity.Stock;
import com.bobo.utils.JdbcUtil;

public class StockServiceImpl implements StockService {
  private StockDao stockDao = new StockDao();

  @Override
  public Stock selectOne(String good_no) {
    Stock res = stockDao.selectOne(good_no);
    JdbcUtil.close();
    return res;
  }

  @Override
  public int update(ShopCar shopCar) {
    int res = stockDao.update(shopCar);
    JdbcUtil.close();
    return res;
  }

  @Override
  public long selectCount(String shop_no) {
    long res = stockDao.selectCount(shop_no);
    JdbcUtil.close();
    return res;
  }

}
