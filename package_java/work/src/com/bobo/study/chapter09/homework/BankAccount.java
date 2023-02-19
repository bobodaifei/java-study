package com.bobo.study.chapter09.homework;

public class BankAccount {
  private double balance;

  public BankAccount(double initialBalance) {
    this.balance = initialBalance;
  }

  public void deposit(double amount) {
    balance += amount;
  }

  public void withdraw(double amount) {
    balance -= amount;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }
}

class CheckingAccount extends BankAccount {

  public CheckingAccount(double initialBalance) {
    super(initialBalance);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void deposit(double amount) {
    // TODO Auto-generated method stub
    super.deposit(amount - 1);
  }

  @Override
  public void withdraw(double amount) {
    // TODO Auto-generated method stub
    super.withdraw(amount + 1);
  }

}

class SavingsAccount extends BankAccount {
  private int surplus = 3;
  private double rate = 0.01;

  public SavingsAccount(double initialBalance) {
    super(initialBalance);
  }

  public void deposit(double amount) {
    if (surplus>0) {
      super.deposit(amount);
      this.surplus--;
    }else{
      super.deposit(amount-1);
    }
  }

  @Override
  public void withdraw(double amount) {
    if (surplus > 0) {
      super.withdraw(amount);
      this.surplus--;
    } else {
      super.withdraw(amount + 1);
    }
  }

  public void earnMonthlylnterestR() {
    this.surplus=3;
    super.deposit(getBalance()*rate);
  }

  public int getSurplus() {
    return surplus;
  }

  public void setSurplus(int surplus) {
    this.surplus = surplus;
  }

  public double getRate() {
    return rate;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }

  

}

class Test001 {
  public static void main(String[] args) {
    CheckingAccount foo = new CheckingAccount(1000);
    foo.withdraw(10);
    System.out.println(foo.getBalance());
    foo.deposit(100);
    System.out.println(foo.getBalance());

    SavingsAccount foo1 = new SavingsAccount(1000);
    foo1.deposit(100);
    System.out.println(foo1.getBalance());
    foo1.deposit(100);
    System.out.println(foo1.getBalance());
    foo1.deposit(100);
    System.out.println(foo1.getBalance());
    foo1.deposit(100);
    System.out.println(foo1.getBalance());

    //定时器
    foo1.earnMonthlylnterestR();
    System.out.println(foo1.getBalance());
  }
}
