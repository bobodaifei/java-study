package com.bobo.publicInterceptor.pojo.vo;

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
public class UserVO implements Serializable {

  private Integer userId;

  private String userName;

  private String password;

  private String token;

  private String ticket;


  @Override
  public String toString() {
    return "UserVO{" +
            "userId=" + userId +
            ", userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            ", token='" + token + '\'' +
            '}';
  }
}

