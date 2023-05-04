package com.bobo.entity;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Log_ {
  private int log_no;
  private int empno;
  private String ope_type;
  private Timestamp create_time;
  private String code;
  private String message;

  public Log_(int empno) {
    this.empno = empno;
  }
  
}
