package com.bobo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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
