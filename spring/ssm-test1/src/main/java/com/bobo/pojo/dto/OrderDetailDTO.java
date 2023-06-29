package com.bobo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
@Accessors(chain = true)
public class OrderDetailDTO implements Serializable {


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

