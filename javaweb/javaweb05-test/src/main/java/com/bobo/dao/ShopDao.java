package com.bobo.dao;

import com.bobo.entity.Shop;

public class ShopDao extends BasicDao<Shop>{

  public Shop selectById(String shop_no) {
    String sql = "SELECT * FROM	shop where shop_no = ?";
    return querySingle(sql, Shop.class, shop_no);
  }

}
