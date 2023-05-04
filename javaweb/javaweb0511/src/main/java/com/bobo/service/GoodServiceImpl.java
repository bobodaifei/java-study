package com.bobo.service;

import com.bobo.dao.GoodDao;
import com.bobo.entity.Good;
import com.bobo.utils.JdbcUtil;

public class GoodServiceImpl implements GoodService {
  private GoodDao goodDao = new GoodDao();

  @Override
  public int update(Good good) {
    int res = goodDao.update(good);
    JdbcUtil.close();
    return res;
  }

}
