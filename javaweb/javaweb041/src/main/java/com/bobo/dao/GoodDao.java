package com.bobo.dao;

import java.util.List;

import com.bobo.entity.Good;

public class GoodDao extends BasicDao<Good>{

  public List<Good> selectPage(long begin, long pageSize, String shop_no) {
    String sql = "select gl.*,gs.shop_no FROM good gl RIGHT JOIN (select good_no,shop_no FROM stock where shop_no=?) gs ON gl.good_no=gs.good_no limit ?,?";
    return queryMulti(sql, Good.class, shop_no, begin, pageSize);
  }

  public Good selectById(String good_no) {
    String sql = "select * from good where good_no=?";
    return querySingle(sql, Good.class, good_no);
  }
}
