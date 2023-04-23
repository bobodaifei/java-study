package com.bobo.service;

import java.util.List;

import com.bobo.dao.OrderDao;
import com.bobo.entity.Order;
import com.bobo.entity.OrderVO;

public class OrderServiceImpl implements OrderService{

  private OrderDao orderDao = new OrderDao();

  @Override
  public long selectCount(String shop_no) {
    String sql = "SELECT count(*) as total FROM order1 where shop_no = ?";
    return (Long) orderDao.queryScalar(sql, shop_no);
  }

  @Override
  public long selectCount(String shop_no, String status) {
    String sql = "SELECT count(*) as total FROM order1 where shop_no = ? and status =?";
    return (Long) orderDao.queryScalar(sql, shop_no, status);
  }

  @Override
  public List<OrderVO> selectPage(int begin, int pageSize, String shop_no) {
    String sql = "SELECT o.*,s.shop_name,c.`name`,c.address,c.mobile FROM	order1 o LEFT JOIN shop s ON o.shop_no=s.shop_no LEFT JOIN customer c on c.customer_no = o.customer_no where o.shop_no = ? limit ?,?";
    return orderDao.queryMulti(sql, OrderVO.class, shop_no, begin, pageSize);
  }

  @Override
  public List<OrderVO> selectPage(int begin, int pageSize, String shop_no, String status) {
    String sql = "SELECT o.*,s.shop_name,c.`name`,c.address,c.mobile FROM	order1 o LEFT JOIN shop s ON o.shop_no=s.shop_no LEFT JOIN customer c on c.customer_no = o.customer_no  where o.shop_no = ? and status = ? limit ?,?";
    return orderDao.queryMulti(sql, OrderVO.class, shop_no, status, begin, pageSize);
  }

  @Override
  public OrderVO selectOne(String order_no) {
    String sql = "SELECT o.*,s.shop_name,c.`name`,c.address,c.mobile FROM	order1 o LEFT JOIN shop s ON o.shop_no=s.shop_no LEFT JOIN customer c on c.customer_no = o.customer_no where o.order_no = ?";
    return orderDao.querySingle(sql, OrderVO.class, order_no);
  }
  
}
