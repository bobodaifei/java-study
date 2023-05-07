package com.bobo.dao;

import java.util.List;

import com.bobo.entity.Shop;

public class ShopDao extends BasicDao<Shop> {

  public long selectCount() {
    String sql = "SELECT count(*) as total FROM shop";
    return (Long) queryScalar(sql);
  }

  public Shop selectById(String shop_no) {
    String sql = "SELECT * FROM shop where shop_no = ?";
    return querySingle(sql, Shop.class, shop_no);
  }

  public List<Shop> selectPage(long begin, long pageSize) {
    String sql = "SELECT * FROM	shop limit ?,?";
    return queryMulti(sql, Shop.class, begin, pageSize);
  }
}
