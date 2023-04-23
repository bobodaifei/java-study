package com.bobo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShopCar {
  private String customer_no;
  private String good_no;
  private int num;
  private int price;
  private String shop_no;

  public ShopCar(String customer_no, String good_no, int num) {
    this.customer_no = customer_no;
    this.good_no = good_no;
    this.num = num;

  }

  public ShopCar(String customer_no, String good_no) {
    this.customer_no = customer_no;
    this.good_no = good_no;
  }

  public ShopCar(String customer_no, String good_no, int num, String shop_no) {
    this.customer_no = customer_no;
    this.good_no = good_no;
    this.num = num;
    this.shop_no = shop_no;
  }

}
