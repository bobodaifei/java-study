package com.bobo.study.demo.work01;

import java.util.Date;

public class HomeWork01 {
  
  public static void main(String[] args) {
    Staff staff = new Staff(1, "staff01", 10, new Date());
    System.out.println(staff);

    
  }
  
}



class Staff{
  private int id;
  private String name;
  private int departId;
  private Date inTime;
  public Staff(int id, String name, int departId, Date inTime) {
    this.id = id;
    this.name = name;
    this.departId = departId;
    this.inTime = inTime;
  }
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getDepartId() {
    return departId;
  }
  public void setDepartId(int departId) {
    this.departId = departId;
  }
  public Date getInTime() {
    return inTime;
  }
  public void setInTime(Date inTime) {
    this.inTime = inTime;
  }
  @Override
  public String toString() {
    return "Staff [id=" + id + ", name=" + name + ", departId=" + departId + ", inTime=" + inTime + "]";
  }
  
}
