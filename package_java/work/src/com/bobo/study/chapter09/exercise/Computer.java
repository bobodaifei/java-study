package com.bobo.study.chapter09.exercise;

public class Computer {
  private String cpu;
  private int memory;
  private int disk;



  public Computer(String cpu, int memory, int disk) {
    this.cpu = cpu;
    this.memory = memory;
    this.disk = disk;
  }



  public String getDetail() {
    return "cpu=" + cpu + ", memory=" + memory + ", disk=" + disk;
  }
  
}
