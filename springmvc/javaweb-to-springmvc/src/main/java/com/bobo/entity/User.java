package com.bobo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
  private String user_no;
  private String user_name;
  private String password;
  private String shop_no;
  private int power;

  private Shop shop;
  private String token;
}
