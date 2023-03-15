package com.bobo.study.work.work02;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.util.Scanner;

public class Work01 {
  public static void main(String[] args) {
    // Method01.octal(15, 2);

    // int[] arr = { 6, 5, 4, 9, 8, 7, 3, 2, 1 };
    // Method01.arrSort(arr);
    // for (int i : arr) {
    // System.out.println(i);
    // }

    // Method01.whileDoWhile();
    // Method01.switchAnimal();

    // System.out.println(Method01.factorial(6));
    // Method01.multipTable();
    Method01.toDate(23501);
    // System.out.println(Method01.factorial1(200));

  }
}

class Method01 {
  // 1、编写一个方法，可以完成任意十进制数，朝十进制以内任意进制转化。（5）
  public static void octal(int i, int j) {
    if (j <= 1) {
      System.out.println("进制错误");
    }
    int curr = i;
    String rem = "";
    while (curr != 0) {
      rem = (curr % j) + rem;
      curr = curr / j;
    }
    System.out.println(rem);
  }
  // 0 0
  // 1 1
  // 2 10
  // 3 11
  // 4 100
  // 5 101
  // 6 110
  // 7 111
  // 8 1000
  // 9 1001
  // 10 1010

  // 2、编写一个方法，将任意int类型数组中的数据，按降序排列。（5）
  public static void arrSort(int[] arr) {

    int temp;
    for (int i = 0; i < arr.length - 1; i++) {
      boolean flag = true;
      for (int j = 0; j < arr.length - i - 1; j++) {
        if (arr[j] < arr[j + 1]) {
          temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
          flag = false;
        }
      }
      if (flag) {
        break;
      }
    }
  }

  // 3、编写一个程序，验证while循环和do{}while()循环的区别（5）
  public static void whileDoWhile() {
    // 第四次要债才还钱
    int i = 1;
    while ((i++) < 4) {
      System.out.println("你说不还钱，然后打你一顿");
    }
    // 第四次要债才还钱
    int j = 1;
    do {
      System.out.println("先打你一顿，然后问你还不还钱");
    } while ((j++) < 4);

  }

  // 4、编写一个程序，要求用switch{}case,参数String
  // animal，如果输入的animal是“小鸡”就控制台输出“你干嘛！哎哟”，如果输入“小狗”，就输出“咯咯哒”，如果输入“小猫”，就输出“汪汪汪”，如果输入是“小狼”，就输出“喵喵喵”（5）
  public static void switchAnimal() {
    Scanner scanner = new Scanner(System.in);
    String animal = scanner.next();
    switch (animal) {
      case "小鸡":
        System.out.println("你干嘛！哎哟");
        break;
      case "小狗":
        System.out.println("咯咯哒");
        break;
      case "小猫":
        System.out.println("汪汪汪");
        break;
      case "小狼":
        System.out.println("喵喵喵");
        break;
      default:
        System.out.println("啥都不是");
        break;
    }
  }

  // 5、写个程序，求任何int类型数据的阶乘（5）
  public static int factorial(int i) {
    if (i < 0) {
      System.out.println("数据有误");
      return -1;
    }
    if (i == 1 || i == 0) {
      return 1;
    }
    return i * factorial(i - 1);
  }

  // 6、写一个程序，打印乘法口诀（5）
  public static void multipTable() {
    for (int i = 1; i <= 9; i++) {
      for (int j = 1; j <= i; j++) {
        System.out.print(i + "*" + j + "=" + i * j + " ");
      }
      System.out.println();
    }
  }

  // 7、定义一个方法，输入一个long型的数据，能够完成时分秒的转化，输出“xx时：xx分：xx秒”
  public static void toDate(long l) {
    if (l < 0) {
      System.out.println("输入错误");
      return;
    }
    int hh = 0;
    int mm = 0;
    int ss = 0;

    hh = (int) ((l / 60) / 60) % 60;
    mm = (int) l / 60 % 60;
    ss = (int) l % 60;
    String str = String.format("%d 时：%d 分：%d秒", hh, mm, ss);
    System.out.println(str);
    Formatter formatter = new Formatter();
    Formatter format = formatter.format("%d 时：%d 分：%d秒", hh, mm, ss);
    System.out.println(format);
  }

  // 8、定义一个方法，输入int类型数据，输出该数据的阶乘，要求该参数在200以上。
  public static BigDecimal factorial1(int i) {
    if (i < 200) {
      System.out.println("数据小于200");
      return new BigDecimal(-1);
    }
    BigDecimal big = new BigDecimal(i);
    for (int j = i - 1; j > 0; j--) {
      big = big.multiply(new BigDecimal(j));
    }
    return big;
  }

}