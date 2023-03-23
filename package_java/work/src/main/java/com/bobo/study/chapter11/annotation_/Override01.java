package com.bobo.study.chapter11.annotation_;

public class Override01 {
  @SuppressWarnings(value = { "" })
  public static void main(String[] args) {
    Son foo = new Son();
    foo.toString();
  }
}

class Father{
  public void funck() {
    
  }
}
@Deprecated
class Son extends Father{

  @Override
  public void funck() {
    // TODO Auto-generated method stub
    super.funck();
  }
  
}