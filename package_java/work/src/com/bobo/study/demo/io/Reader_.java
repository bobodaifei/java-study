package com.bobo.study.demo.io;

//修饰模式，即在原有方法进行增强
public abstract class Reader_ {
  public abstract void read();

}

// 节点流
class FileReader_ extends Reader_ {

  @Override
  public void read() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'read'");
  }

}

// 节点流
class StringReader_ extends Reader_ {

  @Override
  public void read() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'read'");
  }

}

//
class BufferedReader_ extends Reader_ {

  private Reader_ reader_;

  public BufferedReader_(Reader_ reader_) {
    this.reader_ = reader_;
  }

  // 多次处理文件
  public void readFiles(int num) {
    for (int i = 0; i < num; i++) {
      reader_.read();
    }
  }

  @Override
  public void read() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'read'");
  }

}
