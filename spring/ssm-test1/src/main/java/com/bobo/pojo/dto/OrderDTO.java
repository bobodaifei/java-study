package com.bobo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-19
 */
@Data
@Accessors(chain = true)
public class OrderDTO implements Serializable {

  @ApiModelProperty("归属用户id")
  private Integer userId;

  @ApiModelProperty("订单编号")
  private String orderNo;

  @ApiModelProperty("收货地址id")
  private Integer addrId;

  @ApiModelProperty("支付方式")
  private String payMethod;

  @ApiModelProperty("商品ids")
  private String goodsIds;

  private String token;

}

