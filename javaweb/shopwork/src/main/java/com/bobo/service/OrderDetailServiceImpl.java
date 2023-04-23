package com.bobo.service;

import java.util.List;

import com.bobo.dao.OrderDetailDao;
import com.bobo.entity.OrderDetail;
import com.bobo.entity.OrderDetailVO;

public class OrderDetailServiceImpl implements OrderDetailService {

  private OrderDetailDao orderDetailDao = new OrderDetailDao();

  @Override
  public int add(OrderDetail orderDetail) {
    String sql = "insert into order_detail values ( ?, ?, ?, ?)";
    return orderDetailDao.update(sql, orderDetail.getOrder_no(), orderDetail.getGood_no(), orderDetail.getNum(),
        orderDetail.getPrice());
  }

  @Override
  public long selectCount(String order_no) {
    String sql = "SELECT count(*) as total FROM order_detail where order_no = ?";
    return (Long) orderDetailDao.queryScalar(sql, order_no);
  }

  @Override
  public List<OrderDetailVO> selectPage(int begin, int pageSize, String order_no) {
    String sql = "SELECT od.order_no,	`name`,	mobile,	address, create_date,	`status`,	good_name,	num,	price,	num*price as totalPrice FROM	customer ct	RIGHT JOIN (	SELECT o1.order_no,	g.good_name,	customer_no,	create_date,	`status`,	o2.num,	o2.price FROM	`order1` o1	RIGHT JOIN order_detail o2 ON o1.order_no = o2.order_no	LEFT JOIN good g ON g.good_no = o2.good_no WHERE	o1.order_no = ? 	) od ON ct.customer_no = od.customer_no limit ?,?";
    return orderDetailDao.queryMulti(sql, OrderDetailVO.class, order_no, begin, pageSize);
  }

}
