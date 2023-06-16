package com.bobo.service.impl;

import com.bobo.dao.GoodDao;
import com.bobo.entity.Good;
import com.bobo.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodServiceImpl implements GoodService {

  @Autowired
  private GoodDao goodDao;

  @Override
  public int update(Good good) {
    return goodDao.update(good);
  }

}
