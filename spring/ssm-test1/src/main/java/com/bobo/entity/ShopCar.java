package com.bobo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@Alias("ShopCar")
@Accessors(chain = true)
@ApiModel(value = "ShopCar对象", description = "")
public class ShopCar implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty("购物车id")
  private String shopCarId;

  @ApiModelProperty("商品id")
  private Integer goodsId;

  @ApiModelProperty("商品数量")
  private Long num;
}