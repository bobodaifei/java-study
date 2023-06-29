package com.bobo.mapper;

import com.bobo.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-19
 */
public interface OrderMapper {

  public int save(Order entity);

  public int updateByNo(Order order);

  public int  updateStatusByNo(@Param("orderNo") String orderNo, @Param("status") Integer status);

  public int removeById(Integer id);

  public Order selectById(Integer id);

  public Order selectByNo(@Param("orderNo") String orderNo);

  public List<Order> selectPage();

}
