package com.bobo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShopCarVO {
  private String customer_no;
  private String good_no;
  private String good_name;
  private int num;
  private int price;
  private String shop_no;
  private String shop_name;

}
