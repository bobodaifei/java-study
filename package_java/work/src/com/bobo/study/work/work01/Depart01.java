package com.bobo.study.work.work01;

public class Depart01 {
  private int departId;
  private String departName;
  
  public Depart01(int departId, String departName) {
    this.departId = departId;
    this.departName = departName;
  }
  public int getDepartId() {
    return departId;
  }
  public void setDepartId(int departId) {
    this.departId = departId;
  }
  public String getDepartName() {
    return departName;
  }
  public void setDepartName(String departName) {
    this.departName = departName;
  }
  @Override
  public String toString() {
    return "Depart01 [departId=" + departId + ", departName=" + departName + "]";
  }

  
}
