package com.bobo.pojo.vo;

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
public class ShopCarVO implements Serializable {


  public ShopCarVO(Integer userId, Integer goodsId, Long num) {
    this.userId = userId;
    this.goodsId = goodsId;
    this.num = num;
  }

  @ApiModelProperty("用户id")
  private Integer userId;

  @ApiModelProperty("商品id")
  private Integer goodsId;

  @ApiModelProperty("商品数量")
  private Long num;

  @ApiModelProperty("商品内容")
  private GoodsVO goodsVO;
}
