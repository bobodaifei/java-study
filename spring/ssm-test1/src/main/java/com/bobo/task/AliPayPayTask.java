package com.bobo.task;

import com.bobo.service.AliPayService;
import com.bobo.service.OrderService;
import com.bobo.utils.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AliPayPayTask {

  @Autowired
  private OrderService orderService;

  @Autowired
  JedisUtil jedisUtil;

  @Autowired
  private AliPayService aliPayService;

  /**
   * 秒 分 时 日 月 周
   * 以秒为例
   * *:每秒都执行
   * 1-3：从第一秒开始执行，到底三秒结束执行
   * 0/3：从第0秒开始，每隔3秒执行1次
   * 1,2,3：在指定的第1、2、3 执行
   * ?:不指定
   * 日和周不能同时指定，指定其中之一，则另一个设置为？
   */

  /**
   * 从第0秒开始，每隔30秒执行一次，查询创建超过5分钟并且未支付的订单
   */
//  @Scheduled(cron = "5 * * * * ?")
//  public void orderConfirm() throws Exception {
//    List<OrderVO> orderVOList = orderService.checkStatusList();
//    if (orderVOList.size() > 0) {
//      for (OrderVO orderVO : orderVOList) {
//        //如果数据库中已经不为未支付的状态，删除redis信息即可
//        System.out.println(orderVO.getOrderNo());
//        if (orderVO.getStatus().intValue() != OrderUtil.STATUS1.intValue()) {
//          jedisUtil.hdel(OrderUtil.KEY, orderVO.getOrderNo());
//          return;
//        }
//        aliPayService.checkPayStatus(orderVO.getOrderNo());
//      }
//    }
//  }
}
