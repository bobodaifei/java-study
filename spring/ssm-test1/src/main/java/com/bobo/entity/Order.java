package com.bobo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author bobodaifei
 * @since 2023-06-19
 */
@Data
@Alias("Order")
@Accessors(chain = true)
@ApiModel(value = "Order对象", description = "")
public class Order implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty("订单id")
  private Integer orderId;

  @ApiModelProperty("订单编号")
  private String orderNo;

  @ApiModelProperty("归属用户id")
  private Integer userId;

  @ApiModelProperty("收货信息")
  private String addrMsg;

  @ApiModelProperty("订单状态(1 待支付、2 订单超时、3 取消订单、4 支付成功、5 待发货、6 已完成)")
  private Integer status;

  @ApiModelProperty("下单时间")
  private Date createTime;

  @ApiModelProperty("支付时间")
  private Date payTime;

  @ApiModelProperty("物流编号")
  private String logisticsNo;

  @ApiModelProperty("订单价格")
  private BigDecimal totalPrice;

  @ApiModelProperty("支付方式")
  private String payMethod;
}

