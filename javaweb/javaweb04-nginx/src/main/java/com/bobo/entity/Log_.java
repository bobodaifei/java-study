package com.bobo.entity;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Log_ {
  private int log_no;
  private String customer_no;
  private String ope_type;
  private Timestamp create_time;
  private String code;
  private String message;

  public Log_(String customer_no) {
    this.customer_no = customer_no;
  }
  
}
