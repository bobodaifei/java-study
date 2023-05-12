package com.bobo.service;

import com.bobo.dao.GoodDao;
import com.bobo.entity.Good;

public class GoodServiceImpl implements GoodService {
  private GoodDao goodDao = new GoodDao();

  @Override
  public int update(Good good) {
    return goodDao.update(good);
  }

}
