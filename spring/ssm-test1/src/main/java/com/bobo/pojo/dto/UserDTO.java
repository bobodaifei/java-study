package com.bobo.pojo.dto;

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
public class UserDTO implements Serializable {

  @ApiModelProperty("账户")
  private String userName;

  @ApiModelProperty("密码")
  private String password;
}

