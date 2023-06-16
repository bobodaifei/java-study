package com.bobo.service.impl;


import com.bobo.dao.ShopDao;
import com.bobo.entity.Shop;
import com.bobo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

  @Autowired
  private ShopDao shopDao;

  @Override
  public Shop selectById(String shop_no) {
    return shopDao.selectById(shop_no);
  }
  
}
