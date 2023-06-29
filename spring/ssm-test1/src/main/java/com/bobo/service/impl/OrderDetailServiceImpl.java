package com.bobo.service.impl;


import com.bobo.component.convert.OrderDetailConvert;
import com.bobo.entity.OrderDetail;
import com.bobo.mapper.OrderDetailMapper;
import com.bobo.pojo.dto.OrderDetailDTO;
import com.bobo.pojo.query.OrderDetailQuery;
import com.bobo.pojo.vo.OrderDetailVO;
import com.bobo.service.OrderDetailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-19
 */
@Slf4j
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

  @Autowired
  OrderDetailMapper orderDetailMapper;

  private final OrderDetailConvert INSTANCE = OrderDetailConvert.INSTANCE;

  /**
   * 新增
   *
   * @param dto 参数
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public int addOrderDetail(OrderDetailDTO dto) {
    OrderDetail entity = INSTANCE.toEntity(dto);
    return orderDetailMapper.save(entity);
  }

  /**
   * 删除
   *
   * @param id 主键
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public int deleteById(Integer id) {
    return orderDetailMapper.removeById(id);
  }

  /**
   * 根据id获取  详情
   *
   * @param id 主键
   */
  @Override
  public OrderDetailVO selectById(Integer id) {
    OrderDetail entity = orderDetailMapper.selectById(id);
    return INSTANCE.toVO(entity);
  }


  /**
   * 分页查询
   *
   * @param query 参数
   * @return
   */
  @Override
  public PageInfo<OrderDetailVO> pageList(OrderDetailQuery query) {
    PageHelper.startPage(query.getPageNum(), query.getPageSize());
    return new PageInfo<>(INSTANCE.toVOS(orderDetailMapper.selectPage()));

  }
}
