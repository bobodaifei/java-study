package com.bobo.study.work.tomcat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tomcat {

  public void start() {
    
    try {
      //创建20个连接的线程池
      ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(20);
      ServerSocket serverSocket = new ServerSocket(8888);
      while (true) {
        System.out.println("等待连接");
        Socket socket = serverSocket.accept();
        newFixedThreadPool.execute(new SocketThread(socket));
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }


  public static void main(String[] args) {
    Tomcat tomcat = new Tomcat();
    tomcat.start();
  }
}
