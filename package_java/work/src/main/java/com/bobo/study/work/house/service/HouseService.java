package com.bobo.study.work.house.service;

import com.bobo.study.work.house.entity.House;

public class HouseService {
  private House[] houses;
  private int housesNum=0;
  private int counter=1;

  
  public HouseService(int size) {
    houses = new House[size];
  }

  public boolean add(House newHouse) {
    if (housesNum==houses.length) {
      System.out.println("满员");
      return false;
    }
    for (int i = 0; i < houses.length; i++) {
      if (houses[i]==null) {
        newHouse.setId(counter++);
        houses[i]=newHouse;
        housesNum++;
        break;
      }
    }
    return true;
  }

  public boolean detele(int id) {
    if (housesNum==0) {
      System.out.println("房屋不存在");
      return false;
    }
    for (int i = 0; i < houses.length; i++) {
      if (houses[i]!=null&&houses[i].getId()==id) {
        houses[i]=null;
        housesNum--;
        return true;
      }
    }
    System.out.println("房屋不存在");
    return false;
  }

  public House getHouseById(int id) {
    if (housesNum == 0) {
      System.out.println("房屋不存在");
      return null;
    }
    for (int i = 0; i < houses.length; i++) {
      if (houses[i] != null && houses[i].getId() == id) {
        return houses[i];
      }
    }
    System.out.println("房屋不存在");
    return null;

  }

  public House[] getHouses() {
    return houses;
  }

  

}
