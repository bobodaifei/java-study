package com.bobo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

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
@Alias("User")
@Accessors(chain = true)
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty("用户id")
  private Integer userId;

  @ApiModelProperty("账户")
  private String userName;

  @ApiModelProperty("密码")
  private String password;

  private BigDecimal balance;

  private Integer version;
}

