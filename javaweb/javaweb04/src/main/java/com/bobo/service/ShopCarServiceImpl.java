package com.bobo.service;

import java.util.List;

import com.bobo.dao.ShopCarDao;
import com.bobo.entity.ShopCar;
import com.bobo.entity.ShopCarVO;

public class ShopCarServiceImpl implements ShopCarService{

  private ShopCarDao shopCarDao = new ShopCarDao();

  @Override
  public int add(ShopCar shopCar) {
    return shopCarDao.add(shopCar);
  }

  @Override
  public int update(ShopCar shopCar) {
    return shopCarDao.update(shopCar);
  }

  @Override
  public ShopCar selectOne(ShopCar shopCar) {
    return shopCarDao.selectOne(shopCar);
  }

  @Override
  public long selectCount(String customer_no) {
    return shopCarDao.selectCount(customer_no);
  }

  @Override
  public List<ShopCarVO> selectPage(int begin, int pageSize, String customer_no) {
    return shopCarDao.selectPage(begin, pageSize, customer_no);
  }

  @Override
  public List<ShopCarVO> selectList(String customer_no) {
    return shopCarDao.selectList(customer_no);
  }

  @Override
  public int delete(ShopCar shopCar) {
    return shopCarDao.update(shopCar);
  }
  
}
