package com.bobo.pojo.vo;

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
public class AddressVO implements Serializable {

  @ApiModelProperty("用户地址id")
  private Integer addrId;

  @ApiModelProperty("用户id")
  private Integer userId;

  @ApiModelProperty("详细地址")
  private String addr;

  @ApiModelProperty("手机号")
  private String phone;

  @ApiModelProperty("收货人")
  private String consignee;

  public String getAddrMsg() {
    return addr + "," + phone;
  }

  public String getPhone() {
    return phone;
  }

  public String getConsignee() {
    return consignee;
  }
}

