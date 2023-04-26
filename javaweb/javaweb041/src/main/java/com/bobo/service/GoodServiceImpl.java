package com.bobo.service;

import java.util.List;

import com.bobo.dao.GoodDao;
import com.bobo.entity.Good;
import com.bobo.utils.JdbcUtil;

public class GoodServiceImpl implements GoodService {
  private GoodDao goodDao = new GoodDao();

  @Override
  public List<Good> selectPage(long begin, long pageSize, String shop_no) {
    
    List<Good> res = goodDao.selectPage(begin, pageSize,shop_no);
    JdbcUtil.close();
    return res;
  }

  @Override
  public Good selectById(String good_no) {
    Good res = goodDao.selectById(good_no);
    JdbcUtil.close();
    return res;
  }

  

}
