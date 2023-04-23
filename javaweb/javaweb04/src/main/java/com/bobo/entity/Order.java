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

public class Order {
  private String order_no;
  private String shop_no;
  private String customer_no;
  private Date create_date;
  private int status;
  private int price;
}
