package com.bobo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StockVO {
  private String good_no;
  private String good_name;
  private int price;
  private int stock;
  private int is_online;

}