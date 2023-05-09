package com.bobo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.bobo.common.Page;
import com.bobo.dao.GoodDao;
import com.bobo.dao.ShopCarDao;
import com.bobo.dao.ShopDao;
import com.bobo.dao.StockDao;
import com.bobo.entity.Good;
import com.bobo.entity.Shop;
import com.bobo.entity.ShopCar;
import com.bobo.entity.ShopCarVO;
import com.bobo.entity.Stock;
import com.bobo.utils.jedis.JedisUtil;

public class ShopCarServiceImpl implements ShopCarService {

  JedisUtil jedisUtil = JedisUtil.getInstance();

  private ShopCarDao shopCarDao = new ShopCarDao();
  private ShopDao shopDao = new ShopDao();
  private GoodDao goodDao = new GoodDao();

  @Override
  public long add(ShopCar shopCar) {
    String redis_customer_no = "shopCar:" + shopCar.getCustomer_no();
    return jedisUtil.hset(redis_customer_no, shopCar.getGood_no() + ":" + shopCar.getShop_no(), shopCar.getNum() + "");
    // return shopCarDao.add(shopCar);
  }

  @Override
  public long update(ShopCar shopCar) {
    String redis_customer_no = "shopCar:" + shopCar.getCustomer_no();
    return jedisUtil.hset(redis_customer_no, shopCar.getGood_no() + ":" + shopCar.getShop_no(), shopCar.getNum() + "");
    // return shopCarDao.update(shopCar);
  }

  @Override
  public ShopCar selectOne(ShopCar shopCar) {
    String redis_customer_no = "shopCar:" + shopCar.getCustomer_no();
    int num =Integer.valueOf(jedisUtil.hget(redis_customer_no, shopCar.getGood_no() + ":" + shopCar.getShop_no()));
    shopCar.setNum(num);
    return shopCar;
    // return shopCarDao.selectOne(shopCar);
  }

  @Override
  public long selectCount(String customer_no) {
    String redis_customer_no = "shopCar:" + customer_no;
    return jedisUtil.hlen(redis_customer_no);
    // return shopCarDao.selectCount(customer_no);
  }

  @Override
  public Page selectPage(int currentPage, int pageSize, String customer_no) {

    String redis_customer_no = "shopCar:" + customer_no;
    long total = jedisUtil.hlen(redis_customer_no);

    if (total == 0) {
      return new Page<>(null, total, pageSize, 1);
    }

    Map<String, String> map = jedisUtil.hgetAll(redis_customer_no);
    Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
    List<ShopCarVO> list = new ArrayList<>();
    while (iterator.hasNext()) {
      Entry<String, String> next = iterator.next();
      String key = next.getKey();
      String num = next.getValue();
      String[] split = key.split(":");
      String good_no = split[0];
      String shop_no = split[1];
      // 通过商品NO数组获取库存的完整信息
      Shop shop = shopDao.selectById(shop_no);
      // 获取商品信息
      Good good = goodDao.selectById(good_no);
      list.add(new ShopCarVO(customer_no, good_no, good.getGood_name(), Integer.valueOf(num), good.getPrice(), shop_no, 
          shop.getShop_name()));
    }

    return new Page<>(list, total, pageSize, currentPage);
  }

  @Override
  public List<ShopCarVO> selectList(String customer_no) {
    return shopCarDao.selectList(customer_no);
  }

  @Override
  public long delete(ShopCar shopCar) {
    String redis_customer_no = "shopCar:" + shopCar.getCustomer_no();

    return jedisUtil.hdel(redis_customer_no, shopCar.getGood_no() + ":" + shopCar.getShop_no());
    // return shopCarDao.update(shopCar);
  }

}
