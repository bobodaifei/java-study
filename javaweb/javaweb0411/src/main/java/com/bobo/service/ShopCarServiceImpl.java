package com.bobo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.bobo.common.Page;
import com.bobo.dao.ShopCarDao;
import com.bobo.entity.ShopCar;
import com.bobo.entity.ShopCarVO;
import com.bobo.utils.jedis.JedisUtil;

public class ShopCarServiceImpl implements ShopCarService {

  private ShopCarDao shopCarDao = new ShopCarDao();

  @Override
  public long add(ShopCar shopCar) {
    JedisUtil jedisUtil = JedisUtil.getInstance();
    String redis_customer_no = "shopCar:" + shopCar.getCustomer_no();

    return jedisUtil.hset(redis_customer_no, shopCar.getGood_no() + ":" + shopCar.getShop_no(), 1 + "");
    // return shopCarDao.add(shopCar);
  }

  @Override
  public long update(ShopCar shopCar) {
    JedisUtil jedisUtil = JedisUtil.getInstance();
    String redis_customer_no = "shopCar:" + shopCar.getCustomer_no();
    return jedisUtil.hset(redis_customer_no, shopCar.getGood_no() + ":" + shopCar.getShop_no(), shopCar.getNum() + "");
    // return shopCarDao.update(shopCar);
  }

  @Override
  public ShopCar selectOne(ShopCar shopCar) {
    JedisUtil jedisUtil = JedisUtil.getInstance();
    String redis_customer_no = "shopCar:" + shopCar.getCustomer_no();
    int num =Integer.valueOf(jedisUtil.hget(redis_customer_no, shopCar.getGood_no() + ":" + shopCar.getShop_no()));
    shopCar.setNum(num);
    return shopCar;
    // return shopCarDao.selectOne(shopCar);
  }

  @Override
  public long selectCount(String customer_no) {
    JedisUtil jedisUtil = JedisUtil.getInstance();
    String redis_customer_no = "shopCar:" + customer_no;
    return jedisUtil.hlen(redis_customer_no);
    // return shopCarDao.selectCount(customer_no);
  }

  @Override
  public Page selectPage(int currentPage, int pageSize, String customer_no) {

    JedisUtil jedisUtil = JedisUtil.getInstance();

    String redis_customer_no = "shopCar:" + customer_no;
    long total = jedisUtil.hlen(redis_customer_no);

    if (total == 0) {
      return new Page<>(null, total, pageSize, 1);
    }

    int begin = (currentPage - 1) * pageSize;

    if (begin >= total) {
      currentPage--;
      begin = begin - pageSize;
    }
    if (currentPage < 1) {
      currentPage = 1;
      begin = 0;
    }

    Map<String, String> map = jedisUtil.hgetAll(redis_customer_no);
    Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
    List<ShopCarVO> list = new ArrayList<>();
    while (iterator.hasNext()) {
      Entry<String, String> next = iterator.next();
      String key = next.getKey();
      String[] split = key.split(":");
      String num = next.getValue();
      list.add(new ShopCarVO(customer_no, split[0], null, Integer.valueOf(num), 0, split[1], null));
    }

    // List<ShopCarVO> pageList = shopCarDao.selectPage(begin, pageSize,
    // customer_no);

    return new Page<>(list, total, pageSize, currentPage);
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
