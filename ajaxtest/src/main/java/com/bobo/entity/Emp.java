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
  private BigDecimal COMM;
  private int deptno;
  private String bossName;
  public Emp( String ename, String job, int mgr, Date hiredate, BigDecimal sal, BigDecimal COMM, int deptno) {
    this.ename = ename;
    this.job = job;
    this.mgr = mgr;
    this.hiredate = hiredate;
    this.sal = sal;
    this.COMM = COMM;
    this.deptno = deptno;
  }
  
}
