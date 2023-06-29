package com.bobo.service;


import com.bobo.entity.Order;
import com.bobo.pojo.dto.OrderDTO;
import com.bobo.pojo.query.OrderQuery;
import com.bobo.pojo.vo.OrderVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author bobodaifei
 * @since 2023-06-19
 */
public interface OrderService {

  /**
   * 新增
   *
   * @param dto 参数
   */
  public String placeOrder(OrderDTO dto);

  /**
   * 不同支付方式
   *
   * @param dto 参数
   */
  public String toPay(OrderDTO dto);

  /**
   * 修改
   *
   * @param order 参数
   */
  public int modifyOrder(Order order);

  /**
   * 是否直接删除，或超时直接删除
   *
   * @param isDelete 是否直接删除redis
   */
  public void checkDelTimeOut(String orderNo, Integer status, boolean isDelete);

  /**
   * 删除
   *
   * @param id 主键
   */
  public int deleteById(Integer id);

  /**
   * 根据id获取  详情
   *
   * @param id 主键
   */
  public OrderVO selectById(Integer id);

  /**
   * 获取超时的订单
   */
  public List<OrderVO> checkStatusList();

  /**
   * 根据no获取  详情
   *
   * @param orderNo
   */
  OrderVO selectByNo(String orderNo);

  /**
   * 分页查询
   *
   * @param query 参数
   * @return
   */
  public PageInfo<OrderVO> pageList(OrderQuery query);


}
