package com.bobo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author bobodaifei
 * @since 2023-06-19
 */
@Data
@Alias("Address")
@Accessors(chain = true)
@ApiModel(value = "Address对象", description = "")
public class Address implements Serializable {

  private static final long serialVersionUID = 1L;

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
}

