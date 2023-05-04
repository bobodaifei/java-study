package com.bobo.service;

import java.util.List;

import com.bobo.dao.GoodDao;
import com.bobo.entity.Good;

public class GoodServiceImpl implements GoodService {
  private GoodDao goodDao = new GoodDao();

  @Override
  public List<Good> selectPage(long begin, long pageSize, String shop_no) {
    return goodDao.selectPage(begin, pageSize, shop_no);
  }

  @Override
  public Good selectById(String good_no) {
    return goodDao.selectById(good_no);
  }

  

}
