package com.bobo.study.chapter09;

public class Rncap01 {
  public static void main(String[] args) {
    Persion p1 = new Persion();
    p1.setName("xiaozo");
    p1.setAge(11);
    p1.setSalary(123.2);
    System.out.println(p1.toString()); 
  }
}

/**
 * Persion
 */
class Persion {
  public String name;
  private int age;
  private double salary;

  public Persion(){

  }
  
  public Persion(String name, int age, double salary) {
    setName(name);
    setAge(age);
    setSalary(salary);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    if (age >= 1 && age <= 150) {
      this.age = age;
    }else{
      this.age = 20;
    }
    
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  @Override
  public String toString() {
    return "Persion [name=" + name + ", age=" + age + ", salary=" + salary + "]";
  }

}
