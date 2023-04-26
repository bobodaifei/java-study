package com.bobo.dao;

import java.util.List;

import com.bobo.dao.OrderDetailDao;
import com.bobo.entity.OrderDetail;
import com.bobo.entity.OrderDetailVO;

public class OrderDetailDao extends BasicDao<OrderDetail>{

  public long selectCount(String order_no) {
    String sql = "SELECT count(*) as total FROM order_detail where order_no = ?";
    return (Long) queryScalar(sql, order_no);
  }


  public List<OrderDetail> selectPage(int begin, int pageSize, String order_no) {
    String sql = "SELECT o.*,g.good_name FROM order_detail o LEFT JOIN good g ON o.good_no = g.good_no WHERE order_no = ? limit ?,?";
    return queryMulti(sql, OrderDetail.class, order_no, begin, pageSize);
  }


	public long selectCountByGoodNo(String good_no) {
		String sql = "SELECT count(*) as total FROM order_detail where good_no = ?";
    return (Long) queryScalar(sql, good_no);
	}


	public List<OrderDetailVO> selectPageByGoodNo(int begin, int pageSize, String good_no) {
		String sql = "SELECT	od.order_no, `name`,	mobile,	address,	create_date,	`status`,	good_name,	num,	price,	num * price AS totalPrice FROM	customer ct	RIGHT JOIN (	SELECT	o1.order_no,	g.good_name,	customer_no,	create_date,	`status`,	o2.num,	o2.price FROM	`order1` o1	RIGHT JOIN order_detail o2 ON o1.order_no = o2.order_no  LEFT JOIN good g ON g.good_no = o2.good_no WHERE o2.good_no = ?) od ON ct.customer_no = od.customer_no LIMIT ?,?";
    return queryMulti(sql, OrderDetailVO.class, good_no, begin, pageSize);
	}
}
