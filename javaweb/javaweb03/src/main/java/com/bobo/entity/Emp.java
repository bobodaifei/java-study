package com.bobo.entity;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Emp {
  private Integer empno;
  private String ename;
  private String job;
  private int mgr;
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
  private Date hiredate;
  private BigDecimal sal;
  private BigDecimal cOMM;
  private int deptno;
  
  private String dname;
  private String bossName;
  private String token;
  
  public Emp(Integer empno, String ename, String job, int mgr, Date hiredate, BigDecimal sal, BigDecimal cOMM,
      int deptno) {
    this.empno = empno;
    this.ename = ename;
    this.job = job;
    this.mgr = mgr;
    this.hiredate = hiredate;
    this.sal = sal;
    this.cOMM = cOMM;
    this.deptno = deptno;
  }

  public Emp( String ename, String job, int mgr, Date hiredate, BigDecimal sal, BigDecimal cOMM, int deptno) {
    this.ename = ename;
    this.job = job;
    this.mgr = mgr;
    this.hiredate = hiredate;
    this.sal = sal;
    this.cOMM = cOMM;
    this.deptno = deptno;
  }
  
}
