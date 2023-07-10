package com.bobo.sso.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

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
@Alias("User")
@Accessors(chain = true)
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer userId;

  private String userName;

  private String password;
}

