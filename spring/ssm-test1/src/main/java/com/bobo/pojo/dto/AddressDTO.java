package com.bobo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
@Accessors(chain = true)
public class AddressDTO implements Serializable {

  @ApiModelProperty("用户id")
  private Integer userId;

  @ApiModelProperty("详细地址")
  private String addr;

  @ApiModelProperty("手机号")
  private String phone;

  @ApiModelProperty("收货人")
  private String consignee;
}

