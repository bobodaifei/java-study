package com.bobo.mapper;

import com.bobo.entity.OrderDetail;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-19
 */
public interface OrderDetailMapper {

  public int save(OrderDetail entity);


  public int removeById(Integer id);

  public OrderDetail selectById(Integer id);

  public List<OrderDetail> selectPage();
}
