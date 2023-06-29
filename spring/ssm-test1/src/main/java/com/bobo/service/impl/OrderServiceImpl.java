package com.bobo.service.impl;


import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.bobo.component.convert.OrderConvert;
import com.bobo.config.AliPayConfig;
import com.bobo.entity.Order;
import com.bobo.mapper.OrderMapper;
import com.bobo.pojo.dto.OrderDTO;
import com.bobo.pojo.dto.OrderDetailDTO;
import com.bobo.pojo.dto.WXPayDTO;
import com.bobo.pojo.query.OrderQuery;
import com.bobo.pojo.vo.AddressVO;
import com.bobo.pojo.vo.OrderVO;
import com.bobo.pojo.vo.ShopCarVO;
import com.bobo.service.*;
import com.bobo.utils.JedisUtil;
import com.bobo.utils.MapStructUtil;
import com.bobo.utils.OrderUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

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
public class OrderServiceImpl implements OrderService {

  @Autowired
  OrderMapper orderMapper;

  @Autowired
  OrderDetailService orderDetailService;

  @Autowired
  ShopCarService shopCarService;

  @Autowired
  GoodsService goodsService;

  @Autowired
  AddressService addressService;

  @Autowired
  WXService wxService;

  @Autowired
  AliPayConfig aliPayConfig;

  @Autowired
  JedisUtil jedisUtil;


  private final OrderConvert INSTANCE = OrderConvert.INSTANCE;

  /**
   * 新增
   *
   * @param dto 参数
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public String placeOrder(OrderDTO dto) {
    String[] goodIdArr = dto.getGoodsIds().split(",");
//    获取要提交的购物车信息
    List<ShopCarVO> shopCarVOS = shopCarService.selectByIds(dto.getUserId(), goodIdArr);
//    计算总金额
    BigDecimal totalPrice = shopCarVOS.stream().map(shopCar -> shopCar.getGoodsVO().getPrice().multiply(new BigDecimal(shopCar.getNum()))).reduce(BigDecimal.ZERO, BigDecimal::add);
//    获取收货信息
    AddressVO addressVO = addressService.selectById(dto.getAddrId());
//    生成订单no
    String orderNo = IdUtil.getSnowflake().nextIdStr();
    Order entity = new Order();
    entity.setOrderNo(orderNo);
    entity.setUserId(dto.getUserId());
    entity.setAddrMsg(addressVO.getAddrMsg());
    entity.setStatus(OrderUtil.STATUS1);
    entity.setCreateTime(new Date());
    entity.setTotalPrice(totalPrice);
    orderMapper.save(entity);

    Iterator<ShopCarVO> iterator = shopCarVOS.iterator();
    while (iterator.hasNext()) {
      ShopCarVO shopCarVO = iterator.next();
      OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
      orderDetailDTO.setOrderNo(orderNo);
      orderDetailDTO.setGoodsId(shopCarVO.getGoodsId());
      orderDetailDTO.setGoodsName(shopCarVO.getGoodsVO().getGoodsName());
      orderDetailDTO.setNum(shopCarVO.getNum().intValue());
      orderDetailDTO.setPrice(shopCarVO.getGoodsVO().getPrice());
      //生成订单详情
      orderDetailService.addOrderDetail(orderDetailDTO);
      //减库存
      goodsService.modifyGoodsStock(shopCarVO.getGoodsId(), shopCarVO.getNum().intValue());
    }
    //清除购物车
    jedisUtil.hdel(MapStructUtil.userIdToShopCarId(dto.getUserId()), goodIdArr);
    //加入redis用于定时扫描支付状态,35分钟的超时时间
    jedisUtil.hset(OrderUtil.KEY, entity.getOrderNo(), DateUtil.offset(entity.getCreateTime(), DateField.MINUTE, OrderUtil.TIMEOUT).toString());
    return orderNo;
  }

  /**
   * 不同支付方式
   *
   * @param dto 参数
   */
  public String toPay(OrderDTO dto) {
    Order order = orderMapper.selectByNo(dto.getOrderNo());
    String payUrl = null;
    if ("支付宝".equals(dto.getPayMethod())) {
      payUrl = aliPayConfig.getPayUrl() + "?subject=" + "111" + "&outTradeNo=" + order.getOrderNo().substring(0, 3) + "&totalAmount=" + order.getTotalPrice();
    }
    if ("微信".equals(dto.getPayMethod())) {
      WXPayDTO wxPayDTO = new WXPayDTO();
      wxPayDTO.setBody("腾讯充值中心-QQ会员充值");
      wxPayDTO.setOut_trade_no(order.getOrderNo());
      wxPayDTO.setTotal_fee(order.getTotalPrice().intValue());
      payUrl = wxService.pay(wxPayDTO);
    }
    return payUrl;
  }

  /**
   * 修改
   *
   * @param order 参数
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public int modifyOrder(Order order) {
    jedisUtil.hdel(OrderUtil.KEY, order.getOrderNo());
    return orderMapper.updateByNo(order);
  }


  /**
   * 检查是否超时
   *
   * @param isDelete 是否直接删除redis
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public void checkDelTimeOut(String orderNo, Integer status, boolean isDelete) {
    Date date = DateUtil.parse(jedisUtil.hget(OrderUtil.KEY, orderNo));
    //直接删除redis 并修改订单状态
    if (isDelete) {
      jedisUtil.hdel(OrderUtil.KEY, orderNo);
      orderMapper.updateStatusByNo(orderNo, status);
      return;
    }
    //如果不指定删除，则判断是否真正超时，并修改订单状态
    if (date.before(new Date())) {
      jedisUtil.hdel(OrderUtil.KEY, orderNo);
      orderMapper.updateStatusByNo(orderNo, status);
    }
    //未超时，则什么都不做
  }

  /**
   * 删除
   *
   * @param id 主键
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public int deleteById(Integer id) {
    return orderMapper.removeById(id);
  }

  /**
   * 根据id获取  详情
   *
   * @param id 主键
   */
  @Override
  public OrderVO selectById(Integer id) {
    Order entity = orderMapper.selectById(id);
    return INSTANCE.toVO(entity);
  }

  /**
   * 获取超时的订单
   */
  public List<OrderVO> checkStatusList() {
    Map<String, String> allOrderList = jedisUtil.hgetall(OrderUtil.KEY);
    List<OrderVO> list = new ArrayList<>();
    for (Map.Entry<String, String> order : allOrderList.entrySet()) {
      list.add(selectByNo(order.getKey()));
    }
    return list;
  }

  /**
   * 根据no获取  详情
   *
   * @param orderNo
   */
  @Override
  public OrderVO selectByNo(String orderNo) {
    Order entity = orderMapper.selectByNo(orderNo);
    return INSTANCE.toVO(entity);
  }


  /**
   * 分页查询
   *
   * @param query 参数
   * @return
   */
  @Override
  public PageInfo<OrderVO> pageList(OrderQuery query) {
    PageHelper.startPage(query.getPageNum(), query.getPageSize());
    return new PageInfo<>(INSTANCE.toVOS(orderMapper.selectPage()));
  }
}
