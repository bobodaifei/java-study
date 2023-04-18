package com.bobo.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderDetailVO {
  private String order_no;
  private String name;
  private String mobile;
  private String customer_name;
  private String address;
  private Date create_date;
  private int status;
  private String good_name;
  private int num;
  private int price;
  private int totalPrice;
}
