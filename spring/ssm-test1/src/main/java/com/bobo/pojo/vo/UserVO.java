package com.bobo.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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
@Data
@Accessors(chain = true)
public class UserVO implements Serializable {

  @ApiModelProperty("用户id")
  private Integer userId;

  @ApiModelProperty("账户")
  private String userName;

  @ApiModelProperty("密码")
  private String password;

  @ApiModelProperty("token")
  private String token;

  private BigDecimal balance;

  private Integer version;
}

