package com.bobo.dao;

import com.bobo.entity.Good;
import org.springframework.stereotype.Repository;

@Repository
public class GoodDao extends BasicDao<Good>{

  public int update(Good good) {
    String sql = "update good set good_name = ?, price = ? where  good_no = ?";
    return super.update(sql, good.getGood_name(), good.getPrice(), good.getGood_no());
  }
  
}
