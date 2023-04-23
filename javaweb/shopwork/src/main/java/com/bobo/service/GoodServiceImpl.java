package com.bobo.service;

import java.util.List;

import com.bobo.dao.GoodDao;
import com.bobo.entity.Good;

public class GoodServiceImpl implements GoodService {
  private GoodDao goodDao = new GoodDao();

  @Override
  public long selectCount() {
    String sql = "SELECT count(*) as total FROM good";
    return (Long) goodDao.queryScalar(sql);
  }

  @Override
  public List<Good> selectPage(int begin, int pageSize) {
    String sql = "SELECT * FROM	good  limit ?,?";
    return goodDao.queryMulti(sql, Good.class, begin, pageSize);
  }

  @Override
  public List<Good> selectPage(int begin, int pageSize, String shop_no) {
    String sql = "select gl.*,gs.shop_no FROM good gl RIGHT JOIN (select good_no,shop_no FROM stock where shop_no=?) gs ON gl.good_no=gs.good_no limit ?,?";
    return goodDao.queryMulti(sql, Good.class, shop_no, begin, pageSize);
  }

  

}
