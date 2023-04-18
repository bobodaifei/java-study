package com.bobo.service;

import com.bobo.dao.StockDao;
import com.bobo.entity.ShopCar;
import com.bobo.entity.Stock;

public class StockServiceImpl implements StockService {
  private StockDao stockDao = new StockDao();

  @Override
  public Stock selectOne(String good_no) {
    String sql = "select * from stock where good_no = ?";
    return stockDao.querySingle(sql, Stock.class, good_no);
  }

  @Override
  public int update(ShopCar shopCar) {
    String sql = "update stock set stock = stock - ? where good_no = ?";
    return stockDao.update(sql, shopCar.getNum(), shopCar.getGood_no());
  }

  @Override
  public long selectCount(String shop_no) {
    String sql = "SELECT count(*) as total FROM stock where shop_no=?";
    return (Long) stockDao.queryScalar(sql, shop_no);
  }

}
