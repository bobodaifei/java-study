package com.bobo.entity;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Emp {
  private int empno;
  private String ename;
  private String job;
  private int mgr;
  private Date hiredate;
  private BigDecimal sal;
  private BigDecimal comm;
  private int deptno;
  private String bossName;
  
}
