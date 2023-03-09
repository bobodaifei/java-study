package com.bobo.study.chapter14.set_;

import java.util.HashSet;
import java.util.Set;

public class HashSetExercise {
  public static void main(String[] args) {
    Set set = new HashSet();
    set.add(new Employee("bobo", 0));
    set.add(new Employee("bobo", 0));
    set.add(new Employee("bobo", 0));
    set.add(new Employee("bobo", 0));
    System.out.println(set);
  }
}

class Employee{
  private String name;
  private int age;
  public Employee(String name, int age) {
    this.name = name;
    this.age = age;
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + age;
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
    Employee other = (Employee) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (age != other.age)
      return false;
    return true;
  }
  
}
