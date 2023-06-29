package com.bobo.pojo.query;

import com.bobo.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author bobodaifei
 * @since 2023-06-19
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "User 分页参数", description = "")
public class ShopCarQuery extends BasePageQuery {

  @ApiModelProperty("购物车用户id")
  private Integer userId;

  public Integer getUserId() {
    return userId;
  }

  public String getshopCarId() {
    return "shopCar:" + userId;
  }
}