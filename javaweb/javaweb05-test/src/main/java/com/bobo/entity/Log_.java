package com.bobo.entity;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Log_ {
  private int log_no;
  private String user_no;
  private String ope_type;
  private Timestamp create_time;
  private String code;
  private String message;

  public Log_(String user_no) {
    this.user_no = user_no;
  }
  
}
