package com.bobo.dao;

import com.bobo.entity.ShopCar;
import com.bobo.entity.Stock;

public class StockDao extends BasicDao<Stock>{

  public Stock selectById(String good_no) {
    String sql = "select * from stock where good_no = ?";
    return querySingle(sql, Stock.class, good_no);
  }

  public int update(ShopCar shopCar) {
    String sql = "update stock set stock = stock - ? where good_no = ?";
    return update(sql, shopCar.getNum(), shopCar.getGood_no());
  }

  public long selectCount(String shop_no) {
    String sql = "SELECT count(*) as total FROM stock where shop_no=?";
    return (Long) queryScalar(sql, shop_no);
  }
}
