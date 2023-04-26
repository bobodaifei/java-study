package com.bobo.service;

import java.util.List;

import com.bobo.dao.ShopCarDao;
import com.bobo.entity.ShopCar;
import com.bobo.entity.ShopCarVO;
import com.bobo.utils.JdbcUtil;

public class ShopCarServiceImpl implements ShopCarService{

  private ShopCarDao shopCarDao = new ShopCarDao();

  @Override
  public int add(ShopCar shopCar) {
    int res = shopCarDao.add(shopCar);
    JdbcUtil.close();
    return res;
  }

  @Override
  public int update(ShopCar shopCar) {
    int res = shopCarDao.update(shopCar);
    JdbcUtil.close();
    return res;
  }

  @Override
  public ShopCar selectOne(ShopCar shopCar) {
    ShopCar res = shopCarDao.selectOne(shopCar);
    JdbcUtil.close();
    return res;
  }

  @Override
  public long selectCount(String customer_no) {
    long res = shopCarDao.selectCount(customer_no);
    JdbcUtil.close();
    return res;
  }

  @Override
  public List<ShopCarVO> selectPage(int begin, int pageSize, String customer_no) {
    List<ShopCarVO> res = shopCarDao.selectPage(begin, pageSize, customer_no);
    JdbcUtil.close();
    return res;
  }

  @Override
  public List<ShopCarVO> selectList(String customer_no) {
     List<ShopCarVO> res = shopCarDao.selectList(customer_no);
    JdbcUtil.close();
    return res;
  }

  @Override
  public int delete(ShopCar shopCar) {
    int res = shopCarDao.update(shopCar);
    JdbcUtil.close();
    return res;
  }
  
}
