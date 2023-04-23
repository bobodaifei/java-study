package com.bobo.service;

import java.util.List;

import com.bobo.dao.ShopCarDao;
import com.bobo.entity.ShopCar;
import com.bobo.entity.ShopCarVO;

public class ShopCarServiceImpl implements ShopCarService{

  private ShopCarDao shopCarDao = new ShopCarDao();

  @Override
  public int add(ShopCar shopCar) {
    String sql="INSERT INTO `shop_car` VALUES (?, ?, ?, ?, ?)";
    return shopCarDao.update(sql, shopCar.getCustomer_no(), shopCar.getGood_no(), shopCar.getNum(), shopCar.getPrice(),
        shopCar.getShop_no());
  }

  @Override
  public int update(ShopCar shopCar) {
    String sql = "UPDATE shop_car SET num =?  WHERE customer_no = ? AND good_no = ? ";
    return shopCarDao.update(sql, shopCar.getNum(), shopCar.getCustomer_no(), shopCar.getGood_no());
  }

  @Override
  public ShopCar selectOne(ShopCar shopCar) {
    String sql = "select * from shop_car where customer_no = ? and good_no = ?";
    return shopCarDao.querySingle(sql, ShopCar.class, shopCar.getCustomer_no(), shopCar.getGood_no());
  }

  @Override
  public long selectCount(String customer_no) {
    String sql = "SELECT count(*) as total FROM shop_car where customer_no=?";
    return (Long) shopCarDao.queryScalar(sql, customer_no);
  }

  @Override
  public List<ShopCarVO> selectPage(int begin, int pageSize, String customer_no) {
    String sql = "SELECT sc.*, g.good_name,	s.shop_name FROM	shop_car sc	LEFT JOIN good g ON sc.good_no = g.good_no	LEFT JOIN shop s ON sc.shop_no = s.shop_no	WHERE customer_no = ? limit ?,?";
    return shopCarDao.queryMulti(sql, ShopCarVO.class, customer_no, begin, pageSize);
  }

  @Override
  public List<ShopCarVO> selectList(String customer_no) {
    String sql = "SELECT sc.*, g.good_name,	s.shop_name FROM	shop_car sc	LEFT JOIN good g ON sc.good_no = g.good_no	LEFT JOIN shop s ON sc.shop_no = s.shop_no	WHERE customer_no = ?";
    return shopCarDao.queryMulti(sql, ShopCarVO.class, customer_no);
  }

  @Override
  public int delete(ShopCar shopCar) {
    String sql = "delete from shop_car where customer_no = ? and good_no = ? ";
    return shopCarDao.update(sql, shopCar.getCustomer_no(), shopCar.getGood_no());
  }
  
}
