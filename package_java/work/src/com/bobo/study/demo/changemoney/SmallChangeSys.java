package com.bobo.study.demo.changemoney;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SmallChangeSys {
  public static void main(String[] args) {

    // 循环
    boolean loop = true;
    // new一个数据存放list
    List<RevenueExpenditure> list = new ArrayList<>();
    double money = 0;
    App app = new App();
    do {
      app.mainMenu();
      Scanner scanner = new Scanner(System.in);
      System.out.println("请选择1-4");
      switch (scanner.nextInt()) {
        case 1:
          app.detail(list);
          break;
        case 2:
          money = app.income(list, money);
          break;
        case 3:
          money = app.pay(list, money);
          break;
        case 4:
          System.out.println("4 退出");

          loop = false;
          break;

        default:
          System.out.println("输入错误请重新输入");
          break;
      }
    } while (loop);
  }
}

class App {
  public void mainMenu() {
    System.out.println("======零钱通菜单======");
    System.out.println("1 零钱通明细");
    System.out.println("2 收益入账");
    System.out.println("3 消费");
    System.out.println("4 退出");
  }

  public void detail(List<RevenueExpenditure> list) {
    System.out.println("------零钱通明细--------");

    for (int i = 0; i < list.size(); i++) {
      if (list.get(i) instanceof Expenditure) {
        System.out.println(((Expenditure) list.get(i)).toString());
      } else if (list.get(i) instanceof Revenue) {
        System.out.println(((Revenue) list.get(i)).toString());
      } else {
        System.out.println("未知格式的收支");
      }
    }
  }

  public double income(List<RevenueExpenditure> list, double money) {
    System.out.println("------收益入账--------");

    Scanner scanner = new Scanner(System.in);
    System.out.print("请输入收益金额");
    double inMoney = scanner.nextDouble();

    if (inMoney < 0) {
      System.out.println("输入有误");
      return money;
    }

    Date date = new Date();
    RevenueExpenditure revenueExpenditure = new Revenue(inMoney, date, money + inMoney);
    list.add(revenueExpenditure);
    return money+inMoney;
  }

  public double pay(List<RevenueExpenditure> list, double money) {
    System.out.println("------消费--------");

    Scanner scanner = new Scanner(System.in);
    System.out.print("请输入消费项目");
    String consumption = scanner.next();
    System.out.print("请输入消费金额");
    double payMoney = scanner.nextDouble();

    if (payMoney < 0) {
      System.out.println("输入有误");
      return money;
    }
    if (money - payMoney < 0) {
      System.out.println("余额为负");
      return money;
    }

    Date date = new Date();
    RevenueExpenditure revenueExpenditure = new Expenditure(payMoney, date, money - payMoney, consumption);
    list.add(revenueExpenditure);
    return money-payMoney;
  }

}

class RevenueExpenditure {
  private double money;
  private Date date;
  private double currentBalance;

  public RevenueExpenditure(double money, Date date, double currentBalance) {
    this.money = money;
    this.date = date;
    this.currentBalance = currentBalance;
  }

  @Override
  public String toString() {
    return "RevenueExpenditure [money=" + money + ", date=" + date + ", currentBalance=" + currentBalance + "]";
  }

  public double getMoney() {
    return money;
  }

  public void setMoney(double money) {
    this.money = money;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public double getCurrentBalance() {
    return currentBalance;
  }

  public void setCurrentBalance(double currentBalance) {
    this.currentBalance = currentBalance;
  }

}

class Revenue extends RevenueExpenditure {

  public Revenue(double money, Date date, double currentBalance) {
    super(money, date, currentBalance);
    // TODO Auto-generated constructor stub
  }

  @Override
  public String toString() {
    return "收益入账\t+" + this.getMoney() + "\t" + this.getDate() + "\t" + this.getCurrentBalance();
  }

}

class Expenditure extends RevenueExpenditure {
  private String consumption;

  public Expenditure(double money, Date date, double currentBalance, String consumption) {
    super(money, date, currentBalance);
    this.consumption = consumption;
  }

  @Override
  public String toString() {
    return this.consumption + "\t-" + this.getMoney() + "\t" + this.getDate() + "\t" + this.getCurrentBalance();
  }

  public String getConsumption() {
    return consumption;
  }

  public void setConsumption(String consumption) {
    this.consumption = consumption;
  }
}
