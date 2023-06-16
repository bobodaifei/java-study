package com.bobo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderVO {
  private String order_no;
  private String shop_no;
  private String shop_name;
  private String customer_no;
  private String name;
  private String address;
  private String mobile;
  private Date create_date;
  private int status;
  private int price;
}
