package com.bobo.study.demo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSingleThread {
  public static void main(String[] args) throws IOException, InterruptedException {
    // NEW一个ServerSocket，端口9999
    ServerSocket server = new ServerSocket(9999);

    // 阻塞 等待连接 如果连接成功后获取socket
    //单线程模式 串行
    while(true){
      Socket socket = server.accept();
      System.out.println("------被连接后才执行");

      // 获取ip
      InetAddress inetAddress = socket.getInetAddress();
      inetAddress.getHostAddress();
      // 获取端口
      socket.getPort();

      // 读信息 输入
      InputStream in = socket.getInputStream();
      // 写信息 输出
      OutputStream out = socket.getOutputStream();

      byte[] a = new byte[1024];
      // 注意此处堵塞 需要先读信息 才能执行下面的写信息
      int count = in.read(a);

      if (count != -1) {
        String str = new String(a, 0, count);
        System.out.println("server 收到了" + str);
        if ("over".equals(str)) {
          socket.close();
          break;
        }
      }
      // 循环读
      // while(count != -1) {
      // System.out.println("server 收到了" + new String(a, 0, count));
      // //read是阻塞的 如果client未socket.close(); 则一直在阻塞状态 而且存在超时机制
      // count = in.read(a);
      // }
      // System.out.println("------while over");
      System.out.println("----逻辑处理开始");
      Thread.sleep(15000);

      out.write("hello 我是server,server 处理完成".getBytes());

      socket.close();
    }
    server.close();
  }
}
