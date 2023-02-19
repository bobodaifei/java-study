package com.bobo.study.chapter09.exercise;

public class NotePad extends Computer{
  private String color;

  public NotePad(String cpu, int memory, int disk, String color) {
    super(cpu, memory, disk);
    this.color = color;
  }
  
  public void printInfo() {
    System.out.println(getDetail() + "brand" + this.color);
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  
}
