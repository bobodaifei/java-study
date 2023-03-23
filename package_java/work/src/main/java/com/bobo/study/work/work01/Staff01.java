package com.bobo.study.work.work01;

import java.util.Date;

public class Staff01 {
  private int staffId;
  private String staffName;
  private int staffDepartId;
  private Date staffInTime;

  public Staff01() {
  }

  public Staff01(int staffId, String staffName, int staffDepartId, Date staffInTime) {
    this.staffId = staffId;
    this.staffName = staffName;
    this.staffDepartId = staffDepartId;
    this.staffInTime = staffInTime;
  }

  public int getStaffId() {
    return staffId;
  }

  public void setStaffId(int staffId) {
    this.staffId = staffId;
  }

  public String getStaffName() {
    return staffName;
  }

  public void setStaffName(String staffName) {
    this.staffName = staffName;
  }

  public int getStaffDepartId() {
    return staffDepartId;
  }

  public void setStaffDepartId(int staffDepartId) {
    this.staffDepartId = staffDepartId;
  }

  public Date getStaffInTime() {
    return staffInTime;
  }

  public void setStaffInTime(Date staffInTime) {
    this.staffInTime = staffInTime;
  }

  @Override
  public String toString() {
    return "Staff01 [staffId=" + staffId + ", staffName=" + staffName + ", staffDepartId=" + staffDepartId
        + ", staffInTime=" + staffInTime + "]";
  }



 
}
