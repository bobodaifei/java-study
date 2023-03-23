package com.bobo.study.chapter14.set_;

import java.util.HashSet;

public class HashSet02 {
  public static void main(String[] args) {
    HashSet ssSet = new HashSet();
    ssSet.add(new Employee01("bobo", 10, new MyDate("2000", "3", "12")));
    ssSet.add(new Employee01("bobo", 10, new MyDate("2000", "3", "12")));
    ssSet.add(new Employee01("bobo", 10, new MyDate("2000", "3", "12")));
    ssSet.add(new Employee01("bobo1", 10, new MyDate("2000", "3", "12")));
    ssSet.add(new Employee01("bobo", 10, new MyDate("2000", "3", "121")));
    System.out.println(ssSet);
  }
}

class Employee01 {
  private String name;
  private int sal;
  private MyDate birthday;

  public Employee01(String name, int sal, MyDate birthday) {
    this.name = name;
    this.sal = sal;
    this.birthday = birthday;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Employee01 other = (Employee01) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (birthday == null) {
      if (other.birthday != null)
        return false;
    } else if (!birthday.equals(other.birthday))
      return false;
    return true;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSal() {
    return sal;
  }

  public void setSal(int sal) {
    this.sal = sal;
  }

  public MyDate getBirthday() {
    return birthday;
  }

  public void setBirthday(MyDate birthday) {
    this.birthday = birthday;
  }

}

class MyDate {
  private String year;
  private String month;
  private String day;

  

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((year == null) ? 0 : year.hashCode());
    result = prime * result + ((month == null) ? 0 : month.hashCode());
    result = prime * result + ((day == null) ? 0 : day.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    MyDate other = (MyDate) obj;
    if (year == null) {
      if (other.year != null)
        return false;
    } else if (!year.equals(other.year))
      return false;
    if (month == null) {
      if (other.month != null)
        return false;
    } else if (!month.equals(other.month))
      return false;
    if (day == null) {
      if (other.day != null)
        return false;
    } else if (!day.equals(other.day))
      return false;
    return true;
  }

  public MyDate(String year, String month, String day) {
    this.year = year;
    this.month = month;
    this.day = day;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }

}