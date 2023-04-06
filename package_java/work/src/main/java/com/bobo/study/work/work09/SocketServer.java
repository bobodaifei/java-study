package com.bobo.study.work.work09;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

  // 编程题
  // 编写一个程序，能够用多线程，实现两个端互相发文件

  public static void main(String[] args) {
    try {
      ServerSocket server = new ServerSocket(9999);
      Socket socket = server.accept();
      new SocketOutThread(socket).start();
      new SocketInThread(socket).start();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
