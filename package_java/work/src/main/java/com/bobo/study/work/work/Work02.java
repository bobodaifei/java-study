package com.bobo.study.work.work;

import java.util.Scanner;
public class Work02 {
  public static void main(String[] args) {
    Complex cp1 = new Complex();
    Complex cp2 = cp1.mul(new Complex(2, 3));
    cp2.print();
  }

}

class Complex {
  double real; // 实部
  double image; // 虚部

  Complex() { // 不带参数的构造方法
    Scanner input = new Scanner(System.in);
    double real = input.nextDouble();
    double image = input.nextDouble();
    Complex(real, image);
  }

  Complex(double real, double image) { // 带参数的构造方法
    this.real = real;
    this.image = image;
  }

  private void Complex(double real, double image) {
    this.real = real;
    this.image = image;
  }

  public Complex mul(Complex a) { // 复数相乘
    double real2 = a.getReal();
    double image2 = a.getImage();
    double newReal = real * real2 - image * image2;
    double newImage = image * real2 + real * image2;
    Complex result = new Complex(newReal, newImage);
    return result;
  }


  public void print() { // 输出
    if (image > 0) {
      System.out.println(real + " + " + image + "i");
    } else if (image < 0) {
      System.out.println(real + "" + image + "i");
    } else {
      System.out.println(real);
    }
  }

  public double getReal() {
    return real;
  }

  public void setReal(double real) {
    this.real = real;
  }

  public double getImage() {
    return image;
  }

  public void setImage(double image) {
    this.image = image;
  }
}
