package com.bobo.study.chapter10;

public class HomeWokr06 {
  public static void main(String[] args) {

    Persion p1 = new Persion("唐僧", VehicleFactory.getHorse());
    p1.passRiver();
    p1.common();
    p1.passRiver();
    p1.common();

  }
}

interface Vehicles {
  public void work();
}

class Horse implements Vehicles {

  @Override
  public void work() {
    // TODO Auto-generated method stub
    System.out.println("马在工作");
  }

}

class Boat implements Vehicles {

  @Override
  public void work() {
    System.out.println("船在工作");

  }

}

class VehicleFactory {
  private static Horse horse = new Horse();

  private VehicleFactory() {
  }

  public static Horse getHorse() {
    System.out.println("获取了马");
    return horse;
  }

  public static Boat getBoat() {
    System.out.println("获取了船");
    return new Boat();
  }
}

class Persion {
  private String name;
  private Vehicles vehicles;

  public Persion(String name, Vehicles vehicles) {
    this.name = name;
    this.vehicles = vehicles;
  }

  public void passRiver() {
    if (!(vehicles instanceof Boat)) {
      vehicles=VehicleFactory.getBoat();
    }
    vehicles.work();
  }
  public void common() {
    if (!(vehicles instanceof Horse)) {
      vehicles=VehicleFactory.getHorse();
    }
    vehicles.work();
  }

}