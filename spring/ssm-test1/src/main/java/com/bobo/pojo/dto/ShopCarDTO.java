package com.bobo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ShopCarDTO implements Serializable {

  public ShopCarDTO(Integer userId, Integer goodsId) {
    this.userId = userId;
    this.goodsId = goodsId;
  }

  @ApiModelProperty("用户id")
  private Integer userId;

  @ApiModelProperty("商品id")
  private Integer goodsId;

  @ApiModelProperty("商品数量")
  private Long num;

  @ApiModelProperty("商品ids")
  private String goodsIds;
}
