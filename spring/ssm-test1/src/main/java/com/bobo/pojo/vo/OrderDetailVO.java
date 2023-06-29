package com.bobo.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

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
public class OrderDetailVO implements Serializable {

  @ApiModelProperty("订单详情id")
  private Integer orderDetailId;

  @ApiModelProperty("订单编号")
  private String orderNo;

  @ApiModelProperty("商品id")
  private Integer goodsId;

  @ApiModelProperty("商品名称")
  private String goodsName;

  @ApiModelProperty("数量")
  private Integer num;

  @ApiModelProperty("单价")
  private BigDecimal price;
}

