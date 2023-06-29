package com.bobo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author bobodaifei
 * @since 2023-06-19
 */
@Data
@Alias("OrderDetail")
@Accessors(chain = true)
@ApiModel(value = "OrderDetail对象", description = "")
public class OrderDetail implements Serializable {

  private static final long serialVersionUID = 1L;

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

