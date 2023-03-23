package com.bobo.study.demo.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread {
  public static void main(String[] args) throws IOException, InterruptedException {
    // NEW一个ServerSocket，端口9999
    ServerSocket server = new ServerSocket(9999);

    // 阻塞 等待连接 如果连接成功后获取socket
    //多线程模式
    while(true){
      Socket socket = server.accept();
      new Thread(new Hender01(socket)).start();
    }
    // server.close();
  }
}
