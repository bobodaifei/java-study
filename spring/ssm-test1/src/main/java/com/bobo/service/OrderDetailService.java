package com.bobo.service;


import com.bobo.pojo.dto.OrderDetailDTO;
import com.bobo.pojo.query.OrderDetailQuery;
import com.bobo.pojo.vo.OrderDetailVO;
import com.github.pagehelper.PageInfo;

/**
 * @author bobodaifei
 * @since 2023-06-19
 */
public interface OrderDetailService {

  /**
   * 新增
   * @param dto 参数
   */
  public int addOrderDetail(OrderDetailDTO dto);

  /**
   * 删除
   * @param id 主键
   */
  public int deleteById(Integer id);

  /**
   * 根据id获取  详情
   * @param id 主键
   */
  public OrderDetailVO selectById(Integer id);

  /**
   * 分页查询
   * @param query 参数
   * @return
   */
  public PageInfo<OrderDetailVO> pageList(OrderDetailQuery query);
}
