package com.bobo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Stock {
  private String good_no;
  private String shop_no;
  private int is_online;
  private int stock;
  public Stock(String good_no, int is_online, int stock) {
    this.good_no = good_no;
    this.is_online = is_online;
    this.stock = stock;
  }

  
}
