package com.bobo.study.demo.house.view;

import com.bobo.study.demo.house.entity.House;
import com.bobo.study.demo.house.service.HouseService;
import com.bobo.study.demo.house.utils.Utility;

public class HouseView {
  private boolean loop = true;
  private char key = ' ';
  HouseService houseService = new HouseService(10);

  public void mainMenu() {
    do {
      System.out.println("=============房屋出租系统===============");
      System.out.println("\t\t\t1 新增房源");
      System.out.println("\t\t\t2 查找房屋");
      System.out.println("\t\t\t3 删除房源");
      System.out.println("\t\t\t4 修改房源");
      System.out.println("\t\t\t5 房源列表");
      System.out.println("\t\t\t6 退出系统");
      System.out.println("请输入你的选择");
      key = Utility.readChar();
      switch (key) {
        case '1':
          System.out.println("\t\t\t1 新增房源");
          addHouse();
          break;
        case '2':
          System.out.println("\t\t\t2 查找房屋");
          findHouse();
          break;
        case '3':
          System.out.println("\t\t\t3 删除房源");
          deteleHouse();
          break;
        case '4':
          System.out.println("\t\t\t4 修改房源");

          break;
        case '5':
          listHouses();
          break;
        case '6':
          loop=false;
          break;

        default:
          break;
      }
    } while (loop);
  }

  public void addHouse() {
    System.out.println("=============添加房屋===============");
    System.out.print("房屋名：");
    String name = Utility.readString(10);
    System.out.print("手机号：");
    String phone = Utility.readString(12);
    System.out.print("地址：");
    String address = Utility.readString(20);
    System.out.print("租金：");
    int rent = Utility.readInt();
    System.out.print("状态：");
    String state = Utility.readString(3);
    House newHouse = new House(name, phone, address, rent, state);
    if (houseService.add(newHouse)) {
      System.out.println("添加成功");
    }else{
      System.out.println("添加失败");
    }
  }

  public void findHouse() {
    System.out.println("=============查找房屋===============");
    System.out.print("请输入房屋编号：");
    int id = Utility.readInt();
    if (id == -1) {
      return;
    }
    House house = houseService.getHouseById(id);
    if (house != null) {
      System.out.println(house.toString());
    }
  }

  public void deteleHouse() {
    System.out.println("=============删除房屋===============");
    System.out.print("请输入房屋编号：");
    int id = Utility.readInt();
    if (id ==-1) {
      return;
    }
    if (Utility.readConfirmSelection()=='N') {
      return;
    }
    if (houseService.detele(id)) {
      System.out.println("删除成功");
    } else {
      System.out.println("删除失败");
    }
  }

  public void listHouses() {
    System.out.println("=============房屋列表===============");
    House[] houses = houseService.getHouses();
    for (int i = 0; i < houses.length; i++) {
      if (houses[i]!=null) {
        System.out.println(houses[i].toString());
      }
    }
  }
}
