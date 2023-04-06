package com.bobo.study.work.work09;

import java.io.IOException;
import java.net.Socket;

public class SocketClient {
  public static void main(String[] args) {
    try {
      Socket socket = new Socket("127.0.0.1", 9999);
      new SocketOutThread(socket).start();
      new SocketInThread(socket).start();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
