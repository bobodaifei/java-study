package com.bobo.study.work.work;

/**
 * www
 */
public class www {

  public static void main(String[] args) {
    System.out.println(name1(154.2));
    System.out.println(name1(12.6));
  }
  public static int name(double num) {
    double d = (double)((int)num);
    if (num-d>0.5) {
      return (int)num+1;
    }else{
      return (int)num;
    }
  }
  public static int name1(double num) {
    double d = num%1;
    if (d>0.5) {
      return (int) num + 1;
    }else{
      return (int) num;
    }
  }


}