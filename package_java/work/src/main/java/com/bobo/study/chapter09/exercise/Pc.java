package com.bobo.study.chapter09.exercise;

public class Pc extends Computer{
  private String brand;

  public Pc(String cpu, int memory, int disk, String brand) {
    super(cpu, memory, disk);
    this.brand = brand;
  }

  public void printInfo() {
    System.out.println(getDetail() + "brand" + this.brand);
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  
  
}
