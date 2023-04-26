package com.bobo.dao;


import java.util.List;

import com.bobo.entity.Order;
import com.bobo.dao.OrderDao;
import com.bobo.entity.OrderVO;

public class OrderDao extends BasicDao<Order>{

  public long selectCount(String shop_no) {
    String sql = "SELECT count(*) as total FROM order1 where shop_no = ?";
    return (Long) queryScalar(sql, shop_no);
  }

  public long selectCount(String shop_no, String status) {
    String sql = "SELECT count(*) as total FROM order1 where shop_no = ? and status =?";
    return (Long) queryScalar(sql, shop_no, status);
  }

  public List<OrderVO> selectPage(int begin, int pageSize, String shop_no) {
    String sql = "SELECT o.*,s.shop_name,c.`name`,c.address,c.mobile FROM	order1 o LEFT JOIN shop s ON o.shop_no=s.shop_no LEFT JOIN customer c on c.customer_no = o.customer_no where o.shop_no = ? limit ?,?";
    return queryMulti(sql, OrderVO.class, shop_no, begin, pageSize);
  }

  public OrderVO selectOne(String order_no) {
    String sql = "SELECT o.*,s.shop_name,c.`name`,c.address,c.mobile FROM	order1 o LEFT JOIN shop s ON o.shop_no=s.shop_no LEFT JOIN customer c on c.customer_no = o.customer_no where o.order_no = ?";
    return querySingle(sql, OrderVO.class, order_no);
  }
}
