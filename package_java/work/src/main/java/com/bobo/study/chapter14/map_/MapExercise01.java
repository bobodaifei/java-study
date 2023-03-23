package com.bobo.study.chapter14.map_;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({ "all" })
public class MapExercise01 {
  public static void main(String[] args) {
    HashMap map = new HashMap();
    map.put("1", new Employee("yuan1", 20000, 1));
    map.put("2", new Employee("yuan1", 20, 2));
    map.put("3", new Employee("yuan1", 200450, 3));
    map.put("4", new Employee("yuan1", 264800, 4));
    Set set = map.entrySet();
    for (Object object : set) {
      Map.Entry entry = (Map.Entry) object;
      if (((Employee)entry.getValue()).getSal()>18000) {
        System.out.println(((Employee) entry.getValue()).toString());
      }
    }
    Iterator iterator = set.iterator();
    while (iterator.hasNext()) {
      Object object = iterator.next();
      Map.Entry entry = (Map.Entry) object;
      if (((Employee) entry.getValue()).getSal() > 18000) {
        System.out.println(((Employee) entry.getValue()).toString());
      }
    }
  

  }
}

class Employee {
  private String name;
  private double sal;
  private int id;

  public Employee(String name, double sal, int id) {
    this.name = name;
    this.sal = sal;
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getSal() {
    return sal;
  }

  public void setSal(double sal) {
    this.sal = sal;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Employee [name=" + name + ", sal=" + sal + ", id=" + id + "]";
  }

}
